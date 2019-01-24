package kr.green.springtest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.green.springtest.service.BoardService;
import kr.green.springtest.vo.BoardVo;

@Controller
public class BoardController {
	@Autowired
	BoardService boardService;
	@RequestMapping(value="/bbs/list")
	public String list(Model model) {
		List<BoardVo> list = boardService.getBoards();
		model.addAttribute("list",list);
	 
		return "bbs/list";
	}
	@RequestMapping(value="/bbs/register",method=RequestMethod.GET)   //게시글 등록 페이지
	public String registerGET(Model model) {
		
		return "bbs/register";
	}
	@RequestMapping(value="/bbs/register",method=RequestMethod.POST)
	public String registerPOST(Model model,BoardVo board) {
		boardService.registerBoard(board);
		
		return "redirect:/bbs/list";
	}
}
