import 'package:best_flutter_ui_templates/Login/Login_dialog.dart';
import 'package:flutter/material.dart';
import 'package:best_flutter_ui_templates/app_theme.dart';
import 'package:best_flutter_ui_templates/Login/login.dart';
import 'dart:convert';
import 'dart:io';
import 'package:http/http.dart' as http;
import 'Vercode.dart';

class Register extends StatefulWidget {
  @override
  _Rigeister createState() => _Rigeister();
}

class _Rigeister extends State<Register> {
  // 控制器
  final _unameController = TextEditingController();
  final _pwdController = TextEditingController();
  final _repwdController = TextEditingController();
  final _VercodeController = TextEditingController();
  final _EmailController = TextEditingController();
  final _formKey = GlobalKey<FormState>();

  // 焦点
  final focusNode1 = FocusNode();
  final focusNode2 = FocusNode();
  final focusNode3 = FocusNode();
  final focusVercode = FocusNode();
  final focusEmail = FocusNode();

  bool isEye = true;
  bool isBtnEnabled = false;
  bool showLoading = false;

  final _unameExp = RegExp(r'^[0-9a-zA-Z]{6,12}$'); //用户名正则
  final _pwdExp = RegExp(r'^[0-9a-zA-Z]{6,12}$'); //密码正则
  final _pwdExp2 = RegExp(r'^[0-9a-zA-Z]{6,12}$'); //密码正则
  final _EmailExp =
      RegExp(r'^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$'); //邮箱正则

  // 注册按钮是否可点击
  void checkLoginText() {
    if (_unameExp.hasMatch(_unameController.text) &&
        //_EmailExp.hasMatch(_EmailController.text) &&
        _pwdExp.hasMatch(_pwdController.text) &&
        _pwdExp2.hasMatch(_repwdController.text) &&
        (_repwdController.text == _pwdController.text)) {
      this.isBtnEnabled = true;
    } else {
      this.isBtnEnabled = false;
    }
  }

  // 登录提交
  void loginSub() {
    FocusScope.of(context).requestFocus(FocusNode()); //收起键盘
    setState(() {
      this.showLoading = true;
    });
    RegisterRequest().then((v) => {
          setState(() {
            this.showLoading = false;
          }),
        });
  }

  CheckName(String uname) async {
    var user = {
      'username': uname,
    };
    JsonCodec jc = new JsonCodec();
    Object userbody = jc.encode(user);
    String msg = "";

    var url = "http://124.70.130.38:8080/alreadyHasName";
    var httpClient = new HttpClient();
    var response = await http.post(Uri.parse(url),
        headers: {"Content-Type": "application/json"}, body: userbody);
    Utf8Decoder decode = new Utf8Decoder();
    print(response);
    var map = json.decode(decode.convert(response.bodyBytes));
    print('check name');
    print(map);
  }

  user_register() async {
    print('user_register');
    var user = {
      'username': _unameController.text,
      'password': _pwdController.text,
      'user_type': 1,
      'email': _EmailController.text,
    };

    print(user);
    JsonCodec jc = new JsonCodec();
    Object userbody = jc.encode(user);
    String msg = "";

    var url = "http://124.70.130.38:8080/Register";
    var response = await http.post(Uri.parse(url),
        headers: {"Content-Type": "application/json"}, body: userbody);
    Utf8Decoder decode = new Utf8Decoder();
    print(response);
    Map map = json.decode(decode.convert(response.bodyBytes));
    print(map);
  }

  Send_Vercode() async {
    var user = {
      'username': _unameController.text,
      'email': _EmailController.text,
    };
    print(user);
    JsonCodec jc = new JsonCodec();
    Object userbody = jc.encode(user);
    print(userbody);
    String msg = "";

    var url = "http://124.70.130.38:8080/sendMail";
    var httpClient = new HttpClient();
    var response = await http.post(Uri.parse(url),
        headers: {"Content-Type": "application/json"}, body: userbody);
    Utf8Decoder decode = new Utf8Decoder();
    print(response);
    var map = json.decode(decode.convert(response.bodyBytes));
    print(map);
  }

  Check_Vercode() async {
    var user = {
      'username': _unameController.text,
      'checkCode': _VercodeController.text,
    };
    JsonCodec jc = new JsonCodec();
    Object userbody = jc.encode(user);
    String msg = "";

    var url = "http://124.70.130.38:8080/checkCode";
    var response = await http.post(Uri.parse(url),
        headers: {"Content-Type": "application/json"}, body: userbody);
    //var map = json.decode(decode.convert(response.bodyBytes));
    var map = response.body;
    print(map);

    if (map == '校验通过') {
      user_register();

      Navigator.push(
        context,
        MaterialPageRoute(builder: (context) => Login()),
      );
    } else {
      showDialog(
          context: context,
          builder: (_) => LoginDialog(
                title: '提示',
                content: '验证码错误,宝',
              ));
    }
  }

