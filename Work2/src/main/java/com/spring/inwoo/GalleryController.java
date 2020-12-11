package com.spring.inwoo;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.inwoo.service.GalleryService;
import com.spring.inwoo.service.GalleryServiceImpl;
import com.spring.inwoo.service.UserService;
import com.spring.inwoo.vo.GalleryVo;
import com.spring.inwoo.vo.UserVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	private static final Log LOG = LogFactory.getLog(BoardController.class);
	
	@Autowired
	private GalleryService galleryService;
	
	@Autowired
	private UserService userSerivce;
	
	@RequestMapping("")
	public String index(Model model) {
		List<GalleryVo> list = galleryService.getList();
		
		model.addAttribute("list", list);
		model.addAttribute("baseURL", GalleryServiceImpl.BASE_URL);
		
		return "gallery/index";
	}
	
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String upload(
			@ModelAttribute GalleryVo galleryVo,
			@RequestParam("file") MultipartFile multipartfile
			) {
		String email = getPrincipal();
		List<UserVo> list = userSerivce.getByName(email);
				
		galleryVo.setUserNo(list.get(0).getNo());
		LOG.debug("----------------------------/upload called-----------------------------comments = " + galleryVo.getComments());
		galleryService.insert(galleryVo, multipartfile);
		
		return "redirect:/gallery";
	}
	
	/*
	 *  RESTful 서비스의 URL 형태 활용!
	 *  	- REST 하다. 경로처럼 파라미터를 받겠다.
	 *  	- 템플릿 변수 {...}
	 *  
	 *  @PathVariable
	 *  	- URL 경로에 파라미터를 넣어 전달받을 수 있도록!
	 *  	- 경로 데이터를 변수화하여 받아주겠다.
	 *  	- 동일한 이름의 파라미터와 매핑!
	 *  	- 단, NULL 처리는 불안전하다.
	 */
	
	@RequestMapping(value="/delete/{no}")
	public String delete(
			// url주소에서 {no}값을 찾아 변수에 대입
			@PathVariable("no") Long no
			) {
		String email = getPrincipal();
		List<UserVo> list = userSerivce.getByName(email);
		
		
		galleryService.delete(list.get(0).getNo(), no);
		
		return "redirect:/gallery";
	}
	
	
	// 시큐리티에서 유저 아이디 얻기
	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}

		return userName;
	}
}









