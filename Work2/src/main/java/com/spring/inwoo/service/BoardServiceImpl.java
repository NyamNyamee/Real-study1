package com.spring.inwoo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.inwoo.dao.BoardDao;
import com.spring.inwoo.vo.BoardVo;

@Service("boardService")
@Transactional
public class BoardServiceImpl implements BoardService{
	private static final int LIST_SIZE = 5; // 게시물 개수
	private static final int PAGE_SIZE = 5; // 페이지 개수
	
	@Autowired
	private BoardDao boardDao;
	
	
	@Override
	public boolean writeMessage(BoardVo boardVo) {
		Integer groupNo = boardVo.getGroupNo();
		
		if(groupNo != null) {
			Integer orderNo = boardVo.getOrderNo();
			Integer depth = boardVo.getDepth();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("orderNo", orderNo);
			map.put("groupNo", groupNo);
			
			boardDao.increaseOrderNo(map);
			boardVo.setOrderNo(orderNo + 1);
			boardVo.setDepth(depth + 1);
		}
		
		return boardDao.insert(boardVo) == 1;
	}
	
	
	@Override
	public BoardVo getMessage(Long no) {
		BoardVo boardVo = boardDao.getByNo(no);
		if(boardVo != null)
			boardDao.updateHit(no);
		return boardVo;
	}
	
	@Override
	public boolean updateMessage(BoardVo boardVo) {
		return boardDao.update(boardVo) == 1;
	}

	
	
	
	@Override
	public void increaseOrderNo(Integer groupNo, Integer orderNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(BoardVo boardVo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTotalCount(String keyword) {
		return boardDao.getTotalCount(keyword);
	}

	@Override
	public Map<String, Object> getList(String keyword, Integer page, Integer size) {
		// 1. 페이징을 위한 기본 데이터 가공
		int totalCount = boardDao.getTotalCount(keyword);
		int pageCount = (int) Math.ceil((double) totalCount / LIST_SIZE);
		int blockCount = (int) Math.ceil((double) pageCount / PAGE_SIZE);
		int currentBlock = (int) Math.ceil((double) page / PAGE_SIZE);
		// 게시물 조회 수가 12개 라면,
		// 페이지 번호 1번 -> 5개
		// 페이지 번호 2번 -> 5개
		// 페이지 번호 3번 -> 2개

		// 2. 파라미터 page 값 검증
		if (page < 1) {
			page = 1;
			currentBlock = 1;
		} else if (page > pageCount) {
			page = pageCount;
			currentBlock = (int) Math.ceil((double) page / PAGE_SIZE);
		}

		// 3. 뷰에서 페이지 리스트를 렌더링 하기 위한 데이터 가공
		int beginPage // 시작 페이지
				= (currentBlock == 0) ? 1 : (currentBlock - 1) * PAGE_SIZE + 1;
		int prevPage // 이전 페이지
				= (currentBlock > 1) ? (currentBlock - 1) * PAGE_SIZE : 0;
		int nextPage // 다음 페이지
				= (currentBlock < blockCount) ? currentBlock * PAGE_SIZE + 1 : 0;
		int endPage // 마지막 페이지
				= (nextPage > 0) ? (beginPage - 1) + LIST_SIZE : pageCount;

		HashMap<String, Object> map2 = new HashMap<String, Object>();
		map2.put("keyword", keyword);
		map2.put("page", page);
		map2.put("size", LIST_SIZE);

		// 4. 리스트 가져오기
		List<BoardVo> list = boardDao.getList(map2);

		// 5. 맵에 리스트 정보 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalCount", totalCount);
		map.put("listSize", LIST_SIZE);
		map.put("page", page);
		map.put("keyword", keyword);
		map.put("beginPage", beginPage);
		map.put("prevPage", prevPage);
		map.put("nextPage", nextPage);
		map.put("endPage", endPage);

		// 6. 맵 정보 반환
		return map;

	}

	@Override
	public BoardVo getByNo(Long no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateHit(Long no) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(BoardVo boardVo) {
		// TODO Auto-generated method stub
		
	}
}










