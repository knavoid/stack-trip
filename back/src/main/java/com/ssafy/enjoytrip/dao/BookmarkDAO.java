package com.ssafy.enjoytrip.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ssafy.enjoytrip.dto.Bookmark;

@Mapper
@Repository
public interface BookmarkDAO {

	void insert(Bookmark bookmark);

	List<Bookmark> findByUserCode(int userCode);

	Bookmark findByAttractionIdAndUserCode(int attractionId, int userCode);

	void deleteByAttractionIdAndUserCode(int attractionId, int userCode);

}
