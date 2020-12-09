package com.spring.inwoo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.inwoo.service.BoardService;
import com.spring.inwoo.util.WebUtil;
import com.spring.inwoo.vo.BoardVo;
import com.spring.inwoo.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@RequestMapping("")
	public String index(
			@RequestParam(value="p", required=true, defaultValue="1") Integer page,
			@RequestParam(value="kwd", required=true, defaultValue="") String keyword,
			Model model
			) {
		Map<String, Object> map = boardService.getMessageList(page, keyword);
		model.addAttribute("map", map);
		
		return "board/index";
	}
	
	// "/write" 동일한 요청을 GET, POST 나누어 받겠다.
	//	-> RequestMethod.GET만 존재할 시 POST 요청은 405 에러!
	//	-> 요청 방법이 잘못 됐을 경우 발생하는 상태 코드

	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(
			UserVo authUser, @ModelAttribute BoardVo vo,
			@RequestParam(value="p", required=true, defaultValue="1") Integer page,
			@RequestParam(value="kwd", required=true, defaultValue="") String keyword
			) {
		vo.setUserNo(authUser.getNo());
		// 현재 로그인 유저의 번호를 가져와 BoardVo 객체에 설정!
		//	-> 작성한 유저를 관리하기 위해 Board는 유저의 번호를 갖는다.
		boardService.writeMessage(vo);
		// 글쓰기 폼에서 작성한 내용들과 유저 번호를 갖고 있는 BoardVo를 전달!
		
		return "redirect:/board" + "?p=" + page 
								 + "&kwd=" + WebUtil.encodeURL(keyword, "UTF-8");
	}
	
	@RequestMapping("/view")
	public String view(
			@RequestParam(value="no", required=true, defaultValue="0") Long no,
			@RequestParam(value="p", required=true, defaultValue="1") Integer page,
			@RequestParam(value="kwd", required=true, defaultValue="") String keyword,
			Model model) {
		BoardVo boardVo = boardService.getMessage(no);
		
		model.addAttribute("boardVo", boardVo);
		model.addAttribute("page", page);
		model.addAttribute("keyword", keyword);
		System.out.println("param : " + keyword);
		return "board/view";
	}
	
	
	@RequestMapping(value="/reply", method=RequestMethod.GET)
	public String reply(
			@RequestParam(value="no", required=true, defaultValue="0") Long no,
			@RequestParam(value="p", required=true, defaultValue="1") Integer page,
			@RequestParam(value="kwd", required=true, defaultValue="") String keyword,
			Model model) {
		BoardVo boardVo = boardService.getMessage(no);
		
		model.addAttribute("boardVo", boardVo);
		model.addAttribute("page", page);
		model.addAttribute("keyword", keyword);
		
		return "board/reply";
	}
	
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(
			@RequestParam(value="no", required=true, defaultValue="0") Long no,
			@RequestParam(value="p", required=true, defaultValue="1") Integer page,
			@RequestParam(value="kwd", required=true, defaultValue="") String keyword,
			Model model
			) {
		BoardVo boardVo = boardService.getMessage(no);
		
		model.addAttribute("boardVo", boardVo);
		model.addAttribute("page", page);
		model.addAttribute("keyword", keyword);
		
		return "board/modify";
	}
	
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)	
	public String modify(
			UserVo authUser,
			@ModelAttribute BoardVo boardVo,
			@RequestParam(value="p", required=true, defaultValue="1") Integer page,
			@RequestParam(value="kwd", required=true, defaultValue="") String keyword
			) {
		boardVo.setUserNo(authUser.getNo());
		boardService.updateMessage(boardVo);
		
		return "redirect:/board/view"
			 + "?no=" + boardVo.getNo()
			 + "&p=" + page
			 + "&kwd=" + WebUtil.encodeURL(keyword, "UTF-8");
	}
	
}














