package com.ssafy.enjoytrip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Plan {

    private int planId;

    private int tripId;

    private int attractionId;

    private String date;

    private int order;

}