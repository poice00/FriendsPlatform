package com.fp.util;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class MessageSend {

    public void messageSend(String phoneNum,String content)throws Exception{
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn"); 
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
        NameValuePair[] data ={ 
	    new NameValuePair("Uid", "anxue3000"),
	    new NameValuePair("Key", "d5f987bed8ff2b21bcb1"),
	    new NameValuePair("smsMob",phoneNum),
	    new NameValuePair("smsText",content)
        };
        post.setRequestBody(data);
        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:"+statusCode);

        for(Header h : headers){
           System.out.println(h.toString());
        }
        String result = new String(post.getResponseBodyAsString().getBytes("gbk")); 
        System.out.println(result);

        post.releaseConnection();
    }
    public String randomFor6() {
		Random random = new Random();
		String result = "";
		for (int i = 0; i < 6; i++) {
			result += random.nextInt(10);
		}
//		System.out.print(result);
		return result;
	}
}
