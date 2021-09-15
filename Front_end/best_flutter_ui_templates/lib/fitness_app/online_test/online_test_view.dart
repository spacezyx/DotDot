import 'dart:convert';
import 'dart:io';

import 'package:http/http.dart' as http;

import 'package:best_flutter_ui_templates/fitness_app/online_test/course_info_screen.dart';
import 'package:best_flutter_ui_templates/fitness_app/online_test/popular_course_list_view.dart';
import 'package:best_flutter_ui_templates/fitness_app/online_test/robot_list_view.dart';
import 'package:best_flutter_ui_templates/main.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:fluttertoast/fluttertoast.dart';
import '../../app_theme.dart';
import '../fitness_app_theme.dart';
import 'design_course_app_theme.dart';

class OnlineTestView extends StatefulWidget {
  const OnlineTestView({Key? key, this.userid});
  final userid;
  @override
  _OnlineTestViewState createState() => _OnlineTestViewState();
}

class _OnlineTestViewState extends State<OnlineTestView> {
  final _questionController = TextEditingController();
  final _URLController = TextEditingController();
  final focusquestion = FocusNode();
  final focusURL = FocusNode();

  var question_map;
  var URL_map;

  List<Map> inf = [
    {'content-type': '', 'date': '', 'transfer-encoding': ''},
    {'status': '', 'data': '', 'apimsg': ''}
  ];
  var URL;
  bool ret_enabled = false;

  var now_robot = {
    'name': '',
    'apikey': '',
    'apisecret': '',
  };

  List<Map>? RobotList;

  send_question() async {
    print('send_question');
    print(now_robot['apikey']);

    var msg = {
      'question': _questionController.text,
      'apikey': now_robot['apikey'],
      'apisecret': now_robot['apisecret'],
    };
    JsonCodec jc = new JsonCodec();
    Object msgbody = jc.encode(msg);
    print(msgbody);

    var url = "http://124.70.130.38:8080/getUrlForQa_system";
    var httpClient = new HttpClient();
    var response = await http.post(Uri.parse(url),
        headers: {"Content-Type": "application/json"}, body: msgbody);
    Utf8Decoder decode = new Utf8Decoder();
    print(response);
    var map = response.body; //json.decode(decode.convert(response.bodyBytes));
    print(map);

    URL = map;
    setState(() {
      _URLController.text = map;
    });

    getResponse();
    ret_enabled = true;
  }

  getResponse() async {
    print('getresponse');
    print(URL);
    var response = await http.post(Uri.parse(URL));
    print(response);

    Utf8Decoder decode = new Utf8Decoder();
    var map = json.decode(decode.convert(response.bodyBytes));
    inf.add(response.headers); // header
    inf.add(map); //

    inf[0] = response.headers;
    inf[1] = map;

    print("inf" + inf[0].toString());
    print("inf" + inf[1].toString());

    setState(() {});
  }

  copy_url() {
    print('copy url');
    Fluttertoast.showToast(
        msg: "已复制到剪贴板",
        toastLength: Toast.LENGTH_SHORT,
        gravity: ToastGravity.CENTER,
        timeInSecForIosWeb: 1,
        backgroundColor: Colors.red,
        textColor: Colors.white,
        fontSize: 12.0);

    Clipboard.setData(ClipboardData(text: _URLController.text));
  }

  Future<bool> getData() async {
    await Future<dynamic>.delayed(const Duration(milliseconds: 50));
    var user = {
      'user_id': widget.userid,
    };

    JsonCodec jc = new JsonCodec();
    Object userbody = jc.encode(user);
    var url = "http://124.70.130.38:8080/getRobots";
    var httpClient = new HttpClient();

    var response = await http.post(Uri.parse(url),
        headers: {"Content-Type": "application/json"}, body: userbody);

    Utf8Decoder decode = new Utf8Decoder();
    List tmp = json.decode(decode.convert(response.bodyBytes));
    List<Map> map = tmp.map((e) => new Map<String, dynamic>.from(e)).toList();
    RobotList = map;

    question_map = {
      'title': '输 入 内 容 :',
      'hinttext': '在此输入问题',
      'enabled': true,
      'button_name': '发送问题',
      'Onclick': send_question,
      'controller': _questionController,
      'focusnode': focusquestion,
    };
    URL_map = {
      'title': '生 成 URL :',
      'hinttext': '',
      'enabled': false,
      'button_name': '复制URL到剪贴板',
      'Onclick': copy_url,
      'controller': _URLController,
      'focusnode': focusURL,
    };

    return true;
  }

