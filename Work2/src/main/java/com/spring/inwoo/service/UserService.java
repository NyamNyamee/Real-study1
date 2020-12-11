package com.spring.inwoo.service;

import java.util.List;

import com.spring.inwoo.vo.UserVo;

public interface UserService {
	// 사용자 등록
	public void insert(UserVo vo);

	// 중복 이메일 체크
	public int getByEmail(String email);

	// 인증(로그인), 사용자 정의 예외 발생
	public UserVo getByEmailAndPassword(String email, String password);

	// 사용자 정보 가져오기
	public UserVo getByNo(Long no);
	
	// 이름으로 사용자 정보 가져오기
	public List<UserVo> getByName(String email);

	// 사용자 정보 수정
	public void update(UserVo vo);
	
	// 사용자 삭제
	public void delete(Long no);
}
