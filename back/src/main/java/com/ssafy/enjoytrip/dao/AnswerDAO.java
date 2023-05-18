package com.ssafy.enjoytrip.dao;

import com.ssafy.enjoytrip.dto.Answer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AnswerDAO {

    void insert(Answer answer);

    void deleteByAnswerId(int answerId);

}
