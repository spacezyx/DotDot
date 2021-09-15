import unittest

from elas import ElasticObj
from qasystem import qasystem
from simple_mrc import simple_mrc
question = '我们成功了多少个单元测试？'
context = '我们的单元测试成功了6个！'


class MyTestCase(unittest.TestCase):
    def test_delete(self):
        obj = ElasticObj()
        self.assertEqual(obj.delete('qadata'), "Delete succeeded!")

    def test_init(self):
        obj = ElasticObj()
        self.assertEqual(obj.init(), "Init succeeded!")

    def test_insert(self):
        obj = ElasticObj()
        new_data = {
            "answers.start_idx": [10],
            "answers.ans_text": ["6个"],
            "context": context,
            "question": question,
            "title": "test"
        }
        data = {'_index': 'qadata',
                '_primary_term': 1,
                '_seq_no': 0,
                '_shards': {'failed': 0, 'successful': 1, 'total': 2},
                '_type': '_doc',
                '_version': 1,
                'result': 'created'}
        self.assertEqual(
            obj.insert('qadata', new_data["answers.start_idx"], new_data["answers.ans_text"], new_data["context"],
                       new_data["question"], new_data["title"])['_shards'], data['_shards'])

    def test_search(self):
        obj = ElasticObj()
        answer = {'_index': 'qadata',
                  '_score': 1.1507283,
                  '_source': {'answers.ans_text': ['6个'],
                              'answers.start_idx': [10],
                              'context': '我们的单元测试成功了6个！',
                              'question': '我们成功了多少个单元测试？',
                              'title': 'test'},
                  '_type': '_doc'}
        question = input()
        self.assertEqual(obj.search('qadata', question)[0]['_source'], answer['_source'])

    def test_simple_mrc(self):
        new_data = {
            "answers.start_idx": [10],
            "answers.ans_text": ['6个'],
            "context": "我们的单元测试成功了6个！",
            "question": "我们成功了多少个单元测试？",
            "title": "test"
        }
        self.assertEqual(simple_mrc(question, context, "test"), new_data)

    def test_qasystem(self):
        question = input()
        self.assertEqual(qasystem(question, context), ['6个'])


if __name__ == '__main__':
    unittest.main()
