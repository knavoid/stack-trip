package com.ssafy.enjoytrip.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Bookmark {

	@NonNull
	private int bookmarkId;

	@NonNull
	private int attractionId;

	@NonNull
	private int userCode;

	private String regtime;

}
