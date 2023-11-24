// Autogenerated from Pigeon (v0.1.20), do not edit directly.
// See also: https://pub.dev/packages/pigeon
// ignore_for_file: public_member_api_docs, non_constant_identifier_names, avoid_as, unused_import
// @dart = 2.8
import 'dart:async';
import 'dart:typed_data' show Uint8List, Int32List, Int64List, Float64List;

import 'package:flutter/services.dart';

class CommonParams {
  String pageName;
  String uniqueId;
  Map<Object, Object> arguments;

  Object encode() {
    final Map<Object, Object> pigeonMap = <Object, Object>{};
    pigeonMap['pageName'] = pageName;
    pigeonMap['uniqueId'] = uniqueId;
    pigeonMap['arguments'] = arguments;
    return pigeonMap;
  }

  static CommonParams decode(Object message) {
    final Map<Object, Object> pigeonMap = message as Map<Object, Object>;
    return CommonParams()
      ..pageName = pigeonMap['pageName'] as String
      ..uniqueId = pigeonMap['uniqueId'] as String
      ..arguments = pigeonMap['arguments'] as Map<Object, Object>;
  }
}

abstract class FlutterRouterApi {
  void pushRoute(CommonParams arg);
  void popRoute(CommonParams arg);
  void removeRoute(CommonParams arg);
  void onForeground(CommonParams arg);
  void onBackground(CommonParams arg);
  void onNativeViewShow(CommonParams arg);
  void onNativeViewHide(CommonParams arg);
  static void setup(FlutterRouterApi api) {
    {
      const BasicMessageChannel<Object> channel =
          BasicMessageChannel<Object>('dev.flutter.pigeon.FlutterRouterApi.pushRoute', StandardMessageCodec());
      if (api == null) {
        channel.setMessageHandler(null);
      } else {
        channel.setMessageHandler((Object message) async {
          assert(message != null, 'Argument for dev.flutter.pigeon.FlutterRouterApi.pushRoute was null. Expected CommonParams.');
          final CommonParams input = CommonParams.decode(message);
          api.pushRoute(input);
          return;
        });
      }
    }
    {
      const BasicMessageChannel<Object> channel =
          BasicMessageChannel<Object>('dev.flutter.pigeon.FlutterRouterApi.popRoute', StandardMessageCodec());
      if (api == null) {
        channel.setMessageHandler(null);
      } else {
        channel.setMessageHandler((Object message) async {
          assert(message != null, 'Argument for dev.flutter.pigeon.FlutterRouterApi.popRoute was null. Expected CommonParams.');
          final CommonParams input = CommonParams.decode(message);
          api.popRoute(input);
          return;
        });
      }
    }
    {
      const BasicMessageChannel<Object> channel =
          BasicMessageChannel<Object>('dev.flutter.pigeon.FlutterRouterApi.removeRoute', StandardMessageCodec());
      if (api == null) {
        channel.setMessageHandler(null);
      } else {
        channel.setMessageHandler((Object message) async {
          assert(message != null, 'Argument for dev.flutter.pigeon.FlutterRouterApi.removeRoute was null. Expected CommonParams.');
          final CommonParams input = CommonParams.decode(message);
          api.removeRoute(input);
          return;
        });
      }
    }
    {
      const BasicMessageChannel<Object> channel =
          BasicMessageChannel<Object>('dev.flutter.pigeon.FlutterRouterApi.onForeground', StandardMessageCodec());
      if (api == null) {
        channel.setMessageHandler(null);
      } else {
        channel.setMessageHandler((Object message) async {
          assert(message != null, 'Argument for dev.flutter.pigeon.FlutterRouterApi.onForeground was null. Expected CommonParams.');
          final CommonParams input = CommonParams.decode(message);
          api.onForeground(input);
          return;
        });
      }
    }
    {
      const BasicMessageChannel<Object> channel =
          BasicMessageChannel<Object>('dev.flutter.pigeon.FlutterRouterApi.onBackground', StandardMessageCodec());
      if (api == null) {
        channel.setMessageHandler(null);
      } else {
        channel.setMessageHandler((Object message) async {
          assert(message != null, 'Argument for dev.flutter.pigeon.FlutterRouterApi.onBackground was null. Expected CommonParams.');
          final CommonParams input = CommonParams.decode(message);
          api.onBackground(input);
          return;
        });
      }
    }
    {
      const BasicMessageChannel<Object> channel =
          BasicMessageChannel<Object>('dev.flutter.pigeon.FlutterRouterApi.onNativeViewShow', StandardMessageCodec());
      if (api == null) {
        channel.setMessageHandler(null);
      } else {
        channel.setMessageHandler((Object message) async {
          assert(message != null, 'Argument for dev.flutter.pigeon.FlutterRouterApi.onNativeViewShow was null. Expected CommonParams.');
          final CommonParams input = CommonParams.decode(message);
          api.onNativeViewShow(input);
          return;
        });
      }
    }
    {
      const BasicMessageChannel<Object> channel =
          BasicMessageChannel<Object>('dev.flutter.pigeon.FlutterRouterApi.onNativeViewHide', StandardMessageCodec());
      if (api == null) {
        channel.setMessageHandler(null);
      } else {
        channel.setMessageHandler((Object message) async {
          assert(message != null, 'Argument for dev.flutter.pigeon.FlutterRouterApi.onNativeViewHide was null. Expected CommonParams.');
          final CommonParams input = CommonParams.decode(message);
          api.onNativeViewHide(input);
          return;
        });
      }
    }
  }
}

