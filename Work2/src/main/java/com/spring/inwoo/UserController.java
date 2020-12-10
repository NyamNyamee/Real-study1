package com.spring.inwoo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.inwoo.service.UserService;
import com.spring.inwoo.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Log LOG = LogFactory.getLog( BoardController.class );
	
	@Autowired
	private UserService userService;
	
	/*
	 *  @ModelAttribute
	 *  	- joinform.jsp의 스프링 태그와 바인딩될 모델 객체 포함
	 *  	- UserVo 객체는 가지고 있는 제약사항(유효성)을 가지고,
	 *  	  spring form의 데이터와 연결!
	 *  
	 *  	Servlet에서 UserVo 객체 생성 후 setAttribute() 대체!
	 *  	객체의 멤버와 이름이 일치한다면 자동 할당 (바인딩, 매핑)이 이루어진다.
	 *  
	 *  	즉, UserVo를 Model로 등록!
	 *  	단, 이동할 페이지의 spring 요소 유지를 위한 연결일 뿐,
	 *  	유효성을 검증하기 위한 @ModelAttribute 활용은 아니다.
	 */
	
	@RequestMapping("/joinform")
	public String joinform(@ModelAttribute UserVo userVo) {
		return "user/joinform"; // View 전달!
	}
	
	// 로그인화면
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request, Model model)
	{
		/*
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		LOG.debug("----------------------loginform is called----------------------------" + email + ", " + password);
		model.addAttribute("email", email);
		model.addAttribute("password", password);
		*/
		return "user/loginform"; // view 전달
	}
		
	// 아이디중복확인
	@RequestMapping(value = "/emailCheck", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String idCheck(@RequestParam("email")String email, Model model)
	{
		return userService.getByEmail(email) + ""; // idCheck메서드의 리턴타입이 int라서 ""를 더해 String타입으로 변환
	}
	
	// @Valid : Bean의 유효성 자동 검증
	//	-> 스프링 입력 폼으로부터 넘어오는 userVo의 데이터(값)가 유효한지 판단!
	@RequestMapping(value="/join")
	public String join(@ModelAttribute UserVo userVo
					 , BindingResult result) {
		if(result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for(ObjectError oe : list) {
				System.out.println("Object Error : " + oe);
			}
			return "user/joinform";
		}
		
		// 유저 서비스의 회원가입 수행
		userService.insert(userVo);
				
		return "redirect:/user/joinsuccess"; // Redirect (리다이렉트)
		/*
		 *  Spring MVC Redirect
		 *  	뷰 리졸버에 보낼 Context 경로의 접두어 활용
		 *  	"redirect:" + "/user/joinsuccess" 형식으로
		 *  	/user/joinsuccess 반환 값이 뷰 리졸버를 통해 가공되어
		 *  	이동을 수행하지만 포워드 방식이 아닌 리다이렉트로 이동!
		 *  		-> 스프링에서 자동으로 변환
		 *  	그 외의 페이지 이동 경로에 사용자 정의 접두어 활용하는 경우도 존재!
		 *  		-> 접두어 가공 (프레임워크 개발 시 활용)
		 */
	}
	
	@RequestMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping("/loginform")
	public String loginform() {
		return "user/loginform";
	}
	
	/*
	 *  정보 수정 페이지 이동
	 *  	1. 로그인 인증 (세션 확인)
	 *  		-> AuthInterceptor
	 *  	2. 인증 유저에 대한 파라미터 데이터 바인딩
	 *  		-> AuthUserHandlerMethodArgumentResolver
	 *  	3. DB에서 유저 정보 가져오기
	 *  		-> @Service(UserService), @Repository(UserDao)
	 *  	4. Model에 등록 (userVo)
	 *  	5. View("user/modifyform") 반환
	 */
	
	@RequestMapping("/modifyform")
	public String modifyform(UserVo authUser, Model model) {
		//	-> 핸들러 메소드 및 인증 파라미터에 대한 데이터 바인딩
		
		// 바인딩된 유저 객체에서 유저 번호를 추출 후 DB에서 유저의 모든 정보를 가져오겠다.
		UserVo vo = userService.getByNo(authUser.getNo());
		model.addAttribute("userVo", vo); // Model
		
		return "user/modifyform"; // View
	}
	
	
	@RequestMapping("/modify")
	public String modify(UserVo authUser	// ArgumentResolver(Session)에 의한 바인딩!
					   , @ModelAttribute UserVo vo	// modifyform에서 전달되는 파라미터 바인딩!
					   ) {
		// @AuthUser 세션 정보로부터 번호를 가져와,
		// Service에 전달할 vo에 설정!
		vo.setNo(authUser.getNo());
		userService.update(vo);
		authUser.setName(vo.getName());
		
		return "redirect:/user/modifyform?update=success";
	}
	

	// 로그인실패화면
	@RequestMapping(value="/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(Model model)
	{
		String userid = getPrincipal();
		model.addAttribute("user", userid);

		return "user/accessDenied";
	}
	
	// 로그아웃
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) 
		{
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		return "redirect:/";
	}
	
	// 시큐리티에서 유저 아이디 얻기
		private String getPrincipal()
		{
			String userName = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			if (principal instanceof UserDetails) 
			{
				userName = ((UserDetails) principal).getUsername();
			} 
			else
			{
				userName = principal.toString();
			}
			
			return userName;
		}
}











