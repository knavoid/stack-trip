package com.ssafy.enjoytrip.dao;

import com.ssafy.enjoytrip.dto.Plan;
import com.ssafy.enjoytrip.dto.Trip;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Mapper
@Repository
public interface PlanDAO {

    void insert(Plan plan);

    List<Plan> findAllByTripIdAndDate(int tripId, LocalDate date);

    void update(Plan plan);

    void deleteByPlanId(int planId);

}
