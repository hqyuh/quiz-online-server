package com.hqh.graduationthesisserver.controller;

import com.hqh.graduationthesisserver.domain.HttpResponse;
import com.hqh.graduationthesisserver.dto.QuestionDto;
import com.hqh.graduationthesisserver.exception.domain.user.NotAnImageFileException;
import com.hqh.graduationthesisserver.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.hqh.graduationthesisserver.constant.QuestionConstant.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = { "/question" })
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpResponse> createQuestion(@RequestParam("topicQuestion") String topicQuestion,
                                                       @RequestParam(value = "questionImageUrl", required = false)
                                                               MultipartFile questionImageUrl,
                                                       @RequestParam("answerA") String answerA,
                                                       @RequestParam("answerB") String answerB,
                                                       @RequestParam("answerC") String answerC,
                                                       @RequestParam("answerD") String answerD,
                                                       @RequestParam("correctResult") String correctResult,
                                                       @RequestParam("mark") String mark,
                                                       @RequestParam("quizzId") String quizzId)
            throws IOException, NotAnImageFileException {
        questionService.createQuestion(topicQuestion, questionImageUrl, answerA, answerB,
                answerC, answerD, correctResult, Float.parseFloat(mark), Long.parseLong(quizzId));

        return response(CREATED, ADD_SUCCESS_QUESTION);
    }

    @PatchMapping("/update")
    public ResponseEntity<HttpResponse> updateQuestion(@RequestParam("id") String id,
                                                       @RequestParam("topicQuestion") String topicQuestion,
                                                       @RequestParam("answerA") String answerA,
                                                       @RequestParam("answerB") String answerB,
                                                       @RequestParam("answerC") String answerC,
                                                       @RequestParam("answerD") String answerD,
                                                       @RequestParam("correctResult") String correctResult,
                                                       @RequestParam("mark") String mark,
                                                       @RequestParam("quizzId") String quizzId,
                                                       @RequestParam(value = "questionImageUrl", required = false)
                                                                   MultipartFile questionImageUrl)
            throws IOException, NotAnImageFileException {

        questionService.updateQuestion(Long.parseLong(id), topicQuestion, answerA, answerB, answerC, answerD,
                correctResult, Float.parseFloat(mark), Long.parseLong(quizzId), questionImageUrl);

        return response(OK, QUESTION_UPDATE_SUCCESSFUL);
    }

    @GetMapping("/list")
    public ResponseEntity<List<QuestionDto>> getAllQuestion() {
        return ResponseEntity
                .status(OK)
                .body(questionService.getAllQuestion());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpResponse> deleteQuestion(@PathVariable("id") Long id) {
        questionService.deleteQuestion(id);

        return response(OK, QUESTION_DELETED_SUCCESSFULLY);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message){
        HttpResponse body = new HttpResponse(httpStatus.value(), httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase());
        return new ResponseEntity<>(body, httpStatus);
    }

}