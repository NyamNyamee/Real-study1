package com.spring.inwoo.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.spring.inwoo.BoardController;
import com.spring.inwoo.dao.UserDao;
import com.spring.inwoo.vo.UserVo;


public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler
{
	private static final Log LOG = LogFactory.getLog( BoardController.class );
	
	@Autowired
	private UserDao userDao;
	private String failureUrl;

	private static int time = 60 * 60 * 12; // 60초 * 60 * 12 = 12시간 
	
	// 로그인 실패시 처리할 내용
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException 
	{
		LOG.debug("login failed---------------------------------------------------------, failureUrl= " + failureUrl + exception);
		
		if (exception instanceof AuthenticationServiceException) {
			request.setAttribute("loginFailMsg", "존재하지 않는 사용자입니다.");
		
		} else if(exception instanceof BadCredentialsException) {
			request.setAttribute("loginFailMsg", "아이디 또는 비밀번호가 일치하지 않습니다");
			
		} else if(exception instanceof LockedException) {
			request.setAttribute("loginFailMsg", "잠긴 계정입니다..");
			
		} else if(exception instanceof DisabledException) {
			request.setAttribute("loginFailMsg", "비활성화된 계정입니다..");
			
		} else if(exception instanceof AccountExpiredException) {
			request.setAttribute("loginFailMsg", "만료된 계정입니다..");
			
		} else if(exception instanceof CredentialsExpiredException) {
			request.setAttribute("loginFailMsg", "비밀번호가 만료되었습니다.");
		}
		
		// 로그인 실패시 어디론가 리다이렉트
		response.sendRedirect(failureUrl);
		
	}
	
	@Override
	public String toString() {
		return "MyAuthenticationFailureHandler [userDao=" + userDao + ", failureUrl=" + failureUrl + "]";
	}
	
	public MyAuthenticationFailureHandler() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyAuthenticationFailureHandler(UserDao userDao, String failureUrl) {
		super();
		this.userDao = userDao;
		this.failureUrl = failureUrl;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public String getFailureUrl() {
		return failureUrl;
	}

	public void setFailureUrl(String failureUrl) {
		this.failureUrl = failureUrl;
	}

	public static int getTime() {
		return time;
	}

	public static void setTime(int time) {
		MyAuthenticationFailureHandler.time = time;
	}

	public static Log getLog() {
		return LOG;
	}

	
	

}

