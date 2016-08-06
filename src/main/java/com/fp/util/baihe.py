# coding=utf-8
import HTMLParser  
import urlparse  
import urllib  
import urllib2  
import cookielib  
import string  
import re  
import MySQLdb,os
'''
    <li><span class="ranking">        
        <span uid:typeicon="117893724" class="typeIcon"></span>    </span>    
        <div class="pic" id="pic_117893724" onmouseover="show_div(117893724,0,true)" onmouseout="show_div(117893724,1,true)">        
            <a target="_blank" onclick="recordBhtg(&#39;search0result0s1_1&amp;rvCode=search0result0s1_117893724&#39;);" href="http://profile.baihe.com/new/BasicInfo.action?oppId=117893724&showType=2012">        
            <img id="img_117893724" alt="" src="./【新消息】百合网·爱情搜索_files/EDC1EB05813A02A59FD9CC3A6396719B.jpg" width="120px"></a>        
            <div class="stamp_icon" style="left:0; top:0;" stamp:userid="117893724" stamp:size="1" stamp:platform="w"></div>        
            <div class="upBg" style="display: none;" id="fudong0_117893724"></div>        
            <span id="fudongC_117893724">        
            <div class="upCont wIcon" style="display: none;" id="fudong1_117893724" onclick="slightCommunicateAjax(117893724)">有感觉</div>        
            </span>    
        </div> 
        <p><span class="os_icon" os:userid="117893724">
        <img style="border:0" src="./【新消息】百合网·爱情搜索_files/mobile_icon.gif" alt="用户手机在线，可通过手机收到您发送的消息通知" title="用户手机在线，可通过手机收到您发送的消息通知"></span>    
        <a target="_blank" onclick="recordBhtg(&#39;search0result0s1_1&amp;rvCode=search0result0s1_117893724&#39;);"
         href="http://profile.baihe.com/new/BasicInfo.action?oppId=117893724&showType=2012">
                    菟菟</a>        
        <span class="ra_icon" ra:userid="117893724" time_="1445132172954"><span class="realName"><img width="16" height="16" alt="实名认证" src="./【新消息】百合网·爱情搜索_files/name_icon.gif"><span class="realPop" id="realPop_mulit_117893724_1445132172954" style="display:none;" onmouseout="$(&quot;#realPop_mulit_117893724_1445132172954&quot;).hide();">实名用户：<b>陈女士</b><br><a id="realPop_mulitA_117893724_1445132172954" class="realBtn" style="display:block;cursor:pointer">查看实名信息</a><img width="17" height="12" alt="" src="./【新消息】百合网·爱情搜索_files/search_arrow.gif" style="position:absolute; top:75px; left:47px;">  </span> <span class="namePop" id="realInfoShowDiv_117893724_1445132172954" style="display:none;"><img width="17" height="12" alt="" src="./【新消息】百合网·爱情搜索_files/search_arrow.gif" style="position:absolute; top:155px; *top:151px; left:47px;"></span> </span></span>    </p>    
        <p class="intro">29，陕西咸阳，大专</p>    <div class="btnCont">    <div class="btnGray21"><a href="javascript:newSayHi(117893724,1020010003,2011,true)">打招呼</a></div>    <div class="btnYellow21"><a href="http://ms.baihe.com/send?toid=117893724&showType=2013" target="_blank">发消息</a></div>    </div>
    </li>
  '''
  
def makeCookies(loginUrl,loginName,pwd):
    #声明一个MozillaCookieJar对象实例来保存cookie，之后写入文件
    cj = cookielib.LWPCookieJar()  
    cookie_support = urllib2.HTTPCookieProcessor(cj)  
    opener = urllib2.build_opener(cookie_support, urllib2.HTTPHandler)  
    postdata = urllib.urlencode({
                'txtLoginEMail' : loginName, 
                'txtLoginPwd' : pwd
            })
    #模拟登录，并把cookie保存到变量
    result = opener.open(loginUrl,postdata)
    print 'make cookies..'
    return opener
    
def getInfo(infoUrl,opener):
    #利用cookie请求访问另一个网址，此网址是成绩查询网址
    #请求访问信息查询网址
        result = opener.open(infoUrl)
        return result
#获取用户列表    
def getContent(html):
    reg=r'.+?<li.+?<div class="pic".+?<a.+?href="(.+?)".+?src="(.+?)".+?<p class="intro">(.+?)</p>'
    contentre=re.compile(reg,re.DOTALL)
    contentlist=contentre.findall(html)
    return contentlist
