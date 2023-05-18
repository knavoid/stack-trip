package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.dto.Answer;

public interface AnswerService {
    void createAnswer(Answer answer) throws Exception;

    void removeAnswer(int answerId) throws Exception;
}
