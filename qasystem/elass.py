# -*- coding:utf-8 -*-

import os
from elasticsearch import Elasticsearch


class ElasticObjj():
	es = Elasticsearch([{'host': '127.0.0.1', 'port': 9200}])

	# delete function
	def delete(self, index_name='context'):
		self.es.indices.delete(index=index_name)
		return "Delete succeeded!"

	# init function
	def init(self, index_name='context', index_type='context_type'):
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
					"context": {"type": "text",
								"index": True,
								"analyzer": "ik_sync",
								"search_analyzer": "ik_sync_smart"
								}
				}
			}
		}
		self.es.indices.create(index=index_name, body=_index_mappings)
		return "Init succeeded!"

	# insert function
	def insert(self, index_name, flie_path):
		with open(flie_path, "r", encoding='utf-8') as f:
			context = f.read()
		doc = {
			"context": context,
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
							"context": {
								"query": question,
								"minimum_should_match": "90%"
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
								"slop": 9
							}
						}
					}
				}
			}
		})
		return res["hits"]["hits"]


if __name__ == '__main__':
	# 除了while语句以外的代码用于初始化es数据库的context，实质上是对数据库的数据做一个删除，然后将context/中的数据上传。
	obj = ElasticObjj()
	obj.delete()
	obj.init()
	rootdir = "/usr/dotdot/qasystem/context/"
	paths = []
	n = 0
	for root, dirs, files in os.walk(rootdir):
		for file in files:
			paths.append(os.path.join(root, file).encode('gbk').decode('gbk'))
	for i in paths:
		obj.insert('context', i)
		n = n + 1
	print(n, 'context insert succeeded!')
	# while True:
	#     question = input('Any questions? -> ')
	#     res = obj.search('context', question)
	#     for item in res[:3]:
	#         print(item['_score'], item['_source'])
