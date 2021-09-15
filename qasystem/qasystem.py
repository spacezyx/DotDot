# -*- coding:utf-8 -*-

from elas import ElasticObj
from elass import ElasticObjj
from simple_mrc import simple_mrc


def qasystem(question):
    obj = ElasticObj()
    objj = ElasticObjj()
    # print('Loading successful！')
    # while True:
    #	 question = input('Any questions? -> ')
    res = obj.search('qadata', question)
    # 对直接检索得到的答案进行一个相关度判断，考虑是否调用机器阅读理解去查询后台文档
    if (res == [] or res[0]['_score'] < 4):
        title = "Unnamed"
        res = simple_mrc(question, title)
        print(res)
        return res
    else:
        result = res[0]
        print(result['_source']['answers.ans_text'][0])
        return result['_source']['answers.ans_text'][0]
