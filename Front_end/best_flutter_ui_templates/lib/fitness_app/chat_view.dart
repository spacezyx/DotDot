import 'package:best_flutter_ui_templates/app_theme.dart';
import 'package:best_flutter_ui_templates/fitness_app/robot/robot_title_view.dart';
import 'package:best_flutter_ui_templates/fitness_app/fitness_app_theme.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:smart_bubble/smart_bubble_widget.dart';
import 'dart:convert';
import 'dart:io';
import 'package:http/http.dart' as http;

class ChatScreen extends StatefulWidget {
  ChatScreen({Key? key, this.animationController, this.userid})
      : super(key: key);

  final AnimationController? animationController;
  final userid;
  @override
  _ChatScreen createState() => _ChatScreen();
}

class _ChatScreen extends State<ChatScreen> with TickerProviderStateMixin {
  Animation<double>? topBarAnimation;

  final _unameController = TextEditingController();

  // 焦点
  final focusNode1 = FocusNode();
  final focusNode2 = FocusNode();

  bool isEye = true;
  bool isBtnEnabled = false;
  bool showLoading = false;
  final _unameExp = RegExp(r'^(?![0-9]+$)(?![a-z]+$)[0-9a-z]{6,12}$'); //用户名正则

  String message = "";

  int count = 1;
  List<Widget> listViews = <Widget>[];
  final ScrollController scrollController = ScrollController();
  double topBarOpacity = 0.0;

  QAsystem_research() async {
    var user = {
      'question': _unameController.text,
    };
    JsonCodec jc = new JsonCodec();
    Object userbody = jc.encode(user);
    print(userbody);

    //var pdfText= await json.decode(json.encode(response.databody);
    // String msg = await json.decode(json.encode(response.body));
    var msg;
    var url2 = "http://124.70.130.38:8080/QASystem";

    var response2 = await http.post(Uri.parse(url2),
        headers: {"Content-Type": "application/json"}, body: userbody);
    print(response2.body);
    //var pdfText= await json.decode(json.encode(response.databody);
    msg = response2.body;

    //msg = response.toString();

    print("222" + msg);

    listViews.insert(
        listViews.length - 2,
        SmartBubble(
          // maxHeight: 64,
          arrowDirection: ArrowDirection.left,
          child:
              Text(msg, style: TextStyle(color: AppTheme.white, fontSize: 16)),
        ));

    setState(() {
      message = msg;
    });
  }

  @override
  void initState() {
    topBarAnimation = Tween<double>(begin: 0.0, end: 1.0).animate(
        CurvedAnimation(
            parent: widget.animationController!,
            curve: Interval(0, 0.5, curve: Curves.fastOutSlowIn)));
    addAllListData();

    scrollController.addListener(() {
      if (scrollController.offset >= 24) {
        if (topBarOpacity != 1.0) {
          setState(() {
            topBarOpacity = 1.0;
          });
        }
      } else if (scrollController.offset <= 24 &&
          scrollController.offset >= 0) {
        if (topBarOpacity != scrollController.offset / 24) {
          setState(() {
            topBarOpacity = scrollController.offset / 24;
          });
        }
      } else if (scrollController.offset <= 0) {
        if (topBarOpacity != 0.0) {
          setState(() {
            topBarOpacity = 0.0;
          });
        }
      }
    });
    super.initState();
  }

