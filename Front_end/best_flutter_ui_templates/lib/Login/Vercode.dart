import 'package:flutter/material.dart';
import 'package:best_flutter_ui_templates/app_theme.dart';
import 'package:best_flutter_ui_templates/Login/login.dart';
import 'dart:convert';
import 'dart:io';
import 'package:http/http.dart' as http;

class Vercode extends StatefulWidget {
  @override
  _Vercode createState() => _Vercode();
}

class _Vercode extends State<Vercode> {
  // 控制器
  final _VercodeController = TextEditingController();
  final _formKey = GlobalKey<FormState>();

  // 焦点
  final focusVercode = FocusNode();

  bool isBtnEnabled = false;
  bool showLoading = false;

  // 登录提交
  void loginSub() {
    FocusScope.of(context).requestFocus(FocusNode()); //收起键盘
  }

  checkVercode() async {
    var user = {
      //'username': _unameController.text,
      //'password': _pwdController.text,
    };
    print(user);
    JsonCodec jc = new JsonCodec();
    Object userbody = jc.encode(user);
    String msg = "";

    var url = "http://124.70.130.38:8080/Vercode";
    var httpClient = new HttpClient();
    var response = await http.post(Uri.parse(url),
        headers: {"Content-Type": "application/json"}, body: userbody);
    Utf8Decoder decode = new Utf8Decoder();
    print(response);
    Map map = json.decode(decode.convert(response.bodyBytes));
    print(map.length);
  }

  @override
  void initState() {
    super.initState();
  }

  @override
  void dispose() {
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    List<Widget> childrens = [];
    final _main = Center(
      child: ListView(
        padding: EdgeInsets.only(left: 30.0, right: 30.0, top: 70.0),
        children: [
          Padding(
            padding: EdgeInsets.only(bottom: 50),
            child: Hero(
              tag: 'avatar',
              child: CircleAvatar(
                backgroundColor: AppTheme.grey,
                radius: 50.0,
                child: Image.asset('assets/images/userPic.png'),
              ),
            ),
          ),
          Form(
            key: _formKey,
            autovalidate: true,
            child: Column(
              children: [
                SizedBox(height: 15.0),
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
                                  //  checkLoginText();
                                });
                              },
                            )
                          : null),
                  onEditingComplete: () {},
                  //=>FocusScope.of(context).requestFocus(focusNode2),
                  onChanged: (v) {
                    setState(() {
                      // checkLoginText();
                    });
                  },
                ),
                SizedBox(height: 40.0),
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
                child: Text('提交',
                    style: TextStyle(fontSize: 18.0, color: Colors.white)),
                onPressed: () {
                  if (isBtnEnabled) {
                    checkVercode();
                    Navigator.push(
                      context,
                      MaterialPageRoute(builder: (context) => Login()),
                    );
                  }
                }
                //(){NavigationHomeScreen(),}
                ),
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
                child: Text('重新发送',
                    style: TextStyle(fontSize: 18.0, color: Colors.white)),
                onPressed: () {
                  if (isBtnEnabled) {
                    Navigator.push(
                      context,
                      MaterialPageRoute(builder: (context) => Login()),
                    );
                  }
                }
                //(){NavigationHomeScreen(),}
                ),
          ),
          FlatButton(
            child: Text('返回到登录页面',
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
    );

    childrens.add(_main);
    if (this.showLoading) {
      //childrens.add(Loadding());
    }
    return Scaffold(
      body: Stack(children: childrens),
    );
  }
}
