package com.ssafy.enjoytrip.dao;

import com.ssafy.enjoytrip.dto.Question;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionDAO {

    void insert(Question question);

    List<Question> findAll();

    Question findByQuestionId(int questionId);

    void update(Question question);

    void deleteByQuestionId(int questionId);

}
