package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.dto.Bookmark;
import com.ssafy.enjoytrip.service.BookmarkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/bookmark")
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

    @PostMapping
    protected ResponseEntity<Bookmark> createBookmark(@RequestBody Bookmark bookmark) throws Exception {
        bookmarkService.createBookmark(bookmark);
        return ResponseEntity.ok(bookmark);
    }

    @GetMapping("/{userCode}")
    protected ResponseEntity<List<Bookmark>> getUserBookmarks(@PathVariable int userCode) throws Exception {
        return ResponseEntity.ok(bookmarkService.getUserBookmarks(userCode));
    }

    @GetMapping
    protected ResponseEntity<Boolean> isBookmarkExist(@RequestParam int attractionId, @RequestParam int userCode) throws Exception {
        return ResponseEntity.ok(bookmarkService.isBookmarkExist(attractionId, userCode));
    }

    @DeleteMapping
    protected ResponseEntity<?> removeBookmark(@RequestParam int attractionId, @RequestParam int userCode) throws Exception {
        bookmarkService.removeBookmark(attractionId, userCode);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler
    public String handleException(Exception exception, Model model) {
        log.error(exception.getMessage(), exception);
        model.addAttribute("message", exception.getMessage());
        return "error";
    }

}