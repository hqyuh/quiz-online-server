package com.hqh.graduationthesisserver.service.impl;

import com.hqh.graduationthesisserver.domain.TestQuizz;
import com.hqh.graduationthesisserver.domain.User;
import com.hqh.graduationthesisserver.domain.UserMark;
import com.hqh.graduationthesisserver.dto.UserMarkDto;
import com.hqh.graduationthesisserver.helper.quizz.ExcelHelper;
import com.hqh.graduationthesisserver.mapper.UserMarkMapper;
import com.hqh.graduationthesisserver.repository.TestQuizzRepository;
import com.hqh.graduationthesisserver.repository.UserAnswerRepository;
import com.hqh.graduationthesisserver.repository.UserMarkRepository;
import com.hqh.graduationthesisserver.service.UserMarkHelperService;
import com.hqh.graduationthesisserver.service.UserMarkService;
import com.hqh.graduationthesisserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMarkServiceImpl implements UserMarkService, UserMarkHelperService {

    private final UserMarkRepository userMarkRepository;
    private final TestQuizzRepository quizzRepository;
    private final UserMarkMapper userMarkMapper;
    private final UserService userService;
    private final UserAnswerRepository userAnswerRepository;

    @Autowired
    public UserMarkServiceImpl(UserMarkRepository userMarkRepository,
                               TestQuizzRepository quizzRepository,
                               UserMarkMapper userMarkMapper,
                               UserService userService,
                               UserAnswerRepository userAnswerRepository) {
        this.userMarkRepository = userMarkRepository;
        this.quizzRepository = quizzRepository;
        this.userMarkMapper = userMarkMapper;
        this.userService = userService;
        this.userAnswerRepository = userAnswerRepository;
    }

    /***
     *
     * @param userMarkDto
     */
    @Override
    public void saveUserMark(UserMarkDto userMarkDto) {
        TestQuizz quizzId = quizzRepository.findTestQuizzById(userMarkDto.getQuizzId());
        User userId = userService.getCurrentUser();
        UserMark userMark = userMarkMapper
                .map(userMarkDto, quizzId, userId);
        userMark.setCompletedDate(Instant.now());
        userMark.setMark(userAnswerRepository.totalMarkByQuizzId(userMarkDto.getQuizzId()));
        userMark.setPointLock(false);

        userMarkRepository.save(userMark);
    }

    @Override
    public List<UserMarkDto> getAllUserByUsername(String username) {
        return userMarkRepository.findByAllUsername(username)
                                 .stream()
                                 .map(userMarkMapper::mapToDto)
                                 .collect(Collectors.toList());
    }

    @Override
    public List<UserMarkDto> getAllUserByQuizzId(Long quizzId) {
        return userMarkRepository.findByTestQuizzId(quizzId)
                                 .stream()
                                 .map(userMarkMapper::mapToDto)
                                 .collect(Collectors.toList());
    }

    /***
     * get the top 3 users with the highest score according to quiz id
     *
     * @param quizzId
     * @return list
     */
    @Override
    public List<UserMarkDto> getMarkTop3(Long quizzId) {
        return userMarkRepository.getMarkTop3(quizzId)
                                 .stream()
                                 .limit(3)
                                 .map(userMarkMapper::mapToDto)
                                 .collect(Collectors.toList());
    }

    /***
     * export mark by quiz id
     *
     * @param id
     * @return
     */
    @Override
    public ByteArrayInputStream loadUserMarkExcel(long id) {
        List<UserMark> userMark = userMarkRepository.findByTestQuizzId(id);
        return ExcelHelper.userMarkToExcel(userMark);
    }

    @Override
    @Transactional
    public void pointLock(Long userId, boolean isLock) {
        userMarkRepository.markLock(userId, isLock);
    }
}
