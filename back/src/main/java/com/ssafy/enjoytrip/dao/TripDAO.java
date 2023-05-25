package com.ssafy.enjoytrip.dao;

import com.ssafy.enjoytrip.dto.Trip;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TripDAO {

    void insert(Trip trip);

    List<Trip> findAllByUserCode(int userCode);

    Trip findByTripId(int tripId);

    void deleteByTripId(int tripId);

}
