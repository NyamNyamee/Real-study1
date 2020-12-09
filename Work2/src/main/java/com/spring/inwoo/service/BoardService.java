package com.spring.inwoo.service;

import java.util.Map;

import com.spring.inwoo.vo.BoardVo;

public interface BoardService {
	public void increaseOrderNo(Integer groupNo, Integer orderNo);

	public boolean writeMessage(BoardVo boardVo);

	public BoardVo getMessage(Long no);
	
	public boolean updateMessage(BoardVo boardVo);
	
	public void insert(BoardVo boardVo);

	public int getTotalCount(String keyword);

	public Map<String, Object> getList(String keyword, Integer page, Integer size);

	public BoardVo getByNo(Long no);

	public void updateHit(Long no);

	public void update(BoardVo boardVo);

}