  @override
  Widget build(BuildContext context) {
    return Container(
        child: FutureBuilder<bool>(
            future: getData(),
            builder: (BuildContext context, AsyncSnapshot<bool> snapshot) {
              if (!snapshot.hasData) {
                return const SizedBox();
              } else {
                return Container(
                    color: DesignCourseAppTheme.nearlyWhite,
                    child: Scaffold(
                      resizeToAvoidBottomInset: false,
                      backgroundColor: Colors.transparent,
                      body: GestureDetector(
                        behavior: HitTestBehavior.translucent,
                        onTap: () {
                          // 触摸收起键盘
                          FocusScope.of(context).requestFocus(FocusNode());
                        },
                        child: Column(
                          children: <Widget>[
                            SizedBox(
                              height: MediaQuery.of(context).padding.top,
                            ),
                            getAppBarUI(),
                            Expanded(
                              child: SingleChildScrollView(
                                child: Container(
                                  height: MediaQuery.of(context).size.height,
                                  child: Column(
                                    children: <Widget>[
                                      SizedBox(
                                        height: 15,
                                      ),
                                      getChooseRobotUI(RobotList),
                                      getCategoryUI(question_map),
                                      getCategoryUI(URL_map),
                                      Flexible(
                                        child: getPopularCourseUI(),
                                      ),
                                    ],
                                  ),
                                ),
                              ),
                            ),
                          ],
                        ),
                      ),
                    ));
              }
            }));
  }

  //--------------------------------------------------

