package com.fp.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fp.domain.Info;
import com.fp.domain.User;
import com.fp.domain.Cluster;
import com.fp.service.InfoService;
import com.fp.service.UserService;
import com.fp.util.MessageSend;
//import com.fp.util.SVD;
import com.fp.util.WordSplit;

import KmeansCluster.KmeansCluster;

@SuppressWarnings("unchecked")
@Controller
@RequestMapping("/user")
public class UserController  {
	
	@Resource
	private UserService userService;
	@Resource
	private InfoService infoService;
	/** 列表 */
	@RequestMapping("/list")
	public String list(Model model) throws Exception {
		List<User> userList = userService.findAll();
		model.addAttribute("userList", userList);
		return "/user/list";
	}

	/** 删除 */
	@RequestMapping(value = "/delete")
	public String delete(Long id) throws Exception {
		System.out.println("=================" + id);
		userService.delete(id);
		return "redirect:/user/list";
	}

	/** 添加页面 */
	@RequestMapping("/addUI")
	public String addUI(Model model) throws Exception {
		return "/user/addUI";
	}

	/** 添加 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(User user,Long roleId) throws Exception {
		this.userService.save(user);
		return "redirect:list";	
	}

	/** 修改页面 */
	@RequestMapping("/editUI")
	public String editUI(Long id,Model model) throws Exception {
		User user = userService.getById(id);
		model.addAttribute("user", user);
		return "/user/editUI";
	}

	/** 修改 */
	@RequestMapping(value = "/edit")
	public String edit(Long id,User user,Long roleId) throws Exception {
//		User u = userService.getById(id);
//		u.setName(user.getName());
//		u.setDescription(user.getDescription());
//		userService.update(u);
		return "redirect:/user/list";
	}	
	/** 用户登录页面 */
	@RequestMapping("/loginJsp")
	public String loginJsp() throws Exception {
		return "/user/login";
	}
	
