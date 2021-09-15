import 'package:flutter/material.dart';

class LoginDialog extends StatelessWidget {
  const LoginDialog({
    Key? key,
    this.title,
    this.content,
  }) : super(key: key);

  final title;
  final content;

  @override
  Widget build(BuildContext context) {
    return SimpleDialog(
      title: Text(title), // 标题
      titlePadding: EdgeInsets.fromLTRB(140, 20, 0, 0), // 标题外间距
      // 标题样式 TextStyle
      titleTextStyle: TextStyle(
        color: Colors.blue,
        fontSize: 25,
      ),
      contentPadding: EdgeInsets.only(left: 15, right: 15), // 内容外间距
      backgroundColor: Colors.white, // 背景色
      // 子控件，可以随意自定义
      children: [
        Container(
          child: Text(content),
          alignment: Alignment.center,
          padding: EdgeInsets.all(40),
        ),
        FlatButton(
          onPressed: () {
            // 隐藏弹框
            Navigator.pop(context);
          },
          child: Text("确认"),
          textColor: Colors.white,
          color: Colors.blue,
        ),
      ],
    );
  }
}
