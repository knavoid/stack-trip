package com.ssafy.enjoytrip.dao;

import com.ssafy.enjoytrip.dto.Attraction;
import com.ssafy.enjoytrip.dto.AttractionFilter;
import com.ssafy.enjoytrip.dto.Gugun;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AttractionDAO {

    List<Attraction> findAllByFilter(AttractionFilter attractionFilter);

    List<Gugun> findGugunNamesBySidoCode(int sidoCode);

}
