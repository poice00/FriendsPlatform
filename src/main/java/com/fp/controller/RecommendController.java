package com.fp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fp.domain.Feel;
import com.fp.domain.Info;
import com.fp.domain.User;
import com.fp.service.FeelService;
import com.fp.service.InfoService;
import com.fp.service.RecommendService;
import com.fp.service.UserService;

@Controller
@RequestMapping("/user")
public class RecommendController {
	
	@Resource
	private RecommendService recommendService;
	@Resource
	private UserService userService;
	@Resource
	private FeelService feelService;
	@Resource
	private InfoService infoService;
	
	@RequestMapping("/recommend")
	public String recommend(HttpSession session, Model model){
		
		/*当前用户、查询登录次数、按条件匹配*/
		User user = (User) session.getAttribute("currentUser");
		if(null == user)
			return "/common/404";
		
		int loginCount = user.getCount();
		List<Feel> feelList = recommendService.findRecommend(user);
		Collections.reverse(feelList);
		
		/*登录次数少于推荐次数
		 *按用户的择偶要求重新推荐一个 
		 */
		if(loginCount > feelList.size()){
			Set<Long> ids = new HashSet<>();
			for (Feel feel : feelList)
				ids.add(feel.getRecommender().getId());
			
			List<Info> recommendList = recommendService.recommend(user);
			for (Info info : recommendList)
				if (!ids.contains(info.getId())) {
					User recommender = userService.getById(info.getId());
					
					Feel f = new Feel();
					f.setUser(user);
					f.setRecommender(recommender);
					f.setFeel("考虑中");
					f.setTime(new Date());
					
					feelService.save(f);
					feelList.add(0, f);
					
					break;
				}
		}
		
		/*为jsp页面准备数据*/
		Info recommend = infoService.getById(feelList.get(0).getRecommender().getId());
		model.addAttribute("recommend", recommend);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = sdf.format(feelList.get(0).getTime());
		model.addAttribute("time",date);
		
//		StringBuilder sb = new StringBuilder("{");
//		feelList.remove(0);
//		
//		
//		for (Feel feel : feelList){
//			sb.append("{'id':").append(feel.getRecommender().getId());
//			
//			String avatar = infoService.getById(feel.getRecommender().getId()).getHeadImg();
//			int index1 = avatar.lastIndexOf('/');
//			int index2 = avatar.lastIndexOf('.');
//			
//			sb.append(",'avatar':'").append(avatar.substring(index1 + 1, index2)).append("'},");
//		}
//		
//		sb.replace(sb.length() - 1, sb.length(), "");
//		sb.append("}");
		
		feelList.remove(0);
		
		List<Info> infoList = new ArrayList<>();
		for (Feel feel : feelList)
			infoList.add(infoService.getById(feel.getRecommender().getId()));
		
		model.addAttribute("infoList", infoList);
		model.addAttribute("count", 19 - feelList.size());
		
		return "/user/recommend";
	}
	
	@RequestMapping("/profile/{id}")
	public String profile(@PathVariable("id")Long id, Model model){
		Info info = infoService.getById(id);
		
		if(null == info)
			return "/common/404";
		
		model.addAttribute("info", info);
		
		return "/user/profile";
	}
	
	@RequestMapping("/enjoy")
	public void enjoy(HttpSession session, String type, String id){
		User user = (User) session.getAttribute("currentUser");
		if(null != user){
			
			long userId = user.getId();
			long recId = Long.parseLong(id);
			
			recommendService.setFeel(userId, recId, type);
		}
	}
	
}
