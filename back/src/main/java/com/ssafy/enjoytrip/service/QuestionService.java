package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.dto.Question;

import java.util.List;

public interface QuestionService {
    void createQuestion(Question question) throws Exception;

    List<Question> getQuestions() throws Exception;

    Question getQuestion(int questionId) throws Exception;

    void modifyQuestion(Question question) throws Exception;

    void removeQuestion(int questionId) throws Exception;
}
