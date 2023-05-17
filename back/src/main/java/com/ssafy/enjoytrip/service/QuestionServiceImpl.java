package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.dao.QuestionDAO;
import com.ssafy.enjoytrip.dto.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionDAO questionDAO;

    @Override
    public void createQuestion(Question question) throws Exception {
        questionDAO.insert(question);
    }

    @Override
    public List<Question> getQuestions() throws Exception {
        return questionDAO.findAll();
    }

    @Override
    public Question getQuestion(int questionId) throws Exception {
        return questionDAO.findByQuestionId(questionId);
    }

    @Override
    public void modifyQuestion(Question question) throws Exception {
        questionDAO.update(question);
    }

    @Override
    public void removeQuestion(int questionId) throws Exception {
        questionDAO.deleteByQuestionId(questionId);
    }

}
