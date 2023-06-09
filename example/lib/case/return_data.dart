import 'package:flutter/material.dart';
import 'package:flutter_boost/flutter_boost.dart';

class ReturnDataWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: const Text('Return data from a screen')),
        body: Builder(builder: (BuildContext context) {
          return Center(
            child: ElevatedButton(
              child: const Text('Pick an option, any option!'),
              onPressed: () {
                _navigateAndDisplaySelection(context);
              },
            ),
          );
        }));
  }

  _navigateAndDisplaySelection(BuildContext context) async {
    final result = await BoostNavigator.instance
        .push('selectionScreen', withContainer: true);
    ScaffoldMessenger.of(context)
      ..removeCurrentSnackBar()
      ..showSnackBar(SnackBar(content: Text("$result")));
  }
}
