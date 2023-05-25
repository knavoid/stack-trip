package com.ssafy.enjoytrip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class Trip {

    private int tripId;

    private int userCode;

    private String title;

    private String description;

    private LocalDate beginDate;

    private LocalDate endDate;

    private List<Plan> plans;

    public Trip(int tripId, int userCode, String title, String description, LocalDate beginDate, LocalDate endDate) {
        this.tripId = tripId;
        this.userCode = userCode;
        this.title = title;
        this.description = description;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

}
