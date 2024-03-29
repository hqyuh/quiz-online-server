package com.hqh.quizserver.services.impl;

import com.hqh.quizserver.dto.*;
import com.hqh.quizserver.entity.*;
import com.hqh.quizserver.mapper.UserAnswerMapper;
import com.hqh.quizserver.repository.*;
import com.hqh.quizserver.services.UserAnswerService;
import com.hqh.quizserver.services.UserService;
import com.hqh.quizserver.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Slf4j
public class UserAnswerServiceImpl implements UserAnswerService {


    private final UserAnswerMapper userAnswerMapper;
    private final TestQuizzRepository quizzRepository;
    private final QuestionRepository questionRepository;
    private final UserAnswerRepository userAnswerRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final UserMarkRepository userMarkRepository;

    @Autowired
    public UserAnswerServiceImpl(UserAnswerMapper userAnswerMapper,
                                 TestQuizzRepository quizzRepository,
                                 QuestionRepository questionRepository,
                                 UserAnswerRepository userAnswerRepository,
                                 UserService userService,
                                 UserRepository userRepository,
                                 UserMarkRepository userMarkRepository) {
        this.userAnswerMapper = userAnswerMapper;
        this.quizzRepository = quizzRepository;
        this.questionRepository = questionRepository;
        this.userAnswerRepository = userAnswerRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.userMarkRepository = userMarkRepository;
    }

    @Override
    public void saveAllUserAnswer(UserAnswerRequestDTO userAnswerRequestDTO) {

        List<UserAnswer> userAnswerList = new ArrayList<>();
        Long quizzId = userAnswerRequestDTO.getQuizzId();
        TestQuizz testQuizz = quizzRepository.findTestQuizzById(quizzId);
        User user = userService.getCurrentUser();

        UserMark userMark = userMarkRepository.findByUserIdAndPointLockIsFalse(user.getId());
        userMark.setFinishedAt(Instant.now());
        userMark.setPointLock(true);

        for (UserAnswerQuestionRequestDTO value : userAnswerRequestDTO.getListAnswer()) {
            Question question = questionRepository.findQuestionById(value.getQuestionId());
            UserAnswer userAnswer = userAnswerMapper.convertUserAnswerDTOToUserAnswerEntity(value, testQuizz, question, user);
            userAnswer.setIsSelected(CommonUtils.isNull(value.getIsSelected()) ? null : value.getIsSelected());
            userAnswer.setShortAnswer(CommonUtils.isNull(value.getShortAnswer()) ? null : value.getShortAnswer());
            userAnswer.setCreatedAt(new Date());
            userAnswer.setCreatedBy(user.getUsername());
            userAnswer.setUpdatedAt(new Date());
            userAnswer.setUpdatedBy(user.getUsername());

            userAnswerList.add(userAnswer);
        }
        userAnswerRepository.saveAll(userAnswerList);
        userMarkRepository.save(userMark);

        // handle result
        this.handleResult(quizzId, user.getId());

        // handle score
        this.handleScoreProcessing(testQuizz, user, userMark.getId());
    }

    private void handleScoreProcessing(TestQuizz testQuizz, User user, Long tableId) {
        log.info("------Handling the result ::------");
        String username = user.getUsername();
        Long quizzId = testQuizz.getId();
        Long userId = user.getId();
        UserMark userMark = userMarkRepository.findByIdAndUserId(tableId, userId);
        userMark.setTestQuizz(testQuizz);
        userMark.setUser(user);
        float mark = userAnswerRepository.totalMarkByQuizzId(quizzId, user.getId());

        String status = (mark > 5) ? "PASS" : "FAIL";

        userMark.setByPass(status);
        userMark.setMark(mark);
        userMark.setCompletedDate(Instant.now());
        userMark.setCreatedAt(new Date());
        userMark.setCreatedBy(username);
        userMark.setUpdatedAt(new Date());
        userMark.setUpdatedBy(username);
        userMark.setStatus("COMPLETED");

        userMarkRepository.save(userMark);
        log.info("------End handling the result ::------");
    }

    private void handleResult(Long quizzId, Long userId) {
        log.info("------Handling the result ::------");
        List<Question> questionList = questionRepository.getAllByTestQuizzIdOrderById(quizzId);
        for (Question question : questionList) {
            List<UserAnswer> userAnsweredList = userAnswerRepository.getAllUserAnswerByQuestionIdAndUserId(question.getId(), userId);
            userAnsweredList
                    .stream()
                    .filter(userAnswered -> !userAnswered.isUsed()
                            && question.getCorrectResult().equals(userAnswered.getIsSelected()))
                    .forEach(userAnswered -> {
                        userAnswered.setCorrect(true);
                        userAnswerRepository.save(userAnswered);
                    }
            );
        }
        log.info("------End handle the result ::------");
    }

    @Override
    public UserTestQuizzDTO reviewAnswerUser(Long quizzId, Long userId) {

        User user = userRepository.findUserById(userId);
        String username = user.getUsername();
        List<IReviewAnswerResponse> reviewAnswerUser = userAnswerRepository.reviewAnswerUser(quizzId, userId);
        UserMark userMark = userMarkRepository.findByUserId(userId);
        float mark = userMark.getMark();

        return UserTestQuizzDTO
                .builder()
                .username(username)
                .mark(mark)
                .reviewAnswerResponseDTOList(reviewAnswerUser)
                .build();
    }
}
