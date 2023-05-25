package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.dto.Plan;
import com.ssafy.enjoytrip.service.PlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    private PlanService planService;

    @PostMapping
    public ResponseEntity<Void> createPlan(@RequestBody Plan plan) {
        planService.createPlan(plan);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Plan>> getPlans(@RequestParam int tripId) {
        return ResponseEntity.ok(planService.getPlans(tripId));
    }

    @PutMapping("/{planId}")
    public ResponseEntity<Void> modifyPlan(@PathVariable int planId, @RequestBody Plan plan) {
        plan.setPlanId(planId);
        planService.modifyPlan(plan);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<Void> removePlan(@PathVariable int planId) {
        planService.removePlan(planId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler
    public String handleException(Exception exception, Model model) {
        log.error(exception.getMessage(), exception);
        model.addAttribute("message", exception.getMessage());
        return "error";
    }

}