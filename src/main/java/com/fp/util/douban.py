# coding=utf-8
import HTMLParser  
import urlparse  
import urllib  
import urllib2  
import cookielib  
import string  
import re  
import MySQLdb,os
  
    
def getInfo(infoUrl,opener):
    #利用cookie请求访问另一个网址，此网址是成绩查询网址
    #请求访问信息查询网址
        result = opener.open(infoUrl)
        return result
#获取活动列表    
def getContent(html):
    reg=r'<div class="title".+?<a.+?href="(.+?)"'
    contentre=re.compile(reg,re.DOTALL)
    contentlist=contentre.findall(html)
    return contentlist
#获取详细资料    
def getInfomation(html):
    reg=r'<div class="poster">.+?<img.+?src="(.+?)".+?<h1 itemprop="summary">(.+?)</h1>.+?<li class="calendar-str-item ">(.+?)</li>.+?<span itemprop="region" class="micro-address">(.+?)&.+?<span itemprop="street-address" class="micro-address">(.+?)</span>.+?<div class="event-detail">.+?</span>(.+?)</div>.+?itemprop="name">(.+?)</a>.+?class="mod">.+?<a href="(.+?)"'
    #reg=r'.+?itemprop="summary">(.+?)</span>.+?class="hidden-xs".+?</span>(.+?)<'
    contentre=re.compile(reg,re.DOTALL)
    infomationlist=contentre.findall(html)
    return infomationlist
#获取活动图片    
def getImgInfoList(html):
    reg=r'<div class="photo_wrap">.+?<img src="(.+?)"'
    #reg=r'.+?itemprop="summary">(.+?)</span>.+?class="hidden-xs".+?</span>(.+?)<'
    contentre=re.compile(reg,re.DOTALL)
    infomationlist=contentre.findall(html)
    return infomationlist
#下载图片
def saveImg(imgurl):
    imgName = imgurl.split("/")[-1]
    filename = 'F:/eclipse_workspace_J2EE/FriendsPlatform/src/main/webapp/resources/img/douban/' + imgName
    #filename = os.path.join(src_dir,imgName)
    data = urllib.urlopen(imgurl).read()  
    f = file(filename,"wb")  
    f.write(data)  
    f.close()
    print '图片下载成功!'
def saveToSqlImgs(imgList):
   
    conn=MySQLdb.connect(host='localhost',user='root',passwd='123',db='friendsplatform',port=3306,charset='utf8')
    cur=conn.cursor()
    sql = "insert into activityimg(url)\
            values(%s)" 
    for img in imgList:
        #saveImg(img)
        imgName = img.split("/")[-1]
        saveUrl = '/resources/img/douban/' + imgName
        print 'saveUrl : ' , img
        #保存数据库
        param = (saveUrl) 
        cur.execute(sql,param)
        conn.commit()
        print '数据库插入成功!'
        print '...................................................'
    cur.close()
    conn.close()
    
def saveToMySql(infomationlist):
    conn=MySQLdb.connect(host='localhost',user='root',passwd='123',db='friendsplatform',port=3306,charset='utf8')
    cur=conn.cursor()
    sql = "insert into activity(img,title,time,city,address,expense) \
            values(%s,%s,%s,%s,%s,%s)" 
    for info in infomationlist:
            imgurl = info[0].strip()
            imgName = imgurl.split("/")[-1]
            saveUrl = '/resources/img/douban/' + imgName
            #保存数据库
            param = (saveUrl,info[1].strip(),info[2].strip(),info[3].strip(),info[4].strip(),info[5].strip()) 
            cur.execute(sql,param)
            conn.commit()
            print '数据库插入成功!'
            print '...................................................'
    cur.close()
    conn.close()
    

def display(infomationlist):
    #print infomationlist
    for info in infomationlist:
        print 'img : ',info[0].strip()
        print 'title : ',info[1].strip()
        print 'date : ',info[2].strip()
        print 'city : ',info[3].strip()
        print 'address : ',info[4].strip()
        print 'expense : ',info[5].strip()
        print 'user : ',info[6].strip()
        print 'allimgs : ',info[7].strip()
def getHtml(url):
    #构造header，一般header至少要包含一下两项。这两项是从抓到的包里分析得出的。  
    #headers = {
     #  'User-Agent': 'Mozilla/5.0 (Windows NT 6.2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36',
     #  'Accept-Language':  'zh-CN,zh;q=0.8',
     #  'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8',
    #} 
    #request = urllib2.Request(url,'GET', headers)  
    response = urllib2.urlopen(url)  
    text = response.read()  
    return text             
if __name__=="__main__":
    url = 'http://www.douban.com/location/xian/events/week-1407?start=0'
    html=getHtml(url)
    contentlist = getContent(html)
    i = 1
    for content in contentlist:
        print i,"..............................."
        infoUrl = content.strip()
        print 'url : ',infoUrl
        infoHtml = getHtml(infoUrl)
        #print infoHtml
        infomationlist = getInfomation(infoHtml)
        #display(infomationlist)
        #saveToMySql(infomationlist)
        for info in infomationlist:
            #print info[7].strip()
            #saveImg(info[0].strip())
            imgHtml = getHtml(info[7].strip())
            imgList = getImgInfoList(imgHtml)
            #try:
                #saveToSqlImgs(imgList)
            #except MySQLdb.Error,e:
                #print "Mysql Error %d: %s" % (e.args[0], e.args[1])
                #continue
            for img in imgList:
                print '下载图片 : ' , img
                try:
                    saveImg(img)
                except IOError, e:
                    print '下载图片异常..'
                    continue
            
        i+=1
        print 'len : ' ,len(contentlist)
    #info = getInfo(loginUrl,infoUrl,loginName,pwd).read()
    #print info
    
    
    
    
    
    
    
    
    