package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.dto.Trip;

import java.util.List;

public interface TripService {
    void createTrip(Trip trip) throws Exception;

    List<Trip> getTrips(int userCode) throws Exception;

    Trip getTrip(int tripId) throws Exception;

    void removeTrip(int tripId) throws Exception;
}
