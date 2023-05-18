package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.dao.AttractionDAO;
import com.ssafy.enjoytrip.dto.Attraction;
import com.ssafy.enjoytrip.dto.AttractionFilter;
import com.ssafy.enjoytrip.dto.Gugun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<Gugun> getGuguns(int sidoCode) throws Exception {
        return attractionDAO.findGugunNamesBySidoCode(sidoCode);
    }

}
