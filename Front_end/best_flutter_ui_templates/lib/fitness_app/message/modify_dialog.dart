import 'package:flutter/material.dart';
import 'dart:convert';
import 'dart:io';
import 'package:http/http.dart' as http;

class ModifyDialog extends StatelessWidget {
  final id;
  const ModifyDialog({
    Key? key,
    this.id,
  }) : super(key: key);

  modifyMessage(var tar_id) async {
    var tar_message = {
      'id': tar_id,
    };

    JsonCodec jc = new JsonCodec();
    Object id = jc.encode(tar_message);
    String msg = "";

    var url = "http://124.70.130.38:8080/changeMessageType";
    var httpClient = new HttpClient();
    var response = await http.post(Uri.parse(url),
        headers: {"Content-Type": "application/json"}, body: id);
    Utf8Decoder decode = new Utf8Decoder();
    print(response);
    String t = await json.decode(json.encode(response.body));
    print(t);
  }

  @override
  Widget build(BuildContext context) {
    return SimpleDialog(
      title: Text("提示"), // 标题
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
          child: Text("你确定要删除该条消息吗？"),
          alignment: Alignment.center,
          padding: EdgeInsets.all(40),
        ),
        FlatButton(
          onPressed: () {
            // 隐藏弹框
            modifyMessage(id);
            Navigator.pop(context);
          },
          child: Text("确认"),
          textColor: Colors.white,
          color: Colors.blue,
        ),
        FlatButton(
          onPressed: () {
            // 隐藏弹框
            Navigator.pop(context);
          },
          child: Text("取消"),
          textColor: Colors.white,
          color: Colors.blue,
        ),
      ],
    );
  }
}
