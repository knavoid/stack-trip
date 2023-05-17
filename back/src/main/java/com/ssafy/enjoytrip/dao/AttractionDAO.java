package com.ssafy.enjoytrip.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ssafy.enjoytrip.dto.Attraction;
import com.ssafy.enjoytrip.dto.AttractionFilter;

@Mapper
@Repository
public interface AttractionDAO {

	List<Attraction> findAllByFilter(AttractionFilter attractionFilter);

	List<String> findSidoNames();

	List<String> findGugunNamesBySidoCode(int sidoCode);

}
