package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.dto.Attraction;
import com.ssafy.enjoytrip.dto.AttractionFilter;

import java.util.List;

public interface AttractionService {

    List<Attraction> getAttractions(AttractionFilter attractionFilter) throws Exception;

    List<String> getSidoNames() throws Exception;

    List<String> getGugunNames(int sidoCode) throws Exception;

}