  void addAllListData() {
    listViews.add(
      RobotTitleView(
        titleTxt: '和点宝聊聊天吧',
        subTxt: '点宝想和你说：',
        addClick: () {
          setState(() {
            createBubble();
          });
        },
        animation: Tween<double>(begin: 0.0, end: 1.0).animate(CurvedAnimation(
            parent: widget.animationController!,
            curve:
                Interval((1 / count) * 0, 1.0, curve: Curves.fastOutSlowIn))),
        animationController: widget.animationController!,
      ),
    );
    listViews.add(
      SmartBubble(
        title: Text("点宝"),
        //color: AppTheme.grey,
        arrowDirection: ArrowDirection.left,
        child: Text("Hello",
            style: TextStyle(color: AppTheme.white, fontSize: 16)),
      ),
    );
    listViews.add(
      TextFormField(
        controller: _unameController,
        focusNode: focusNode1, //关联focusNode1
        keyboardType: TextInputType.text, //键盘类型
        maxLength: 240,
        textInputAction: TextInputAction.done, //显示'完成'
        decoration: InputDecoration(
            labelText: "问点宝一个问题吧",
            contentPadding: EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
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
                    ),
                    onPressed: () {
                      setState(() {
                        _unameController.text = '';
                        getInputs();
                      });
                    },
                  )
                : null),
        onEditingComplete: () =>
            FocusScope.of(context).requestFocus(focusNode2),
        onChanged: (v) {
          setState(() {
            //getInputs();
          });
        },
      ),
    );
    listViews.add(
      Padding(
        padding: EdgeInsets.symmetric(vertical: 5.0),
        child: RaisedButton(
            // color: const Color(0xff2a8fbd),
            color: AppTheme.nearlyBlack,
            padding: EdgeInsets.symmetric(vertical: 10.0),
            disabledColor: AppTheme.nearlyWhite,
            shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(60.0)), //圆角
            child: Text('提交',
                style: TextStyle(fontSize: 18.0, color: Colors.white)),
            onPressed: () {
              getInputs();
            }),
      ),
    );
  }

  void getInputs() {
    listViews.insert(
      listViews.length - 2,
      SmartBubble(
        //color: AppTheme.grey,
        child: Text(_unameController.text, style: TextStyle(fontSize: 16)),
      ),
    );
    setState(() {});
    QAsystem_research();
    _unameController.text = '';
    setState(() {});
  }

  void addToList(String m) {
    listViews.insert(
        listViews.length - 2,
        SmartBubble(
          maxHeight: 64,
          arrowDirection: ArrowDirection.left,
          child: Text(m, style: TextStyle(color: AppTheme.white, fontSize: 16)),
        ));
    setState(() {});
  }

  void createBubble() {
    listViews.insert(
        listViews.length - 2,
        SmartBubble(
          title: Text("点宝"),
          //color: AppTheme.grey,
          arrowDirection: ArrowDirection.left,
          child: Text("快来和我聊天啊~",
              style: TextStyle(color: AppTheme.white, fontSize: 16)),
        ));
  }

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
        behavior: HitTestBehavior.translucent,
        onTap: () {
          // 触摸收起键盘
          FocusScope.of(context).requestFocus(FocusNode());
        },
        child: Container(
          color: FitnessAppTheme.background,
          child: Scaffold(
            backgroundColor: Colors.transparent,
            body: Stack(
              children: <Widget>[
                getMainListViewUI(),
                SizedBox(
                  height: MediaQuery.of(context).padding.bottom,
                )
              ],
            ),
          ),
        ));
  }

  Future<bool> getData() async {
    await Future<dynamic>.delayed(const Duration(milliseconds: 50));
    return true;
  }

  Widget getMainListViewUI() {
    return FutureBuilder<bool>(
      future: getData(),
      builder: (BuildContext context, AsyncSnapshot<bool> snapshot) {
        if (!snapshot.hasData) {
          return const SizedBox();
        } else {
          return ListView.builder(
            controller: scrollController,
            padding: EdgeInsets.only(
              top: AppBar().preferredSize.height +
                  MediaQuery.of(context).padding.top +
                  24,
              bottom: 62 + MediaQuery.of(context).padding.bottom,
            ),
            itemCount: listViews.length,
            scrollDirection: Axis.vertical,
            itemBuilder: (BuildContext context, int index) {
              widget.animationController?.forward();
              return listViews[index];
            },
          );
        }
      },
    );
  }
}
