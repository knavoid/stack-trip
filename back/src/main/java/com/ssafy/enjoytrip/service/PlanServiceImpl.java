package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.dao.PlanDAO;
import com.ssafy.enjoytrip.dto.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanDAO planDAO;

    @Override
    public void createPlan(Plan plan) {
        planDAO.insert(plan);
    }

    @Override
    public List<Plan> getPlans(int tripId) {
        return planDAO.findAllByTripId(tripId);
    }

    @Override
    public void modifyPlan(Plan plan) {
        planDAO.update(plan);
    }

    @Override
    public void removePlan(int planId) {
        planDAO.deleteByPlanId(planId);
    }
}