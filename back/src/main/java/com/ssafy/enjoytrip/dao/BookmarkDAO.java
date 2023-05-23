package com.ssafy.enjoytrip.dao;

import com.ssafy.enjoytrip.dto.Bookmark;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookmarkDAO {

    void insert(Bookmark bookmark);

    Bookmark findByAttractionIdAndUserCode(int attractionId, int userCode);

    int countByAttractionId(int attractionId);

    void deleteByAttractionIdAndUserCode(int attractionId, int userCode);

}
