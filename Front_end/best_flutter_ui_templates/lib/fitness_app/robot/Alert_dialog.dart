import 'dart:convert';
import 'dart:io';
// import 'dart:js_util';
import 'package:best_flutter_ui_templates/fitness_app/robot/buy_times_dialog.dart';
import 'package:best_flutter_ui_templates/fitness_app/robot/no_add_dialog.dart';
import 'package:http/http.dart' as http;
import 'package:best_flutter_ui_templates/app_theme.dart';
import 'package:flutter/material.dart';
import 'package:best_flutter_ui_templates/fitness_app/fitness_app_theme.dart';
import 'package:flutter/services.dart';
import 'package:fluttertoast/fluttertoast.dart';
import '../fitness_app_theme.dart';

class Robot_Management_dialog extends StatefulWidget {
  Robot_Management_dialog({
    Key? key,
    this.robot,
  }) : super(key: key);

  final fsize = 15.0;

  var robot;

  @override
  _Robot_Management_dialogState createState() =>
      _Robot_Management_dialogState();
}

class _Robot_Management_dialogState extends State<Robot_Management_dialog> {
  final TextEditingController _controller = new TextEditingController();
  final focusNode1 = FocusNode();
  final focusNode2 = FocusNode();
  bool Change_enabled = false;
  bool change_name = false;
  String show_image = 'assets/images/userImage.png';

  void showPhoto(BuildContext context, Widget image) {
    Navigator.push(context,
        MaterialPageRoute<void>(builder: (BuildContext context) {
      return GestureDetector(
        child: SizedBox.expand(
          child: Hero(
            tag: image,
            child: image,
          ),
        ),
        onTap: () {
          Navigator.maybePop(context);
        },
      );
    }));
  }
//用user_id去获取这个用户名下的所有robot
//目前这个函数连接在复制apikey的按钮上
  // getRobots() async {
  //   var user = {
  //     //这里传入现在的用户id 先放个1好测试
  //     'user_id': 1,
  //   };
  //   print(user);
  //   JsonCodec jc = new JsonCodec();
  //   Object userbody = jc.encode(user);
  //   print(userbody);

  //   var url = "http://10.0.2.2:8080/getRobots";
  //   var httpClient = new HttpClient();
  //   var response = await http.post(Uri.parse(url),
  //       headers: {"Content-Type": "application/json"}, body: userbody);
  //   Utf8Decoder decode = new Utf8Decoder();
  //   print(response);
  //   print(response);
  //   List tmp = json.decode(decode.convert(response.bodyBytes));
  //   print(tmp);
  //   List<Map> map = tmp.map((e) => new Map<String, dynamic>.from(e)).toList();
  //   print(map.length);
  //   for (var it in map) {
  //     print(it['id']);
  //     print(it['user_id']);
  //     print(it['name']);
  //     print(it['last_time']);
  //     print(it['establish_time']);
  //     print(it['valid']);
  //     print(it['used_times']);
  //     print(it['apikey']);
  //   }
  // }

  //修改robot函数
//先放了固定的内容 把参数传递进来即可
//目前连接在关闭按钮上

  modifyrobot(var ChangeRobot) async {
    print(ChangeRobot);
    JsonCodec jc = new JsonCodec();
    Object robotbody = jc.encode(ChangeRobot);
    String msg = "";

    var url = "http://124.70.130.38:8080/modifyRobot";
    var httpClient = new HttpClient();
    var response = await http.post(Uri.parse(url),
        headers: {"Content-Type": "application/json"}, body: robotbody);
    Utf8Decoder decode = new Utf8Decoder();
    print(response);
    String t = await json.decode(json.encode(response.body));
    print(t);
  }

  buyTimes(var ChangeRobot) async {
    print(ChangeRobot);
    JsonCodec jc = new JsonCodec();
    Object robotbody = jc.encode(ChangeRobot);
    String msg = "";

    var url = "http://124.70.130.38:8080/buyTimes";
    var httpClient = new HttpClient();
    var response = await http.post(Uri.parse(url),
        headers: {"Content-Type": "application/json"}, body: robotbody);
    Utf8Decoder decode = new Utf8Decoder();
    print(response);
    String t = await json.decode(json.encode(response.body));
    print(t);

    print('只能有一个未激活状态的机器人');
    showDialog(context: context, builder: (_) => Times());
  }

