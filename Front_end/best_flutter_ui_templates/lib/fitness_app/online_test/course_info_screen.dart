import 'package:best_flutter_ui_templates/fitness_app/online_test/models/category.dart';
import 'package:flutter/material.dart';
import 'design_course_app_theme.dart';

class CourseInfoScreen extends StatefulWidget {
  const CourseInfoScreen({
    Key? key,
    this.category,
    this.infor,
  });

  final infor;
  final Category? category;

  @override
  _CourseInfoScreenState createState() => _CourseInfoScreenState();
}

class _CourseInfoScreenState extends State<CourseInfoScreen>
    with TickerProviderStateMixin {
  final double infoHeight = 364.0;
  AnimationController? animationController;
  Animation<double>? animation;
  double opacity1 = 0.0;
  double opacity2 = 0.0;
  double opacity3 = 0.0;
  @override
  void initState() {
    animationController = AnimationController(
        duration: const Duration(milliseconds: 1000), vsync: this);
    animation = Tween<double>(begin: 0.0, end: 1.0).animate(CurvedAnimation(
        parent: animationController!,
        curve: Interval(0, 1.0, curve: Curves.fastOutSlowIn)));
    setData();
    super.initState();
  }

  Future<void> setData() async {
    animationController?.forward();
  }

  @override
  Widget build(BuildContext context) {
    final double tempHeight = MediaQuery.of(context).size.height -
        (MediaQuery.of(context).size.width / 1.2) +
        24.0;
    return Container(
      color: DesignCourseAppTheme.nearlyWhite,
      child: Scaffold(
        backgroundColor: Colors.transparent,
        body: Stack(
          children: <Widget>[
            Column(
              children: <Widget>[
                AspectRatio(
                  aspectRatio: 1.2,
                  child: Image.asset('assets/design_course/webInterFace.png'),
                ),
              ],
            ),
            Positioned(
              top: (MediaQuery.of(context).size.width / 1.2) - 24.0,
              bottom: 0,
              left: 0,
              right: 0,
              child: Container(
                decoration: BoxDecoration(
                  color: DesignCourseAppTheme.nearlyWhite,
                  borderRadius: const BorderRadius.only(
                      topLeft: Radius.circular(32.0),
                      topRight: Radius.circular(32.0)),
                  boxShadow: <BoxShadow>[
                    BoxShadow(
                        color: DesignCourseAppTheme.grey.withOpacity(0.2),
                        offset: const Offset(1.1, 1.1),
                        blurRadius: 10.0),
                  ],
                ),
                child: Padding(
                  padding: const EdgeInsets.only(left: 8, right: 8),
                  child: SingleChildScrollView(
                    child: Container(
                      constraints: BoxConstraints(
                          minHeight: infoHeight,
                          maxHeight: tempHeight > infoHeight
                              ? tempHeight
                              : infoHeight),
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: <Widget>[
                          Padding(
                            padding: const EdgeInsets.only(
                                top: 32.0, left: 18, right: 16),
                            child: Text(
                              widget.category!.title,
                              textAlign: TextAlign.left,
                              style: TextStyle(
                                fontWeight: FontWeight.w600,
                                fontSize: 22,
                                letterSpacing: 0.27,
                                color: DesignCourseAppTheme.darkerText,
                              ),
                            ),
                          ),
                          Padding(
                            padding: const EdgeInsets.only(
                                left: 16, right: 16, bottom: 8, top: 16),
                            child: Row(
                              mainAxisAlignment: MainAxisAlignment.spaceBetween,
                              crossAxisAlignment: CrossAxisAlignment.center,
                              children: <Widget>[
                                Text(
                                  widget.category!.type,
                                  textAlign: TextAlign.left,
                                  style: TextStyle(
                                    fontWeight: FontWeight.w200,
                                    fontSize: 18,
                                    letterSpacing: 0.27,
                                    color: DesignCourseAppTheme.nearlyBlue,
                                  ),
                                ),
                                Container(
                                  child: Icon(
                                    Icons.star,
                                    color: DesignCourseAppTheme.nearlyBlue,
                                    size: 24,
                                  ),
                                ),
                              ],
                            ),
                          ),
                          // AnimatedOpacity(
                          //   duration: const Duration(milliseconds: 500),
                          //   opacity: opacity1,
                          //   child: Padding(
                          //     padding: const EdgeInsets.all(8),
                          //     child: Row(
                          //       children: <Widget>[
                          //         getTimeBoxUI('24', 'Classe'),
                          //         getTimeBoxUI('2hours', 'Time'),
                          //         getTimeBoxUI('24', 'Seat'),
                          //       ],
                          //     ),
                          //   ),
                          // ),

                          //getContentListView(),
                          Expanded(
                              child: AnimatedOpacity(
                                  duration: const Duration(milliseconds: 500),
                                  opacity: opacity2,
                                  child: Row(children: <Widget>[
                                    Padding(
                                      padding: const EdgeInsets.only(
                                          left: 16,
                                          right: 16,
                                          top: 8,
                                          bottom: 8),
                                      child: Text(
                                        ' asfahgohaergjalk;ej;lkawlka',
                                        textAlign: TextAlign.justify,
                                        style: TextStyle(
                                          fontWeight: FontWeight.w200,
                                          fontSize: 14,
                                          letterSpacing: 0.27,
                                          color: DesignCourseAppTheme.grey,
                                        ),
                                        maxLines: 3,
                                        overflow: TextOverflow.ellipsis,
                                      ),
                                    ),
                                  ]))),

                          // AnimatedOpacity(
                          //   duration: const Duration(milliseconds: 500),
                          //   opacity: opacity3,
                          //   child: Padding(
                          //     padding: const EdgeInsets.only(
                          //         left: 16, bottom: 16, right: 16),
                          //     child: Row(
                          //       mainAxisAlignment: MainAxisAlignment.center,
                          //       crossAxisAlignment: CrossAxisAlignment.center,
                          //       children: <Widget>[
                          //         Container(
                          //           width: 48,
                          //           height: 48,
                          //           child: Container(
                          //             decoration: BoxDecoration(
                          //               color: DesignCourseAppTheme.nearlyWhite,
                          //               borderRadius: const BorderRadius.all(
                          //                 Radius.circular(16.0),
                          //               ),
                          //               border: Border.all(
                          //                   color: DesignCourseAppTheme.grey
                          //                       .withOpacity(0.2)),
                          //             ),
                          //             child: Icon(
                          //               Icons.add,
                          //               color: DesignCourseAppTheme.nearlyBlue,
                          //               size: 28,
                          //             ),
                          //           ),
                          //         ),
                          //         const SizedBox(
                          //           width: 16,
                          //         ),
                          //         Expanded(
                          //           child: Container(
                          //             height: 48,
                          //             decoration: BoxDecoration(
                          //               color: DesignCourseAppTheme.nearlyBlue,
                          //               borderRadius: const BorderRadius.all(
                          //                 Radius.circular(16.0),
                          //               ),
                          //               boxShadow: <BoxShadow>[
                          //                 BoxShadow(
                          //                     color: DesignCourseAppTheme
                          //                         .nearlyBlue
                          //                         .withOpacity(0.5),
                          //                     offset: const Offset(1.1, 1.1),
                          //                     blurRadius: 10.0),
                          //               ],
                          //             ),
                          //             child: Center(
                          //               child: Text(
                          //                 'Join Course',
                          //                 textAlign: TextAlign.left,
                          //                 style: TextStyle(
                          //                   fontWeight: FontWeight.w600,
                          //                   fontSize: 18,
                          //                   letterSpacing: 0.0,
                          //                   color: DesignCourseAppTheme
                          //                       .nearlyWhite,
                          //                 ),
                          //               ),
                          //             ),
                          //           ),
                          //         )
                          //       ],
                          //     ),
                          //   ),
                          // ),
                          SizedBox(
                            height: MediaQuery.of(context).padding.bottom,
                          )
                        ],
                      ),
                    ),
                  ),
                ),
              ),
            ),
            Positioned(
              top: (MediaQuery.of(context).size.width / 1.2) - 24.0 - 35,
              right: 35,
              child: ScaleTransition(
                alignment: Alignment.center,
                scale: CurvedAnimation(
                    parent: animationController!, curve: Curves.fastOutSlowIn),
                child: Card(
                  color: DesignCourseAppTheme.nearlyBlue,
                  shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(50.0)),
                  elevation: 10.0,
                  child: Container(
                    width: 60,
                    height: 60,
                    child: Center(
                      child: Icon(
                        Icons.favorite,
                        color: DesignCourseAppTheme.nearlyWhite,
                        size: 30,
                      ),
                    ),
                  ),
                ),
              ),
            ),
            Padding(
              padding: EdgeInsets.only(top: MediaQuery.of(context).padding.top),
              child: SizedBox(
                width: AppBar().preferredSize.height,
                height: AppBar().preferredSize.height,
                child: Material(
                  color: Colors.transparent,
                  child: InkWell(
                    borderRadius:
                        BorderRadius.circular(AppBar().preferredSize.height),
                    child: Icon(
                      Icons.arrow_back_ios,
                      color: DesignCourseAppTheme.nearlyBlack,
                    ),
                    onTap: () {
                      Navigator.pop(context);
                    },
                  ),
                ),
              ),
            )
          ],
        ),
      ),
    );
  }

  List<List> show_list = [
    ['content-type', 'date', 'transfer-encoding'],
    ['status', 'data', 'apimsg']
  ];

  Widget getContentListView() {
    int index = 0;
    if (widget.category!.type == '????????????')
      index = 0;
    else if (widget.category!.type == '????????????') index = 1;

    String title0 = show_list[index][0];
    String title1 = show_list[index][1];
    String title2 = show_list[index][2];

    return Padding(
        padding: const EdgeInsets.all(8.0),
        child: Container(
            child: Column(children: <Widget>[
          Expanded(
            child: AnimatedOpacity(
                duration: const Duration(milliseconds: 500),
                opacity: opacity2,
                child: Row(children: <Widget>[
                  Padding(
                    padding: const EdgeInsets.only(
                        left: 16, right: 16, top: 8, bottom: 8),
                    child: Text(
                      '{',
                      textAlign: TextAlign.justify,
                      style: TextStyle(
                        fontWeight: FontWeight.w200,
                        fontSize: 14,
                        letterSpacing: 0.27,
                        color: DesignCourseAppTheme.grey,
                      ),
                      maxLines: 3,
                      overflow: TextOverflow.ellipsis,
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.only(
                        left: 16, right: 16, top: 8, bottom: 8),
                    child: Text(
                      ' $title0  :' + widget.infor[0],
                      textAlign: TextAlign.justify,
                      style: TextStyle(
                        fontWeight: FontWeight.w200,
                        fontSize: 14,
                        letterSpacing: 0.27,
                        color: DesignCourseAppTheme.grey,
                      ),
                      maxLines: 3,
                      overflow: TextOverflow.ellipsis,
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.only(
                        left: 16, right: 16, top: 8, bottom: 8),
                    child: Text(
                      ' $title1  :' + widget.infor[1],
                      textAlign: TextAlign.justify,
                      style: TextStyle(
                        fontWeight: FontWeight.w200,
                        fontSize: 14,
                        letterSpacing: 0.27,
                        color: DesignCourseAppTheme.grey,
                      ),
                      maxLines: 3,
                      overflow: TextOverflow.ellipsis,
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.only(
                        left: 16, right: 16, top: 8, bottom: 8),
                    child: Text(
                      ' $title2  :' + widget.infor[2],
                      textAlign: TextAlign.justify,
                      style: TextStyle(
                        fontWeight: FontWeight.w200,
                        fontSize: 14,
                        letterSpacing: 0.27,
                        color: DesignCourseAppTheme.grey,
                      ),
                      maxLines: 3,
                      overflow: TextOverflow.ellipsis,
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.only(
                        left: 16, right: 16, top: 8, bottom: 8),
                    child: Text(
                      '{',
                      textAlign: TextAlign.justify,
                      style: TextStyle(
                        fontWeight: FontWeight.w200,
                        fontSize: 14,
                        letterSpacing: 0.27,
                        color: DesignCourseAppTheme.grey,
                      ),
                      maxLines: 3,
                      overflow: TextOverflow.ellipsis,
                    ),
                  ),
                ])),
          ),
        ])));
  }

  // Widget getTimeBoxUI(String text1, String txt2) {
  //   return Padding(
  //     padding: const EdgeInsets.all(8.0),
  //     child: Container(
  //       decoration: BoxDecoration(
  //         color: DesignCourseAppTheme.nearlyWhite,
  //         borderRadius: const BorderRadius.all(Radius.circular(16.0)),
  //         boxShadow: <BoxShadow>[
  //           BoxShadow(
  //               color: DesignCourseAppTheme.grey.withOpacity(0.2),
  //               offset: const Offset(1.1, 1.1),
  //               blurRadius: 8.0),
  //         ],
  //       ),
  //       child: Padding(
  //         padding: const EdgeInsets.only(
  //             left: 18.0, right: 18.0, top: 12.0, bottom: 12.0),
  //         child: Column(
  //           mainAxisAlignment: MainAxisAlignment.center,
  //           crossAxisAlignment: CrossAxisAlignment.center,
  //           children: <Widget>[
  //             Text(
  //               text1,
  //               textAlign: TextAlign.center,
  //               style: TextStyle(
  //                 fontWeight: FontWeight.w600,
  //                 fontSize: 14,
  //                 letterSpacing: 0.27,
  //                 color: DesignCourseAppTheme.nearlyBlue,
  //               ),
  //             ),
  //             Text(
  //               txt2,
  //               textAlign: TextAlign.center,
  //               style: TextStyle(
  //                 fontWeight: FontWeight.w200,
  //                 fontSize: 14,
  //                 letterSpacing: 0.27,
  //                 color: DesignCourseAppTheme.grey,
  //               ),
  //             ),
  //           ],
  //         ),
  //       ),
  //     ),
  //   );
  // }
}
