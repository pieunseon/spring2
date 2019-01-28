package kr.green.springtest.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.green.springtest.service.AccountService;
import kr.green.springtest.vo.AccountVo;

@Controller
public class MainController {
	@Autowired
	AccountService accountService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homeGet(Locale locale, Model model) {
		return "home";
	}
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String homePost(Model model, AccountVo loginInfo) {  //자료형 type에 유의해야함(ex:date)
		 AccountVo user =accountService.signin(loginInfo);
		 model.addAttribute("user",user);
		return "redirect:/";
	}
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupGet(Model model) {
		
		return "signup";
	}
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signupPost(Model model, AccountVo userInfo) {
		 boolean isUser =accountService.signup(userInfo);
		 if(isUser)
			 return "redirect:/";
	     return "redirect:/signup";
	}
	@RequestMapping(value = "/signup/dup") 
	@ResponseBody
	public Map<Object,Object> idcheck(@RequestBody String id) {
		Map<Object,Object> map =new HashMap<Object,Object>();  //id, pw
		 boolean dup =accountService.isDuplicated(id);  //해당하는 id가 있는지 없는지 확인 boolean타입으로 저장.
		 map.put("dup",dup);
		 return map;
	}
	//로그아웃
	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public String signoutGet(Model model,HttpServletRequest request) {
		 HttpSession session=request.getSession();
		 session.removeAttribute("user");
	     return "redirect:/bbs/list";
	}
	
}
