package com.ssafy.enjoytrip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Plan {

    private int planId;

    private int tripId;

    private int attractionId;

    private LocalDate date;

    private int order;

}