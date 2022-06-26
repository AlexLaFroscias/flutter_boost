<p align="center">
  <img src="flutter_boost.png">
</p>


# Release Note

 请查看最新版本0.1.50的release note 确认变更，[0.1.50 release note](https://github.com/alibaba/flutter_boost/releases)。

# FlutterBoost

新一代Flutter-Native混合解决方案。 FlutterBoost是一个Flutter插件，它可以轻松地为现有原生应用程序提供Flutter混合集成方案。FlutterBoost的理念是将Flutter像Webview那样来使用。在现有应用程序中同时管理Native页面和Flutter页面并非易事。 FlutterBoost帮你处理页面的映射和跳转，你只需关心页面的名字和参数即可（通常可以是URL）。


# 前置条件
在继续之前，您需要将Flutter集成到你现有的项目中。flutter sdk 的版本需要 v1.5.4-hotfixes，否则会编译失败.

# 安装

## 在Flutter项目中添加依赖项。

打开pubspec.yaml并将以下行添加到依赖项：

```json
flutter_boost: ^0.1.54
```

或者可以直接依赖github的项目的版本，Tag，pub发布会有延迟，推荐直接依赖Github项目

```java

flutter_boost:
        git:
            url: 'https://github.com/alibaba/flutter_boost.git'
            ref: '0.1.54'
            
```
## Dart代码的集成
将init代码添加到App App

```dart
void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();

    ///register page widget builders,the key is pageName
    FlutterBoost.singleton.registerPageBuilders({
      'sample://firstPage': (pageName, params, _) => FirstRouteWidget(),
      'sample://secondPage': (pageName, params, _) => SecondRouteWidget(),
    });

  }

  @override
  Widget build(BuildContext context) => MaterialApp(
      title: 'Flutter Boost example',
      builder: FlutterBoost.init(), ///init container manager
      home: Container());
}
```

## iOS代码集成。

注意：需要将libc++ 加入 "Linked Frameworks and Libraries" 中。

使用FLBFlutterAppDelegate作为AppDelegate的超类

```objectivec
@interface AppDelegate : FLBFlutterAppDelegate <UIApplicationDelegate>
@end
```


为您的应用程序实现FLBPlatform协议方法。

```objectivec
@interface PlatformRouterImp : NSObject<FLBPlatform>

@property (nonatomic,strong) UINavigationController *navigationController;

+ (PlatformRouterImp *)sharedRouter;

@end


@implementation PlatformRouterImp

- (void)openPage:(NSString *)name
          params:(NSDictionary *)params
        animated:(BOOL)animated
      completion:(void (^)(BOOL))completion
{
    if([params[@"present"] boolValue]){
        FLBFlutterViewContainer *vc = FLBFlutterViewContainer.new;
        [vc setName:name params:params];
        [self.navigationController presentViewController:vc animated:animated completion:^{}];
    }else{
        FLBFlutterViewContainer *vc = FLBFlutterViewContainer.new;
        [vc setName:name params:params];
        [self.navigationController pushViewController:vc animated:animated];
    }
}


- (void)closePage:(NSString *)uid animated:(BOOL)animated params:(NSDictionary *)params completion:(void (^)(BOOL))completion
{
    FLBFlutterViewContainer *vc = (id)self.navigationController.presentedViewController;
    if([vc isKindOfClass:FLBFlutterViewContainer.class] && [vc.uniqueIDString isEqual: uid]){
        [vc dismissViewControllerAnimated:animated completion:^{}];
    }else{
        [self.navigationController popViewControllerAnimated:animated];
    }
}

@end
```



在应用程序开头使用FLBPlatform初始化FlutterBoost。

```objc
 PlatformRouterImp *router = [PlatformRouterImp new];
 [FlutterBoostPlugin.sharedInstance startFlutterWithPlatform：router
                                                        onStart：^（FlutterEngine *engine）{
                                                            
                                                        }];
```

## Android代码集成。

在Application.onCreate（）中初始化FlutterBoost

```java
public class MyApplication extends FlutterApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        FlutterBoostPlugin.init(new IPlatform() {
            @Override
            public Application getApplication() {
                return MyApplication.this;
            }

            @Override
            public boolean isDebug() {
                return true;
            }

            @Override
            public void openContainer(Context context, String url, Map<String, Object> urlParams, int requestCode, Map<String, Object> exts) {
                PageRouter.openPageByUrl(context,url,urlParams,requestCode);
            }

            @Override
            public IFlutterEngineProvider engineProvider() {
                return new BoostEngineProvider(){
                    @Override
                    public BoostFlutterEngine createEngine(Context context) {
                        return new BoostFlutterEngine(context, new DartExecutor.DartEntrypoint(
                                context.getResources().getAssets(),
                                FlutterMain.findAppBundlePath(context),
                                "main"),"/");
                    }
                };
            }

            @Override
            public int whenEngineStart() {
                return ANY_ACTIVITY_CREATED;
            }
        });
    }
```

# 基本用法
## 概念

所有页面路由请求都将发送到Native路由器。Native路由器与Native Container Manager通信，Native Container Manager负责构建和销毁Native Containers。

## 使用Flutter Boost Native Container用Native代码打开Flutter页面。

```objc
 FLBFlutterViewContainer *vc = FLBFlutterViewContainer.new;
        [vc setName:name params:params];
        [self.navigationController presentViewController:vc animated:animated completion:^{}];
```

但是，这种方式无法获取页面返回的数据，建议你按上面的example实现类似于PlatformRouterImp这样的路由器，然后通过以下方式来打开/关闭页面

```objc
//push the page
[FlutterBoostPlugin open:@"first" urlParams:@{kPageCallBackId:@"MycallbackId#1"} exts:@{@"animated":@(YES)} onPageFinished:^(NSDictionary *result) {
        NSLog(@"call me when page finished, and your result is:%@", result);
    } completion:^(BOOL f) {
        NSLog(@"page is opened");
    }];
//prsent the page
[FlutterBoostPlugin open:@"second" urlParams:@{@"present":@(YES),kPageCallBackId:@"MycallbackId#2"} exts:@{@"animated":@(YES)} onPageFinished:^(NSDictionary *result) {
        NSLog(@"call me when page finished, and your result is:%@", result);
    } completion:^(BOOL f) {
        NSLog(@"page is presented");
    }];
//close the page
[FlutterBoostPlugin close:yourUniqueId result:yourdata exts:exts completion:nil];
```
Android

```java
public class FlutterPageActivity extends BoostFlutterActivity {


    @Override
    public String getContainerUrl() {
        //specify the page name register in FlutterBoost
        return "sample://firstPage";
    }

    @Override
    public Map getContainerUrlParams() {
        //params of the page
        Map<String,String> params = new HashMap<>();
        params.put("key","value");
        return params;
    }
}
```

或者用Fragment

```java
public class FlutterFragment extends BoostFlutterFragment {

    @Override
    public String getContainerUrl() {
        return "sample://firstPage";
    }

    @Override
    public Map getContainerUrlParams() {
        Map<String,String> params = new HashMap<>();
        params.put("key","value");
        return params;
    }
}
```


## 使用Flutter Boost在dart代码打开页面。
Dart

```java

 FlutterBoost.singleton
                .open("sample://flutterFragmentPage")

```


## 使用Flutter Boost在dart代码关闭页面。

```java
 FlutterBoost.singleton.close(uniqueId);
```

# Examples
更详细的使用例子请参考Demo

# 许可证
该项目根据MIT许可证授权 - 有关详细信息，请参阅[LICENSE.md]（LICENSE.md）文件
<a name="Acknowledgments"> </a>

# 问题反馈群（钉钉群)

<img width="200" src="https://img.alicdn.com/tfs/TB1JSzVeYY1gK0jSZTEXXXDQVXa-892-1213.jpg">


## 关于我们
阿里巴巴-闲鱼技术是国内最早也是最大规模线上运行Flutter的团队。

我们在公众号中为你精选了Flutter独家干货，全面而深入。

内容包括：Flutter的接入、规模化应用、引擎探秘、工程体系、创新技术等教程和开源信息。

**架构／服务端／客户端／前端／算法／质量工程师 在公众号中投递简历，名额不限哦**

欢迎来闲鱼做一个好奇、幸福、有影响力的程序员，简历投递：tino.wjf@alibaba-inc.com

订阅地址

<img src="https://img.alicdn.com/tfs/TB17Ki5XubviK0jSZFNXXaApXXa-656-656.png" width="328px" height="328px">

[For English](https://twitter.com/xianyutech "For English")
