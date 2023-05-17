package com.ssafy.enjoytrip.dto;

import lombok.*;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Question {

	@NonNull
	private int questionId;

	@NonNull
	private int userCode;

	@NonNull
	private String content;

	private String regTime;

	private List<Answer> answers;

}