	/** 用户登录及验证*/
	@RequestMapping("/login")
	public void userLogin(String name,String password,User user,HttpSession session,HttpServletResponse hsr) throws Exception {
		System.out.println(name+"   and!!!!   "+ password);
		String str="";
		hsr.setContentType("test/html;charset=UTF-8");
		if (name==null || password==null ) {
			str="用户名或密码不能为空";
			hsr.getWriter().write(str);
			return;
		} else {
        str=userService.checkUser(name,password);
        if (str=="正确的用户") {
        	
        	/*登录次数加1*/
        	User u = userService.getByName(name);
        	u.setCount(u.getCount() + 1);
        	
        	User currentUser = userService.getByName(name);
			session.setAttribute("currentUser", currentUser);
			Cookie cookie = new Cookie("login_state","1");
			cookie.setMaxAge(60 * 30);
			cookie.setPath("/");
			hsr.addCookie(cookie);
		} else {
			hsr.getWriter().write(str);
		}
        return;
		}	
	}
	/** 用户注册页面 */
	@RequestMapping("/registerJsp")
	public String userRegisterJsp() throws Exception {
		return "/user/register";
	}
	/** 用户手机号码验证 */
	@RequestMapping(value = "/phoneNumCheck")
	public void phoneNumCheck(String phone,HttpSession session,HttpServletResponse hsr) throws Exception {
		Boolean phoneExit=userService.CheckUserExit(phone);
		hsr.setContentType("test/html;charset=UTF-8");
		String str="";
		if (phoneExit) {
			str="该手机号已被注册";
			hsr.getWriter().write(str);
		} else {
			MessageSend messageSend=new MessageSend();
			String code=messageSend.randomFor6();
			System.out.println(code);
			String content="你好，你的验证码是"+code+",请尽快输入";
			messageSend.messageSend(phone, content);
			session.setAttribute("checkCode", code);
			hsr.getWriter().write("yes");
		}
	}
	/** 用户注册 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public void userRegister(User user,HttpSession session,String phone,String password,String code,HttpServletResponse hsr) throws Exception {
		String checkCode=(String) session.getAttribute("checkCode");
		System.out.println(checkCode);
		hsr.setContentType("test/html;charset=UTF-8");
		String str="";
		if (checkCode.contains(code)) {
			user.setCount(1);
			user.setLoginName(phone);
			user.setPassword(password);
			userService.save(user);
			str="注册成功，自动跳转到首页";
			hsr.getWriter().write(str);
			session.setAttribute("currentUser", user);
			Cookie cookie = new Cookie("login_state","1");
			cookie.setMaxAge(60 * 30);
			cookie.setPath("/");
			hsr.addCookie(cookie);
		} else {
			str="你输入的验证码不正确";
			hsr.getWriter().write(str);
		}
	}
	/** 用户个人首页 */
	@RequestMapping(value = "/shouye")
	public String userShouYe(HttpSession session,Model model) throws Exception {
		User user=(User) session.getAttribute("currentUser");
		model.addAttribute("user", user);
		Info info=infoService.getByUserId((long) 3);
		model.addAttribute("info", info);	
		return "/user/shouye";
	}
	/** 用户学历修改点击时弹出修改框 */
	@RequestMapping(value = "/education")
	public void education(HttpSession session,HttpServletResponse hsr) throws Exception {
		User user=(User) session.getAttribute("currentUser");
		String str="";
		str+="<div id='layer'>\n";
		str+="    <div class='top black'>\n";
		str+="        <dl>\n";
		str+="            <dt>亲！完善资料有助于获得更精准推荐哦！</dt>\n";
		str+="            <dd>\n";
		str+="	          	  <span>资料完整度</span>\n";
		str+="				  <div class='bj09'>\n";
		str+="					  <div class='loading' style='width:100%'>100%</div>\n";
		str+="                </div>\n";
		str+="                <a href='javascript:;' class='close' onclick='educationClose()'>关闭</a>\n";
		str+="            </dd>\n";
		str+="        </dl>\n";
		str+="    </div>\n";
		str+="	  <div class='cont black h01'>\n"; 
		str+="        <div class='title'>\n";
		str+="        	  <div class='iconEducate'></div>\n";
		str+="			   我的学历是？\n";
		str+="        </div>\n";
		str+="        <div class='txtIcon w07a'>\n";
		Info info=infoService.getByUserId(user.getId());
		if (info.getEducation().contains("中专")||info.getEducation().contains("高中")||info.getEducation().contains("初中")||info.getEducation().contains("小学")||info.getEducation().contains("职高")||info.getEducation().contains("技校")) {
			str+="            <a href='javascript:;' id='blewdazhuan' data-value='10' class='active' onclick='blewdazhuan()'>大专以下</a>";
		}else {
			str+="            <a href='javascript:;' id='blewdazhuan' data-value='10' onclick='blewdazhuan()'>大专以下</a>";
		}
		if (info.getEducation().contains("大专")) {
			str+="<a href='javascript:;' data-value='20' id='dazhuan' class='active' onclick='dazhuan()'>大专</a>";
		}else {
			str+="<a href='javascript:;' data-value='20' id='dazhuan' onclick='dazhuan()'>大专</a>";
		}
		if (info.getEducation().contains("本科")) {
			str+="<a href='javascript:;' data-value='30' id='benke' class='active' onclick='benke()'>本科</a>";
		}else {
			str+="<a href='javascript:;' data-value='30' id='benke' onclick='benke()'>本科</a>";
		}
		if (info.getEducation().contains("硕士")) {
			str+="<a href='javascript:;' data-value='40' id='suoshi' class='active' onclick='suoshi()'>硕士</a>";
		}else {
			str+="<a href='javascript:;' data-value='40' id='suoshi' onclick='suoshi()'>硕士</a>";
		}
		if (info.getEducation().contains("博士")) {
			str+="<a href='javascript:;' data-value='50' id='boshi' class='active' onclick='boshi()'>博士</a>\n";
		}else {
			str+="<a href='javascript:;' data-value='50' id='boshi' onclick='boshi()'>博士</a>\n";
		}
		str+="    </div>\n";
		str+="        <div class='improveIcon'>\n";
		str+="            <a href='javascript:;' class='save' onclick='save()'>保存</a>\n";
		str+="            <a href='javascript:;' class='cancel' onclick='educationClose()'>取消</a>\n";
		str+="        </div>\n";
		str+="    </div>\n";
		str+="</div>\n";

		/*str+="<script type='text/javascript'>\n";
		if (info.getEducation().contains("中专")||info.getEducation().contains("高中")||info.getEducation().contains("初中")||info.getEducation().contains("小学")||info.getEducation().contains("职高")||info.getEducation().contains("技校")) {
			str+="	  window.education = '10';\n";
		}
		if (info.getEducation().contains("大专")) {
			str+="    window.education = '20';\n";
		}
		if (info.getEducation().contains("本科")) {
			str+="    window.education = '30';\n";
		}
		if (info.getEducation().contains("硕士")) {
			str+="	  window.education = '40';\n";
		}
		if (info.getEducation().contains("博士")) {
			str+="    window.education = '50';\n";
		}
		str+="    $('a.close,a.cancel').on('click', Util.artDialogClose);\n";
		str+="	  var educationEle = $('a[data-action='education']'), educationSpan = $('#education');\n";
		str+="    $('a.save').on('click', function() {\n";
		str+="        if (education == -1) {\n";
		str+="            return false;\n";
		str+="        }\n";
		str+="        $.post('/basic/education', {education:education}, function(r) {\n";
		str+="        	  if (r.ret != 0) {\n";
		str+="				  alert(r.message);\n";
	    str+="				  } else {\n";
		str+="					educationEle.text(educationEle.data('txt') === 0 ? r.result : ('学历：' + r.result));\n";
		str+="					educationSpan.size() && educationSpan.text(r.result);\n";
		str+="					Util.artDialogClose();\n";
		str+="				}\n";
	    str+="			}, 'json');\n";
		str+="		  });\n";
		str+="    var txtIcon = $('div.txtIcon'), txtIcona = txtIcon.find('a');\n";
		str+="    txtIcona.on('click', function() {\n";
		str+="        txtIcona.removeClass('active');\n";
		str+="        this.className = 'active';\n";
		str+="        window.education = this.getAttribute('data-value');\n";
		str+="    });\n";
		str+="</script>\n";*/
		hsr.setContentType("text/html;charset=UTF-8");
		hsr.getWriter().write(str);	
	}
	/** 用户学历修改保存 */
	@RequestMapping(value = "/educationModify")
	public void educationModify(HttpSession session,String education,HttpServletResponse hsr) throws Exception {
		User user=(User) session.getAttribute("currentUser");
		Info info=infoService.getByUserId(user.getId());
		info.setEducation(education);
		infoService.update(info);
		String str="";
		hsr.setContentType("test/html;charset=utf-8");
		hsr.getWriter().write(str);	
	}
	/** 词云显示页面 */
	@RequestMapping(value = "/wordCloud")
	public String wordCloud(HttpServletRequest request) throws Exception {
		/*String path=request.getSession().getServletContext().getRealPath("resources/wordCloud");
		KmeansCluster kmeansCluster=new KmeansCluster();
		kmeansCluster.KmeansClusterMain(path);*/
		return "/user/wordCloud";
	}
	/** 词的散点图显示页面 */
	@RequestMapping(value = "/diantu")
	public String sandianTu(HttpServletRequest request,Model model) throws Exception {
//		SVD svd=new SVD();
//		List<Cluster> clusters=svd.svd();
//		model.addAttribute("clusters", clusters);
		return "/user/diantu";
	}
	/** 词云显示页面 */
	@RequestMapping(value = "/wordCloud2")
	public String wordCloud2(HttpServletRequest request) throws Exception {
		/*String path=request.getSession().getServletContext().getRealPath("resources/wordCloud");
		KmeansCluster kmeansCluster=new KmeansCluster();
		kmeansCluster.KmeansClusterMain(path);*/
		//WordSplit wordSplit=new WordSplit();
		//wordSplit.writeToJson(path, null);
		return "/user/wordCloud2";
	}
}



