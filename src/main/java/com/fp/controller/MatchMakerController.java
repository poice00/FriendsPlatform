package com.fp.controller;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.helpers.MarkerIgnoringBase;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fp.domain.Info;
import com.fp.domain.LoveGroup;
import com.fp.domain.LoveType;
import com.fp.domain.Matchmaker;
import com.fp.domain.SuccessCase;
import com.fp.domain.User;
import com.fp.service.InfoService;
import com.fp.service.LoveTypeService;
import com.fp.service.MatchMakerService;
import com.fp.service.UserService;
import com.fp.util.LoveTypeUtil;
import com.sun.net.httpserver.Authenticator.Success;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings("unchecked")
@Controller
public class MatchMakerController  {
	
	
	@Resource
	private MatchMakerService matchMakerService;
	@Resource
	private UserService userService;
	@Resource
	private InfoService infoService;
	/** 聊天页面 */
	@RequestMapping("/chat/{id}")
	public String chat(Model model,@PathVariable("id") String id) throws Exception {
		Matchmaker detail = this.matchMakerService.getByStringId(id);
		model.addAttribute("detail", detail);
		return "/matchMaker/chat";
	}
	/** 红娘聊天页面 */
	@RequestMapping("/makerChat/{id}")
	public String makerChat(HttpSession session,Model model,@PathVariable("id") String id,HttpServletResponse response,String content,String currentTime,String makerId) throws Exception {
		if(session.getAttribute("result")!=null)
			session.removeAttribute("result");
		Matchmaker detail = this.matchMakerService.getByStringId(id);
		if(session.getAttribute("currentUser")!=null){
			User currentUser = (User) session.getAttribute("currentUser");
			Info info = this.infoService.getByUserId(currentUser.getId());
			model.addAttribute("info", info);
		}
		return "/matchMaker/makerChat";
	}
	/** 红娘主页面 */
	@RequestMapping("/maker")
	public String matchMaker(Model model) throws Exception {
		List<Matchmaker> makersList = this.matchMakerService.findAll();
		model.addAttribute("makersList", makersList);
		return "/matchMaker/show";
	}
	
	/** 用户发送*/
	@RequestMapping("/visterSend")
	public void visterSend(String content,String currentTime,String makerId,HttpServletResponse response,HttpSession session) throws Exception {
		
		PrintWriter out= response.getWriter();
		//matchmaker.setState(1);//1.有消息
		//content.replace("\\n", "<br>");
		System.out.println("makerId : " + makerId);
		System.out.println("content : " + content);
		System.out.println("currentTime : " + currentTime);
		if(content!=null||currentTime!=null||makerId!=null){
			Matchmaker matchmaker = this.matchMakerService.getByStringId(makerId);//需要发送给的红娘
			session.setAttribute("content", content);
			session.setAttribute("currentTime", currentTime);
			StringBuilder sb = new StringBuilder();
			sb.append("{'content':'").append(content).append("','markerId':'").append(makerId).append("','currentTime':'").append(currentTime).append("'}");
			
			JSONObject result = JSONObject.fromObject(sb.toString());
			session.setAttribute("result", result);
		}
		if(session.getAttribute("result")!=null){
			System.out.println("=====================" +session.getAttribute("result"));
			out.write(session.getAttribute("result").toString());
			
			//session.removeAttribute("result");
		}
	}
	/** 红娘发送*/
	@RequestMapping("/makerSend")
	public void makerSend(String makercontent,String makercurrentTime,HttpServletResponse response,HttpSession session) throws Exception {
		PrintWriter out= response.getWriter();
		System.out.println("makercontent : " + makercontent);
		System.out.println("makercurrentTime : " + makercurrentTime);
		session.setAttribute("makercontent", makercontent);
		session.setAttribute("makercurrentTime", makercurrentTime);
		StringBuilder sb = new StringBuilder();
		sb.append("{'makercontent':'").append(makercontent).append("','makercurrentTime':'").append(makercurrentTime).append("'}");
		
		JSONObject result = JSONObject.fromObject(sb.toString());
		out.write(result.toString());
	}
	/** jieshou*/
	@RequestMapping("/visterGet")
	public void visterGet(String makerId,HttpServletResponse response,HttpSession session) throws Exception {
		PrintWriter out= response.getWriter();
		String content = (String) session.getAttribute("content");
		String currentTime = (String) session.getAttribute("currentTime");
		//content.replace("\\n", "<br>");
		System.out.println("content =  " + content);
		System.out.println("currentTime =  " + currentTime);
		if(content!=null||currentTime!=null){
			StringBuilder sb = new StringBuilder();
			sb.append("{'content':'").append(content).append("','currentTime':'").append(currentTime).append("'}");
			JSONObject result = JSONObject.fromObject(sb.toString());
			out.write(result.toString());
			session.removeAttribute("content");
			session.removeAttribute("currentTime");
		}
		/*}else{
			out.println("fail");
		}*/
	}
	/** jieshou*/
	@RequestMapping("/makerGet")
	public void makerGet(String makerId,HttpServletResponse response,HttpSession session) throws Exception {
		PrintWriter out= response.getWriter();
		String makercontent = (String) session.getAttribute("makercontent");
		String makercurrentTime = (String) session.getAttribute("makercurrentTime");
		System.out.println("makercontent =  " + makercontent);
		System.out.println("makercurrentTime =  " + makercurrentTime);
		if(makercontent!=null||makercurrentTime!=null){
			StringBuilder sb = new StringBuilder();
			sb.append("{'makercontent':'").append(makercontent).append("','makercurrentTime':'").append(makercurrentTime).append("'}");
			JSONObject result = JSONObject.fromObject(sb.toString());
			out.write(result.toString());
			session.removeAttribute("makercontent");
			session.removeAttribute("makercurrentTime");
		}
		/*}else{
			out.println("fail");
		}*/
	}
	
	/** 红娘信息 */
	@RequestMapping("/detail/{id}")
	public String detail(Model model,@PathVariable("id") String id) throws Exception {
		System.out.println("id : " + id);
		Matchmaker detail = this.matchMakerService.getByStringId(id);
		model.addAttribute("detail", detail);
		SuccessCase cs = null;
		Set<SuccessCase> cases = detail.getCases();
		Iterator<SuccessCase> it = cases.iterator();
		while(it.hasNext()){
			cs = it.next();
		}
		model.addAttribute("cs", cs);
		return "/matchMaker/detail";
	}
	/** 登录 */
	@RequestMapping("/maker/loginUI")
	public String loginUI() throws Exception {
		return "/matchMaker/loginUI";
	}
	/** 注销 */
	@RequestMapping("/maker/logout")
	public String logout(HttpSession session) throws Exception {
		Matchmaker matchmaker  = (Matchmaker) session.getAttribute("matchmaker");
		matchmaker.setState(0);
		matchMakerService.update(matchmaker);
		session.removeAttribute("matchmaker");
		return "redirect:/maker";
	}
	/** 登录主页面 */
	@RequestMapping("/maker/login")
	public String login(Model model,String username,String password,HttpSession session) throws Exception {
		boolean result=matchMakerService.findUserBy(username,password);
		if(result){
			Matchmaker matchmaker  = matchMakerService.getByName(username);
			matchmaker.setState(1);//在线了
			matchMakerService.update(matchmaker);
			session.setAttribute("matchmaker", matchmaker);
			return "/matchMaker/index";
		}
		return "redirect:/maker";
	}
	
	
}
