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
#import "FlutterBoost.h"
#import "FLBFlutterContainerManager.h"
#import "FLBViewProviderFactory.h"

@interface FLBFlutterApplication()
@property (nonatomic,strong) FLBFlutterContainerManager *manager;
@property (nonatomic,strong) id<FLBFlutterViewProvider> viewProvider;

@property (nonatomic,assign) BOOL isRendering;
@property (nonatomic,assign) BOOL isRunning;
@end


@implementation FLBFlutterApplication

- (BOOL)isRunning
{
    return _isRunning;
}

- (void)startFlutterWithPlatform:(id<FLB2Platform>)platform onStart:(void (^)(id<FlutterBinaryMessenger,FlutterTextureRegistry,FlutterPluginRegistry> _Nonnull))callback
{
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        self.platform = platform;
        self.viewProvider = [[FLBViewProviderFactory new] createViewProviderWithPlatform:platform];
        [self.viewProvider resume];
        self.isRendering = YES;
        self.isRunning = YES;
        if(callback) callback(self.viewProvider.viewController);
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

- (FlutterViewController *)flutterViewController
{
    return self.viewProvider.viewController;
}


- (BOOL)contains:(id<FLBFlutterContainer>)vc
{
    return [_manager contains:vc];
}

- (void)addUniqueViewController:(id<FLBFlutterContainer>)vc
{
    return [_manager addUnique:vc];
}

- (void)removeViewController:(id<FLBFlutterContainer>)vc
{
    return [_manager remove:vc];
}


- (BOOL)isTop:(NSString *)pageId
{
    return [_manager.peak isEqual:pageId];
}

- (void)pause
{
    if(!_isRendering) return;
    
    [self.viewProvider pause];
    
    _isRendering = NO;
    
}

- (void)resume
{
    if(_isRendering) return;
    
    [self.viewProvider resume];
    
    _isRendering = YES;
    
}

- (void)inactive
{
    [self.viewProvider inactive];
}

- (id<FLB2FlutterProvider>)flutterProvider
{
    return (id)_viewProvider;
}

- (void)close:(NSString *)uid
       result:(NSDictionary *)result
         exts:(NSDictionary *)exts
   completion:(void (^)(BOOL))completion
{
    [self.platform closePage:uid
                    animated:[exts[@"animated"] boolValue]
                      params:exts[@"params"]
                  completion:completion];
}

- (void)open:(NSString *)url
   urlParams:(NSDictionary *)urlParams
        exts:(NSDictionary *)exts
       reult:(void (^)(NSDictionary *))resultCallback
  completion:(void (^)(BOOL))completion
{
    [self.platform openPage:url
                     params:urlParams
                   animated:[exts[@"animated"] boolValue]
                 completion:completion];
}

@end
