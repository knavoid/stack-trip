package com.ssafy.enjoytrip.dao;

import com.ssafy.enjoytrip.dto.Bookmark;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookmarkDAO {

    void insert(Bookmark bookmark);

    List<Bookmark> findByUserCode(int userCode);

    Bookmark findByAttractionIdAndUserCode(int attractionId, int userCode);

    void deleteByAttractionIdAndUserCode(int attractionId, int userCode);

}
