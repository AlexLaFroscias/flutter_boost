// Autogenerated from Pigeon (v0.1.23), do not edit directly.
// See also: https://pub.dev/packages/pigeon

package com.idlefish.flutterboost;

import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.StandardMessageCodec;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/** Generated class from Pigeon. */
@SuppressWarnings({"unused", "unchecked", "CodeBlock2Expr", "RedundantSuppression"})
public class Messages {

  /** Generated class from Pigeon that represents data sent in messages. */
  public static class CommonParams {
    private String pageName;
    public String getPageName() { return pageName; }
    public void setPageName(String setterArg) { this.pageName = setterArg; }

    private String uniqueId;
    public String getUniqueId() { return uniqueId; }
    public void setUniqueId(String setterArg) { this.uniqueId = setterArg; }

    private Map<Object, Object> arguments;
    public Map<Object, Object> getArguments() { return arguments; }
    public void setArguments(Map<Object, Object> setterArg) { this.arguments = setterArg; }

    Map<String, Object> toMap() {
      Map<String, Object> toMapResult = new HashMap<>();
      toMapResult.put("pageName", pageName);
      toMapResult.put("uniqueId", uniqueId);
      toMapResult.put("arguments", arguments);
      return toMapResult;
    }
    static CommonParams fromMap(Map<String, Object> map) {
      CommonParams fromMapResult = new CommonParams();
      Object pageName = map.get("pageName");
      fromMapResult.pageName = (String)pageName;
      Object uniqueId = map.get("uniqueId");
      fromMapResult.uniqueId = (String)uniqueId;
      Object arguments = map.get("arguments");
      fromMapResult.arguments = (Map<Object, Object>)arguments;
      return fromMapResult;
    }
  }

  /** Generated class from Pigeon that represents data sent in messages. */
  public static class StackInfo {
    private List<Object> containers;
    public List<Object> getContainers() { return containers; }
    public void setContainers(List<Object> setterArg) { this.containers = setterArg; }

    private Map<Object, Object> routes;
    public Map<Object, Object> getRoutes() { return routes; }
    public void setRoutes(Map<Object, Object> setterArg) { this.routes = setterArg; }

    Map<String, Object> toMap() {
      Map<String, Object> toMapResult = new HashMap<>();
      toMapResult.put("containers", containers);
      toMapResult.put("routes", routes);
      return toMapResult;
    }
    static StackInfo fromMap(Map<String, Object> map) {
      StackInfo fromMapResult = new StackInfo();
      Object containers = map.get("containers");
      fromMapResult.containers = (List<Object>)containers;
      Object routes = map.get("routes");
      fromMapResult.routes = (Map<Object, Object>)routes;
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
      Map<String, Object> inputMap = argInput.toMap();
      channel.send(inputMap, channelReply -> {
        callback.reply(null);
      });
    }
    public void popRoute(CommonParams argInput, Reply<Void> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.FlutterRouterApi.popRoute", new StandardMessageCodec());
      Map<String, Object> inputMap = argInput.toMap();
      channel.send(inputMap, channelReply -> {
        callback.reply(null);
      });
    }
    public void removeRoute(CommonParams argInput, Reply<Void> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.FlutterRouterApi.removeRoute", new StandardMessageCodec());
      Map<String, Object> inputMap = argInput.toMap();
      channel.send(inputMap, channelReply -> {
        callback.reply(null);
      });
    }
    public void onForeground(CommonParams argInput, Reply<Void> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.FlutterRouterApi.onForeground", new StandardMessageCodec());
      Map<String, Object> inputMap = argInput.toMap();
      channel.send(inputMap, channelReply -> {
        callback.reply(null);
      });
    }
    public void onBackground(CommonParams argInput, Reply<Void> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.FlutterRouterApi.onBackground", new StandardMessageCodec());
      Map<String, Object> inputMap = argInput.toMap();
      channel.send(inputMap, channelReply -> {
        callback.reply(null);
      });
    }
    public void onNativeViewShow(CommonParams argInput, Reply<Void> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.FlutterRouterApi.onNativeViewShow", new StandardMessageCodec());
      Map<String, Object> inputMap = argInput.toMap();
      channel.send(inputMap, channelReply -> {
        callback.reply(null);
      });
    }
    public void onNativeViewHide(CommonParams argInput, Reply<Void> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.FlutterRouterApi.onNativeViewHide", new StandardMessageCodec());
      Map<String, Object> inputMap = argInput.toMap();
      channel.send(inputMap, channelReply -> {
        callback.reply(null);
      });
    }
    public void onNativeResult(CommonParams argInput, Reply<Void> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.FlutterRouterApi.onNativeResult", new StandardMessageCodec());
      Map<String, Object> inputMap = argInput.toMap();
      channel.send(inputMap, channelReply -> {
        callback.reply(null);
      });
    }
    public void onContainerShow(CommonParams argInput, Reply<Void> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.FlutterRouterApi.onContainerShow", new StandardMessageCodec());
      Map<String, Object> inputMap = argInput.toMap();
      channel.send(inputMap, channelReply -> {
        callback.reply(null);
      });
    }
    public void onContainerHide(CommonParams argInput, Reply<Void> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.FlutterRouterApi.onContainerHide", new StandardMessageCodec());
      Map<String, Object> inputMap = argInput.toMap();
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
    StackInfo getStackFromHost();
    void saveStackToHost(StackInfo arg);

    /** Sets up an instance of `NativeRouterApi` to handle messages through the `binaryMessenger`. */
    static void setup(BinaryMessenger binaryMessenger, NativeRouterApi api) {
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.NativeRouterApi.pushNativeRoute", new StandardMessageCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              @SuppressWarnings("ConstantConditions")
              CommonParams input = CommonParams.fromMap((Map<String, Object>)message);
              api.pushNativeRoute(input);
              wrapped.put("result", null);
            }
            catch (Error | RuntimeException exception) {
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
            Map<String, Object> wrapped = new HashMap<>();
            try {
              @SuppressWarnings("ConstantConditions")
              CommonParams input = CommonParams.fromMap((Map<String, Object>)message);
              api.pushFlutterRoute(input);
              wrapped.put("result", null);
            }
            catch (Error | RuntimeException exception) {
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
            Map<String, Object> wrapped = new HashMap<>();
            try {
              @SuppressWarnings("ConstantConditions")
              CommonParams input = CommonParams.fromMap((Map<String, Object>)message);
              api.popRoute(input);
              wrapped.put("result", null);
            }
            catch (Error | RuntimeException exception) {
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
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.NativeRouterApi.getStackFromHost", new StandardMessageCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              StackInfo output = api.getStackFromHost();
              wrapped.put("result", output.toMap());
            }
            catch (Error | RuntimeException exception) {
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
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.NativeRouterApi.saveStackToHost", new StandardMessageCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              @SuppressWarnings("ConstantConditions")
              StackInfo input = StackInfo.fromMap((Map<String, Object>)message);
              api.saveStackToHost(input);
              wrapped.put("result", null);
            }
            catch (Error | RuntimeException exception) {
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
  private static Map<String, Object> wrapError(Throwable exception) {
    Map<String, Object> errorMap = new HashMap<>();
    errorMap.put("message", exception.toString());
    errorMap.put("code", exception.getClass().getSimpleName());
    errorMap.put("details", null);
    return errorMap;
  }
}
