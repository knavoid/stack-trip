package com.ssafy.enjoytrip.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Question {

    @NonNull
    private int questionId;

    @NonNull
    private int userCode;

    @NonNull
    private String userName;

    @NonNull
    private String content;

    private String regTime;

    private List<Answer> answers;

}
