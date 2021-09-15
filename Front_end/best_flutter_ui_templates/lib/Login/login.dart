import 'dart:convert';
import 'dart:io';

import 'package:flutter/material.dart';
// import '../component/toast.dart';
// import '../component/loadding.dart';
// import 'package:best_flutter_ui_templates/app_theme.dart';
// import 'package:flutter/material.dart';
import '../navigation_home_screen.dart';
import 'package:best_flutter_ui_templates/app_theme.dart';
import 'package:http/http.dart' as http;

import 'Login_dialog.dart';

class Login extends StatefulWidget {
  @override
  _LoginState createState() => _LoginState();
}

class _LoginState extends State<Login> {
  // 控制器
  final _unameController = TextEditingController();
  final _pwdController = TextEditingController();
  final _formKey = GlobalKey<FormState>();

  // 焦点
  final focusNode1 = FocusNode();
  final focusNode2 = FocusNode();

  bool isEye = true;
  bool isBtnEnabled = false;
  bool showLoading = false;
  bool isLogin = false;
  final _unameExp = RegExp(r'^[0-9a-zA-Z]{6,12}$'); //用户名正则
  final _pwdExp = RegExp(r'^[0-9a-zA-Z]{6,12}$'); //密码正则

  var user_inf;

  // 登录按钮是否可点击
  void checkLoginText() {
    if (_unameExp.hasMatch(_unameController.text) &&
        _pwdExp.hasMatch(_pwdController.text)) {
      this.isBtnEnabled = true;
    } else {
      this.isBtnEnabled = false;
    }
  }

  isRight() async {
    print('isright');
    var user = {
      'username': _unameController.text,
      'password': _pwdController.text
    };
    print(user);
    JsonCodec jc = new JsonCodec();
    Object userbody = jc.encode(user);
    print(userbody);
    String msg = "";

    var url = "http://124.70.130.38:8080/login";
    var httpClient = new HttpClient();
    var response = await http.post(Uri.parse(url),
        headers: {"Content-Type": "application/json"}, body: userbody);
    Utf8Decoder decode = new Utf8Decoder();
    print(response);
    Map map = json.decode(decode.convert(response.bodyBytes));
    print('Howdy, ${map['status']}');
    print('Howdy, ${map['msg']}');
    print(map);
    if (map['msg'] == "欢迎登录点宝系统！") {
      isLogin = true;
      user_inf = map['data'];
    } else {
      showDialog(
          context: context,
          builder: (_) => LoginDialog(
                title: '提示',
                content: '错误的用户名或密码',
              ));
    }
  }

  // isRight() async {
  //   print('isright');
  //   var user = {
  //     'username': _unameController.text,
  //     'password': _pwdController.text
  //   };
  //   print(user);
  //   JsonCodec jc = new JsonCodec();
  //   Object userbody = jc.encode(user);
  //   String msg = "";

  //   var url = "http://124.70.130.38:8080/login";
  //   var httpClient = new HttpClient();
  //   var response = await http.post(Uri.parse(url),
  //       headers: {"Content-Type": "application/json"}, body: userbody);
  //   Utf8Decoder decode = new Utf8Decoder();
  //   print(response);
  //   Map map = json.decode(decode.convert(response.bodyBytes));
  //   print(map);

  //   user_inf = map['data']['id'];
  //   print('user_inf   ' + user_inf.toString());

  //   if (map['msg'] == "欢迎登录点宝系统！") {
  //     var tar_user = {
  //       'username': user['username'],
  //     };
  //     print(user['username']);
  //     print('tar_user');
  //     print(tar_user);
  //     isLogin = true;
  //     return;
  //   }
  // }

  // 登录提交
  void loginSub() {
    FocusScope.of(context).requestFocus(FocusNode()); //收起键盘
    setState(() {
      this.showLoading = true;
    });
    loginRequest().then((v) => {
          setState(() {
            this.showLoading = false;
          }),
        });
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
  Future loginRequest() async {
    return Future.delayed(Duration(seconds: 3), () {
      print('login success');
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
          Padding(
            padding: EdgeInsets.only(bottom: 40),
            child: Hero(
              tag: 'avatar',
              child: CircleAvatar(
                backgroundColor: AppTheme.grey,
                radius: 50.0,
                child: Image.asset('assets/images/userImage.png'),
              ),
            ),
          ),
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
                  onEditingComplete: () =>
                      FocusScope.of(context).requestFocus(focusNode2),
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
                  focusNode: focusNode2, //关联focusNode1
                  obscureText: isEye, //密码类型 内容用***显示
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
                )
              ],
            ),
          ),
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
                child: Text('登录',
                    style: TextStyle(fontSize: 18.0, color: Colors.white)),
                onPressed: () {
                  isRight();
                  if (isLogin) {
                    var user = {
                      'username': _unameController.text,
                    };
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (context) => NavigationHomeScreen(
                                user_inf: user_inf,
                              )),
                    );
                  }
                }),
          ),
          FlatButton(
            child: Text('忘记密码?',
                style: TextStyle(color: Colors.black54, fontSize: 15.0)),
            onPressed: () {
              Navigator.pushNamed(context, 'forget');
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
