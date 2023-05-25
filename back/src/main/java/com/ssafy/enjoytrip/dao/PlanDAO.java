package com.ssafy.enjoytrip.dao;

import com.ssafy.enjoytrip.dto.Plan;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PlanDAO {

    void insert(Plan plan);

    List<Plan> findAllByTripId(int tripId);

    void update(Plan plan);

    void deleteByPlanId(int planId);

}
