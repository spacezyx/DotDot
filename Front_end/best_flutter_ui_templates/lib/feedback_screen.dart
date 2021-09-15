import 'package:best_flutter_ui_templates/app_theme.dart';
import 'package:best_flutter_ui_templates/fitness_app/fitness_app_theme.dart';
import 'package:best_flutter_ui_templates/fitness_app/online_test/design_course_app_theme.dart';
import 'package:flutter/material.dart';

import 'dart:convert';
import 'dart:io';
import 'package:http/http.dart' as http;

class FeedbackScreen extends StatefulWidget {
  @override
  _FeedbackScreenState createState() => _FeedbackScreenState();
}

class _FeedbackScreenState extends State<FeedbackScreen> {
  @override
  void initState() {
    getFeedbacks();
    super.initState();
  }

  List<Widget> listViews = <Widget>[];

  //用user_id去获取这个用户的历史反馈
//目前这个函数在initstate中
  getFeedbacks() async {
    var user = {
      //这里传入现在的用户id 先放个1好测试
      'user_id': 1,
    };
    print(user);
    JsonCodec jc = new JsonCodec();
    Object userbody = jc.encode(user);
    print(userbody);

    var url = "http://10.0.2.2:8080/getFeedbacks";
    var httpClient = new HttpClient();
    var response = await http.post(Uri.parse(url),
        headers: {"Content-Type": "application/json"}, body: userbody);
    Utf8Decoder decode = new Utf8Decoder();
    print(response);
    print(response);
    List tmp = json.decode(decode.convert(response.bodyBytes));
    print(tmp);
    List<Map> map = tmp.map((e) => new Map<String, dynamic>.from(e)).toList();
    print(map.length);
    for (var it in map) {
      print(it['id']);
      print(it['user_id']);
      print(it['content']);
      print(it['timestamp']);
    }
  }

