package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.dto.Attraction;
import com.ssafy.enjoytrip.dto.AttractionFilter;
import com.ssafy.enjoytrip.service.AttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/attraction")
public class AttractionController {

    @Autowired
    private AttractionService attractionService;

    @GetMapping
    protected ResponseEntity<List<Attraction>> getAttractions(AttractionFilter attractionFilter) throws Exception {
        return ResponseEntity.ok(attractionService.getAttractions(attractionFilter));
    }

    @GetMapping("/sido")
    protected ResponseEntity<List<String>> getSidoNames() throws Exception {
        return ResponseEntity.ok(attractionService.getSidoNames());
    }

    @GetMapping("/gugun")
    protected ResponseEntity<List<String>> getGugunNames(@RequestParam int sidoCode) throws Exception {
        return ResponseEntity.ok(attractionService.getGugunNames(sidoCode));
    }

}
