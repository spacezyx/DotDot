# -*- coding:utf-8 -*-

import jsonlines
from elasticsearch import Elasticsearch


class ElasticObj():
	es = Elasticsearch([{'host': '127.0.0.1', 'port': 9200}])

	# delete function
	def delete(self, index_name='qadata'):
		self.es.indices.delete(index=index_name)
		return "Delete succeeded!"

	# init function
	def init(self, index_name='qadata', index_type='qadata_type'):
		_index_mappings = {
			"settings": {
				"analysis": {
					"filter": {
							"word_sync": {
								"type": "synonym",
								"synonyms_path": "analysis/synonyms.txt"
							}
					},
					"analyzer": {
						"ik_sync": {
							"filter": [
								"word_sync"
							],
							"type": "custom",
							"tokenizer": "ik_max_word"
						},
						"ik_sync_smart": {
							"filter": [
								"word_sync"
							],
							"type": "custom",
							"tokenizer": "ik_smart"
						}
					}
				}	
			},
			"mappings": {
				"properties": {
					"answers.start_idx": {"type": "integer"
										  },
					"answers.ans_text": {"type": "keyword"
										 },
					"context": {"type": "keyword"
								},
					"question": {"type": "text",
								 "index": True,
								 "analyzer": "ik_sync",
								 "search_analyzer": "ik_sync_smart"
								 },
					"title": {"type": "keyword",
							  "index": True
							  }
				}
			}
		}
		self.es.indices.create(index=index_name, body=_index_mappings)
		return "Init succeeded!"

	# insert function
	def insert(self, index_name, start_idx, ans_text, context, question, title):
		doc = {
			"answers.start_idx": start_idx,
			"answers.ans_text": ans_text,
			"context": context,
			"question": question,
			"title": title
		}
		res = self.es.index(index=index_name, doc_type="_doc", body=doc)
		return res

	# search function
	def search(self, index_name, question):
		res = self.es.search(index=index_name, doc_type="_doc", body=
		{
			"query": {
				"bool": {
					"must": {
						"match": {
							"question": {
								"query": question,
								"minimum_should_match": "95%"
							}
						}
					}
				}
			},
			"rescore": {
				"window_size": 3,
				"query": {
					"rescore_query": {
						"match_phrase": {
							"title": {
								"query": "question",
								"slop": 3
							}
						}
					}
				}
			}
		})
		return res["hits"]["hits"]


if __name__ == '__main__':
	# 除了while语句以外的代码用于初始化es数据库的qadata，实质上是对数据库的数据做一个删除，然后将train.jsonl中的数据上传，在运行程序前，请先到train.jsonl进行修改与删除。
	obj = ElasticObj()
	obj.delete()
	obj.init()
	with jsonlines.open('./train.jsonl', mode='r') as r:
		for line in r:
			obj.insert('qadata', line["answers.start_idx"], line["answers.ans_text"], line["context"], line["question"],
					   line["title"])
	# while True:
	#     question = input('Any questions? -> ')
	#     res = obj.search('qadata', question)
	#     for item in res:
	#         if item['_score'] < 4:
	#             print ('No more answers.\n')
	#             break
	#         print(item['_score'], item['_source']['answers.ans_text'][0])
