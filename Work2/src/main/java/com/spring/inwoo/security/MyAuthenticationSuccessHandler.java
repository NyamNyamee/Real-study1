package com.spring.inwoo.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.spring.inwoo.BoardController;
import com.spring.inwoo.dao.UserDao;
import com.spring.inwoo.vo.UserVo;


public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler
{
	private static final Log LOG = LogFactory.getLog( BoardController.class );
	
	@Autowired
	private UserDao userDao;
	private String successUrl;

	private static int time = 60 * 60 * 12; // 60초 * 60 * 12 = 12시간 
	
	@Override // 로그인 성공시 처리핼 내용
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException 
	{
		// 세션 타임아웃 시간 지정
		request.getSession().setMaxInactiveInterval(time);
		// 회원 정보를 읽어 세션에 저장
		String email = request.getParameter("email"); // 아이디 읽기
		List<UserVo> list = userDao.getByName(email);
		// LOG.debug("--------------------------------email: " + email);
		if(list!=null && list.size() > 0)
		{
			UserVo vo = list.get(0);
			// 회원정보를 세션에 저장하기
			request.getSession().setAttribute("userVo", vo);
		}
		// LOG.debug("login success---------------------------------------------------------, successUrl= " + successUrl);
		// 어딘가로 이동
        // request.getRequestDispatcher(successUrl).forward(request, response);
		response.sendRedirect(successUrl);

	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public static int getTime() {
		return time;
	}

	public static void setTime(int time) {
		MyAuthenticationSuccessHandler.time = time;
	}

	public MyAuthenticationSuccessHandler() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyAuthenticationSuccessHandler(UserDao userDao, String successUrl) {
		super();
		this.userDao = userDao;
		this.successUrl = successUrl;
	}

	@Override
	public String toString() {
		return "MyAuthenticationSuccessHandler [userDao=" + userDao + ", successUrl=" + successUrl + "]";
	}
	

}

