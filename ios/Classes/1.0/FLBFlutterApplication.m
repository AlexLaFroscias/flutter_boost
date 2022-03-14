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

#import "FLBFlutterApplication.h"
#import "FLBFlutterContainerManager.h"
#import "FLBFlutterEngine.h"

@interface FLBFlutterApplication()
@property (nonatomic,strong) FLBFlutterContainerManager *manager;
@property (nonatomic,strong) id<FLB2FlutterProvider> viewProvider;
@property (nonatomic,assign) BOOL isRunning;
@end


@implementation FLBFlutterApplication

+ (FLBFlutterApplication *)sharedApplication
{
    static FLBFlutterApplication *instance = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        instance = [self new];
    });
    return instance;
}

- (BOOL)isRunning
{
    return _isRunning;
}

- (id)flutterProvider
{
    return _viewProvider;
}

- (void)startFlutterWithPlatform:(id<FLB2Platform>)platform
                         onStart:(void (^)(id<FlutterBinaryMessenger,FlutterTextureRegistry,FlutterPluginRegistry> _Nonnull))callback
{
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        self.platform = platform;
        self.viewProvider = [[FLBFlutterEngine alloc] init];
        _isRunning = YES;
        if(callback) callback(self.viewProvider.engine);
    });
}

- (instancetype)init
{
    if (self = [super init]) {
        _manager = [FLBFlutterContainerManager new];
    }
    return self;
}

- (void)dealloc
{
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}

- (UIView *)flutterView
{
    return [self flutterViewController].view;
}


- (BOOL)contains:(FLBFlutterViewContainer  *)vc
{
    return [_manager contains:vc];
}

- (void)addUniqueViewController:(FLBFlutterViewContainer  *)vc
{
    return [_manager addUnique:vc];
}

- (void)removeViewController:(FLBFlutterViewContainer  *)vc
{
    return [_manager remove:vc];
}


- (BOOL)isTop:(NSString *)pageId
{
    return [_manager.peak isEqual:pageId];
}

- (void)pause
{
    [self.viewProvider pause];
}

- (void)resume
{
    [self.viewProvider resume];
}

- (void)inactive
{
    [self.viewProvider inactive];
}

- (FlutterViewController *)flutterViewController
{
    return self.flutterProvider.engine.viewController;
}

@end