  //新增反馈
//先放了固定的内容 把参数传递进来即可
//目前连接在send按钮上
  addFeedback() async {
    var robot = {
      "user_id": 1,
      "content": "zengyuxin真帅啊",
      "timestamp": "2021-04-11"
    };
    print(robot);
    JsonCodec jc = new JsonCodec();
    Object robotbody = jc.encode(robot);
    print(robotbody);
    String msg = "";

    var url = "http://10.0.2.2:8080/addFeedback";
    var httpClient = new HttpClient();
    var response = await http.post(Uri.parse(url),
        headers: {"Content-Type": "application/json"}, body: robotbody);
    Utf8Decoder decode = new Utf8Decoder();
    print(response);
    String t = await json.decode(json.encode(response.body));
    print(t);
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      color: AppTheme.nearlyWhite,
      child: SafeArea(
        top: false,
        child: Scaffold(
          backgroundColor: AppTheme.nearlyWhite,
          body: SingleChildScrollView(
            child: SizedBox(
              height: MediaQuery.of(context).size.height,
              child: Column(
                children: <Widget>[
                  // Container(
                  //   padding: EdgeInsets.only(
                  //       top: MediaQuery.of(context).padding.top,
                  //       left: 16,
                  //       right: 16),
                  //   child: Image.asset('assets/images/feedbackImage.png'),
                  // ),
                  Container(
                    padding: const EdgeInsets.only(top: 30),
                    child: Text(
                      'API使用教程',
                      style: TextStyle(
                        fontSize: 20,
                        fontWeight: FontWeight.bold,
                        color: FitnessAppTheme.nearlyDarkBlue,
                      ),
                    ),
                  ),
                  Container(
                    padding: const EdgeInsets.only(top: 8),
                    child: const Text(
                      '接口地址：http://124.70.130.38:8080/qa_system',
                      textAlign: TextAlign.center,
                      style: TextStyle(
                        fontSize: 16,
                      ),
                    ),
                  ),
                  Container(
                    padding: const EdgeInsets.only(top: 8),
                    child: const Text(
                      '支持格式：JSON',
                      textAlign: TextAlign.center,
                      style: TextStyle(
                        fontSize: 16,
                      ),
                    ),
                  ),
                  Container(
                    padding: const EdgeInsets.only(top: 8),
                    child: const Text(
                      '请求方法：GET POST',
                      textAlign: TextAlign.center,
                      style: TextStyle(
                        fontSize: 16,
                      ),
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.only(top: 8),
                    child: Center(
                      child: Container(
                        width: 120,
                        height: 40,
                        decoration: BoxDecoration(
                          color: Colors.blue,
                          borderRadius:
                              const BorderRadius.all(Radius.circular(4.0)),
                          boxShadow: <BoxShadow>[
                            BoxShadow(
                                color: Colors.grey.withOpacity(0.6),
                                offset: const Offset(4, 4),
                                blurRadius: 8.0),
                          ],
                        ),
                        child: Material(
                          color: Colors.transparent,
                          child: InkWell(
                            onTap: () {
                              addFeedback();
                              FocusScope.of(context).requestFocus(FocusNode());
                            },
                            child: Center(
                              child: Padding(
                                padding: const EdgeInsets.all(4.0),
                                child: Text(
                                  // '{status: 101, data: 周点知是上海交通大学的传奇人物！, apimsg: 请求成功}',
                                  '请 求 示 例 ： ',
                                  style: TextStyle(
                                    fontWeight: FontWeight.w500,
                                    color: Colors.white,
                                  ),
                                ),
                              ),
                            ),
                          ),
                        ),
                      ),
                    ),
                  ),
                  Container(
                    padding:
                        const EdgeInsets.only(top: 16, left: 10, right: 30),
                    child: const Text(
                      'http://124.70.130.38:8080/qa_system?' +
                          'question=你是谁&apikey=ExHyw8XR&timestamp=1628211389425&sign=3ded50b91e54b8d5ab173aa205ccd77c',
                      textAlign: TextAlign.center,
                      style: TextStyle(
                        fontSize: 16,
                      ),
                    ),
                  ),
                  // _buildComposer(),
                  Padding(
                    padding: const EdgeInsets.only(top: 16),
                    child: Center(
                      child: Container(
                        width: 120,
                        height: 40,
                        decoration: BoxDecoration(
                          color: Colors.blue,
                          borderRadius:
                              const BorderRadius.all(Radius.circular(4.0)),
                          boxShadow: <BoxShadow>[
                            BoxShadow(
                                color: Colors.grey.withOpacity(0.6),
                                offset: const Offset(4, 4),
                                blurRadius: 8.0),
                          ],
                        ),
                        child: Material(
                          color: Colors.transparent,
                          child: InkWell(
                            onTap: () {
                              addFeedback();
                              FocusScope.of(context).requestFocus(FocusNode());
                            },
                            child: Center(
                              child: Padding(
                                padding: const EdgeInsets.all(4.0),
                                child: Text(
                                  // '{status: 101, data: 周点知是上海交通大学的传奇人物！, apimsg: 请求成功}',
                                  '返 回 示 例 ： ',
                                  style: TextStyle(
                                    fontWeight: FontWeight.w500,
                                    color: Colors.white,
                                  ),
                                ),
                              ),
                            ),
                          ),
                        ),
                      ),
                    ),
                  ),
                  Container(
                    padding:
                        const EdgeInsets.only(top: 16, left: 10, right: 30),
                    child: const Text(
                      'http://124.70.130.38:8080/qa_system?' +
                          'question=你是谁&apikey=ExHyw8XR&timestamp=1628211389425&sign=3ded50b91e54b8d5ab173aa205ccd77c',
                      textAlign: TextAlign.center,
                      style: TextStyle(
                        fontSize: 16,
                      ),
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.only(top: 8, bottom: 10),
                    child: Center(
                      child: Container(
                        width: 120,
                        height: 40,
                        decoration: BoxDecoration(
                          color: Colors.blue,
                          borderRadius:
                              const BorderRadius.all(Radius.circular(4.0)),
                          boxShadow: <BoxShadow>[
                            BoxShadow(
                                color: Colors.grey.withOpacity(0.6),
                                offset: const Offset(4, 4),
                                blurRadius: 8.0),
                          ],
                        ),
                        child: Material(
                          color: Colors.transparent,
                          child: InkWell(
                            onTap: () {
                              addFeedback();
                              FocusScope.of(context).requestFocus(FocusNode());
                            },
                            child: Center(
                              child: Padding(
                                padding: const EdgeInsets.all(4.0),
                                child: Text(
                                  // '{status: 101, data: 周点知是上海交通大学的传奇人物！, apimsg: 请求成功}',
                                  '错 误 码 参 照 ： ',
                                  style: TextStyle(
                                    fontWeight: FontWeight.w500,
                                    color: Colors.white,
                                  ),
                                ),
                              ),
                            ),
                          ),
                        ),
                      ),
                    ),
                  ),

                  Table(
                    //所有列宽
                    columnWidths: const {
                      //列宽
                      0: FixedColumnWidth(180.0),
                      1: FixedColumnWidth(180.0),
                    },

                    //表格边框样式
                    border: TableBorder.all(
                      color: FitnessAppTheme.nearlyDarkBlue,
                    ),
                    children: [
                      TableRow(children: [
                        //增加行高
                        SizedBox(
                            height: 25.0,
                            child: Text(
                              '代号',
                              style: TextStyle(fontWeight: FontWeight.bold),
                            )),

                        Text(
                          '说明',
                          style: TextStyle(fontWeight: FontWeight.bold),
                        )
                      ]),
                      TableRow(children: [
                        Text('101'),
                        Text('APIKEY为空或不存在'),
                      ]),
                      TableRow(children: [
                        Text('102'),
                        Text('APIKEY已过期'),
                      ]),
                      TableRow(children: [
                        Text('103'),
                        Text('签名错误'),
                      ]),
                      TableRow(children: [
                        Text('104'),
                        Text('请求超过次数限制'),
                      ]),
                      TableRow(children: [
                        Text('105'),
                        Text('发生未知错误'),
                      ]),
                      TableRow(children: [
                        Text('106'),
                        Text('签名过期'),
                      ]),
                      TableRow(children: [
                        Text('108'),
                        Text('接口已停用'),
                      ]),
                    ],
                  ),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }

  // Widget _buildComposer() {
  //   return Padding(
  //     padding: const EdgeInsets.only(top: 16, left: 32, right: 32),
  //     child: Container(
  //       decoration: BoxDecoration(
  //         color: AppTheme.white,
  //         borderRadius: BorderRadius.circular(8),
  //         boxShadow: <BoxShadow>[
  //           BoxShadow(
  //               color: Colors.grey.withOpacity(0.8),
  //               offset: const Offset(4, 4),
  //               blurRadius: 8),
  //         ],
  //       ),
  //       child: ClipRRect(
  //         borderRadius: BorderRadius.circular(25),
  //         child: Container(
  //           padding: const EdgeInsets.all(4.0),
  //           constraints: const BoxConstraints(minHeight: 80, maxHeight: 160),
  //           color: AppTheme.white,
  //           child: SingleChildScrollView(
  //             padding:
  //                 const EdgeInsets.only(left: 10, right: 10, top: 0, bottom: 0),
  //             child: TextField(
  //               maxLines: null,
  //               onChanged: (String txt) {},
  //               style: TextStyle(
  //                 fontFamily: AppTheme.fontName,
  //                 fontSize: 16,
  //                 color: AppTheme.dark_grey,
  //               ),
  //               cursorColor: Colors.blue,
  //               decoration: InputDecoration(
  //                 border: InputBorder.none,
  //                 hintText: 'http://124.70.130.38:8080/qa_system?' +
  //                     'question=你是谁&apikey=ExHyw8XR&timestamp=1628211389425&sign=3ded50b91e54b8d5ab173aa205ccd77c',
  //               ),
  //             ),
  //           ),
  //         ),
  //       ),
  //     ),
  //   );
  // }
}
