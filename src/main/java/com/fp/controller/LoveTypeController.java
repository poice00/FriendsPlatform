package com.fp.controller;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fp.domain.Info;
import com.fp.domain.LoveGroup;
import com.fp.domain.LoveType;
import com.fp.service.InfoService;
import com.fp.service.LoveTypeService;
import com.fp.util.LoveTypeUtil;

@SuppressWarnings("unchecked")
@Controller
public class LoveTypeController  {
	
	@Resource
	private LoveTypeService loveTypeService;
	
	@Resource
	private InfoService infoService;
	
	/** 答题页面 */
	@RequestMapping("/twelve")
	public String twelve() throws Exception {
		return "/loveType/twelve";
	}
	/** 匹配页面 */
	@RequestMapping("/match")
	public String match() throws Exception {
		return "/loveType/typematch";
	}
	/** 匹配结果*/
	@RequestMapping("/result")
	public String result(HttpServletRequest request,Model model) throws Exception {
		String result1="";
		String result2="";
		String result3="";
		String result4="";
		int sex = 0; 
		//感性与理性(FT)
		System.out.println("==============感性与理性===============");
		String a1 = request.getParameter("questions[1]");   
		String a2 = request.getParameter("questions[4]");   
		String a3 = request.getParameter("questions[12]");
		String a = a1+a2+a3;
		System.out.println("答案是 ： " + a1 +a2 + a3);
//		System.out.println("结果是 ： " + LoveTypeUtil.getResult(a));
		if("A".equals(LoveTypeUtil.getResult(a))){
			result1 = "F" ;
		}else{
			result1 = "T" ;
		}
		//内向与外向(IE)
		System.out.println("==============内向与外向===============");														
		String b1 = request.getParameter("questions[2]");
		String b2 = request.getParameter("questions[6]");
		String b3 = request.getParameter("questions[11]");
		String b = b1+b2+b3;
		System.out.println("答案是 ： " + b1 +b2 + b3);
//		System.out.println("结果是 ： " + LoveTypeUtil.getResult(b));
		if("A".equals(LoveTypeUtil.getResult(b))){
			result2 = "I" ;
		}else{
			result2 = "E" ;
		}
		//整体 细节(现实) NS
		System.out.println("==============整体与细节===============");
		String c1 = request.getParameter("questions[3]");
		String c2 = request.getParameter("questions[7]");
		String c3 = request.getParameter("questions[9]");
		String c = c1 + c2 +c3;
		System.out.println("答案是 ： " + c1 +c2 + c3);
//		System.out.println("结果是 ： " + LoveTypeUtil.getResult(c));
		if("A".equals(LoveTypeUtil.getResult(c))){
			result3 = "N" ;
		}else{
			result3 = "S" ;
		}
		//随性与计划 PJ
		System.out.println("==============随性与计划===============");
		String d1 = request.getParameter("questions[5]");
		String d2 = request.getParameter("questions[8]");
		String d3 = request.getParameter("questions[10]");
		String d = c1 + d2 + d3;
//		System.out.println("结果是 ： " + LoveTypeUtil.getResult(d));
		System.out.println("答案是 ： " + d1 +d2 + d3);
		if("A".equals(LoveTypeUtil.getResult(d))){
			result4 = "P" ;
		}else{
			result4 = "J" ;
		}
		String result = result2 + result3 + result1 + result4 ;
//		String result = "ESTP" ;
		System.out.println(result);
		String rs = request.getParameter("questions[13]");
		if("B".equals(rs)) sex = 1 ;
//		百合恋爱类型说明 魅力四射的挑战者型(ESTP)	百合恋爱类型说明 引人瞩目的表演者型(ESFP)
//		百合恋爱类型说明 求新求变的冒险家型(ISTP)	百合恋爱类型说明 浪漫另类的艺术家型(ISFP)
//		百合恋爱类型说明 外刚内柔的领袖型(ENTJ)	百合恋爱类型说明 善于照顾人的主人型(ESFJ)
//		百合恋爱类型说明 按部就班的公务员型(ISTJ)	百合恋爱类型说明 让人依靠的照顾者型(ISFJ)
//		百合恋爱类型说明 卓越领导式的将军型(ESTJ)	百合恋爱类型说明 推陈出新的发明家型(ENTP)
//		百合恋爱类型说明 实事求是的专家型(INTJ)	百合恋爱类型说明 一板一眼的学者型(INTP)
//		百合恋爱类型说明 理性特质的教师型(ENFJ)	百合恋爱类型说明 冒险特质的记者型(ENFP)
//		百合恋爱类型说明 灵性特质的作家型(INFJ)	百合恋爱类型说明 知性特质的哲学家型(INFP)
		if("ESTP".equals(result)){ 	  
			LoveType exactLoveType = loveTypeService.getByName("INFJ");//额外的类型
			LoveType loveType = loveTypeService.getByName(result);//当前选择的类型
			LoveGroup loveGroup = loveType.getLoveGroup();//当前类型所属的爱情部落
			Set<LoveType> types = loveGroup.getLoveTypes();//得到当前爱情部落下的所有类型
			List<Info> resultList = new ArrayList<Info>();//显示的12条结果
			List<Info> showList = new ArrayList<Info>();//6条显示
			List<Info> hideList = new ArrayList<Info>();//6条隐藏
			List<Info> infoList = new ArrayList<Info>();//数据库查询到的信息
			Iterator<LoveType> it = types.iterator();
			while(it.hasNext()){
				List<Info> typeInfo = infoService.getByParams(it.next().getName(),sex);//类型 、性别
				List<Info> exactInfo = infoService.getByParams(exactLoveType.getName(),sex);//额外的类型 、性别
				infoList.addAll(typeInfo);
				infoList.addAll(exactInfo);
			}
			List<Integer> ten = LoveTypeUtil.getTenData(infoList.size());
			for (Integer num : ten) {
				resultList.add(infoList.get(num));
			}
			for (int i = 0; i < 6; i++) {
				showList.add(resultList.get(i));
			}
			for (int i = 6; i < 12; i++) {
				hideList.add(resultList.get(i));
			}
			
			model.addAttribute("showList", showList);
			model.addAttribute("hideList", hideList);
			return "/loveType/challenger";
		}		
		else if("ESFP".equals(result)) {
			LoveType loveType = loveTypeService.getByName(result);//当前选择的类型
			LoveGroup loveGroup = loveType.getLoveGroup();//当前类型所属的爱情部落
			Set<LoveType> types = loveGroup.getLoveTypes();//得到当前爱情部落下的所有类型
			List<Info> resultList = new ArrayList<Info>();//显示的12条结果
			List<Info> showList = new ArrayList<Info>();//6条显示
			List<Info> hideList = new ArrayList<Info>();//6条隐藏
			List<Info> infoList = new ArrayList<Info>();//数据库查询到的信息
			Iterator<LoveType> it = types.iterator();
			while(it.hasNext()){
				List<Info> typeInfo = infoService.getByParams(it.next().getName(),sex);//类型 、性别
				infoList.addAll(typeInfo);
			}
			List<Integer> ten = LoveTypeUtil.getTenData(infoList.size());
			for (Integer num : ten) {
				resultList.add(infoList.get(num));
			}
			for (int i = 0; i < 6; i++) {
				showList.add(resultList.get(i));
			}
			for (int i = 6; i < 12; i++) {
				hideList.add(resultList.get(i));
			}
			
			model.addAttribute("showList", showList);
			model.addAttribute("hideList", hideList);
			return "/loveType/performer";
		}
		else if("ISTP".equals(result)) {
			LoveType exactLoveType = loveTypeService.getByName("ENFJ");//额外的类型
			LoveType loveType = loveTypeService.getByName(result);//当前选择的类型
			LoveGroup loveGroup = loveType.getLoveGroup();//当前类型所属的爱情部落
			Set<LoveType> types = loveGroup.getLoveTypes();//得到当前爱情部落下的所有类型
			List<Info> resultList = new ArrayList<Info>();//显示的12条结果
			List<Info> showList = new ArrayList<Info>();//6条显示
			List<Info> hideList = new ArrayList<Info>();//6条隐藏
			List<Info> infoList = new ArrayList<Info>();//数据库查询到的信息
			Iterator<LoveType> it = types.iterator();
			while(it.hasNext()){
				List<Info> typeInfo = infoService.getByParams(it.next().getName(),sex);//类型 、性别
				List<Info> exactInfo = infoService.getByParams(exactLoveType.getName(),sex);//额外的类型 、性别
				infoList.addAll(typeInfo);
				infoList.addAll(exactInfo);
			}
			List<Integer> ten = LoveTypeUtil.getTenData(infoList.size());
			for (Integer num : ten) {
				resultList.add(infoList.get(num));
			}
			for (int i = 0; i < 6; i++) {
				showList.add(resultList.get(i));
			}
			for (int i = 6; i < 12; i++) {
				hideList.add(resultList.get(i));
			}
			
			model.addAttribute("showList", showList);
			model.addAttribute("hideList", hideList);
			return "/loveType/adventurer";
		}
//		百合恋爱类型说明 魅力四射的挑战者型(ESTP)	百合恋爱类型说明 引人瞩目的表演者型(ESFP)
//		百合恋爱类型说明 求新求变的冒险家型(ISTP)	百合恋爱类型说明 浪漫另类的艺术家型(ISFP)
//		百合恋爱类型说明 外刚内柔的领袖型(ENTJ)	百合恋爱类型说明 善于照顾人的主人型(ESFJ)
//		百合恋爱类型说明 按部就班的公务员型(ISTJ)	百合恋爱类型说明 让人依靠的照顾者型(ISFJ)
//		百合恋爱类型说明 卓越领导式的将军型(ESTJ)	百合恋爱类型说明 推陈出新的发明家型(ENTP)
//		百合恋爱类型说明 实事求是的专家型(INTJ)	百合恋爱类型说明 一板一眼的学者型(INTP)
//		百合恋爱类型说明 理性特质的教师型(ENFJ)	百合恋爱类型说明 冒险特质的记者型(ENFP)
//		百合恋爱类型说明 灵性特质的作家型(INFJ)	百合恋爱类型说明 知性特质的哲学家型(INFP)
		else if("ISFP".equals(result)) {
			LoveType loveType = loveTypeService.getByName(result);//当前选择的类型
			LoveGroup loveGroup = loveType.getLoveGroup();//当前类型所属的爱情部落
			Set<LoveType> types = loveGroup.getLoveTypes();//得到当前爱情部落下的所有类型
			List<Info> resultList = new ArrayList<Info>();//显示的12条结果
			List<Info> showList = new ArrayList<Info>();//6条显示
			List<Info> hideList = new ArrayList<Info>();//6条隐藏
			List<Info> infoList = new ArrayList<Info>();//数据库查询到的信息
			Iterator<LoveType> it = types.iterator();
			while(it.hasNext()){
				List<Info> typeInfo = infoService.getByParams(it.next().getName(),sex);//类型 、性别
				infoList.addAll(typeInfo);
			}
			List<Integer> ten = LoveTypeUtil.getTenData(infoList.size());
			for (Integer num : ten) {
				resultList.add(infoList.get(num));
			}
			for (int i = 0; i < 6; i++) {
				showList.add(resultList.get(i));
			}
			for (int i = 6; i < 12; i++) {
				hideList.add(resultList.get(i));
			}
			
			model.addAttribute("showList", showList);
			model.addAttribute("hideList", hideList);
			return "/loveType/artist";
		}
//		百合恋爱类型说明 魅力四射的挑战者型(ESTP)	百合恋爱类型说明 引人瞩目的表演者型(ESFP)
//		百合恋爱类型说明 求新求变的冒险家型(ISTP)	百合恋爱类型说明 浪漫另类的艺术家型(ISFP)
//		百合恋爱类型说明 外刚内柔的领袖型(ENTJ)	百合恋爱类型说明 善于照顾人的主人型(ESFJ)
//		百合恋爱类型说明 按部就班的公务员型(ISTJ)	百合恋爱类型说明 让人依靠的照顾者型(ISFJ)
//		百合恋爱类型说明 卓越领导式的将军型(ESTJ)	百合恋爱类型说明 推陈出新的发明家型(ENTP)
//		百合恋爱类型说明 实事求是的专家型(INTJ)	百合恋爱类型说明 一板一眼的学者型(INTP)
//		百合恋爱类型说明 理性特质的教师型(ENFJ)	百合恋爱类型说明 冒险特质的记者型(ENFP)
//		百合恋爱类型说明 灵性特质的作家型(INFJ)	百合恋爱类型说明 知性特质的哲学家型(INFP)
		else if("ENTJ".equals(result)) {
			LoveType exactLoveType = loveTypeService.getByName("ESTJ");//额外的类型
			LoveType loveType = loveTypeService.getByName(result);//当前选择的类型
			LoveGroup loveGroup = loveType.getLoveGroup();//当前类型所属的爱情部落
			Set<LoveType> types = loveGroup.getLoveTypes();//得到当前爱情部落下的所有类型
			List<Info> resultList = new ArrayList<Info>();//显示的12条结果
			List<Info> showList = new ArrayList<Info>();//6条显示
			List<Info> hideList = new ArrayList<Info>();//6条隐藏
			List<Info> infoList = new ArrayList<Info>();//数据库查询到的信息
			Iterator<LoveType> it = types.iterator();
			while(it.hasNext()){
				List<Info> typeInfo = infoService.getByParams(it.next().getName(),sex);//类型 、性别
				List<Info> exactInfo = infoService.getByParams(exactLoveType.getName(),sex);//额外的类型 、性别
				infoList.addAll(typeInfo);
				infoList.addAll(exactInfo);
			}
			List<Integer> ten = LoveTypeUtil.getTenData(infoList.size());
			for (Integer num : ten) {
				resultList.add(infoList.get(num));
			}
			for (int i = 0; i < 6; i++) {
				showList.add(resultList.get(i));
			}
			for (int i = 6; i < 12; i++) {
				hideList.add(resultList.get(i));
			}
			
			model.addAttribute("showList", showList);
			model.addAttribute("hideList", hideList);
			return "/loveType/leader";
		}
		
		else if("ESFJ".equals(result)) {
			LoveType loveType = loveTypeService.getByName(result);//当前选择的类型
			LoveGroup loveGroup = loveType.getLoveGroup();//当前类型所属的爱情部落
			Set<LoveType> types = loveGroup.getLoveTypes();//得到当前爱情部落下的所有类型
			List<Info> resultList = new ArrayList<Info>();//显示的12条结果
			List<Info> showList = new ArrayList<Info>();//6条显示
			List<Info> hideList = new ArrayList<Info>();//6条隐藏
			List<Info> infoList = new ArrayList<Info>();//数据库查询到的信息
			Iterator<LoveType> it = types.iterator();
			while(it.hasNext()){
				List<Info> typeInfo = infoService.getByParams(it.next().getName(),sex);//类型 、性别
				infoList.addAll(typeInfo);
			}
			List<Integer> ten = LoveTypeUtil.getTenData(infoList.size());
			for (Integer num : ten) {
				resultList.add(infoList.get(num));
			}
			for (int i = 0; i < 6; i++) {
				showList.add(resultList.get(i));
			}
			for (int i = 6; i < 12; i++) {
				hideList.add(resultList.get(i));
			}
			
			model.addAttribute("showList", showList);
			model.addAttribute("hideList", hideList);
			return "/loveType/hoster";
		}

		else if("ISTJ".equals(result)) {
			LoveType loveType = loveTypeService.getByName(result);//当前选择的类型
			LoveGroup loveGroup = loveType.getLoveGroup();//当前类型所属的爱情部落
			Set<LoveType> types = loveGroup.getLoveTypes();//得到当前爱情部落下的所有类型
			List<Info> resultList = new ArrayList<Info>();//显示的12条结果
			List<Info> showList = new ArrayList<Info>();//6条显示
			List<Info> hideList = new ArrayList<Info>();//6条隐藏
			List<Info> infoList = new ArrayList<Info>();//数据库查询到的信息
			Iterator<LoveType> it = types.iterator();
			while(it.hasNext()){
				List<Info> typeInfo = infoService.getByParams(it.next().getName(),sex);//类型 、性别
				infoList.addAll(typeInfo);
			}
			List<Integer> ten = LoveTypeUtil.getTenData(infoList.size());
			for (Integer num : ten) {
				resultList.add(infoList.get(num));
			}
			for (int i = 0; i < 6; i++) {
				showList.add(resultList.get(i));
			}
			for (int i = 6; i < 12; i++) {
				hideList.add(resultList.get(i));
			}
			
			model.addAttribute("showList", showList);
			model.addAttribute("hideList", hideList);
			return "/loveType/officialer";
		}
		else if("ISFJ".equals(result)) {
			LoveType loveType = loveTypeService.getByName(result);//当前选择的类型
			LoveGroup loveGroup = loveType.getLoveGroup();//当前类型所属的爱情部落
			Set<LoveType> types = loveGroup.getLoveTypes();//得到当前爱情部落下的所有类型
			List<Info> resultList = new ArrayList<Info>();//显示的12条结果
			List<Info> showList = new ArrayList<Info>();//6条显示
			List<Info> hideList = new ArrayList<Info>();//6条隐藏
			List<Info> infoList = new ArrayList<Info>();//数据库查询到的信息
			Iterator<LoveType> it = types.iterator();
			while(it.hasNext()){
				List<Info> typeInfo = infoService.getByParams(it.next().getName(),sex);//类型 、性别
				infoList.addAll(typeInfo);
			}
			List<Integer> ten = LoveTypeUtil.getTenData(infoList.size());
			for (Integer num : ten) {
				resultList.add(infoList.get(num));
			}
			for (int i = 0; i < 6; i++) {
				showList.add(resultList.get(i));
			}
			for (int i = 6; i < 12; i++) {
				hideList.add(resultList.get(i));
			}
			
			model.addAttribute("showList", showList);
			model.addAttribute("hideList", hideList);
			return "/loveType/carer";
		}
//		百合恋爱类型说明 魅力四射的挑战者型(ESTP)	百合恋爱类型说明 引人瞩目的表演者型(ESFP)
//		百合恋爱类型说明 求新求变的冒险家型(ISTP)	百合恋爱类型说明 浪漫另类的艺术家型(ISFP)
//		百合恋爱类型说明 外刚内柔的领袖型(ENTJ)	百合恋爱类型说明 善于照顾人的主人型(ESFJ)
//		百合恋爱类型说明 按部就班的公务员型(ISTJ)	百合恋爱类型说明 让人依靠的照顾者型(ISFJ)
//		百合恋爱类型说明 卓越领导式的将军型(ESTJ)	百合恋爱类型说明 推陈出新的发明家型(ENTP)
//		百合恋爱类型说明 实事求是的专家型(INTJ)	百合恋爱类型说明 一板一眼的学者型(INTP)
//		百合恋爱类型说明 理性特质的教师型(ENFJ)	百合恋爱类型说明 冒险特质的记者型(ENFP)
//		百合恋爱类型说明 灵性特质的作家型(INFJ)	百合恋爱类型说明 知性特质的哲学家型(INFP)
		else if("ESTJ".equals(result)) {
			LoveType exactLoveType = loveTypeService.getByName("ENTJ");//额外的类型
			LoveType loveType = loveTypeService.getByName(result);//当前选择的类型
			LoveGroup loveGroup = loveType.getLoveGroup();//当前类型所属的爱情部落
			Set<LoveType> types = loveGroup.getLoveTypes();//得到当前爱情部落下的所有类型
			List<Info> resultList = new ArrayList<Info>();//显示的12条结果
			List<Info> showList = new ArrayList<Info>();//6条显示
			List<Info> hideList = new ArrayList<Info>();//6条隐藏
			List<Info> infoList = new ArrayList<Info>();//数据库查询到的信息
			Iterator<LoveType> it = types.iterator();
			while(it.hasNext()){
				List<Info> typeInfo = infoService.getByParams(it.next().getName(),sex);//类型 、性别
				List<Info> exactInfo = infoService.getByParams(exactLoveType.getName(),sex);//额外的类型 、性别
				infoList.addAll(typeInfo);
				infoList.addAll(exactInfo);
			}
			List<Integer> ten = LoveTypeUtil.getTenData(infoList.size());
			for (Integer num : ten) {
				resultList.add(infoList.get(num));
			}
			for (int i = 0; i < 6; i++) {
				showList.add(resultList.get(i));
			}
			for (int i = 6; i < 12; i++) {
				hideList.add(resultList.get(i));
			}
			
			model.addAttribute("showList", showList);
			model.addAttribute("hideList", hideList);
			return "/loveType/general";
		}
		else if("ENTP".equals(result)) {
			LoveType loveType = loveTypeService.getByName(result);//当前选择的类型
			LoveGroup loveGroup = loveType.getLoveGroup();//当前类型所属的爱情部落
			Set<LoveType> types = loveGroup.getLoveTypes();//得到当前爱情部落下的所有类型
			List<Info> resultList = new ArrayList<Info>();//显示的12条结果
			List<Info> showList = new ArrayList<Info>();//6条显示
			List<Info> hideList = new ArrayList<Info>();//6条隐藏
			List<Info> infoList = new ArrayList<Info>();//数据库查询到的信息
			Iterator<LoveType> it = types.iterator();
			while(it.hasNext()){
				List<Info> typeInfo = infoService.getByParams(it.next().getName(),sex);//类型 、性别
				infoList.addAll(typeInfo);
			}
			List<Integer> ten = LoveTypeUtil.getTenData(infoList.size());
			for (Integer num : ten) {
				resultList.add(infoList.get(num));
			}
			for (int i = 0; i < 6; i++) {
				showList.add(resultList.get(i));
			}
			for (int i = 6; i < 12; i++) {
				hideList.add(resultList.get(i));
			}
			
			model.addAttribute("showList", showList);
			model.addAttribute("hideList", hideList);
			return "/loveType/inventor";
		}
//		百合恋爱类型说明 魅力四射的挑战者型(ESTP)	百合恋爱类型说明 引人瞩目的表演者型(ESFP)
//		百合恋爱类型说明 求新求变的冒险家型(ISTP)	百合恋爱类型说明 浪漫另类的艺术家型(ISFP)
//		百合恋爱类型说明 外刚内柔的领袖型(ENTJ)	百合恋爱类型说明 善于照顾人的主人型(ESFJ)
//		百合恋爱类型说明 按部就班的公务员型(ISTJ)	百合恋爱类型说明 让人依靠的照顾者型(ISFJ)
//		百合恋爱类型说明 卓越领导式的将军型(ESTJ)	百合恋爱类型说明 推陈出新的发明家型(ENTP)
//		百合恋爱类型说明 实事求是的专家型(INTJ)	百合恋爱类型说明 一板一眼的学者型(INTP)
//		百合恋爱类型说明 理性特质的教师型(ENFJ)	百合恋爱类型说明 冒险特质的记者型(ENFP)
//		百合恋爱类型说明 灵性特质的作家型(INFJ)	百合恋爱类型说明 知性特质的哲学家型(INFP)
		else if("INTJ".equals(result)) {
			LoveType exactLoveType = loveTypeService.getByName("ESTJ");//额外的类型
			LoveType loveType = loveTypeService.getByName(result);//当前选择的类型
			LoveGroup loveGroup = loveType.getLoveGroup();//当前类型所属的爱情部落
			Set<LoveType> types = loveGroup.getLoveTypes();//得到当前爱情部落下的所有类型
			List<Info> resultList = new ArrayList<Info>();//显示的12条结果
			List<Info> showList = new ArrayList<Info>();//6条显示
			List<Info> hideList = new ArrayList<Info>();//6条隐藏
			List<Info> infoList = new ArrayList<Info>();//数据库查询到的信息
			Iterator<LoveType> it = types.iterator();
			while(it.hasNext()){
				List<Info> typeInfo = infoService.getByParams(it.next().getName(),sex);//类型 、性别
				List<Info> exactInfo = infoService.getByParams(exactLoveType.getName(),sex);//额外的类型 、性别
				infoList.addAll(typeInfo);
				infoList.addAll(exactInfo);
			}
			List<Integer> ten = LoveTypeUtil.getTenData(infoList.size());
			for (Integer num : ten) {
				resultList.add(infoList.get(num));
			}
			for (int i = 0; i < 6; i++) {
				showList.add(resultList.get(i));
			}
			for (int i = 6; i < 12; i++) {
				hideList.add(resultList.get(i));
			}
			
			model.addAttribute("showList", showList);
			model.addAttribute("hideList", hideList);
			return "/loveType/expert";
		}
		else if("INTP".equals(result)) {
			LoveType exactLoveType = loveTypeService.getByName("INFJ");//额外的类型
			LoveType loveType = loveTypeService.getByName(result);//当前选择的类型
			LoveGroup loveGroup = loveType.getLoveGroup();//当前类型所属的爱情部落
			Set<LoveType> types = loveGroup.getLoveTypes();//得到当前爱情部落下的所有类型
			List<Info> resultList = new ArrayList<Info>();//显示的12条结果
			List<Info> showList = new ArrayList<Info>();//6条显示
			List<Info> hideList = new ArrayList<Info>();//6条隐藏
			List<Info> infoList = new ArrayList<Info>();//数据库查询到的信息
			Iterator<LoveType> it = types.iterator();
			while(it.hasNext()){
				List<Info> typeInfo = infoService.getByParams(it.next().getName(),sex);//类型 、性别
				List<Info> exactInfo = infoService.getByParams(exactLoveType.getName(),sex);//额外的类型 、性别
				infoList.addAll(typeInfo);
				infoList.addAll(exactInfo);
			}
			List<Integer> ten = LoveTypeUtil.getTenData(infoList.size());
			for (Integer num : ten) {
				resultList.add(infoList.get(num));
			}
			for (int i = 0; i < 6; i++) {
				showList.add(resultList.get(i));
			}
			for (int i = 6; i < 12; i++) {
				hideList.add(resultList.get(i));
			}
			
			model.addAttribute("showList", showList);
			model.addAttribute("hideList", hideList);
			return "/loveType/scholar";
		}
//		百合恋爱类型说明 魅力四射的挑战者型(ESTP)	百合恋爱类型说明 引人瞩目的表演者型(ESFP)
//		百合恋爱类型说明 求新求变的冒险家型(ISTP)	百合恋爱类型说明 浪漫另类的艺术家型(ISFP)
//		百合恋爱类型说明 外刚内柔的领袖型(ENTJ)	百合恋爱类型说明 善于照顾人的主人型(ESFJ)
//		百合恋爱类型说明 按部就班的公务员型(ISTJ)	百合恋爱类型说明 让人依靠的照顾者型(ISFJ)
//		百合恋爱类型说明 卓越领导式的将军型(ESTJ)	百合恋爱类型说明 推陈出新的发明家型(ENTP)
//		百合恋爱类型说明 实事求是的专家型(INTJ)	百合恋爱类型说明 一板一眼的学者型(INTP)
//		百合恋爱类型说明 理性特质的教师型(ENFJ)	百合恋爱类型说明 冒险特质的记者型(ENFP)
//		百合恋爱类型说明 灵性特质的作家型(INFJ)	百合恋爱类型说明 知性特质的哲学家型(INFP)
		else if("ENFJ".equals(result)) {
			LoveType exactLoveType = loveTypeService.getByName("ISTP");//额外的类型
			LoveType loveType = loveTypeService.getByName(result);//当前选择的类型
			LoveGroup loveGroup = loveType.getLoveGroup();//当前类型所属的爱情部落
			Set<LoveType> types = loveGroup.getLoveTypes();//得到当前爱情部落下的所有类型
			List<Info> resultList = new ArrayList<Info>();//显示的12条结果
			List<Info> showList = new ArrayList<Info>();//6条显示
			List<Info> hideList = new ArrayList<Info>();//6条隐藏
			List<Info> infoList = new ArrayList<Info>();//数据库查询到的信息
			Iterator<LoveType> it = types.iterator();
			while(it.hasNext()){
				List<Info> typeInfo = infoService.getByParams(it.next().getName(),sex);//类型 、性别
				List<Info> exactInfo = infoService.getByParams(exactLoveType.getName(),sex);//额外的类型 、性别
				infoList.addAll(typeInfo);
				infoList.addAll(exactInfo);
			}
			List<Integer> ten = LoveTypeUtil.getTenData(infoList.size());
			for (Integer num : ten) {
				resultList.add(infoList.get(num));
			}
			for (int i = 0; i < 6; i++) {
				showList.add(resultList.get(i));
			}
			for (int i = 6; i < 12; i++) {
				hideList.add(resultList.get(i));
			}
			
			model.addAttribute("showList", showList);
			model.addAttribute("hideList", hideList);
			return "/loveType/teacher";
		}
		else if("ENFP".equals(result)) {
			LoveType loveType = loveTypeService.getByName(result);//当前选择的类型
			LoveGroup loveGroup = loveType.getLoveGroup();//当前类型所属的爱情部落
			Set<LoveType> types = loveGroup.getLoveTypes();//得到当前爱情部落下的所有类型
			List<Info> resultList = new ArrayList<Info>();//显示的12条结果
			List<Info> showList = new ArrayList<Info>();//6条显示
			List<Info> hideList = new ArrayList<Info>();//6条隐藏
			List<Info> infoList = new ArrayList<Info>();//数据库查询到的信息
			Iterator<LoveType> it = types.iterator();
			while(it.hasNext()){
				List<Info> typeInfo = infoService.getByParams(it.next().getName(),sex);//类型 、性别
				infoList.addAll(typeInfo);
			}
			List<Integer> ten = LoveTypeUtil.getTenData(infoList.size());
			for (Integer num : ten) {
				resultList.add(infoList.get(num));
			}
			for (int i = 0; i < 6; i++) {
				showList.add(resultList.get(i));
			}
			for (int i = 6; i < 12; i++) {
				hideList.add(resultList.get(i));
			}
			
			model.addAttribute("showList", showList);
			model.addAttribute("hideList", hideList);
			return "/loveType/inventor";
		}
		else if("INFJ".equals(result)) {
			LoveType loveType = loveTypeService.getByName(result);//当前选择的类型
			LoveGroup loveGroup = loveType.getLoveGroup();//当前类型所属的爱情部落
			Set<LoveType> types = loveGroup.getLoveTypes();//得到当前爱情部落下的所有类型
			List<Info> resultList = new ArrayList<Info>();//显示的12条结果
			List<Info> showList = new ArrayList<Info>();//6条显示
			List<Info> hideList = new ArrayList<Info>();//6条隐藏
			List<Info> infoList = new ArrayList<Info>();//数据库查询到的信息
			Iterator<LoveType> it = types.iterator();
			while(it.hasNext()){
				List<Info> typeInfo = infoService.getByParams(it.next().getName(),sex);//类型 、性别
				infoList.addAll(typeInfo);
			}
			List<Integer> ten = LoveTypeUtil.getTenData(infoList.size());
			for (Integer num : ten) {
				resultList.add(infoList.get(num));
			}
			for (int i = 0; i < 6; i++) {
				showList.add(resultList.get(i));
			}
			for (int i = 6; i < 12; i++) {
				hideList.add(resultList.get(i));
			}
			
			model.addAttribute("showList", showList);
			model.addAttribute("hideList", hideList);
			return "/loveType/author";
		}
		else if("INFP".equals(result)) {
			LoveType loveType = loveTypeService.getByName(result);//当前选择的类型
			LoveGroup loveGroup = loveType.getLoveGroup();//当前类型所属的爱情部落
			Set<LoveType> types = loveGroup.getLoveTypes();//得到当前爱情部落下的所有类型
			List<Info> resultList = new ArrayList<Info>();//显示的12条结果
			List<Info> showList = new ArrayList<Info>();//6条显示
			List<Info> hideList = new ArrayList<Info>();//6条隐藏
			List<Info> infoList = new ArrayList<Info>();//数据库查询到的信息
			Iterator<LoveType> it = types.iterator();
			while(it.hasNext()){
				List<Info> typeInfo = infoService.getByParams(it.next().getName(),sex);//类型 、性别
				infoList.addAll(typeInfo);
			}
			List<Integer> ten = LoveTypeUtil.getTenData(infoList.size());
			for (Integer num : ten) {
				resultList.add(infoList.get(num));
			}
			for (int i = 0; i < 6; i++) {
				showList.add(resultList.get(i));
			}
			for (int i = 6; i < 12; i++) {
				hideList.add(resultList.get(i));
			}
			
			model.addAttribute("showList", showList);
			model.addAttribute("hideList", hideList);
			return "/loveType/philosopher";
		}
		else return "/loveType/error";
	}
	
	
}
