# coding=utf-8
import HTMLParser  
import urlparse  
import urllib  
import urllib2  
import cookielib  
import string  
import re  
import MySQLdb,os,uuid
  
    
def getInfo(infoUrl,opener):
    #利用cookie请求访问另一个网址，此网址是成绩查询网址
    #请求访问信息查询网址
        result = opener.open(infoUrl)
        return result
#获取活动列表    
def getContent(html):
    reg=r'<td class="title">.+?<a href="(.+?)".+?<td.*?>.+?<td.*?>(.+?)</td>'
    contentre=re.compile(reg,re.DOTALL)
    contentlist=contentre.findall(html)
    return contentlist
def getTitle(html):
    reg='<div id="content">.+?<h1>(.+?)</h1>.+?<span class="from">.+?<a.+?>(.+?)</a>.+?<span class="color-green">(.+?)</span>.+?<div class="topic-content">.+?<p>(.+?)</p>'
    contentre=re.compile(reg,re.DOTALL)
    title=contentre.findall(html)
    return title
#获取详细资料    
def getInfomation(html):
    reg=r'<div class="bg-img-green">.+?class="">(.+?)</a>.+?class="pubtime">(.+?)</span>.+?<p class="">(.+?)</p>'
    #reg=r'.+?itemprop="summary">(.+?)</span>.+?class="hidden-xs".+?</span>(.+?)<'
    contentre=re.compile(reg,re.DOTALL)
    infomationlist=contentre.findall(html)
    return infomationlist
def saveToMySql(title,titleId):
    conn=MySQLdb.connect(host='localhost',user='root',passwd='123',db='friendsplatform',port=3306,charset='utf8')
    cur=conn.cursor()
    sql = "insert into topic(id,title,author,time,content)\
            values(%s,%s,%s,%s,%s)"
    for t in title:
        param = (titleId,t[0].strip(),t[1].strip(),t[2].strip(),t[3].strip()) 
        cur.execute(sql,param)
        conn.commit()
        print '数据库插入成功!'
        print '...................................................'
    cur.close()
    conn.close()
def saveToMyReplySql(title,titleId):
    conn=MySQLdb.connect(host='localhost',user='root',passwd='123',db='friendsplatform',port=3306,charset='utf8')
    cur=conn.cursor()
    sql = "insert into reply(topicId,replyer,time,content)\
            values(%s,%s,%s,%s)"
    for info in infomationlist:
        param = (titleId,info[0].strip(),info[1].strip(),info[2].strip()) 
        cur.execute(sql,param)
        conn.commit()
        print '数据库插入成功!'
        print '...................................................'
    cur.close()
    conn.close()
def getUUID():
    return uuid.uuid1()
def getHtml(url):
    response = urllib2.urlopen(url)  
    text = response.read()  
    return text             
if __name__=="__main__":
    a=1
    for i in range(0,5000,25):
        print '================================第',a,"页==================================="
        url = 'http://www.douban.com/group/kaopulove/discussion?start=%s'%i
        html=getHtml(url)
        try:
            contentList = getContent(html)
        except:
                print '连接异常..'
                continue
        for content in contentList:
            url = content[0]
            try:
                replyCount =int(content[1])
            except ValueError:
                print '连接异常..'
                continue
            print 'url : ',url
            print '回复数 :',replyCount
            try:
                infoHtml = getHtml(url)
            except:
                continue
            #获取标题内容
            title = getTitle(infoHtml)
            titleId = getUUID()
            try:
                saveToMySql(title,titleId)
            except MySQLdb.Error,e:
                print "Mysql Error %d: %s" % (e.args[0], e.args[1])
                continue   
            for t in title:
                print '标题id ： ',titleId
                print '标题 ： ',t[0].strip()
                print '作者 ： ' ,t[1].strip()
                print '发布时间 ： ' ,t[2].strip()
                print '内容 ： ' ,t[3].strip()
                print '.................................'
            #pageNum = (replyCount/100 + 1)*100
            #for i in range(0,100,100):
                #print '第',i,'页'
            #pageUrl = content[0]+'?start=%s'%i
            replyHtml = getHtml(url)
            #获取回复
            infomationlist = getInfomation(replyHtml) 
            saveToMyReplySql(infomationlist,titleId)
            for info in infomationlist:
                print  '标题的id ： ',titleId
                print  '回复人 : ' ,info[0].strip()
                print  '回复时间  : ' ,info[1].strip()
                print  '回复内容  : ' ,info[2].strip()
                print '.................................'
            print len(contentList)
        a+=1
    
    
    
    
    
    
    
    