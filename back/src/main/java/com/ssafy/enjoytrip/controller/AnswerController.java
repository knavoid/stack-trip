package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.dto.Answer;
import com.ssafy.enjoytrip.service.AnswerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/answer")
public class AnswerController {

	@Autowired
	private AnswerService answerService;

	@PostMapping
	protected ResponseEntity<Answer> createAnswer(@RequestBody Answer newAnswer) throws Exception {
		answerService.createAnswer(newAnswer);
		return ResponseEntity.created(URI.create("/answer/" + newAnswer.getAnswerId())).body(newAnswer);
	}

	@PutMapping("/{answerId}")
	protected ResponseEntity<Answer> modifyAnswer(@RequestBody Answer answer) throws Exception {
		answerService.modifyAnswer(answer);
		return ResponseEntity.ok(answer);
	}

	@DeleteMapping("/{answerId}")
	protected ResponseEntity<?> removeAnswer(@PathVariable int answerId) throws Exception {
		answerService.removeAnswer(answerId);
		return ResponseEntity.noContent().build();
	}

	@ExceptionHandler
	public String handleException(Exception exception, Model model) {
		log.error(exception.getMessage(), exception);
		model.addAttribute("message", exception.getMessage());
		return "error";
	}

}
