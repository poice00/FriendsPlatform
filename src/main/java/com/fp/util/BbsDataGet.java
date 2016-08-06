package com.fp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.swing.plaf.synth.SynthSpinnerUI;
import org.hibernate.SessionFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fp.domain.BbsReply;
import com.fp.domain.BbsTopic;

public class BbsDataGet {
	
	   
	public static String getHtmlResource(String url, String encoding) {
		StringBuffer buffer=new StringBuffer();
		URL urlObj=null;
		URLConnection uc=null;
		InputStreamReader in=null;
		BufferedReader reader=null;
		try{
			urlObj=new URL(url);
			uc=urlObj.openConnection();
			in=new InputStreamReader(uc.getInputStream(),encoding);
			reader=new BufferedReader(in);
			String temp=null;
			while((temp=reader.readLine())!=null){
				buffer.append(temp+"\n");
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("网络连接失败");
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return buffer.toString();
	}
	
	public static void main(String[] args) throws Exception {
		String url,html;
		String encoding="GB2312";
		url="http://bbs.xjtu.edu.cn/BMYARYULTNVGAJPTMFIWQAVWVIZEMIUTUVTD_B/tdoc?B=PieBridge";
		html=getHtmlResource(url,encoding);
		Document doc=Jsoup.parse(html);
		Elements initelements=doc.getElementsByTag("table");
		Elements elements2=initelements.get(initelements.size()-2).getElementsByTag("a");
		String str=elements2.get(elements2.size()-1).attr("href");
		String[] strings=str.split("=");
		int lastPage=Integer.parseInt(strings[strings.length-1]);
		int total=0;
		url="http://bbs.xjtu.edu.cn/BMYARYULTNVGAJPTMFIWQAVWVIZEMIUTUVTD_B/bbstdoc?B=PieBridge&S="+String.valueOf(lastPage);
		for (int i = 0; i < 34; i++) {
			html=getHtmlResource(url,encoding);
			Document doc2=Jsoup.parse(html);
			Elements initelements2=doc2.getElementsByTag("table");
			Elements elements3=initelements2.get(initelements2.size()-3).getElementsByTag("a");
			Elements elements4=initelements2.get(initelements2.size()-2).getElementsByTag("a");
			if (i==0) {
				str=elements4.get(elements4.size()-2).attr("href");
			} else {
				str=elements4.get(elements4.size()-3).attr("href");
			}
			strings=str.split("=");
			lastPage=Integer.parseInt(strings[strings.length-1]);
			url="http://bbs.xjtu.edu.cn/BMYARYULTNVGAJPTMFIWQAVWVIZEMIUTUVTD_B/bbstdoc?B=PieBridge&S="+String.valueOf(lastPage);
		    for (int j = 0; j < 40; j=j+2) {
				str=elements3.get(j+1).attr("href");
				String strtext=elements3.get(j+1).text();
				if (strtext.contains("征")) {
					if (strtext.contains("合集")) {
					}else{
						total+=1;
						str="http://bbs.xjtu.edu.cn/BMYARYULTNVGAJPTMFIWQAVWVIZEMIUTUVTD_B/"+str;
						html=getHtmlResource(str,encoding);
						Document doc3=Jsoup.parse(html);
						Elements initelements3=doc3.getElementsByTag("div");
					    String topicinfo=initelements3.get(1).text();
				        String topicAuthor=exact(topicinfo, "发信人:(.*), 信区");
				        topicAuthor=topicAuthor.replace(" ", "");
				        String topicTitle=exact(topicinfo, "标  题: (.*) 发信站:");
				        String topicPostTime=exact(topicinfo, "兵马俑BBS (.*), 本站");
				        topicPostTime=topicPostTime.substring(topicPostTime.indexOf("(")+1, topicPostTime.indexOf(")"));
				        String  topicContent=exact(topicinfo, ", 本站(.*) ---");
				        String[] tos ;
				        topicContent=topicContent.replace("(bbs.xjtu.edu.cn) ", "");
				        if (topicContent.contains("附图")) {
							tos=topicContent.split("1 附图:");
							topicContent=tos[0];
						}
				        BbsTopic bbsTopic=new BbsTopic();
				        bbsTopic.setName(topicTitle);
				        bbsTopic.setContent(topicContent);
				        bbsTopic.setPostTime(topicPostTime);
				        bbsTopic.setUserName(topicAuthor);
				        BbsDataGetService bbsDataGetService=new BbsDataGetService();
				        bbsDataGetService.saveBbsTopic(bbsTopic);
				        if (initelements3.size()>2) {
				        	for (int k = 2; k < initelements3.size(); k++) {
				        		System.out.println(topicTitle+"第"+total+"个帖子的回复");
								String topicinfo1 = initelements3.get(k).text();
								String topicAuthor1 = exact(topicinfo1, "发信人:(.*), 信区");
								topicAuthor1=topicAuthor1.replace(" ", "");
								//String topicTitle1 = exact(topicinfo1, "标  题: (.*) 发信站:");
								String topicPostTime1 = exact(topicinfo1, "兵马俑BBS (.*), 本站");
								String topicContent1 = exact(topicinfo1, ", 本站(.*)【 在");
								if (topicContent1.contains("【 ")) {
									tos =topicContent1.split("【 ");
								} 
								topicPostTime1=topicPostTime1.substring(topicPostTime1.indexOf("(")+1, topicPostTime1.indexOf(")"));
								tos =topicContent1.split(" ");
								topicContent1=tos[1];
								BbsReply bbsReply=new BbsReply();
								bbsReply.setTopic(bbsTopic);
								bbsReply.setContent(topicContent1);
								bbsReply.setPostTime(topicPostTime1);
								bbsReply.setUserName(topicAuthor1);
								bbsDataGetService.saveBbsReply(bbsReply);
							}
						}
					}
				} 
			} 
		   /* System.out.println(i+"sssssssss");
		    if (total==480) {
				return;
			}
		    */
		}
	}
	public static String exact(String string,String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(string);
		while (matcher.find()) {
		 string=matcher.group(1);
		}
		return string;
	}
	@Test
	public void testEx()
	{
		Pattern pattern = Pattern.compile(", 本站(.*) 【 ");
		Matcher matcher = pattern.matcher("兵马俑BBS (Tue Oct 20 11:51:03 2015), 本站(bbs.xjtu.edu.cn) 164很高了，穿高跟鞋，175刚好。 【 在 wbwood 的大作中提到: 】 : 身高是硬伤。 : : : ");
		while (matcher.find()) {
		 String string=matcher.group(1);
		 System.out.println(string);
		}
		System.out.println("wu");
	}
}
