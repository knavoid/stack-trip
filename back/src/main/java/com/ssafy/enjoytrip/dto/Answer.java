package com.ssafy.enjoytrip.dto;

import lombok.*;

@Getter
@RequiredArgsConstructor
public class Answer {

//	@NonNull
	private int answerId;

//	@NonNull
	private int questionId;

//	@NonNull
	private int userCode;
	
//	@NonNull
	private String content;

	private String regTime;

}
