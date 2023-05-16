package com.ssafy.enjoytrip.service;

import java.util.List;

import com.ssafy.enjoytrip.dto.Attraction;
import com.ssafy.enjoytrip.dto.AttractionFilter;

public interface AttractionService {

	List<Attraction> getAttractions(AttractionFilter attractionFilter) throws Exception;

}