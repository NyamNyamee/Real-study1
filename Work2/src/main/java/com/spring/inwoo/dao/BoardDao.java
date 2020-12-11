package com.spring.inwoo.dao;

import java.util.HashMap;
import java.util.List;

import com.spring.inwoo.vo.BoardVo;

public interface BoardDao {
	// 글 표시순서 +1씩 증가시키기
	public void increaseOrderNo(HashMap<String, Integer> map);
	
	// 글 작성
	public int insert(BoardVo boardVo);
	
	// 글 개수
	public int getTotalCount(String keyword);
	
	// 글 목록
	public List<BoardVo> getList(HashMap<String, Object> map);
	
	// 글 한개 정보
	public BoardVo getByNo(Long no);
	
	// 조회수 증가
	public void updateHit(Long no);
	
	// 글 수정
	public int update(BoardVo boardVo);
	
	// 글 삭제
	public void delete(Long no);
}










