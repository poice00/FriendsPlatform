# coding=utf-8
import HTMLParser  
import urlparse  
import urllib  
import urllib2  
import cookielib  
import string  
import re  
import MySQLdb,os,uuid
from pip._vendor.distlib.version import Matcher
  
    
def getInfo(infoUrl,opener):
    #利用cookie请求访问另一个网址，此网址是成绩查询网址
    #请求访问信息查询网址
        result = opener.open(infoUrl)
        return result
#获取红娘   
def getContent(html):
    reg=r'<li>.+?<a href="(.+?)".+?<img src="(.+?)".+?class="icon2".+?>(.+?)</a>'
    contentre=re.compile(reg,re.DOTALL)
    contentlist=contentre.findall(html)
    return contentlist
#获取详细资料    
def getInfomation(html):
    reg=r'<div class="content">.+?<img src="(.+?)".+?<h4>(.+?)<span>(.+?)</span>.+?</span>(.+?)</p>.+?</span>(.+?)</p>.+?</span>(.+?)</p>.+?<div class="text">(.+?)</div>.+?<div class="experience">.+?<p>(.+?)</p>.+?<div class="motto">.+?<p>(.+?)</p>.+?<div class="successCase">.+?<img src="(.+?)".+?<a href="#">(.+?)</a>.+?<img src="(.+?)".+?<h5>(.+?)<span>(.+?)</span>.+?<span>(.+?)</span>.+?<span>(.+?)</span>.+?<img src="(.+?)".+?<h5>(.+?)<span>(.+?)</span>.+?<span>(.+?)</span>.+?<span>(.+?)</span>.+?<p>(.+?)</p>'
    #reg=r'.+?itemprop="summary">(.+?)</span>.+?class="hidden-xs".+?</span>(.+?)<'
    contentre=re.compile(reg,re.DOTALL)
    infomationlist=contentre.findall(html)
    return infomationlist
#下载图片
def saveImg(imgurl):
    imgName = imgurl.split("/")[-1]
    filename = 'F:/eclipse_workspace_J2EE/FriendsPlatform/src/main/webapp/resources/img/matchMaker/' + imgName
    #filename = os.path.join(src_dir,imgName)
    data = urllib.urlopen(imgurl).read()
    
    f = file(filename.decode('utf-8'),'wb')  
    f.write(data)  
    f.close()
    print '图片下载成功!'
def getUUID():
    return uuid.uuid1()
def saveToMySql(infomationlist,matcherId):
    conn=MySQLdb.connect(host='localhost',user='root',passwd='123',db='friendsplatform',port=3306,charset='utf8')
    cur=conn.cursor()
    sql = "insert into matchmaker(id,img,name,job,workTime,phone,serviceTime,intro,experience,motto) \
            values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)" 
    for info in infomationlist:
            imgurl = info[0].strip()
            imgName = imgurl.split("/")[-1]
            saveUrl = '/resources/img/matchMaker/' + imgName
            #保存数据库
            param = (matcherId,saveUrl,info[1].strip(),info[2].strip(),info[3].strip(),info[4].strip(),info[5].strip(),info[6].strip(),info[7].strip(),info[8].strip()) 
            cur.execute(sql,param)
            conn.commit()
            print '数据库matchmaker插入成功!'
            print '...................................................'
    cur.close()
    conn.close()
    

def display(infomationlist):
    #print infomationlist
    for info in infomationlist:
        print '图片 : ',info[0].strip()
        print '姓名: ',info[1].strip()
        print '职位 : ',info[2].strip()
        print '从业时间 : ',info[3].strip()
        print '咨询热线 : ',info[4].strip()
        print '服务时间: ',info[5].strip()
        print '简介  : ',info[6].strip()
        
        print '心得资历 : ',info[7].strip()
        print '座右铭 : ',info[8].strip()
        print '成功案例。。。。。'
        print '成功案例图片 : ',info[9].strip()
        print '小提示 : ',info[10].strip()
        print '女方.....'
        print '图片 : ',info[11].strip()
        print '姓名 : ',info[12].strip()
        print '年龄 : ',info[13].strip()
        print '年: ',info[14].strip()
        print '月: ',info[15].strip()
        print '男方.....'
        print '图片 : ',info[16].strip()
        print '姓名 : ',info[17].strip()
        print '年龄 : ',info[18].strip()
        print '年: ',info[19].strip()
        print '月: ',info[20].strip()
        print '红娘寄语 : ',info[21].strip()
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
def saveUrl(imgurl):
    imgName = imgurl.split("/")[-1]
    saveUrl = '/resources/img/matchMaker/' + imgName
    return saveUrl;
def saveToMyCaseSql(infomationlist,matcherId):
    conn=MySQLdb.connect(host='localhost',user='root',passwd='123',db='friendsplatform',port=3306,charset='utf8')
    cur=conn.cursor()
    sql = "insert into successcase(matchMakerId,pic,tip,femalePic,femaleName,femaleAge,femaleYear,femaleMonth,\
            malePic,maleName,maleAge,maleYear,maleMonth,matchMaketips)\
            values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
    for info in infomationlist:
        pic = saveUrl(info[9].strip())
        femalePic = saveUrl(info[11].strip())
        malePic = saveUrl(info[16].strip())
        param = (matcherId,pic,info[10].strip(),femalePic,info[12].strip(),info[13].strip(),info[14].strip(),info[15].strip(),malePic,info[17].strip(),info[18].strip(),info[19].strip(),info[20].strip(),info[21].strip()) 
        cur.execute(sql,param)
        conn.commit()
        print '数据库successcase插入成功!'
        print '...................................................'
    cur.close()
    conn.close()
if __name__=="__main__":
    url = 'http://vip.baihe.com/matchmaker/index.html'
    html=getHtml(url)
    contentlist = getContent(html)
    i = 1 
    for content in contentlist:
        matcherId = getUUID()
        print i,'.........................'
        print 'url : ' ,content[0].strip()
        print 'img : ' ,content[1].strip()
        print 'name : ' ,content[2].strip()
        saveImg(content[1].strip())
        
        #detailUrl = 'http://vip.baihe.com/matchmaker/'+content[0].strip()
        #infoHtml = getHtml(detailUrl)
        #infomationList = getInfomation(infoHtml)
        #saveToMySql(infomationList,matcherId)
        #saveToMyCaseSql(infomationList,matcherId)
        #for info in infomationList:
            #print info[0].strip()
            #saveImg(info[0].strip())
            #saveImg(info[9].strip())
            #saveImg(info[11].strip())
            #saveImg(info[16].strip())
        i+=1
    print 'len : ' ,len(contentlist)
    #info = getInfo(loginUrl,infoUrl,loginName,pwd).read()
    #print info
    
    
    
    
    
    
    
    
    