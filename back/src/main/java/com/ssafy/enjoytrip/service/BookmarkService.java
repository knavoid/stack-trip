package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.dto.Bookmark;

import java.util.List;

public interface BookmarkService {

    void createBookmark(Bookmark bookmark) throws Exception;

    boolean isBookmarkExist(int attractionId, int userCode) throws Exception;

    int getBookmarkCounts(int attractionId) throws Exception;

    void removeBookmark(int attractionId, int userCode) throws Exception;

}
