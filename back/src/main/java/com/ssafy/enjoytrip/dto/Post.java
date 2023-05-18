package com.ssafy.enjoytrip.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Post {

    @NonNull
    private int postId;

    @NonNull
    private int userCode;

    @NonNull
    private String subject;

    @NonNull
    private String content;

    @NonNull
    private int type;

    private String regTime;

    private int views;

}
