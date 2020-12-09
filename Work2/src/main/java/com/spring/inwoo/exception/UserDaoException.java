package com.spring.inwoo.exception;

// 사용자 정의 예외 클래스
public class UserDaoException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3609157017229644896L;
	
	public UserDaoException() {
		super("User Not Found");
	}
}









