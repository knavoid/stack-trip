package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.dao.TripDAO;
import com.ssafy.enjoytrip.dto.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripDAO tripDAO;

    @Override
    public void createTrip(Trip trip) throws Exception {
        tripDAO.insert(trip);
    }

    @Override
    public List<Trip> getTrips(int userCode) throws Exception {
        return tripDAO.findAllByUserCode(userCode);
    }

    @Override
    public Trip getTrip(int tripId) throws Exception {
        return tripDAO.findByTripId(tripId);
    }

    @Override
    public void removeTrip(int tripId) throws Exception {
        tripDAO.deleteByTripId(tripId);
    }

}
