package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.dao.BookmarkDAO;
import com.ssafy.enjoytrip.dto.Bookmark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookmarkServiceImpl implements BookmarkService {

    @Autowired
    private BookmarkDAO bookmarkDAO;

    @Override
    public void createBookmark(Bookmark bookmark) throws Exception {
        bookmarkDAO.insert(bookmark);
    }

    @Override
    public List<Bookmark> getUserBookmarks(int userCode) throws Exception {
        return bookmarkDAO.findByUserCode(userCode);
    }

    @Override
    public boolean isBookmarkExist(int attractionId, int userCode) throws Exception {
        return bookmarkDAO.findByAttractionIdAndUserCode(attractionId, userCode) != null;
    }

    @Override
    public void removeBookmark(int attractionId, int userCode) throws Exception {
        bookmarkDAO.deleteByAttractionIdAndUserCode(attractionId, userCode);
    }

}
