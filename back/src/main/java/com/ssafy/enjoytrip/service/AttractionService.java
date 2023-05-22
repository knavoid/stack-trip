package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.dto.Attraction;
import com.ssafy.enjoytrip.dto.AttractionFilter;
import com.ssafy.enjoytrip.dto.Gugun;

import java.util.List;

public interface AttractionService {

    List<Attraction> getAttractions(AttractionFilter attractionFilter) throws Exception;

    List<Attraction> getTop5Attractions() throws Exception;

    List<Gugun> getGuguns(int sidoCode) throws Exception;

}