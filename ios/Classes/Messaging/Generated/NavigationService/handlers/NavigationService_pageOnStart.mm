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

 //Generated by AIOCodeGen.
 
 #import "ServiceGateway.h"
 #import "NavigationService_pageOnStart.h"
#import "FlutterBoostPlugin_private.h"

 @implementation NavigationService_pageOnStart

 - (void)onCall:(void (^)(NSDictionary *))result params:(NSDictionary *)params 
 {
     NSMutableDictionary *pageInfo = [NSMutableDictionary new];
     pageInfo[@"name"] =[FlutterBoostPlugin sharedInstance].fPagename;
     pageInfo[@"params"] = [FlutterBoostPlugin sharedInstance].fParams;
     pageInfo[@"uniqueId"] = [FlutterBoostPlugin sharedInstance].fPageId;
     if(result) result(pageInfo);
 }
 
 #pragma mark - Do not edit these method.
 - (void)__flutter_p_handler_pageOnStart:(NSDictionary *)args result:(void (^)(NSDictionary *))result {
     [self onCall:result params:args[@"params"]];
 }
 + (void)load{
     [[ServiceGateway sharedInstance] registerHandler:[NavigationService_pageOnStart new]];
 }
 - (NSString *)returnType
 {
   return @"NSDictionary *";
 }
 - (NSString *)service
 {
   return @"NavigationService";
 }
 
 @end
