import 'dart:convert';
import 'dart:io';

import 'package:best_flutter_ui_templates/fitness_app/robot/robot_body_view.dart';
import 'package:best_flutter_ui_templates/fitness_app/robot/no_more_robot_view.dart';
import 'package:best_flutter_ui_templates/fitness_app/robot/robot_title_view.dart';
import 'package:best_flutter_ui_templates/fitness_app/fitness_app_theme.dart';
import 'package:flutter/material.dart';
import 'package:best_flutter_ui_templates/fitness_app/robot/no_add_dialog.dart';

import 'package:http/http.dart' as http;

class MyDiaryScreen extends StatefulWidget {
  MyDiaryScreen({Key? key, this.animationController, this.userid})
      : super(key: key);

  final AnimationController? animationController;
  final userid;
  @override
  _MyDiaryScreenState createState() => _MyDiaryScreenState();
}

class _MyDiaryScreenState extends State<MyDiaryScreen>
    with TickerProviderStateMixin {
  Animation<double>? topBarAnimation;

  var Robots_map;
  int count = 1;
  List<Widget> listViews = <Widget>[];
  final ScrollController scrollController = ScrollController();
  double topBarOpacity = 0.0;
  var new_robot;

  @override
  void initState() {
    super.initState();

    topBarAnimation = Tween<double>(begin: 0.0, end: 1.0).animate(
        CurvedAnimation(
            parent: widget.animationController!,
            curve: Interval(0, 0.5, curve: Curves.fastOutSlowIn)));

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

  void SetListView() {
    listViews.clear();
    addListTitle();
    addListRobots();
  }

  void addListRobots() {
    for (var it in Robots_map) {
      listViews.insert(
        1,
        RobotBodyView(
          animation: Tween<double>(begin: 0.0, end: 1.0).animate(
              CurvedAnimation(
                  parent: widget.animationController!,
                  curve: Interval((1 / count) * count, 1.0,
                      curve: Curves.fastOutSlowIn))),
          animationController: widget.animationController!,
          robot: it,
        ),
      );
      count++;
    }
  }

  add_robot_remote() async {
    JsonCodec jc = new JsonCodec();
    Object robotbody = jc.encode(new_robot);
    String msg = "";

    var url = "http://124.70.130.38:8080/addRobot";
    var httpClient = new HttpClient();
    var response = await http.post(Uri.parse(url),
        headers: {"Content-Type": "application/json"}, body: robotbody);
    Utf8Decoder decode = new Utf8Decoder();
    String t = await json.decode(json.encode(response.body));

    print(t);
    if (t == 'false') {
      print('只能有一个未激活状态的机器人');
      showDialog(context: context, builder: (_) => NoAddDialog());
    }
  }

  void addListTitle() {
    listViews.add(
      RobotTitleView(
        titleTxt: '我的机器人',
        subTxt: '新建机器人',
        addClick: () {
          setState(() {
            add_robot_remote();
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
      GlassView(
          animation: Tween<double>(begin: 0.0, end: 1.0).animate(
              CurvedAnimation(
                  parent: widget.animationController!,
                  curve: Interval((1 / count) * count, 1.0,
                      curve: Curves.fastOutSlowIn))),
          animationController: widget.animationController!),
    );
  }

  void createRobot() {
    listViews.insert(
      1,
      RobotBodyView(
        animation: Tween<double>(begin: 0.0, end: 1.0).animate(CurvedAnimation(
            parent: widget.animationController!,
            curve: Interval((1 / count) * count, 1.0,
                curve: Curves.fastOutSlowIn))),
        animationController: widget.animationController!,
        robot: this.new_robot,
      ),
    );
    count++;
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
    count = map.length + 1;

    DateTime nowTime = DateTime.now();

    String ltime = nowTime.toString().substring(0, 16);
    String etime = nowTime.toString().substring(0, 10);
    new_robot = {
      "user_id": widget.userid,
      'name': '赵铁柱$count号机',
      "last_time": ltime,
      "establish_time": etime,
      "valid": false,
      "used_times": 0,
      "type": 3
    };

    Robots_map = map;

    SetListView();
    return true;
  }

  @override
  Widget build(BuildContext context) {
    return Container(
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
    );
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
