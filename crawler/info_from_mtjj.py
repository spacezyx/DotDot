import re
import requests
from bs4 import BeautifulSoup
from pyquery import PyQuery
from lxml import etree
import os
import shutil

headers = {
    'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9',
    'Accept-Encoding': 'gzip, deflate',
    'Accept-Language': 'zh-CN,zh;q=0.9',
    'Cache-Control': 'max-age=0',
    'Connection': 'keep-alive',
    'Cookie': 'cZBD_2132_saltkey=JFG36F5m; cZBD_2132_lastvisit=1592714390; Hm_lvt_2504f1c08c3a31e74bbfb16ecaff376b=1592717993; cZBD_2132_adclose_88=1; cZBD_2132_sid=ChElwH; cZBD_2132_st_t=0%7C1592718787%7C7cfe33c6fc8493fe312e796a99ca0dad; cZBD_2132_forum_lastvisit=D_97_1592718341D_83_1592718519D_277_1592718765D_86_1592718787; cZBD_2132_ulastactivity=596177hNivTSB%2FriI9%2FBHBSYoKbt4EeT91XI5SC5R2lZRKqPI5v5; cZBD_2132_auth=f040j2lAGWalqGmrBuzDI4Q9veLHnWl21UOHi031c%2BuUvxUmZx%2FAH5hH7r7pHpWt8L1RyLHPKrol3N69FKxPpDfE8Tg; cZBD_2132_lastcheckfeed=485953%7C1592719011; cZBD_2132_lip=218.92.226.20%2C1592718764; cZBD_2132_nofavfid=1; cZBD_2132_onlineusernum=2271; cZBD_2132_noticeTitle=1; cZBD_2132_ignore_notice=1; Hm_lpvt_2504f1c08c3a31e74bbfb16ecaff376b=1592719183; cZBD_2132_lastact=1592719260%09search.php%09forum',
    'Host': 'cskaoyan.com',
    'Referer': 'http://cskaoyan.com/search.php?mod=forum&searchid=145&orderby=lastpost&ascdesc=desc&searchsubmit=yes&kw=%B4%F3%D1%A7',
    'Upgrade-Insecure-Requests': '1',
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36'
}

findTitle = re.compile(r'<title>(.*?)</title>') #标题
findTime = re.compile(r'class="time">(.*?)</span>') #时间
findText = re.compile(r'Article_content"(.*?)</div>')   #正文
# findText = re.compile(r'<h2 class="Article-title" id="ivs_title">(.*?)</h2>')   #正文

# 如果正则表达式返回为空列表，则返回""，否则返回第0个元素
def getContent(content):
    if content == []:
        return ""
    else:
        return content[0]

def filter_tags(htmlstr):
    #先过滤CDATA
    re_cdata=re.compile('//<!\[CDATA\[[^>]*//\]\]>',re.I) #匹配CDATA
    re_script=re.compile('<\s*script[^>]*>[^<]*<\s*/\s*script\s*>',re.I)#Script
    re_style=re.compile('<\s*style[^>]*>[^<]*<\s*/\s*style\s*>',re.I)#style
    re_br=re.compile('<br\s*?/?>')#处理换行
    re_h=re.compile('</?\w+[^>]*>')#HTML标签
    re_comment=re.compile('<!--[^>]*-->')#HTML注释
    s=re_cdata.sub('',htmlstr)#去掉CDATA
    s=re_script.sub('',s) #去掉SCRIPT
    s=re_style.sub('',s)#去掉style
    s=re_br.sub('\n',s)#将br转换为换行
    s=re_h.sub('',s) #去掉HTML 标签
    s=re_comment.sub('',s)#去掉HTML注释

    #去掉多余的空行
    blank_line=re.compile('\n+')
    s=blank_line.sub('\n',s)
    s=replaceCharEntity(s)#替换实体

    return s

