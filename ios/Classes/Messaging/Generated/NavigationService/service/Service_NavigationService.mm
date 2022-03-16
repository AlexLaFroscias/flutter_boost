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

 //Generated by AIOCodeGen. Do not edit!
 
 #import "Service_NavigationService.h"
 #import "ServiceGateway.h"
 #import "FlutterServiceTemplate.h"
 
 @implementation Service_NavigationService
 
 + (FlutterServiceTemplate *)service
 {
     static id _instance = nil;
     static dispatch_once_t onceToken;
     dispatch_once(&onceToken, ^{
         _instance = [[FlutterServiceTemplate alloc] initWithName:@"NavigationService"];
     });
     
     return _instance;
 }
 
 + (void)load{
      [[ServiceGateway sharedInstance] addService:[self service]];
 }
 
 + (void)onNativePageResult:(void (^)(NSNumber *))result uniqueId:(NSString *)uniqueId key:(NSString *)key resultData:(NSDictionary *)resultData params:(NSDictionary *)params
 {
     NSMutableDictionary *tmp = [NSMutableDictionary dictionary];
     if(uniqueId) tmp[@"uniqueId"] = uniqueId;
     if(key) tmp[@"key"] = key;
     if(resultData) tmp[@"resultData"] = resultData;
     if(params) tmp[@"params"] = params;
     [self.service invoke:@"onNativePageResult" args:tmp result:^(id tTesult) {
         if (result) {
             result(tTesult);
         }
     }];
 }
 
 + (void)didShowPageContainer:(void (^)(NSNumber *))result pageName:(NSString *)pageName params:(NSDictionary *)params uniqueId:(NSString *)uniqueId
 {
     NSMutableDictionary *tmp = [NSMutableDictionary dictionary];
     if(pageName) tmp[@"pageName"] = pageName;
     if(params) tmp[@"params"] = params;
     if(uniqueId) tmp[@"uniqueId"] = uniqueId;
     [self.service invoke:@"didShowPageContainer" args:tmp result:^(id tTesult) {
         if (result) {
             result(tTesult);
         }
     }];
 }
 
 + (void)willShowPageContainer:(void (^)(NSNumber *))result pageName:(NSString *)pageName params:(NSDictionary *)params uniqueId:(NSString *)uniqueId
 {
     NSMutableDictionary *tmp = [NSMutableDictionary dictionary];
     if(pageName) tmp[@"pageName"] = pageName;
     if(params) tmp[@"params"] = params;
     if(uniqueId) tmp[@"uniqueId"] = uniqueId;
     [self.service invoke:@"willShowPageContainer" args:tmp result:^(id tTesult) {
         if (result) {
             result(tTesult);
         }
     }];
 }
 
 + (void)willDisappearPageContainer:(void (^)(NSNumber *))result pageName:(NSString *)pageName params:(NSDictionary *)params uniqueId:(NSString *)uniqueId
 {
     NSMutableDictionary *tmp = [NSMutableDictionary dictionary];
     if(pageName) tmp[@"pageName"] = pageName;
     if(params) tmp[@"params"] = params;
     if(uniqueId) tmp[@"uniqueId"] = uniqueId;
     [self.service invoke:@"willDisappearPageContainer" args:tmp result:^(id tTesult) {
         if (result) {
             result(tTesult);
         }
     }];
 }
 
 + (void)didDisappearPageContainer:(void (^)(NSNumber *))result pageName:(NSString *)pageName params:(NSDictionary *)params uniqueId:(NSString *)uniqueId
 {
     NSMutableDictionary *tmp = [NSMutableDictionary dictionary];
     if(pageName) tmp[@"pageName"] = pageName;
     if(params) tmp[@"params"] = params;
     if(uniqueId) tmp[@"uniqueId"] = uniqueId;
     [self.service invoke:@"didDisappearPageContainer" args:tmp result:^(id tTesult) {
         if (result) {
             result(tTesult);
         }
     }];
 }
 
 + (void)didInitPageContainer:(void (^)(NSNumber *))result pageName:(NSString *)pageName params:(NSDictionary *)params uniqueId:(NSString *)uniqueId
 {
     NSMutableDictionary *tmp = [NSMutableDictionary dictionary];
     if(pageName) tmp[@"pageName"] = pageName;
     if(params) tmp[@"params"] = params;
     if(uniqueId) tmp[@"uniqueId"] = uniqueId;
     [self.service invoke:@"didInitPageContainer" args:tmp result:^(id tTesult) {
         if (result) {
             result(tTesult);
         }
     }];
 }
 
 + (void)willDeallocPageContainer:(void (^)(NSNumber *))result pageName:(NSString *)pageName params:(NSDictionary *)params uniqueId:(NSString *)uniqueId
 {
     NSMutableDictionary *tmp = [NSMutableDictionary dictionary];
     if(pageName) tmp[@"pageName"] = pageName;
     if(params) tmp[@"params"] = params;
     if(uniqueId) tmp[@"uniqueId"] = uniqueId;
     [self.service invoke:@"willDeallocPageContainer" args:tmp result:^(id tTesult) {
         if (result) {
             result(tTesult);
         }
     }];
 }

+ (void)canPopPageName:(NSString *)pageName params:(NSDictionary *)params uniqueId:(NSString *)uniqueId{
    NSMutableDictionary *tmp = [NSMutableDictionary dictionary];
    if(pageName) tmp[@"pageName"] = pageName;
    if(params) tmp[@"params"] = params;
    if(uniqueId) tmp[@"uniqueId"] = uniqueId;
    [self.service invoke:@"canPop" args:tmp result:^(id tTesult) {
    }];
}
 
 @end
