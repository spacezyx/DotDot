# -*- coding:utf-8 -*-

from elas import ElasticObj


def simple_search(question):
    obj = ElasticObj()
    res = obj.search('qadata', question)
    if (res == [] or res[0]['_score'] < 4):
        print("82146974")
        return "82146974"
    else:
        result = res[0]
        print(result['_source']['answers.ans_text'][0])
        return result['_source']['answers.ans_text'][0]
