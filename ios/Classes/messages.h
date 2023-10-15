// Autogenerated from Pigeon (v0.1.17), do not edit directly.
// See also: https://pub.dev/packages/pigeon
#import <Foundation/Foundation.h>
@protocol FlutterBinaryMessenger;
@class FlutterError;
@class FlutterStandardTypedData;

NS_ASSUME_NONNULL_BEGIN

@class HRCommonParams;

@interface HRCommonParams : NSObject
@property(nonatomic, copy, nullable) NSString * pageName;
@property(nonatomic, copy, nullable) NSString * uniqueId;
@property(nonatomic, copy, nullable) NSString * groupName;
@property(nonatomic, strong, nullable) NSNumber * openContainer;
@property(nonatomic, strong, nullable) NSDictionary * arguments;
@end

@interface HRFlutterRouterApi : NSObject
- (instancetype)initWithBinaryMessenger:(id<FlutterBinaryMessenger>)binaryMessenger;
- (void)pushRoute:(HRCommonParams*)input completion:(void(^)(NSError* _Nullable))completion;
- (void)pushOrShowRoute:(HRCommonParams*)input completion:(void(^)(NSError* _Nullable))completion;
- (void)showTabRoute:(HRCommonParams*)input completion:(void(^)(NSError* _Nullable))completion;
- (void)popRoute:(HRCommonParams*)input completion:(void(^)(NSError* _Nullable))completion;
@end
@protocol HRNativeRouterApi
-(void)pushNativeRoute:(HRCommonParams*)input error:(FlutterError *_Nullable *_Nonnull)error;
-(void)pushFlutterRoute:(HRCommonParams*)input error:(FlutterError *_Nullable *_Nonnull)error;
-(void)popRoute:(HRCommonParams*)input error:(FlutterError *_Nullable *_Nonnull)error;
@end

extern void HRNativeRouterApiSetup(id<FlutterBinaryMessenger> binaryMessenger, id<HRNativeRouterApi> _Nullable api);

NS_ASSUME_NONNULL_END
