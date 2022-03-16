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
#import <Flutter/Flutter.h>

#import "FLB2Platform.h"
#import "FLBBroadcastor.h"

@interface FlutterBoostPlugin : NSObject<FlutterPlugin>


#pragma mark - Initializer
+ (instancetype)sharedInstance;

- (void)startFlutterWithPlatform:(id<FLB2Platform>)platform
                         onStart:(void (^)(id<FlutterBinaryMessenger, FlutterTextureRegistry, FlutterPluginRegistry> engine))callback;

#pragma mark - Some properties.
- (BOOL)isRunning;
- (FlutterViewController *)currentViewController;

#pragma mark - broadcast event to/from flutter
- (void)sendEvent:(NSString *)eventName
        arguments:(NSDictionary *)arguments
           result:(FlutterResult)result;

- (FLBVoidCallback)addEventListener:(FLBEventListener)listner
                            forName:(NSString *)name;

#pragma mark - handing vc result.
- (void)openPage:(NSString *)name
          params:(NSDictionary *)params
        animated:(BOOL)animated
      completion:(void (^)(BOOL finished))completion
   resultHandler:(void (^)(NSString *resultId,NSDictionary *rData))resultHandler;

- (void)onResultForKey:(NSString *)vcId
            resultData:(NSDictionary *)resultData
                params:(NSDictionary *)params;

- (void)setResultHandler:(void (^)(NSString *, NSDictionary *))handler
                  forKey:(NSString *)result_id;
- (void)removeHandlerForKey:(NSString *)vcid;
@end
