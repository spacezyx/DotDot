# -*- coding:utf-8 -*-

import jsonlines
from transformers import pipeline, AutoModelForQuestionAnswering, BertTokenizer

from elas import ElasticObj
from elass import ElasticObjj

# 文件夹下还提供了另一种模型可以进行替换
tokenizer = BertTokenizer.from_pretrained(
    f'/usr/dotdot/qasystem/roberta-base-chinese-extractive-qa')
model = AutoModelForQuestionAnswering.from_pretrained(
    f'/usr/dotdot/qasystem/roberta-base-chinese-extractive-qa')
QA = pipeline('question-answering', model=model, tokenizer=tokenizer)


def simple_mrc(question, title):
    obj = ElasticObj()
    objj = ElasticObjj()
    res = objj.search('context', question)
    context = res[0]['_source']['context']
    result = QA(question=question, context=context)
    context = context[int(result['start']) - 32:int(result['end']) + 32]
    with jsonlines.open('./train.jsonl', mode='a') as w:
        new_data = {
            "answers.start_idx": [result['start']],
            "answers.ans_text": [result['answer']],
            "context": context,
            "question": question,
            "title": title
        }
        w.write(new_data)
    obj.insert('qadata', new_data["answers.start_idx"], new_data["answers.ans_text"], new_data["context"],
               new_data["question"], new_data["title"])
    print(new_data["answers.ans_text"][0])
    return new_data["answers.ans_text"][0]
