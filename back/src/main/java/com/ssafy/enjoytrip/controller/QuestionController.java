package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.dto.Question;
import com.ssafy.enjoytrip.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@PostMapping
	protected ResponseEntity<Question> createQuestion(@RequestBody Question newQuestion) throws Exception {
		Question question = questionService.getQuestion(newQuestion.getQuestionId());
		if (question == null) {
			questionService.createQuestion(newQuestion);
			return ResponseEntity.created(URI.create("/question/" + newQuestion.getQuestionId())).body(newQuestion);
		} else {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping
	protected ResponseEntity<List<Question>> getQuestions() throws Exception {
		return ResponseEntity.ok(questionService.getQuestions());
	}

	@GetMapping("/{questionId}")
	protected ResponseEntity<Question> getQuestion(@PathVariable int questionId) throws Exception {
		Question question = questionService.getQuestion(questionId);
		if (question != null) {
			return ResponseEntity.ok(question);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{questionId}")
	protected ResponseEntity<Question> modifyQuestion(@RequestBody Question question) throws Exception {
		questionService.modifyQuestion(question);
		return ResponseEntity.ok(question);
	}

	@DeleteMapping("/{questionId}")
	protected ResponseEntity<?> removeQuestion(@PathVariable int questionId) throws Exception {
		Question question = questionService.getQuestion(questionId);
		if (question != null) {
			questionService.removeQuestion(questionId);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@ExceptionHandler
	public String handleException(Exception exception, Model model) {
		log.error(exception.getMessage(), exception);
		model.addAttribute("message", exception.getMessage());
		return "error";
	}

}
