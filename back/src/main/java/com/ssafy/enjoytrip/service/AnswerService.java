package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.dto.Answer;

import java.util.List;

public interface AnswerService {
    void createAnswer(Answer answer) throws Exception;

    void modifyAnswer(Answer answer) throws Exception;

    void removeAnswer(int answerId) throws Exception;
}
