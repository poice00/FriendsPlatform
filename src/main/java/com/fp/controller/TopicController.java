package com.fp.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fp.domain.Page;
import com.fp.domain.Reply;
import com.fp.domain.Topic;
import com.fp.service.TopicService;

@SuppressWarnings("unchecked")
@Controller
@RequestMapping("/topic")
public class TopicController {

	@Resource
	private TopicService topicService;

	/** 列表 */
	@RequestMapping("/list/{CurrentPage}")
	public String list(@PathVariable("CurrentPage") String CurrentPage, Model model) throws Exception {
		int cp = Integer.parseInt(CurrentPage);
		Page<Topic> topicList = topicService.findTopic(cp, 24);
		model.addAttribute("topicList", topicList);

		return "/topic/list";
	}

	/** 详情 */
	@RequestMapping("/detail/{TopicId}/{CurrentPage}")
	public String view(@PathVariable("TopicId") String TopicId, @PathVariable("CurrentPage") String CurrentPage,
			Model model, HttpSession session) throws Exception {
		Topic topic = topicService.getById(TopicId);
		model.addAttribute("topic", topic);
		int cp = Integer.parseInt(CurrentPage);
		Page<Reply> replyList = topicService.findByTopic(topic, cp, 6);
		model.addAttribute("replyList", replyList);

		return "/topic/detail";
	}
}
