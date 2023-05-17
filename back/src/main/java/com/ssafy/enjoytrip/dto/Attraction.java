package com.ssafy.enjoytrip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Attraction {

    private int attractionId;

    private int typeId;

    private String title;

    private String address;

    private String image;

    private String sidoCode;

    private String gugunCode;

    private String latitude;

    private String longitude;

    private String overview;

}
