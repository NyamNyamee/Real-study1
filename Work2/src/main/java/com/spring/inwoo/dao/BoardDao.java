package com.spring.inwoo.dao;

import java.util.HashMap;
import java.util.List;

import com.spring.inwoo.vo.BoardVo;

public interface BoardDao {
	
	public void increaseOrderNo(HashMap<String, Integer> map);
	
	public int insert(BoardVo boardVo);
	
	public int getTotalCount(String keyword);
	
	public List<BoardVo> getList(HashMap<String, Object> map);
	
	public BoardVo getByNo(Long no);
	
	public void updateHit(Long no);
	
	public int update(BoardVo boardVo);
}










