package com.spring.inwoo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	//logback 테스트 
	// private static final Log LOG = LogFactory.getLog(MainController.class);
	
	/*
	 *  프로젝트 우 클릭 -> run on server 실행 시,
	 *  기본 경로 요청 ("/")에 대한 처리를 하겠다!
	 *  
	 *  web.xml의 <welcome-file>로 설정하지 않고,
	 *  Controller로 정의하겠다.
	 */
	@RequestMapping(value= { "/main", "/" })
	public String index() {
		return "main/index";
	}
	
	/*
	// logback 테스트
	@RequestMapping("/logger")
	public String logger() {
		LOG.debug("MainController.logger() Called...---------------------------------------------");
		
		// 강제 실행 예외 발생! (GlobalExceptionHandler Test)
//		if(1 - 1 == 0) {
//			throw new RuntimeException("Logger Test RuntimeException...");
//		}
		
		return "TODO"; // 404 Not Found
	}
	*/
}













