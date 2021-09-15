import 'package:flutter/material.dart';

class ResetSuccess extends StatelessWidget {
  const ResetSuccess({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SimpleDialog(
      title: Text("重置成功"), // 标题
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
          child: Text("所有机器人均已清空"),
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
