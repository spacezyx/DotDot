class Category {
  Category({
    this.title = '',
    this.imagePath = '',
    this.type = '',
  });

  String title;
  String type;
  String imagePath;

  // static List<Category> categoryList = <Category>[
  //   Category(
  //     imagePath: 'assets/design_course/interFace1.png',
  //     title: 'User interface Design',
  //   ),
  //   Category(
  //     imagePath: 'assets/design_course/interFace2.png',
  //     title: 'User interface Design',
  //   ),
  // ];

  static List<Category> popularCourseList = <Category>[
    Category(
      imagePath: 'assets/design_course/interFace3.png',
      title: 'Header 返回值',
      type: '请求参数',
    ),
    Category(
      imagePath: 'assets/design_course/interFace4.png',
      title: 'Body 返回值',
      type: '返回参数',
    ),
    // Category(
    //   imagePath: 'assets/design_course/interFace3.png',
    //   title: 'App Design Course',
    // ),
    // Category(
    //   imagePath: 'assets/design_course/interFace4.png',
    //   title: 'Web Design Course',
    // ),
  ];
}
