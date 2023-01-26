/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2019 Alibaba Group
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
import 'dart:async';
import 'dart:io';

import 'package:flutter/material.dart';

import 'channel/boost_channel.dart';
import 'container/boost_container.dart';
import 'container/container_coordinator.dart';
import 'container/container_manager.dart';
import 'observers_holders.dart';

export 'container/boost_container.dart';
export 'container/container_manager.dart';

typedef PageBuilder = Widget Function(
    String pageName, Map<String, dynamic> params, String uniqueId);

typedef PrePushRoute = Route<T> Function<T>(String url, String uniqueId,
    Map<String, dynamic> params, Route<dynamic> route);

typedef PostPushRoute = void Function(
  String url,
  String uniqueId,
  Map<String, dynamic> params,
  Route<dynamic> route,
  Future<dynamic> result,
);

class FlutterBoost {
  FlutterBoost() {
    ContainerCoordinator(_boostChannel);
  }

  static final FlutterBoost _instance = FlutterBoost();

  static FlutterBoost get singleton => _instance;

  static ContainerManagerState get containerManager =>
      _instance.containerManagerKey.currentState;

  final GlobalKey<ContainerManagerState> containerManagerKey =
      GlobalKey<ContainerManagerState>();
  final ObserversHolder _observersHolder = ObserversHolder();
  final BoostChannel _boostChannel = BoostChannel();

  static void onPageStart() {
    WidgetsBinding.instance.addPostFrameCallback((Duration _) {
      singleton.channel
          .invokeMethod<Map<String, dynamic>>('pageOnStart')
          .then((Map<String, dynamic> pageInfo) {
        if (pageInfo?.isEmpty ?? true) {
          return;
        }
        if (pageInfo.containsKey('name') &&
            pageInfo.containsKey('params') &&
            pageInfo.containsKey('uniqueId')) {
          ContainerCoordinator.singleton.nativeContainerDidShow(
            pageInfo['name'] as String,
            (pageInfo['params'] as Map<dynamic, dynamic>)
                ?.cast<String, dynamic>(),
            pageInfo['uniqueId'] as String,
          );
        }
      });
    });
  }

  static TransitionBuilder init({
    TransitionBuilder builder,
    PrePushRoute prePush,
    PostPushRoute postPush,
  }) {
    if (Platform.isAndroid) {
      onPageStart();
    } else if (Platform.isIOS) {
      // TODO(AlexVincent525): 未解之谜
      assert(() {
        () async {
          onPageStart();
        }();
        return true;
      }());
    }

    return (BuildContext context, Widget child) {
      assert(child is Navigator, 'child must be Navigator, what is wrong?');

      final BoostContainerManager manager = BoostContainerManager(
        key: _instance.containerManagerKey,
        initNavigator: child as Navigator,
        prePushRoute: prePush,
        postPushRoute: postPush,
      );

      if (builder != null) {
        return builder(context, manager);
      } else {
        return manager;
      }
    };
  }

  ObserversHolder get observersHolder => _observersHolder;

  BoostChannel get channel => _boostChannel;

  /// Register a default page builder.
  void registerDefaultPageBuilder(PageBuilder builder) {
    ContainerCoordinator.singleton.registerDefaultPageBuilder(builder);
  }

  /// Register a map builders
  void registerPageBuilders(Map<String, PageBuilder> builders) {
    ContainerCoordinator.singleton.registerPageBuilders(builders);
  }

  Future<Map<String, dynamic>> open(
    String url, {
    Map<String, dynamic> urlParams,
    Map<String, dynamic> exts,
  }) {
    final Map<String, dynamic> properties = <String, dynamic>{};
    properties['url'] = url;
    properties['urlParams'] = urlParams;
    properties['exts'] = exts;
    return channel.invokeMethod<Map<String, dynamic>>('openPage', properties);
  }

  Future<bool> close(
    String id, {
    Map<String, dynamic> result,
    Map<String, dynamic> exts,
  }) {
    assert(id != null);

    final BoostContainerSettings settings = containerManager?.onstageSettings;
    final Map<String, dynamic> properties = <String, dynamic>{};

    exts ??= <String, dynamic>{};

    exts['params'] = settings.params;

    if (!exts.containsKey('animated')) {
      exts['animated'] = true;
    }

    properties['uniqueId'] = id;

    if (result != null) {
      properties['result'] = result;
    }

    if (exts != null) {
      properties['exts'] = exts;
    }
    return channel.invokeMethod<bool>('closePage', properties);
  }

  Future<bool> closeCurrent({
    Map<String, dynamic> result,
    Map<String, dynamic> exts,
  }) {
    final BoostContainerSettings settings = containerManager?.onstageSettings;
    exts ??= <String, dynamic>{};
    exts['params'] = settings.params;
    if (!exts.containsKey('animated')) {
      exts['animated'] = true;
    }
    return close(settings.uniqueId, result: result, exts: exts);
  }

  Future<bool> closeByContext(
    BuildContext context, {
    Map<String, dynamic> result,
    Map<String, dynamic> exts,
  }) {
    final BoostContainerSettings settings = containerManager?.onstageSettings;
    exts ??= <String, dynamic>{};
    exts['params'] = settings.params;
    if (!exts.containsKey('animated')) {
      exts['animated'] = true;
    }
    return close(settings.uniqueId, result: result, exts: exts);
  }

  /// Register for Container changed callbacks
  VoidCallback addContainerObserver(BoostContainerObserver observer) =>
      _observersHolder.addObserver<BoostContainerObserver>(observer);

  /// Register for Container lifecycle callbacks
  VoidCallback addBoostContainerLifeCycleObserver(
          BoostContainerLifeCycleObserver observer) =>
      _observersHolder.addObserver<BoostContainerLifeCycleObserver>(observer);

  /// Register callbacks for [Navigator.push] & [Navigator.pop]
  void addBoostNavigatorObserver(NavigatorObserver observer) =>
      ContainerNavigatorObserver.boostObservers.add(observer);
}