class NativeRouterApi {
  Future<void> pushNativeRoute(CommonParams arg) async {
    final Object encoded = arg.encode();
    const BasicMessageChannel<Object> channel =
        BasicMessageChannel<Object>('dev.flutter.pigeon.NativeRouterApi.pushNativeRoute', StandardMessageCodec());
    final Map<Object, Object> replyMap = await channel.send(encoded) as Map<Object, Object>;
    if (replyMap == null) {
      throw PlatformException(
        code: 'channel-error',
        message: 'Unable to establish connection on channel.',
        details: null,
      );
    } else if (replyMap['error'] != null) {
      final Map<Object, Object> error = replyMap['error'] as Map<Object, Object>;
      throw PlatformException(
        code: error['code'] as String,
        message: error['message'] as String,
        details: error['details'],
      );
    } else {
      // noop
    }
  }

  Future<void> pushFlutterRoute(CommonParams arg) async {
    final Object encoded = arg.encode();
    const BasicMessageChannel<Object> channel =
        BasicMessageChannel<Object>('dev.flutter.pigeon.NativeRouterApi.pushFlutterRoute', StandardMessageCodec());
    final Map<Object, Object> replyMap = await channel.send(encoded) as Map<Object, Object>;
    if (replyMap == null) {
      throw PlatformException(
        code: 'channel-error',
        message: 'Unable to establish connection on channel.',
        details: null,
      );
    } else if (replyMap['error'] != null) {
      final Map<Object, Object> error = replyMap['error'] as Map<Object, Object>;
      throw PlatformException(
        code: error['code'] as String,
        message: error['message'] as String,
        details: error['details'],
      );
    } else {
      // noop
    }
  }

  Future<void> popRoute(CommonParams arg) async {
    final Object encoded = arg.encode();
    const BasicMessageChannel<Object> channel =
        BasicMessageChannel<Object>('dev.flutter.pigeon.NativeRouterApi.popRoute', StandardMessageCodec());
    final Map<Object, Object> replyMap = await channel.send(encoded) as Map<Object, Object>;
    if (replyMap == null) {
      throw PlatformException(
        code: 'channel-error',
        message: 'Unable to establish connection on channel.',
        details: null,
      );
    } else if (replyMap['error'] != null) {
      final Map<Object, Object> error = replyMap['error'] as Map<Object, Object>;
      throw PlatformException(
        code: error['code'] as String,
        message: error['message'] as String,
        details: error['details'],
      );
    } else {
      // noop
    }
  }
}
