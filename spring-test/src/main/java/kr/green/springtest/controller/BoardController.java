package kr.green.springtest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import kr.green.springtest.service.BoardService;
import kr.green.springtest.vo.AccountVo;
import kr.green.springtest.vo.BoardVo;

@Controller
public class BoardController {
	private static final BoardVo String = null;
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
	 
	@RequestMapping(value="/bbs/detail",method=RequestMethod.GET)   
	public String detailGET(Model model,Integer id) { 
		//System.out.println(id);
		BoardVo board =boardService.detailBoard(id);
		if(board ==null)
			return "redirect:/bbs/list";
		model.addAttribute("board",board);
		
		/* 1.전달받은 아이디 값을 콘솔에 출력
		 * 2.service에서 id값을 이용하여 해당 게시글을 가져오는 메소드 호출
		 * 3.해당 게시글을 jsp로 전달
		 */
		return "bbs/detail";
	}
	
	//게시글 삭제
	@RequestMapping(value="/bbs/delete",method=RequestMethod.GET)   
	public String deleteGET(Model model,Integer id,HttpServletRequest request) { 
		//request 에서 session 정보를 가져와서 session에 저장된 user정보를 가져옴
		HttpSession session = request.getSession();
		AccountVo user = (AccountVo)session.getAttribute("user");
		//삭제권한이 없는 유저가 URI를 통해 삭제를 시도할 수 있기 때문에 권한 없는 유저가 삭제 시도하면 막아주기 위해서 게시판 id와 현재 로그인한 유저 정보를 전달한다.
		boardService.deleteBoard(id,user);
		return "redirect:/bbs/list";
		
	}
	
	/* [게시글 수정]
	 * 1.detail.jsp에 수정 버튼 생성
	 * 2.modify.jsp를 생성하고 register.jsp코드를 이용하여 구현
	 * 3.URI 선정후 (/bbs/modify)컨트롤러에서 /bbs/modify URI가 들어오면 modify.jsp가 출력되도록 메소드 생성 및 구현 (method=RequestMethod.GET)
	 * 4.수정 페이지에서 완료 버튼을 누르면  동작할 수 있도록 컨트롤러에서 메소드 생성 (method=Request.POST)
	 * 5.컨트롤러, 서비스에  수정하는 메소드 생성 및 구현 
	 * */
	
	@RequestMapping(value="/bbs/modify",method=RequestMethod.GET)   
	public String modifyGET(Model model,Integer id) { 
		
		BoardVo board =boardService.detailBoard(id);
		if(board ==null)
			return "redirect:/bbs/list";
		model.addAttribute("board",board);
		return "bbs/modify";
	}
	
	@RequestMapping(value="/bbs/modify",method=RequestMethod.POST)   
	public String modifyPOST(Model model,BoardVo board,HttpServletRequest request) { 
		HttpSession session = request.getSession();    //로그인된 회원만 수정해야되기때문에 
		AccountVo user = (AccountVo)session.getAttribute("user");   //유저정보 가져와서 일치하는지 확인
		
		if(boardService.modifyBoard (board,user))
			return "redirect:/bbs/list";
		model.addAttribute("id",board.getId());
		return "redirect:/bbs/detail";
	}
}