package com.hqh.quizserver.services;

import com.hqh.quizserver.dto.TestQuizzDTO;
import com.hqh.quizserver.dto.TestQuizzRequestDTO;
import com.hqh.quizserver.dto.TestQuizzResponseDTO;
import com.hqh.quizserver.entity.TestQuizz;
import com.hqh.quizserver.exceptions.domain.quizz.TestQuizzCreateTimeException;
import com.hqh.quizserver.exceptions.domain.quizz.TestQuizzExistException;
import com.hqh.quizserver.exceptions.domain.quizz.TestQuizzNotFoundException;

import java.util.List;


public interface TestQuizzService {

    TestQuizz createQuizz(TestQuizzRequestDTO request) throws TestQuizzExistException, TestQuizzNotFoundException, TestQuizzCreateTimeException;

    TestQuizz updateQuizz(TestQuizzRequestDTO request)
            throws TestQuizzExistException, TestQuizzNotFoundException, TestQuizzCreateTimeException;

    TestQuizz findTestQuizzByTestName(String testName);

    List<TestQuizzResponseDTO> getAllQuizz();

    void deleteQuizz(Long id);

    TestQuizzDTO findTestQuizzByActivationCode(String code, Integer amount) throws TestQuizzNotFoundException;

    List<TestQuizzResponseDTO> findAllTestQuizzByTopicId(Long id);

    TestQuizz findTestQuizzById(Long id);

    void lockQuizz(Long id, boolean isStatus);

}
