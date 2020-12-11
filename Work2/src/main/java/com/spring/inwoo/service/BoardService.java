package com.spring.inwoo.service;

import java.util.Map;

import com.spring.inwoo.vo.BoardVo;

public interface BoardService {
	public void increaseOrderNo(Integer groupNo, Integer orderNo);
	
	public boolean insert(BoardVo boardVo);

	public int getTotalCount(String keyword);

	public Map<String, Object> getList(String keyword, Integer page, Integer size);
	
	public BoardVo getByNo(Long no);
	
	public void updateHit(Long no);
	
	public boolean update(BoardVo boardVo);

	public void delete(Long no);
}