#获取详细资料    
def getInfomation(html):
    reg=r'<div class="name">.+?</span>(.*?)&.+?<div class="data">.+?<span>(.*?)ID.+?</span>(.+?)</dt>.+?</span>(.+?)</dt>.+?<p>(.+?)\<span class="cbd">/</span>(.+?)<span class="cbd">/</span>(.+?)<span class="cbd">/</span>(.+?)<span class="cbd">/</span>(.+?)</p>.+?<div class="intr">(.+?)</div>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<h3>家庭状况.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>.+?</span>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<h3>择偶意向.+?<dd.*?>(.*?)<.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.*?)<.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>.+?<dd.*?>(.+?)</dd>'
    #reg=''
    contentre=re.compile(reg,re.DOTALL)
    infomationlist=contentre.findall(html)
    return infomationlist
#获取用户标签信息   
def getTags(html):
    reg=r'<div class="inter label">(.+?)</div>'
    contentre=re.compile(reg,re.DOTALL)
    result=contentre.findall(html)
    taglist=''
    if result is not None:
        reg=r'<span>(.+?)</span>'
        contentre=re.compile(reg,re.DOTALL)
        for res in result:
            taglist=contentre.findall(res)
    return taglist
#下载图片
def saveImg(imgurl):
    imgName = imgurl.split("/")[-1]
    src_dir = 'F:/eclipse_workspace_J2EE/FriendsPlatform/src/main/webapp/resources/img/'
    filename = os.path.join(src_dir,imgName)
    data = urllib.urlopen(imgurl).read()  
    f = file(filename,"wb")  
    f.write(data)  
    f.close()
    print '图片下载成功!'
    
def saveToMySql(imgurl,infomationlist):
    conn=MySQLdb.connect(host='localhost',user='root',passwd='123',db='friendsplatform',port=3306,charset='utf8')
    cur=conn.cursor()
    sql = "insert into info(headImg,nickName,realName,realNameID,loveType,age,\
            height,education,city,Ismarraied,introduction,liveCity,nation,homeCity,\
            animal,astroLogical,bloodType,bodyType,weigth,job,salary,house,car,lauauge,school,\
            majorDirection,religion,liveStyle,children,selfScore,IsSmoke,IsDrink,\
            companyType,companyIndustry,jobState,familyRank,parentLive,fatherJob,\
            motherJob,parentIncome,parentHealthInsure,IsLiveParent,whenMarry,needChild,\
            AppointmentStyle,hopeImportant,expectWedding,cook,hourseHold,mateName,\
            mateHeight,mateEduction,mateSalary,mateLive,mateMarried,mateHourse,mateIsChildren,sex\
            ) values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,\
            %s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,\
            %s,%s,%s,%s,%s,%s,%s,%s)" 
    for info in infomationlist:
            print imgurl;
            imgName = imgurl.split("/")[-1]
            saveUrl = '/resources/img/' + imgName
            #保存数据库
            param = (saveUrl,info[0].strip(),info[1].strip(),info[2].strip(),info[3].strip(),info[4].strip(),info[5].strip(),info[6].strip(),info[7].strip(),info[8].strip(),info[9].strip(),info[10].strip(),info[11].strip(),info[12].strip(),info[13].strip(),info[14].strip(),info[15].strip(),info[16].strip(),info[17].strip(),info[18].strip(),info[19].strip(),info[20].strip(),info[21].strip(),info[22].strip(),info[23].strip(),info[24].strip(),info[25].strip(),info[26].strip(),info[27].strip(),info[28].strip(),info[29].strip(),info[30].strip(),info[31].strip(),info[32].strip(),info[33].strip(),info[34].strip(),info[35].strip(),info[36].strip(),info[37].strip(),info[38].strip(),info[39].strip(),info[40].strip(),info[41].strip(),info[42].strip(),info[43].strip(),info[44].strip(),info[45].strip(),info[46].strip(),info[47].strip(),info[48].strip(),info[49].strip(),info[50].strip(),info[51].strip(),info[52].strip(),info[53].strip(),info[54].strip(),info[55].strip(),0) 
            cur.execute(sql,param)
            conn.commit()
            print '数据库插入成功!'
            print '...................................................'
    cur.close()
    conn.close()
    

