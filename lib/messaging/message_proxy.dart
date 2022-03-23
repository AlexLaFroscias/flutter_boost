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
import 'package:flutter_boost/messaging/service/navigation_service.dart';
import 'dart:async';

abstract class MessageProxy{
  Future<Map<String,dynamic>> open(String url,{Map<String,dynamic> urlParams,Map<String,dynamic> exts});
  void close(String id,{Map<String,dynamic> result,Map<String,dynamic> exts});
}

class MessageProxyImp implements MessageProxy{

  @override
  Future<Map<String,dynamic>> open(String url,{Map<String,dynamic> urlParams,Map<String,dynamic> exts}){
    return NavigationService.openPage(url, urlParams, exts);
  }

  @override
  void close(String id,{Map<String,dynamic> result,Map<String,dynamic> exts}){
    return NavigationService.closePage(uniqueId,result: result,exts: exts);
  }



}

