package com.spring.inwoo;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.inwoo.service.BoardService;
import com.spring.inwoo.service.UserService;
import com.spring.inwoo.util.WebUtil;
import com.spring.inwoo.vo.BoardVo;
import com.spring.inwoo.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;

	@Autowired
	UserService userService;

	private static final Log LOG = LogFactory.getLog(BoardController.class);

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(@RequestParam(value = "p", required = true, defaultValue = "1") Integer page,
			@RequestParam(value = "kwd", required = true, defaultValue = "") String keyword, Model model) {
		Map<String, Object> map = boardService.getList(keyword, page, 5);

		LOG.debug("--------------------------" + page + ", " + keyword);
		LOG.debug("--------------------------" + "map.list=" + ": " + map.get("list"));

		model.addAttribute("map", map);

		return "board/index";
	}

	// "/write" 동일한 요청을 GET, POST 나누어 받겠다.
	// -> RequestMethod.GET만 존재할 시 POST 요청은 405 에러!
	// -> 요청 방법이 잘못 됐을 경우 발생하는 상태 코드

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "board/write";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@ModelAttribute BoardVo vo,
			@RequestParam(value = "p", required = true, defaultValue = "1") Integer page,
			@RequestParam(value = "kwd", required = true, defaultValue = "") String keyword) {
		
		String email = getPrincipal();
		List<UserVo> list = userService.getByName(email);
		
		vo.setUserNo(list.get(0).getNo());
		
		// 현재 로그인 유저의 번호를 가져와 BoardVo 객체에 설정!
		// -> 작성한 유저를 관리하기 위해 Board는 유저의 번호를 갖는다.
		boardService.insert(vo);
		// 글쓰기 폼에서 작성한 내용들과 유저 번호를 갖고 있는 BoardVo를 전달!

		return "redirect:/board" + "?p=" + page + "&kwd=" + WebUtil.encodeURL(keyword, "UTF-8");
	}

	@RequestMapping("/view")
	public String view(@RequestParam(value = "no", required = true, defaultValue = "0") Long no,
			@RequestParam(value = "p", required = true, defaultValue = "1") Integer page,
			@RequestParam(value = "kwd", required = true, defaultValue = "") String keyword, Model model) {
		BoardVo boardVo = boardService.getByNo(no);

		String email = getPrincipal();
		LOG.debug("-------------------------------userEmail=" + email);
		List<UserVo> list = userService.getByName(email);

		model.addAttribute("userList", list);
		model.addAttribute("boardVo", boardVo);
		model.addAttribute("page", page);
		model.addAttribute("keyword", keyword);
		System.out.println("param : " + keyword);
		return "board/view";
	}

	@RequestMapping(value = "/reply", method = RequestMethod.GET)
	public String reply(@RequestParam(value = "no", required = true, defaultValue = "0") Long no,
			@RequestParam(value = "p", required = true, defaultValue = "1") Integer page,
			@RequestParam(value = "kwd", required = true, defaultValue = "") String keyword, Model model) {
		BoardVo boardVo = boardService.getByNo(no);

		model.addAttribute("boardVo", boardVo);
		model.addAttribute("page", page);
		model.addAttribute("keyword", keyword);

		return "board/reply";
	}

	// board/view에서 글 수정 링크 클릭시
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@RequestParam(value = "no", required = true, defaultValue = "0") Long no,
			@RequestParam(value = "p", required = true, defaultValue = "1") Integer page,
			@RequestParam(value = "kwd", required = true, defaultValue = "") String keyword, Model model) {
		BoardVo boardVo = boardService.getByNo(no);

		model.addAttribute("boardVo", boardVo);
		model.addAttribute("page", page);
		model.addAttribute("keyword", keyword);

		return "board/modify";
	}

	// board/modify에서 수정 버튼 클릭시
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(@ModelAttribute BoardVo boardVo,
			@RequestParam(value = "p", required = true, defaultValue = "1") Integer page,
			@RequestParam(value = "kwd", required = true, defaultValue = "") String keyword,
			Model model) {
		String email = getPrincipal();
		LOG.debug("-------------------------------userEmail=" + email);
		List<UserVo> list = userService.getByName(email);
		boardVo.setUserNo(list.get(0).getNo());
		boardService.update(boardVo);
		
		return "redirect:/board/view" + "?no=" + boardVo.getNo() + "&p=" + page + "&kwd="
				+ WebUtil.encodeURL(keyword, "UTF-8");
	}
	
	
	@RequestMapping(value="/delete")
	public String delete(@ModelAttribute BoardVo boardVo) {
		
		boardService.delete(boardVo.getNo());
		
		return "redirect:/board";
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