def display(infomationlist,taglist):
    #for content in contentlist:
        #values=dict(poster=content[0],title=content[1],rating=content[2],ticket_btn=content[3])
        for info in infomationlist:
            #print 'address','\t',content[0].strip()
            #print 'img','\t',content[1].strip()
            #print 'info','\t',content[2].strip()
            
            #for tag in taglist:
               #print '标签 : ',tag
            print '昵称: ',info[0].strip()
            print '姓名 : ',info[1].strip()
            print 'ID : ',info[2].strip()
            print '恋爱类型 : ',info[3].strip()
            print '年龄: ',info[4].strip()
            print '身高 : ',info[5].strip()
            print '学历 : ',info[6].strip()
            print '户口: ',info[7].strip()
            print '婚否: ',info[8].strip()
            print '交友宣言 : ',info[9].strip()
            
            print '........个人及工作状况.......'
            print '居住地: ',info[10].strip()
            print '民族 : ',info[11].strip()
            print '家乡 : ',info[12].strip()
            print '属相 : ',info[13].strip()     
            print '星座 : ',info[14].strip()
            print '血型 : ',info[15].strip()
            print '体型 : ',info[16].strip()
            print '体重 : ',info[17].strip()
            print '职业 : ',info[18].strip()
            print '月薪 : ',info[19].strip()
            print '购房 : ',info[20].strip()
            print '购车 : ',info[21].strip()
            print '掌握语言 : ',info[22].strip()
            print '毕业学校 : ',info[23].strip()
            print '所学专业: ',info[24].strip()
            print '宗教信仰: ',info[25].strip()
            print '生活作息: ',info[26].strip()
            print '有无子女: ',info[27].strip()
            print '相貌自评: ',info[28].strip()
            print '是否吸烟: ',info[29].strip()
            print '是否喝酒: ',info[30].strip()
            print '公司性质: ',info[31].strip()
            print '公司行业: ',info[32].strip()
            print '工作状况: ',info[33].strip()
            
            print '........家庭情况&爱情规划........'
            print '家中排行: ',info[34].strip()
            print '父母情况: ',info[35].strip()
            print '父亲工作: ',info[36].strip()
            print '母亲工作: ',info[37].strip()
            print '父母经济: ',info[38].strip()
            print '父母医保: ',info[39].strip()
            print '愿与长辈同住: ',info[40].strip()
            print '想何时结婚: ',info[41].strip()
            print '是否想要小孩: ',info[42].strip()
            print '偏爱约会方式: ',info[43].strip()
            print '希望对方看重: ',info[44].strip()
            print '期待婚礼形式: ',info[45].strip()
            print '厨艺状况: ',info[46].strip()
            print '家务分工: ',info[47].strip()
            
            print '........择偶意向........'
            print '年龄: ',info[48].strip()
            print '身高: ',info[49].strip()
            print '学历: ',info[50].strip()
            print '月收入: ',info[51].strip()
            print '所在地区: ',info[52].strip()
            print '婚姻状况: ',info[53].strip()
            print '购房情况: ',info[54].strip()
            print '有无子女: ',info[55].strip()
            print'..............................................................................' 
            break   
             
if __name__=="__main__":

    loginName='18793786893' 
    pwd = 'flb123'
    loginUrl = 'http://my.baihe.com/login/gotoLogin'
    print 'begin..'
    opener = makeCookies(loginUrl,loginName,pwd)
    file_object = open('C:/Users/ssy/Desktop/bj/3.html')
    try:
        all_the_text = file_object.read()
        contentlist=getContent(all_the_text)
        i = 1
        for content in contentlist:
            print i,"..............................."
            
            infoUrl = content[0].strip()
            print 'url : ',infoUrl
            print 'img : ',content[1].strip()
            print 'infos : ',content[2].strip()
            try:
                info = getInfo(infoUrl,opener).read()
                infomationlist = getInfomation(info)
                #saveImg(content[1].strip())
                if infomationlist:
                    saveToMySql(content[1].strip(),infomationlist)
                else:
                    continue
            except MySQLdb.Error,e:
                print "Mysql Error %d: %s" % (e.args[0], e.args[1])
            #taglist = getTags(info)
            #display(infomationlist,taglist)
            i+=1
        print len(contentlist)
    finally:
        file_object.close()
    #info = getInfo(loginUrl,infoUrl,loginName,pwd).read()
    #print info
    
    
    
    
    
    
    
    
    