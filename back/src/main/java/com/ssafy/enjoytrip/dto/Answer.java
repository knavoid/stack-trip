package com.ssafy.enjoytrip.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Answer {

    private int answerId;

    private int questionId;

    private int userCode;

    private String content;

    private String regTime;

}
