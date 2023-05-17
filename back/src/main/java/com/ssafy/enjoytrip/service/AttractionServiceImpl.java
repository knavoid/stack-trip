package com.ssafy.enjoytrip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoytrip.dao.AttractionDAO;
import com.ssafy.enjoytrip.dto.Attraction;
import com.ssafy.enjoytrip.dto.AttractionFilter;

@Service
@Transactional
public class AttractionServiceImpl implements AttractionService {

	@Autowired
	private AttractionDAO attractionDAO;

	@Override
	public List<Attraction> getAttractions(AttractionFilter attractionFilter) throws Exception {
		return attractionDAO.findAllByFilter(attractionFilter);
	}

	@Override
	public List<String> getSidoNames() throws Exception {
		return attractionDAO.findSidoNames();
	}

	@Override
	public List<String> getGugunNames(int sidoCode) throws Exception {
		return attractionDAO.findGugunNamesBySidoCode(sidoCode);
	}

}
