package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.dao.AnswerDAO;
import com.ssafy.enjoytrip.dto.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerDAO answerDAO;

    @Override
    public void createAnswer(Answer answer) throws Exception {
        answerDAO.insert(answer);
    }

    @Override
    public void modifyAnswer(Answer answer) throws Exception {
        answerDAO.update(answer);
    }

    @Override
    public void removeAnswer(int answerId) throws Exception {
        answerDAO.deleteByAnswerId(answerId);
    }

}