  @override
  void initState() {
    super.initState();
  }

  @override
  void dispose() {
    super.dispose();
  }

  // 异步操作
  Future RegisterRequest() async {
    return Future.delayed(Duration(seconds: 3), () {
      print('register success');
    });
  }

  @override
  Widget build(BuildContext context) {
    List<Widget> childrens = [];
    final _main = Center(
        child: GestureDetector(
      behavior: HitTestBehavior.translucent,
      onTap: () {
        // 触摸收起键盘
        FocusScope.of(context).requestFocus(FocusNode());
      },
      child: ListView(
        padding: EdgeInsets.only(left: 30.0, right: 30.0, top: 70.0),
        children: [
          SizedBox(height: 20.0),
          Form(
            key: _formKey,
            autovalidate: true,
            child: Column(
              children: [
                TextFormField(
                  //用户名
                  controller: _unameController,
                  focusNode: focusNode1, //关联focusNode1
                  keyboardType: TextInputType.text, //键盘类型
                  maxLength: 12,
                  textInputAction: TextInputAction.next, //显示'下一步'
                  decoration: InputDecoration(
                      hintText: '请输入账号',
                      labelText: "账号",
                      contentPadding:
                          EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
                      prefixIcon: Icon(Icons.perm_identity),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(40.0) //圆角大小
                          ),
                      suffixIcon: _unameController.text.length > 0
                          ? IconButton(
                              icon: Icon(
                                Icons.clear,
                                size: 21,
                                color: AppTheme.nearlyBlack,
                                //color: Color(0xff666666),
                              ),
                              onPressed: () {
                                setState(() {
                                  _unameController.text = '';
                                  checkLoginText();
                                });
                              },
                            )
                          : null),
                  validator: (v) {
                    return !_unameExp.hasMatch(v!) ? '账号由6到12位数字与小写字母组成' : null;
                  },
                  onEditingComplete: () {},
                  //=>FocusScope.of(context).requestFocus(focusNode2),
                  onChanged: (v) {
                    setState(() {
                      checkLoginText();
                    });
                  },
                ),
                SizedBox(height: 15.0),
                TextFormField(
                  //密码
                  controller: _pwdController,
                  focusNode: focusNode2, //关联focusNode2
                  obscureText: !isEye, //密码类型 内容显示
                  maxLength: 12,
                  textInputAction: TextInputAction.done, //显示'完成'
                  decoration: InputDecoration(
                      hintText: '请输入密码',
                      labelText: '密码',
                      contentPadding:
                          EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
                      prefixIcon: Icon(Icons.lock),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(40.0)),
                      suffixIcon: IconButton(
                        icon: Icon(
                          Icons.remove_red_eye,
                          size: 21,
                          //color: Color(0xff666666),
                          color: AppTheme.nearlyBlack,
                        ),
                        onPressed: () {
                          setState(() {
                            isEye = !isEye;
                          });
                        },
                      )),
                  validator: (v) {
                    return !_pwdExp.hasMatch(v!) ? '密码由6到12位数字与小写字母组成' : null;
                  },
                  onChanged: (v) {
                    setState(() {
                      checkLoginText();
                    });
                  },
                  onEditingComplete: () => loginSub(), //'完成'回调
                ),
                SizedBox(height: 15.0),
                TextFormField(
                  //确认密码
                  controller: _repwdController,
                  focusNode: focusNode3, //关联focusNode3
                  obscureText: !isEye, //密码类型 内容显示
                  maxLength: 12,
                  textInputAction: TextInputAction.done, //显示'完成'
                  decoration: InputDecoration(
                      hintText: '请重复密码',
                      labelText: '密码确认',
                      contentPadding:
                          EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
                      prefixIcon: Icon(Icons.lock),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(40.0)),
                      suffixIcon: IconButton(
                        icon: Icon(
                          Icons.remove_red_eye,
                          size: 21,
                          //color: Color(0xff666666),
                          color: AppTheme.nearlyBlack,
                        ),
                        onPressed: () {
                          setState(() {
                            isEye = !isEye;
                          });
                        },
                      )),
                  validator: (v) {
                    if (_repwdController.text == _pwdController.text)
                      return !_pwdExp2.hasMatch(v!)
                          ? '密码由6到12位数字与小写字母组成'
                          : null;
                    else
                      return '两次密码输入不同';
                  },
                  onChanged: (v) {
                    setState(() {
                      checkLoginText();
                    });
                  },
                  onEditingComplete: () => loginSub(), //'完成'回调
                ),
                SizedBox(height: 15.0),
                TextFormField(
                  //邮箱
                  controller: _EmailController,
                  focusNode: focusEmail, //关联focusEmail
                  keyboardType: TextInputType.text, //键盘类型
                  textInputAction: TextInputAction.next, //显示'下一步'
                  decoration: InputDecoration(
                      hintText: '请输入邮箱',
                      labelText: "邮箱",
                      contentPadding:
                          EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
                      prefixIcon: Icon(Icons.perm_identity),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(40.0) //圆角大小
                          ),
                      suffixIcon: _EmailController.text.length > 0
                          ? IconButton(
                              icon: Icon(
                                Icons.clear,
                                size: 21,
                                color: AppTheme.nearlyBlack,
                                //color: Color(0xff666666),
                              ),
                              onPressed: () {
                                setState(() {
                                  _EmailController.text = '';
                                });
                              },
                            )
                          : null),
                  // validator: (v) {
                  //   return !_EmailExp.hasMatch(v!)
                  //       ? '例如1466298788@qq.com'
                  //       : null;
                  // },
                  onEditingComplete: () {},
                  //=>FocusScope.of(context).requestFocus(focusNode2),
                  onChanged: (v) {
                    setState(() {
                      checkLoginText();
                    });
                  },
                ),
                SizedBox(height: 35.0),
                TextFormField(
                  //验证码
                  controller: _VercodeController,
                  focusNode: focusVercode, //关联focusVercode
                  keyboardType: TextInputType.text, //键盘类型
                  maxLength: 12,
                  textInputAction: TextInputAction.next, //显示'下一步'
                  decoration: InputDecoration(
                      hintText: '请输入验证码',
                      labelText: "验证码",
                      contentPadding:
                          EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
                      prefixIcon: Icon(Icons.perm_identity),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(40.0) //圆角大小
                          ),
                      suffixIcon: _VercodeController.text.length > 0
                          ? IconButton(
                              icon: Icon(
                                Icons.clear,
                                size: 21,
                                color: AppTheme.nearlyBlack,
                                //color: Color(0xff666666),
                              ),
                              onPressed: () {
                                setState(() {
                                  _VercodeController.text = '';
                                });
                              },
                            )
                          : null),
                  validator: (v) {
                    return !_unameExp.hasMatch(v!) ? '验证码十分钟内有效' : null;
                  },
                  onEditingComplete: () {},
                  //=>FocusScope.of(context).requestFocus(focusNode2),
                  onChanged: (v) {
                    // setState(() {
                    //    checkLoginText();
                    // });
                  },
                ),
                SizedBox(height: 15.0),
              ],
            ),
          ),
          Padding(
            padding: EdgeInsets.symmetric(vertical: 5.0),
            child: RaisedButton(
                color: AppTheme.nearlyBlack,
                padding: EdgeInsets.symmetric(vertical: 10.0),
                disabledColor: AppTheme.nearlyWhite,
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(60.0)), //圆角
                child: Text('获取验证码',
                    style: TextStyle(fontSize: 18.0, color: Colors.white)),
                onPressed: () {
                  if (isBtnEnabled) {
                    Send_Vercode();
                    // Navigator.push(
                    //   context,
                    //   MaterialPageRoute(builder: (context) => Login()),
                    // );
                  }
                }),
          ),
          SizedBox(height: 15.0),
          Padding(
            padding: EdgeInsets.symmetric(vertical: 5.0),
            child: RaisedButton(
                // color: const Color(0xff2a8fbd),
                color: AppTheme.nearlyBlack,
                padding: EdgeInsets.symmetric(vertical: 10.0),
                // splashColor: const Color(0xffde19de),//水波纹颜色
                // disabledColor: const Color(0xff999999),
                disabledColor: AppTheme.nearlyWhite,
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(60.0)), //圆角
                child: Text('注册',
                    style: TextStyle(fontSize: 18.0, color: Colors.white)),
                onPressed: () {
                  if (isBtnEnabled) {
                    Check_Vercode();
                  }
                }
                //(){NavigationHomeScreen(),}
                ),
          ),
          FlatButton(
            child: Text('返回',
                style: TextStyle(color: Colors.black54, fontSize: 15.0)),
            onPressed: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => Login()),
              );
            },
          ),
        ],
      ),
    ));

    childrens.add(_main);
    if (this.showLoading) {
      //childrens.add(Loadding());
    }
    return Scaffold(
      body: Stack(children: childrens),
    );
  }
}