  @override
  Widget build(BuildContext context) {
    setState(() {});
    return Scaffold(
        body: GestureDetector(
            behavior: HitTestBehavior.translucent,
            onTap: () {
              // 触摸收起键盘
              FocusScope.of(context).requestFocus(FocusNode());
            },
            child: Container(
                child: Padding(
                    padding: const EdgeInsets.only(
                      left: 24,
                      right: 24,
                      top: 16,
                      bottom: 20,
                    ),
                    child: Container(
                      decoration: BoxDecoration(
                        color: FitnessAppTheme.white,
                        borderRadius: BorderRadius.only(
                            topLeft: Radius.circular(8.0),
                            bottomLeft: Radius.circular(8.0),
                            bottomRight: Radius.circular(8.0),
                            topRight: Radius.circular(68.0)),
                        boxShadow: <BoxShadow>[
                          BoxShadow(
                              color: FitnessAppTheme.grey.withOpacity(0.2),
                              offset: Offset(1.1, 1.1),
                              blurRadius: 10.0),
                        ],
                      ),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Padding(
                            padding: const EdgeInsets.only(
                                left: 16, bottom: 20, top: 50),
                            child: Text(
                              '管理机器人',
                              style: TextStyle(
                                  fontFamily: FitnessAppTheme.fontName,
                                  fontWeight: FontWeight.w500,
                                  fontSize: 30,
                                  letterSpacing: -0.1,
                                  color: Colors.black),
                            ),
                          ),
                          Row(
                            children: <Widget>[
                              Container(
                                width: 100,
                                child: Padding(
                                  padding: const EdgeInsets.only(
                                      left: 16, bottom: 8, top: 8),
                                  child: Text(
                                    '机器人名称:',
                                    textAlign: TextAlign.left,
                                    style: TextStyle(
                                        fontFamily: FitnessAppTheme.fontName,
                                        fontWeight: FontWeight.w500,
                                        fontSize: widget.fsize,
                                        letterSpacing: -0.1,
                                        color: FitnessAppTheme.darkText),
                                  ),
                                ),
                              ),
                              new Expanded(
                                  child: Padding(
                                padding: const EdgeInsets.only(
                                    left: 16, bottom: 8, top: 8, right: 16),
                                child: TextFormField(
                                  enabled: Change_enabled,
                                  controller: _controller,
                                  focusNode: focusNode1,
                                  keyboardType: TextInputType.text, //键盘类型
                                  maxLength: 12,
                                  // textInputAction: TextInputAction.next, //显示'下一步'
                                  decoration: InputDecoration(
                                      hintText: widget.robot['name'].toString(),
                                      hintStyle: TextStyle(
                                          color: AppTheme.nearlyBlack),
                                      suffixIcon: _controller.text.length > 0
                                          ? IconButton(
                                              icon: Icon(
                                                Icons.clear,
                                                size: 21,
                                                color: AppTheme.nearlyBlack,
                                                //color: Color(0xff666666),
                                              ),
                                              onPressed: () {
                                                setState(() {});
                                              },
                                            )
                                          : null),

                                  onEditingComplete: () {
                                    setState(() {
                                      Change_enabled = false;
                                    });
                                  },
                                  onChanged: (v) {
                                    setState(() {});
                                  },
                                ),
                              )),
                              // child: Container(
                              //     child: Padding(
                              //   padding: const EdgeInsets.only(
                              //       left: 16, bottom: 8, top: 8, right: 16),
                              //   child: Text(
                              //     widget.robot['name'].toString(),
                              //     textAlign: TextAlign.left,
                              //     style: TextStyle(
                              //         fontFamily: FitnessAppTheme.fontName,
                              //         fontWeight: FontWeight.w500,
                              //         fontSize: widget.fsize,
                              //         letterSpacing: -0.1,
                              //         color: FitnessAppTheme.darkText),
                              //   ),
                              // )),

                              InkWell(
                                highlightColor: Colors.transparent,
                                borderRadius:
                                    BorderRadius.all(Radius.circular(4.0)),
                                onTap: () {
                                  print('修改名称');
                                  setState(() {
                                    Change_enabled = true;
                                    change_name = true;
                                  });
                                },
                                child: Padding(
                                  padding: const EdgeInsets.only(
                                      left: 16, bottom: 8, top: 8, right: 16),
                                  child: Row(
                                    children: <Widget>[
                                      Text(
                                        '修改名称',
                                        textAlign: TextAlign.left,
                                        style: TextStyle(
                                          fontFamily: FitnessAppTheme.fontName,
                                          fontWeight: FontWeight.normal,
                                          fontSize: widget.fsize,
                                          letterSpacing: 0.5,
                                          color: FitnessAppTheme.nearlyDarkBlue,
                                        ),
                                      ),
                                      SizedBox(
                                        height: 16,
                                        width: 20,
                                        child: Icon(
                                          Icons.add_circle_outline,
                                          color: FitnessAppTheme.darkText,
                                          size: 18,
                                        ),
                                      ),
                                    ],
                                  ),
                                ),
                              )
                            ],
                          ),
                          Row(children: <Widget>[
                            Container(
                              width: 100,
                              child: Padding(
                                padding: const EdgeInsets.only(
                                    left: 16, bottom: 8, top: 8),
                                child: Text(
                                  'APIkey:',
                                  textAlign: TextAlign.left,
                                  style: TextStyle(
                                      fontFamily: FitnessAppTheme.fontName,
                                      fontWeight: FontWeight.w500,
                                      fontSize: widget.fsize,
                                      letterSpacing: -0.1,
                                      color: FitnessAppTheme.darkText),
                                ),
                              ),
                            ),
                            new Expanded(
                              child: Container(
                                  child: Padding(
                                padding: const EdgeInsets.only(
                                    left: 16, bottom: 8, top: 8, right: 16),
                                child: Text(
                                  widget.robot['apikey'].toString(),
                                  textAlign: TextAlign.left,
                                  style: TextStyle(
                                      fontFamily: FitnessAppTheme.fontName,
                                      fontWeight: FontWeight.w500,
                                      fontSize: widget.fsize,
                                      letterSpacing: -0.1,
                                      color: FitnessAppTheme.darkText),
                                ),
                              )),
                            ),
                            InkWell(
                              highlightColor: Colors.transparent,
                              borderRadius:
                                  BorderRadius.all(Radius.circular(4.0)),
                              onTap: () {
                                // getRobots();
                                print('复制Key');
                                Fluttertoast.showToast(
                                    msg: "已复制到剪贴板",
                                    toastLength: Toast.LENGTH_SHORT,
                                    gravity: ToastGravity.CENTER,
                                    timeInSecForIosWeb: 1,
                                    backgroundColor: Colors.red,
                                    textColor: Colors.white,
                                    fontSize: 12.0);

                                Clipboard.setData(ClipboardData(
                                    text: widget.robot['apikey'].toString()));
                              },
                              child: Padding(
                                padding: const EdgeInsets.only(
                                    left: 16, bottom: 8, top: 8, right: 16),
                                child: Row(
                                  children: <Widget>[
                                    Text(
                                      '复制Key',
                                      textAlign: TextAlign.left,
                                      style: TextStyle(
                                        fontFamily: FitnessAppTheme.fontName,
                                        fontWeight: FontWeight.normal,
                                        fontSize: widget.fsize,
                                        letterSpacing: 0.5,
                                        color: FitnessAppTheme.nearlyDarkBlue,
                                      ),
                                    ),
                                    SizedBox(
                                      height: 16,
                                      width: 20,
                                      child: Icon(
                                        Icons.add_circle_outline,
                                        color: FitnessAppTheme.darkText,
                                        size: 18,
                                      ),
                                    ),
                                  ],
                                ),
                              ),
                            )
                          ]),
                          Padding(
                            padding: const EdgeInsets.only(
                                left: 24, right: 24, top: 8, bottom: 8),
                            child: Container(
                              height: 2,
                              decoration: BoxDecoration(
                                color: FitnessAppTheme.background,
                                borderRadius:
                                    BorderRadius.all(Radius.circular(4.0)),
                              ),
                            ),
                          ),
                          Padding(
                            padding: const EdgeInsets.only(
                                left: 24, right: 24, top: 8, bottom: 16),
                            child: Row(
                              children: <Widget>[
                                Expanded(
                                  child: Column(
                                    mainAxisAlignment: MainAxisAlignment.center,
                                    crossAxisAlignment:
                                        CrossAxisAlignment.start,
                                    children: <Widget>[
                                      Text(
                                        widget.robot['establish_time']
                                            .toString(),
                                        textAlign: TextAlign.center,
                                        style: TextStyle(
                                          fontFamily: FitnessAppTheme.fontName,
                                          fontWeight: FontWeight.w500,
                                          fontSize: 16,
                                          letterSpacing: -0.2,
                                          color: FitnessAppTheme.darkText,
                                        ),
                                      ),
                                      Padding(
                                        padding: const EdgeInsets.only(top: 6),
                                        child: Text(
                                          '建立时间',
                                          textAlign: TextAlign.center,
                                          style: TextStyle(
                                            fontFamily:
                                                FitnessAppTheme.fontName,
                                            fontWeight: FontWeight.w600,
                                            fontSize: 12,
                                            color: FitnessAppTheme.grey
                                                .withOpacity(0.5),
                                          ),
                                        ),
                                      ),
                                    ],
                                  ),
                                ),
                                Expanded(
                                  child: Row(
                                    mainAxisAlignment: MainAxisAlignment.center,
                                    crossAxisAlignment:
                                        CrossAxisAlignment.center,
                                    children: <Widget>[
                                      Column(
                                        mainAxisAlignment:
                                            MainAxisAlignment.center,
                                        crossAxisAlignment:
                                            CrossAxisAlignment.center,
                                        children: <Widget>[
                                          Text(
                                            widget.robot['valid'].toString(),
                                            textAlign: TextAlign.center,
                                            style: TextStyle(
                                              fontFamily:
                                                  FitnessAppTheme.fontName,
                                              fontWeight: FontWeight.w500,
                                              fontSize: 16,
                                              letterSpacing: -0.2,
                                              color: FitnessAppTheme.darkText,
                                            ),
                                          ),
                                          Padding(
                                            padding:
                                                const EdgeInsets.only(top: 6),
                                            child: Text(
                                              '激活状态',
                                              textAlign: TextAlign.center,
                                              style: TextStyle(
                                                fontFamily:
                                                    FitnessAppTheme.fontName,
                                                fontWeight: FontWeight.w600,
                                                fontSize: 12,
                                                color: FitnessAppTheme.grey
                                                    .withOpacity(0.5),
                                              ),
                                            ),
                                          ),
                                        ],
                                      ),
                                    ],
                                  ),
                                ),
                                Expanded(
                                  child: Row(
                                    mainAxisAlignment: MainAxisAlignment.center,
                                    crossAxisAlignment:
                                        CrossAxisAlignment.center,
                                    children: <Widget>[
                                      Column(
                                        mainAxisAlignment:
                                            MainAxisAlignment.center,
                                        crossAxisAlignment:
                                            CrossAxisAlignment.center,
                                        children: <Widget>[
                                          Text(
                                            widget.robot['used_times']
                                                .toString(),
                                            style: TextStyle(
                                              fontFamily:
                                                  FitnessAppTheme.fontName,
                                              fontWeight: FontWeight.w500,
                                              fontSize: 16,
                                              letterSpacing: -0.2,
                                              color: FitnessAppTheme.darkText,
                                            ),
                                          ),
                                          Padding(
                                            padding:
                                                const EdgeInsets.only(top: 6),
                                            child: Text(
                                              '剩余次数',
                                              textAlign: TextAlign.center,
                                              style: TextStyle(
                                                fontFamily:
                                                    FitnessAppTheme.fontName,
                                                fontWeight: FontWeight.w600,
                                                fontSize: 12,
                                                color: FitnessAppTheme.grey
                                                    .withOpacity(0.5),
                                              ),
                                            ),
                                          ),
                                        ],
                                      ),
                                    ],
                                  ),
                                )
                              ],
                            ),
                          ),
                          Padding(
                            padding: const EdgeInsets.only(
                                left: 35, right: 24, top: 8, bottom: 16),
                            child: Row(
                              children: <Widget>[
                                Expanded(
                                  child: Row(
                                    mainAxisAlignment: MainAxisAlignment.center,
                                    crossAxisAlignment:
                                        CrossAxisAlignment.center,
                                    children: <Widget>[
                                      Column(
                                        mainAxisAlignment:
                                            MainAxisAlignment.center,
                                        crossAxisAlignment:
                                            CrossAxisAlignment.center,
                                        children: <Widget>[
                                          new TextButton(
                                              onPressed: () {
                                                //  add_robot();
                                                print('激活');
                                                widget.robot['valid'] = true;

                                                var Buytimes = {
                                                  "user_id": 1,
                                                  "apikey":
                                                      widget.robot['apikey'],
                                                  "money": 50,
                                                  "times": 2000
                                                };
                                                buyTimes(Buytimes);

                                                // showPhoto(
                                                //     context,
                                                //     Image(
                                                //         image: new AssetImage(
                                                //             show_image)));
                                              },
                                              child: new Text(
                                                '激活/购买',
                                                textAlign: TextAlign.center,
                                                style: TextStyle(
                                                  fontFamily:
                                                      FitnessAppTheme.fontName,
                                                  fontWeight: FontWeight.w500,
                                                  fontSize: 16,
                                                  letterSpacing: -0.2,
                                                  color:
                                                      FitnessAppTheme.darkText,
                                                ),
                                              ))
                                        ],
                                      ),
                                    ],
                                  ),
                                ),
                                Expanded(
                                  child: Row(
                                    mainAxisAlignment: MainAxisAlignment.center,
                                    crossAxisAlignment:
                                        CrossAxisAlignment.center,
                                    children: <Widget>[
                                      Column(
                                        mainAxisAlignment:
                                            MainAxisAlignment.center,
                                        crossAxisAlignment:
                                            CrossAxisAlignment.center,
                                        children: <Widget>[
                                          new TextButton(
                                              onPressed: () {
                                                print('保存');

                                                var ChangeRobot;

                                                DateTime nowTime =
                                                    DateTime.now();

                                                String ntime = nowTime
                                                    .toString()
                                                    .substring(0, 16);
                                                if (change_name) {
                                                  ChangeRobot = {
                                                    "id": widget.robot['id'],
                                                    "name": _controller.text,
                                                    "valid":
                                                        widget.robot['valid'],
                                                    "last_time": ntime,
                                                  };
                                                } else {
                                                  ChangeRobot = {
                                                    "id": widget.robot['id'],
                                                    "name":
                                                        widget.robot['name'],
                                                    "valid":
                                                        widget.robot['valid'],
                                                    "last_time": ntime,
                                                  };
                                                }
                                                modifyrobot(ChangeRobot);
                                              },
                                              child: new Text(
                                                '保存',
                                                textAlign: TextAlign.center,
                                                style: TextStyle(
                                                  fontFamily:
                                                      FitnessAppTheme.fontName,
                                                  fontWeight: FontWeight.w500,
                                                  fontSize: 16,
                                                  letterSpacing: -0.2,
                                                  color:
                                                      FitnessAppTheme.darkText,
                                                ),
                                              ))
                                        ],
                                      ),
                                    ],
                                  ),
                                ),
                                Expanded(
                                  child: Row(
                                    mainAxisAlignment: MainAxisAlignment.center,
                                    crossAxisAlignment:
                                        CrossAxisAlignment.center,
                                    children: <Widget>[
                                      Column(
                                        mainAxisAlignment:
                                            MainAxisAlignment.center,
                                        crossAxisAlignment:
                                            CrossAxisAlignment.center,
                                        children: <Widget>[
                                          new TextButton(
                                              onPressed: () {
                                                print('关闭');
                                                //     modifyrobot();
                                                Navigator.pop(context);
                                              },
                                              child: new Text(
                                                '关闭',
                                                textAlign: TextAlign.center,
                                                style: TextStyle(
                                                  fontFamily:
                                                      FitnessAppTheme.fontName,
                                                  fontWeight: FontWeight.w500,
                                                  fontSize: 16,
                                                  letterSpacing: -0.2,
                                                  color:
                                                      FitnessAppTheme.darkText,
                                                ),
                                              ))
                                        ],
                                      ),
                                    ],
                                  ),
                                ),
                              ],
                            ),
                          ),
                        ],
                      ),
                    )))));
  }
}
