package com.ssafy.enjoytrip.dao;

import com.ssafy.enjoytrip.dto.Attraction;
import com.ssafy.enjoytrip.dto.AttractionFilter;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AttractionDAO {

    List<Attraction> findAllByFilter(AttractionFilter attractionFilter);

    List<String> findSidoNames();

    List<String> findGugunNamesBySidoCode(int sidoCode);

}