  Widget getCategoryUI(map) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Row(children: [
          Container(
            width: 170,
            child: Padding(
              padding: const EdgeInsets.only(
                  top: 8.0, bottom: 8, left: 18, right: 16),
              child: Text(
                map['title'],
                textAlign: TextAlign.left,
                style: TextStyle(
                  fontWeight: FontWeight.w600,
                  fontSize: 22,
                  letterSpacing: 0.27,
                  color: FitnessAppTheme.nearlyDarkBlue,
                ),
              ),
            ),
          ),
          Expanded(
            child: Padding(
              padding: const EdgeInsets.only(left: 16, right: 16),
              child: Row(
                children: <Widget>[
                  getButtonUI(map['button_name'], map['Onclick']),
                  const SizedBox(
                    width: 16,
                  ),
                ],
              ),
            ),
          )
        ]),
        SizedBox(
          height: 10,
        ),
        Padding(
          padding: const EdgeInsets.only(top: 8.0, left: 18),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Container(
                width: MediaQuery.of(context).size.width * 0.75,
                height: 64,
                child: TextFormField(
                  enabled: map['enabled'],
                  controller: map['controller'],
                  focusNode: map['focusnode'],
                  keyboardType: TextInputType.text, //键盘类型
                  decoration: InputDecoration(
                      labelText: map['hinttext'],
                      contentPadding:
                          EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(40.0) //圆角大小
                          ),
                      suffixIcon: _questionController.text.length > 0
                          ? IconButton(
                              icon: Icon(
                                Icons.clear,
                                size: 21,
                                color: AppTheme.nearlyBlack,
                                //color: Color(0xff666666),
                              ),
                              onPressed: () {
                                setState(() {
                                  _questionController.text = '';
                                });
                              },
                            )
                          : null),
                  // validator: (v) {
                  //   return !_unameExp.hasMatch(v!) ? '账号由6到12位数字与小写字母组成' : null;
                  // },-------正则
                  onChanged: (v) {
                    setState(() {});
                  },
                ),
              ),
            ],
          ),
        ),
      ],
    );
  }

  Widget getChooseRobotUI(robot_list) {
    return Padding(
      padding: const EdgeInsets.only(top: 8.0, left: 18, right: 16),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Text(
            '选 择 机 器 人:',
            textAlign: TextAlign.left,
            style: TextStyle(
              fontWeight: FontWeight.w600,
              fontSize: 22,
              letterSpacing: 0.27,
              color: FitnessAppTheme.nearlyDarkBlue,
            ),
          ),
          RobotListView(
              now_robot: now_robot,
              robotList: robot_list,
              callBack: () {
                var name = now_robot['name'];
                Fluttertoast.showToast(
                    msg: "选择机器人: $name",
                    toastLength: Toast.LENGTH_SHORT,
                    gravity: ToastGravity.CENTER,
                    timeInSecForIosWeb: 1,
                    backgroundColor: Colors.red,
                    textColor: Colors.white,
                    fontSize: 12.0);
              }),
        ],
      ),
    );
  }

  Widget getPopularCourseUI() {
    return Padding(
      padding: const EdgeInsets.only(top: 8.0, left: 18, right: 16),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Text(
            '输 出 结 果 :',
            textAlign: TextAlign.left,
            style: TextStyle(
              fontWeight: FontWeight.w600,
              fontSize: 20,
              letterSpacing: 0.27,
              color: FitnessAppTheme.nearlyDarkBlue,
            ),
          ),
          // Flexible(
          //     child: PopularCourseListView(
          //   infor: inf,
          // ))
          Text(
            "header返回值 : ",
            textAlign: TextAlign.left,
            style: TextStyle(
              fontWeight: FontWeight.w600,
              fontSize: 18,
              letterSpacing: 0.27,
              color: FitnessAppTheme.darkText,
            ),
          ),
          Text(
            inf[0].toString(),
            textAlign: TextAlign.left,
            style: TextStyle(
              fontWeight: FontWeight.w600,
              fontSize: 14,
              letterSpacing: 0.27,
              color: FitnessAppTheme.darkText,
            ),
          ),
          Text(
            "body返回值：",
            textAlign: TextAlign.left,
            style: TextStyle(
              fontWeight: FontWeight.w600,
              fontSize: 18,
              letterSpacing: 0.27,
              color: FitnessAppTheme.darkText,
            ),
          ),
          Text(
            inf[1].toString(),
            textAlign: TextAlign.left,
            style: TextStyle(
              fontWeight: FontWeight.w600,
              fontSize: 14,
              letterSpacing: 0.27,
              color: FitnessAppTheme.darkText,
            ),
          )
        ],
      ),
    );
  }

  Widget getSearchBarUI() {
    return Padding(
      padding: const EdgeInsets.only(top: 8.0, left: 18),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Container(
            width: MediaQuery.of(context).size.width * 0.75,
            height: 64,
            child: Padding(
              padding: const EdgeInsets.only(top: 8, bottom: 8),
              child: Container(
                decoration: BoxDecoration(
                  color: HexColor('#F8FAFB'),
                  borderRadius: const BorderRadius.only(
                    bottomRight: Radius.circular(13.0),
                    bottomLeft: Radius.circular(13.0),
                    topLeft: Radius.circular(13.0),
                    topRight: Radius.circular(13.0),
                  ),
                ),
                child: Row(
                  children: <Widget>[
                    Expanded(
                      child: Container(
                        padding: const EdgeInsets.only(left: 16, right: 16),
                        child: TextFormField(
                          style: TextStyle(
                            fontFamily: 'WorkSans',
                            fontWeight: FontWeight.bold,
                            fontSize: 16,
                            color: DesignCourseAppTheme.nearlyBlue,
                          ),
                          keyboardType: TextInputType.text,
                          decoration: InputDecoration(
                            labelText: '在此输入问题',
                            border: InputBorder.none,
                            helperStyle: TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: 16,
                              color: HexColor('#B9BABC'),
                            ),
                            labelStyle: TextStyle(
                              fontWeight: FontWeight.w600,
                              fontSize: 16,
                              letterSpacing: 0.2,
                              color: HexColor('#B9BABC'),
                            ),
                          ),
                          onEditingComplete: () {},
                        ),
                      ),
                    ),
                    SizedBox(
                      width: 60,
                      height: 60,
                      child: Icon(Icons.search, color: HexColor('#B9BABC')),
                    )
                  ],
                ),
              ),
            ),
          ),
          const Expanded(
            child: SizedBox(),
          )
        ],
      ),
    );
  }

  Widget getAppBarUI() {
    return Padding(
      padding: const EdgeInsets.only(top: 8.0, left: 18, right: 18),
      child: Row(
        children: <Widget>[
          Expanded(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.end,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: <Widget>[
                SizedBox(
                  height: 15,
                ),
                Text(
                  '点宝',
                  textAlign: TextAlign.left,
                  style: TextStyle(
                    fontWeight: FontWeight.w400,
                    fontSize: 16,
                    letterSpacing: 0.2,
                    color: DesignCourseAppTheme.grey,
                  ),
                ),
                SizedBox(
                  height: 10,
                ),
                Text(
                  '在 线 调 试 ！',
                  textAlign: TextAlign.left,
                  style: TextStyle(
                    fontWeight: FontWeight.bold,
                    fontSize: 25,
                    letterSpacing: 0.27,
                    color: DesignCourseAppTheme.darkerText,
                  ),
                ),
                SizedBox(
                  height: 10,
                ),
              ],
            ),
          ),
          Container(
            width: 60,
            height: 60,
            child: Image.asset('assets/design_course/userImage.png'),
          )
        ],
      ),
    );
  }

  Widget getButtonUI(button, Onclick) {
    return Expanded(
      child: Container(
        decoration: BoxDecoration(
            color: DesignCourseAppTheme.nearlyBlue,
            borderRadius: const BorderRadius.all(Radius.circular(24.0)),
            border: Border.all(color: DesignCourseAppTheme.nearlyBlue)),
        child: Material(
          color: Colors.transparent,
          child: InkWell(
            splashColor: Colors.white24,
            borderRadius: const BorderRadius.all(Radius.circular(24.0)),
            onTap: () {
              setState(() {
                Onclick();
              });
            },
            child: Padding(
              padding:
                  const EdgeInsets.only(top: 6, bottom: 6, left: 18, right: 18),
              child: Center(
                child: Text(
                  button,
                  textAlign: TextAlign.left,
                  style: TextStyle(
                    fontWeight: FontWeight.w600,
                    fontSize: 16,
                    letterSpacing: 0.27,
                    color: DesignCourseAppTheme.nearlyWhite,
                  ),
                ),
              ),
            ),
          ),
        ),
      ),
    );
  }
}
