package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.dto.Plan;

import java.util.List;

public interface PlanService {
    void createPlan(Plan plan);

    List<Plan> getPlans(int tripId);

    void modifyPlan(Plan plan);

    void removePlan(int planId);
}
