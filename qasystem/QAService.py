# -*- coding:utf-8 -*-

import json

from flask import Flask, request

from qasystem import qasystem
from simple_mrc import simple_mrc
from simple_search import simple_search

app = Flask(__name__)

BASE_URL = '/api/'


# qasystem
@app.route(BASE_URL + 'get/qasystem', methods=['GET'])
def get_qasystem():
    param = request.args.to_dict()
    question = param['question']
    return qasystem(question)


@app.route(BASE_URL + 'post/qasystem', methods=['POST'])
def post_qasystem():
    data = request.get_data()
    json_data = json.loads(data.decode("utf-8"))
    question = json_data['question']
    return qasystem(question)


# search
@app.route(BASE_URL + 'get/search', methods=['GET'])
def get_search():
    param = request.args.to_dict()
    question = param['question']
    return simple_search(question)


@app.route(BASE_URL + 'post/search', methods=['POST'])
def post_search():
    data = request.get_data()
    json_data = json.loads(data.decode("utf-8"))
    question = json_data['question']
    return simple_search(question)


# mrc
@app.route(BASE_URL + 'get/mrc', methods=['GET'])
def get_mrc():
    param = request.args.to_dict()
    question = param['question']
    title = "Unnamed"
    return simple_mrc(question, title)


@app.route(BASE_URL + 'post/mrc', methods=['POST'])
def post_mrc():
    data = request.get_data()
    json_data = json.loads(data.decode("utf-8"))
    question = json_data['question']
    title = "Unnamed"
    return simple_mrc(question, title)


if __name__ == '__main__':
    app.run(debug=True)
