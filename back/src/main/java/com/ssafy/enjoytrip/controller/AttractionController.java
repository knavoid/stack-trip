package com.ssafy.enjoytrip.controller;

import java.util.List;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ssafy.enjoytrip.dto.Attraction;
import com.ssafy.enjoytrip.dto.AttractionFilter;
import com.ssafy.enjoytrip.service.AttractionService;

import lombok.extern.slf4j.Slf4j;

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
