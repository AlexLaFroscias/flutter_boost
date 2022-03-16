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

 
#import "ServiceGateway.h"
#import "NavigationService_closePage.h"
#import "FlutterBoostPlugin_private.h"

#define FLUTTER_APP [FlutterBoostPlugin sharedInstance].application
 
 @implementation NavigationService_closePage
 
 - (void)onCall:(void (^)(BOOL))result
       uniqueId:(NSString *)uniqueId
       pageName:(NSString *)pageName
         params:(NSDictionary *)params
       animated:(NSNumber *)animated
 {
     //Add your handler code here!
     [FLUTTER_APP.platform closePage:uniqueId
                            animated:animated.boolValue
                              params:params
                          completion:^(BOOL finished) {
                    if(result) result(finished);
     }];
 }
 
 #pragma mark - Do not edit these method.
- (BOOL)call:(id<FLBMessage>)msg result:(void (^)(BOOL))result
{
    NSDictionary *args = msg.params;
     [self onCall:result
         uniqueId:args[@"uniqueId"]
         pageName:args[@"pageName"]
           params:args[@"params"]
         animated:args[@"animated"]];
    return YES;
 }

 - (NSString *)returnType
 {
   return @"BOOL";
 }

- (NSArray *)handledMessageNames
{
    return @[@"closePage"];
}


 @end
