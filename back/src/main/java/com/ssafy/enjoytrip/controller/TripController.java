package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.dto.Trip;
import com.ssafy.enjoytrip.service.TripService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/trip")
public class TripController {

    @Autowired
    private TripService tripService;

    @PostMapping
    public ResponseEntity<Void> createTrip(@RequestBody Trip trip) throws Exception {
        tripService.createTrip(trip);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Trip>> getTrips(@RequestParam int userCode) throws Exception {
        List<Trip> trips = tripService.getTrips(userCode);
        return ResponseEntity.ok(trips);
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<Trip> getTrip(@PathVariable int tripId) throws Exception {
        Trip trip = tripService.getTrip(tripId);
        if (trip != null) {
            return ResponseEntity.ok(trip);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{tripId}")
    public ResponseEntity<Void> removeTrip(@PathVariable int tripId) throws Exception {
        tripService.removeTrip(tripId);
        return ResponseEntity.noContent().build();
    }
    @ExceptionHandler
    public String handleException(Exception exception, Model model) {
        log.error(exception.getMessage(), exception);
        model.addAttribute("message", exception.getMessage());
        return "error";
    }
}