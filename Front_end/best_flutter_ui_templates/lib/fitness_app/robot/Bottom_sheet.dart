import 'package:flutter/material.dart';

class BottomSheet extends StatefulWidget {
  BottomSheet({Key? key}) : super(key: key);

  @override
  _BottomSheetState createState() => _BottomSheetState();
}

class _BottomSheetState extends State<BottomSheet> {
  void showPhoto(BuildContext context, Widget image) {
    Navigator.push(context,
        MaterialPageRoute<void>(builder: (BuildContext context) {
      return GestureDetector(
        child: SizedBox.expand(
          child: Hero(
            tag: image,
            child: image,
          ),
        ),
        onTap: () {
          Navigator.maybePop(context);
        },
      );
    }));
  }

  List<Widget> _list = <Widget>[
    ClipRRect(
      child: Image.asset(
        'images/hanyun.jpg',
        fit: BoxFit.cover,
      ),
      borderRadius: BorderRadius.circular(8),
    ),
    ClipRRect(
      child: Image.asset(
        'images/hanyun.jpg',
        fit: BoxFit.cover,
      ),
      borderRadius: BorderRadius.circular(8),
    ),
    ClipRRect(
      child: Image.asset(
        'images/hanyun.jpg',
        fit: BoxFit.cover,
      ),
      borderRadius: BorderRadius.circular(8),
    ),
    ClipRRect(
      child: Image.asset(
        'images/hanyun.jpg',
        fit: BoxFit.cover,
      ),
      borderRadius: BorderRadius.circular(8),
    ),
    ClipRRect(
      child: Image.asset(
        'images/hanyun.jpg',
        fit: BoxFit.cover,
      ),
      borderRadius: BorderRadius.circular(8),
    ),
    ClipRRect(
      child: Image.asset(
        'images/hanyun.jpg',
        fit: BoxFit.cover,
      ),
      borderRadius: BorderRadius.circular(8),
    ),
    ClipRRect(
      child: Image.asset(
        'images/hanyun.jpg',
        fit: BoxFit.cover,
      ),
      borderRadius: BorderRadius.circular(8),
    ),
    ClipRRect(
      child: Image.asset(
        'images/hanyun.jpg',
        fit: BoxFit.cover,
      ),
      borderRadius: BorderRadius.circular(8),
    ),
    ClipRRect(
      child: Image.asset(
        'images/hanyun.jpg',
        fit: BoxFit.cover,
      ),
      borderRadius: BorderRadius.circular(8),
    ),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          // Here we take the value from the BottomSheet object that was created by
          // the App.build method, and use it to set our appbar title.
          title: Text('flutter展示底部弹窗'),
          centerTitle: true,
        ),
        body: Center(
          child: RaisedButton(
            child: const Text('展示底部弹窗'),
            onPressed: () {
              showModalBottomSheet<void>(
                  context: context,
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.only(
                      topLeft: Radius.circular(20),
                      topRight: Radius.circular(20),
                    ),
                  ),
                  builder: (BuildContext context) {
                    return Container(
                      child: GridView.count(
                        crossAxisCount: 3,
                        mainAxisSpacing: 4.0,
                        crossAxisSpacing: 4.0,
                        padding: const EdgeInsets.all(4.0),
                        children: _list.map(
                          (Widget img) {
                            return GestureDetector(
                              onTap: () {
                                showPhoto(context, img);
                              },
                              child: Hero(
                                tag: img,
                                child: img,
                              ),
                            );
                          },
                        ).toList(),
                      ),
                    );
                  });
            },
          ),
        ));
  }
}
