// Autogenerated from Pigeon (v0.1.17), do not edit directly.
// See also: https://pub.dev/packages/pigeon

package com.idlefish.flutterboost;

import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.StandardMessageCodec;
import java.util.ArrayList;
import java.util.HashMap;

/** Generated class from Pigeon. */
@SuppressWarnings("unused")
public class Messages {

  /** Generated class from Pigeon that represents data sent in messages. */
  public static class CommonParams {
    private String pageName;
    public String getPageName() { return pageName; }
    public void setPageName(String setterArg) { this.pageName = setterArg; }

    private String uniqueId;
    public String getUniqueId() { return uniqueId; }
    public void setUniqueId(String setterArg) { this.uniqueId = setterArg; }

    private Long hint;
    public Long getHint() { return hint; }
    public void setHint(Long setterArg) { this.hint = setterArg; }

    private HashMap arguments;
    public HashMap getArguments() { return arguments; }
    public void setArguments(HashMap setterArg) { this.arguments = setterArg; }

    HashMap toMap() {
      HashMap<String, Object> toMapResult = new HashMap<>();
      toMapResult.put("pageName", pageName);
      toMapResult.put("uniqueId", uniqueId);
      toMapResult.put("hint", hint);
      toMapResult.put("arguments", arguments);
      return toMapResult;
    }
    static CommonParams fromMap(HashMap map) {
      CommonParams fromMapResult = new CommonParams();
      Object pageName = map.get("pageName");
      fromMapResult.pageName = (String)pageName;
      Object uniqueId = map.get("uniqueId");
      fromMapResult.uniqueId = (String)uniqueId;
      Object hint = map.get("hint");
      fromMapResult.hint = (hint == null) ? null : ((hint instanceof Integer) ? (Integer)hint : (Long)hint);
      Object arguments = map.get("arguments");
      fromMapResult.arguments = (HashMap)arguments;
      return fromMapResult;
    }
  }

  /** Generated class from Pigeon that represents Flutter messages that can be called from Java.*/
  public static class FlutterRouterApi {
    private final BinaryMessenger binaryMessenger;
    public FlutterRouterApi(BinaryMessenger argBinaryMessenger){
      this.binaryMessenger = argBinaryMessenger;
    }
    public interface Reply<T> {
      void reply(T reply);
    }
    public void pushRoute(CommonParams argInput, Reply<Void> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.FlutterRouterApi.pushRoute", new StandardMessageCodec());
      HashMap inputMap = argInput.toMap();
      channel.send(inputMap, channelReply -> {
        callback.reply(null);
      });
    }
    public void popRoute(CommonParams argInput, Reply<Void> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.FlutterRouterApi.popRoute", new StandardMessageCodec());
      HashMap inputMap = argInput.toMap();
      channel.send(inputMap, channelReply -> {
        callback.reply(null);
      });
    }
    public void removeRoute(CommonParams argInput, Reply<Void> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.FlutterRouterApi.removeRoute", new StandardMessageCodec());
      HashMap inputMap = argInput.toMap();
      channel.send(inputMap, channelReply -> {
        callback.reply(null);
      });
    }
    public void onForeground(CommonParams argInput, Reply<Void> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.FlutterRouterApi.onForeground", new StandardMessageCodec());
      HashMap inputMap = argInput.toMap();
      channel.send(inputMap, channelReply -> {
        callback.reply(null);
      });
    }
    public void onBackground(CommonParams argInput, Reply<Void> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.FlutterRouterApi.onBackground", new StandardMessageCodec());
      HashMap inputMap = argInput.toMap();
      channel.send(inputMap, channelReply -> {
        callback.reply(null);
      });
    }
    public void onAppear(CommonParams argInput, Reply<Void> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.FlutterRouterApi.onAppear", new StandardMessageCodec());
      HashMap inputMap = argInput.toMap();
      channel.send(inputMap, channelReply -> {
        callback.reply(null);
      });
    }
    public void onDisappear(CommonParams argInput, Reply<Void> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.FlutterRouterApi.onDisappear", new StandardMessageCodec());
      HashMap inputMap = argInput.toMap();
      channel.send(inputMap, channelReply -> {
        callback.reply(null);
      });
    }
  }

  /** Generated interface from Pigeon that represents a handler of messages from Flutter.*/
  public interface NativeRouterApi {
    void pushNativeRoute(CommonParams arg);
    void pushFlutterRoute(CommonParams arg);
    void popRoute(CommonParams arg);

    /** Sets up an instance of `NativeRouterApi` to handle messages through the `binaryMessenger` */
    static void setup(BinaryMessenger binaryMessenger, NativeRouterApi api) {
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.NativeRouterApi.pushNativeRoute", new StandardMessageCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            HashMap<String, HashMap> wrapped = new HashMap<>();
            try {
              @SuppressWarnings("ConstantConditions")
              CommonParams input = CommonParams.fromMap((HashMap)message);
              api.pushNativeRoute(input);
              wrapped.put("result", null);
            }
            catch (Exception exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.NativeRouterApi.pushFlutterRoute", new StandardMessageCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            HashMap<String, HashMap> wrapped = new HashMap<>();
            try {
              @SuppressWarnings("ConstantConditions")
              CommonParams input = CommonParams.fromMap((HashMap)message);
              api.pushFlutterRoute(input);
              wrapped.put("result", null);
            }
            catch (Exception exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.NativeRouterApi.popRoute", new StandardMessageCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            HashMap<String, HashMap> wrapped = new HashMap<>();
            try {
              @SuppressWarnings("ConstantConditions")
              CommonParams input = CommonParams.fromMap((HashMap)message);
              api.popRoute(input);
              wrapped.put("result", null);
            }
            catch (Exception exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
    }
  }
  private static HashMap wrapError(Exception exception) {
    HashMap<String, Object> errorMap = new HashMap<>();
    errorMap.put("message", exception.toString());
    errorMap.put("code", exception.getClass().getSimpleName());
    errorMap.put("details", null);
    return errorMap;
  }
}