# 爬取网页
def getData(html):
    new_html=(html.text.replace('<br>','')).replace('<br/>','')
    soup = BeautifulSoup(new_html, "html.parser")
    datalist = []
    for item in soup.find_all():  # 查找符合要求的字符串
        data = []  # 保存信息
        temp = soup.find_all("p")  # 1.内容
        length = len(temp)
        textInfo = ""
        for i in range(length):
            st = temp[i].string
            if(st != None):
                textInfo = textInfo + st + "\n"
    titleInfo = getContent(re.findall(findTitle, html.text))  # 0.标题
    timeInfo = getContent(re.findall(findTime, html.text))  # 1.时间
    data.append(titleInfo)
    data.append(timeInfo)
    data.append(textInfo)

    return data

def replaceCharEntity(htmlstr):
    CHAR_ENTITIES={'nbsp':' ','160':' ',
                   'lt':'<','60':'<',
                   'gt':'>','62':'>',
                   'amp':'&','38':'&',
                   'quot':'"','34':'"',}

    re_charEntity=re.compile(r'&#?(?P<name>\w+);')
    sz=re_charEntity.search(htmlstr)
    while sz:
        entity=sz.group()#entity全称，如>
        key=sz.group('name')#去除&;后entity,如>为gt
        try:
            htmlstr=re_charEntity.sub(CHAR_ENTITIES[key],htmlstr,1)
            if(htmlstr==""):
                return ""
            sz=re_charEntity.search(htmlstr)
        except KeyError:
            #以空串代替
            htmlstr=re_charEntity.sub('',htmlstr,1)
            sz=re_charEntity.search(htmlstr)
    return htmlstr

def getallcontent(url,txtname):
    html = requests.get(url)
    html.encoding = 'GBK'
    html.encoding = 'utf-8'
    #news = html.text
    data = getData(html)
    txt = open("./"+path+"/"+path+"-"+txtname+".txt", "a",encoding='utf-8').write("文章标题\n")
    txt = open("./"+path+"/"+path+"-"+txtname+".txt", "a",encoding='utf-8').write(data[0]+"\n")
    txt = open("./"+path+"/"+path+"-"+txtname+".txt", "a",encoding='utf-8').write("发布时间\n")
    txt = open("./"+path+"/"+path+"-"+txtname+".txt", "a",encoding='utf-8').write(data[1]+"\n")
    txt = open("./"+path+"/"+path+"-"+txtname+".txt", "a",encoding='utf-8').write("文章内容\n")
    txt = open("./"+path+"/"+path+"-"+txtname+".txt", "a",encoding='utf-8').write(data[2])


def comp(url):
    html_data = requests.get(url)
    html = html_data.text
    selector = etree.HTML(html)
    return selector

#获取首页所有的标题链接
def getdetailurl(url,details_url):
    selector = comp(url)
    datalist = selector.xpath('//a/@href')
    # datalist = res.xpath('//*[@class="fl_g"]/dl/dt/a/@href').extract()
    for item in datalist:
        new_url =  item
        if('javascript:void(0)' in new_url):
            pass
        if('#' in new_url):
            pass
        if('http' in new_url):
            # details_url.append(new_url)
            pass
        if('index' in new_url):
            pass
        elif('mtjj' in new_url):
            details_url.append("https://news.sjtu.edu.cn"+item)

details_url = []
# datalist = []
cont = 0
path = "mtjj_data"

if __name__=='__main__':

    getdetailurl("https://news.sjtu.edu.cn/mtjj/index.html",details_url)
    isPathExists = os.path.exists(path)
    if(isPathExists):
        shutil.rmtree(path)
        os.makedirs(path)
    if(not isPathExists):
        os.makedirs(path)

    #数据过多 暂时选取前100页新闻数据
    for i in range(2,101):
        getdetailurl("https://news.sjtu.edu.cn/mtjj/index_"+str(i)+".html",details_url)

    for item in details_url:
        cont = cont+1
        print("now content number is ",cont,"   ",str(item))
        getallcontent(item,str(cont))
        pass