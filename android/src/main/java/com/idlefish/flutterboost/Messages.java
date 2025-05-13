// Autogenerated from Pigeon (v3.0.0), do not edit directly.
// See also: https://pub.dev/packages/pigeon

package com.idlefish.flutterboost;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MessageCodec;
import io.flutter.plugin.common.StandardMessageCodec;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/** Generated class from Pigeon. */
@SuppressWarnings({"unused", "unchecked", "CodeBlock2Expr", "RedundantSuppression"})
public class Messages {

  /** Generated class from Pigeon that represents data sent in messages. */
  public static class CommonParams {
    private @Nullable String pageName;
    public @Nullable String getPageName() { return pageName; }
    public void setPageName(@Nullable String setterArg) {
      this.pageName = setterArg;
    }

    private @Nullable String uniqueId;
    public @Nullable String getUniqueId() { return uniqueId; }
    public void setUniqueId(@Nullable String setterArg) {
      this.uniqueId = setterArg;
    }

    private @Nullable Map<String, Object> arguments;
    public @Nullable Map<String, Object> getArguments() { return arguments; }
    public void setArguments(@Nullable Map<String, Object> setterArg) {
      this.arguments = setterArg;
    }

    private @Nullable Boolean opaque;
    public @Nullable Boolean getOpaque() { return opaque; }
    public void setOpaque(@Nullable Boolean setterArg) {
      this.opaque = setterArg;
    }

    private @Nullable String key;
    public @Nullable String getKey() { return key; }
    public void setKey(@Nullable String setterArg) {
      this.key = setterArg;
    }

    public static final class Builder {
      private @Nullable String pageName;
      public @NonNull Builder setPageName(@Nullable String setterArg) {
        this.pageName = setterArg;
        return this;
      }
      private @Nullable String uniqueId;
      public @NonNull Builder setUniqueId(@Nullable String setterArg) {
        this.uniqueId = setterArg;
        return this;
      }
      private @Nullable Map<String, Object> arguments;
      public @NonNull Builder setArguments(@Nullable Map<String, Object> setterArg) {
        this.arguments = setterArg;
        return this;
      }
      private @Nullable Boolean opaque;
      public @NonNull Builder setOpaque(@Nullable Boolean setterArg) {
        this.opaque = setterArg;
        return this;
      }
      private @Nullable String key;
      public @NonNull Builder setKey(@Nullable String setterArg) {
        this.key = setterArg;
        return this;
      }
      public @NonNull CommonParams build() {
        CommonParams pigeonReturn = new CommonParams();
        pigeonReturn.setPageName(pageName);
        pigeonReturn.setUniqueId(uniqueId);
        pigeonReturn.setArguments(arguments);
        pigeonReturn.setOpaque(opaque);
        pigeonReturn.setKey(key);
        return pigeonReturn;
      }
    }
    @NonNull Map<String, Object> toMap() {
      Map<String, Object> toMapResult = new HashMap<>();
      toMapResult.put("pageName", pageName);
      toMapResult.put("uniqueId", uniqueId);
      toMapResult.put("arguments", arguments);
      toMapResult.put("opaque", opaque);
      toMapResult.put("key", key);
      return toMapResult;
    }
    static @NonNull CommonParams fromMap(@NonNull Map<String, Object> map) {
      CommonParams pigeonResult = new CommonParams();
      Object pageName = map.get("pageName");
      pigeonResult.setPageName((String)pageName);
      Object uniqueId = map.get("uniqueId");
      pigeonResult.setUniqueId((String)uniqueId);
      Object arguments = map.get("arguments");
      pigeonResult.setArguments((Map<String, Object>)arguments);
      Object opaque = map.get("opaque");
      pigeonResult.setOpaque((Boolean)opaque);
      Object key = map.get("key");
      pigeonResult.setKey((String)key);
      return pigeonResult;
    }
  }

  /** Generated class from Pigeon that represents data sent in messages. */
  public static class StackInfo {
    private @Nullable List<String> containers;
    public @Nullable List<String> getContainers() { return containers; }
    public void setContainers(@Nullable List<String> setterArg) {
      this.containers = setterArg;
    }

    private @Nullable Map<String, List<Map<String, Object>>> routes;
    public @Nullable Map<String, List<Map<String, Object>>> getRoutes() { return routes; }
    public void setRoutes(@Nullable Map<String, List<Map<String, Object>>> setterArg) {
      this.routes = setterArg;
    }

    public static final class Builder {
      private @Nullable List<String> containers;
      public @NonNull Builder setContainers(@Nullable List<String> setterArg) {
        this.containers = setterArg;
        return this;
      }
      private @Nullable Map<String, List<Map<String, Object>>> routes;
      public @NonNull Builder setRoutes(@Nullable Map<String, List<Map<String, Object>>> setterArg) {
        this.routes = setterArg;
        return this;
      }
      public @NonNull StackInfo build() {
        StackInfo pigeonReturn = new StackInfo();
        pigeonReturn.setContainers(containers);
        pigeonReturn.setRoutes(routes);
        return pigeonReturn;
      }
    }
    @NonNull Map<String, Object> toMap() {
      Map<String, Object> toMapResult = new HashMap<>();
      toMapResult.put("containers", containers);
      toMapResult.put("routes", routes);
      return toMapResult;
    }
    static @NonNull StackInfo fromMap(@NonNull Map<String, Object> map) {
      StackInfo pigeonResult = new StackInfo();
      Object containers = map.get("containers");
      pigeonResult.setContainers((List<String>)containers);
      Object routes = map.get("routes");
      pigeonResult.setRoutes((Map<String, List<Map<String, Object>>>)routes);
      return pigeonResult;
    }
  }

  public interface Result<T> {
    void success(T result);
    void error(Throwable error);
  }
  private static class NativeRouterApiCodec extends StandardMessageCodec {
    public static final NativeRouterApiCodec INSTANCE = new NativeRouterApiCodec();
    private NativeRouterApiCodec() {}
    @Override
    protected Object readValueOfType(byte type, ByteBuffer buffer) {
      switch (type) {
        case (byte)128:         
          return CommonParams.fromMap((Map<String, Object>) readValue(buffer));
        
        case (byte)129:         
          return StackInfo.fromMap((Map<String, Object>) readValue(buffer));
        
        default:        
          return super.readValueOfType(type, buffer);
        
      }
    }
    @Override
    protected void writeValue(ByteArrayOutputStream stream, Object value)     {
      if (value instanceof CommonParams) {
        stream.write(128);
        writeValue(stream, ((CommonParams) value).toMap());
      } else 
      if (value instanceof StackInfo) {
        stream.write(129);
        writeValue(stream, ((StackInfo) value).toMap());
      } else 
{
        super.writeValue(stream, value);
      }
    }
  }

  /** Generated interface from Pigeon that represents a handler of messages from Flutter.*/
  public interface NativeRouterApi {
    void pushNativeRoute(@NonNull CommonParams param);
    void pushFlutterRoute(@NonNull CommonParams param);
    void popRoute(@NonNull CommonParams param, Result<Void> result);
    @NonNull StackInfo getStackFromHost();
    void saveStackToHost(@NonNull StackInfo stack);
    void sendEventToNative(@NonNull CommonParams params);

    /** The codec used by NativeRouterApi. */
    static MessageCodec<Object> getCodec() {
      return NativeRouterApiCodec.INSTANCE;
    }

    /** Sets up an instance of `NativeRouterApi` to handle messages through the `binaryMessenger`. */
    static void setup(BinaryMessenger binaryMessenger, NativeRouterApi api) {
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.NativeRouterApi.pushNativeRoute", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              ArrayList<Object> args = (ArrayList<Object>)message;
              CommonParams paramArg = (CommonParams)args.get(0);
              if (paramArg == null) {
                throw new NullPointerException("paramArg unexpectedly null.");
              }
              api.pushNativeRoute(paramArg);
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
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.NativeRouterApi.pushFlutterRoute", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              ArrayList<Object> args = (ArrayList<Object>)message;
              CommonParams paramArg = (CommonParams)args.get(0);
              if (paramArg == null) {
                throw new NullPointerException("paramArg unexpectedly null.");
              }
              api.pushFlutterRoute(paramArg);
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
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.NativeRouterApi.popRoute", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              ArrayList<Object> args = (ArrayList<Object>)message;
              CommonParams paramArg = (CommonParams)args.get(0);
              if (paramArg == null) {
                throw new NullPointerException("paramArg unexpectedly null.");
              }
              Result<Void> resultCallback = new Result<Void>() {
                public void success(Void result) {
                  wrapped.put("result", null);
                  reply.reply(wrapped);
                }
                public void error(Throwable error) {
                  wrapped.put("error", wrapError(error));
                  reply.reply(wrapped);
                }
              };

              api.popRoute(paramArg, resultCallback);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
              reply.reply(wrapped);
            }
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.NativeRouterApi.getStackFromHost", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              StackInfo output = api.getStackFromHost();
              wrapped.put("result", output);
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
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.NativeRouterApi.saveStackToHost", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              ArrayList<Object> args = (ArrayList<Object>)message;
              StackInfo stackArg = (StackInfo)args.get(0);
              if (stackArg == null) {
                throw new NullPointerException("stackArg unexpectedly null.");
              }
              api.saveStackToHost(stackArg);
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
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.NativeRouterApi.sendEventToNative", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              ArrayList<Object> args = (ArrayList<Object>)message;
              CommonParams paramsArg = (CommonParams)args.get(0);
              if (paramsArg == null) {
                throw new NullPointerException("paramsArg unexpectedly null.");
              }
              api.sendEventToNative(paramsArg);
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
  private static class FlutterRouterApiCodec extends StandardMessageCodec {
    public static final FlutterRouterApiCodec INSTANCE = new FlutterRouterApiCodec();
    private FlutterRouterApiCodec() {}
    @Override
    protected Object readValueOfType(byte type, ByteBuffer buffer) {
      switch (type) {
        case (byte)128:         
          return CommonParams.fromMap((Map<String, Object>) readValue(buffer));
        
        default:        
          return super.readValueOfType(type, buffer);
        
      }
    }
    @Override
    protected void writeValue(ByteArrayOutputStream stream, Object value)     {
      if (value instanceof CommonParams) {
        stream.write(128);
        writeValue(stream, ((CommonParams) value).toMap());
      } else 
{
        super.writeValue(stream, value);
      }
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
    static MessageCodec<Object> getCodec() {
      return FlutterRouterApiCodec.INSTANCE;
    }

    public void pushRoute(@NonNull CommonParams paramArg, Reply<Void> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.FlutterRouterApi.pushRoute", getCodec());
      channel.send(new ArrayList<Object>(Arrays.asList(paramArg)), channelReply -> {
        callback.reply(null);
      });
    }
    public void popRoute(@NonNull CommonParams paramArg, Reply<Void> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.FlutterRouterApi.popRoute", getCodec());
      channel.send(new ArrayList<Object>(Arrays.asList(paramArg)), channelReply -> {
        callback.reply(null);
      });
    }
    public void removeRoute(@NonNull CommonParams paramArg, Reply<Void> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.FlutterRouterApi.removeRoute", getCodec());
      channel.send(new ArrayList<Object>(Arrays.asList(paramArg)), channelReply -> {
        callback.reply(null);
      });
    }
    public void onForeground(@NonNull CommonParams paramArg, Reply<Void> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.FlutterRouterApi.onForeground", getCodec());
      channel.send(new ArrayList<Object>(Arrays.asList(paramArg)), channelReply -> {
        callback.reply(null);
      });
    }
    public void onBackground(@NonNull CommonParams paramArg, Reply<Void> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.FlutterRouterApi.onBackground", getCodec());
      channel.send(new ArrayList<Object>(Arrays.asList(paramArg)), channelReply -> {
        callback.reply(null);
      });
    }
    public void onNativeResult(@NonNull CommonParams paramArg, Reply<Void> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.FlutterRouterApi.onNativeResult", getCodec());
      channel.send(new ArrayList<Object>(Arrays.asList(paramArg)), channelReply -> {
        callback.reply(null);
      });
    }
    public void onContainerShow(@NonNull CommonParams paramArg, Reply<Void> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.FlutterRouterApi.onContainerShow", getCodec());
      channel.send(new ArrayList<Object>(Arrays.asList(paramArg)), channelReply -> {
        callback.reply(null);
      });
    }
    public void onContainerHide(@NonNull CommonParams paramArg, Reply<Void> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.FlutterRouterApi.onContainerHide", getCodec());
      channel.send(new ArrayList<Object>(Arrays.asList(paramArg)), channelReply -> {
        callback.reply(null);
      });
    }
    public void sendEventToFlutter(@NonNull CommonParams paramArg, Reply<Void> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.FlutterRouterApi.sendEventToFlutter", getCodec());
      channel.send(new ArrayList<Object>(Arrays.asList(paramArg)), channelReply -> {
        callback.reply(null);
      });
    }
    public void onBackPressed(Reply<Void> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.FlutterRouterApi.onBackPressed", getCodec());
      channel.send(null, channelReply -> {
        callback.reply(null);
      });
    }
  }
  private static Map<String, Object> wrapError(Throwable exception) {
    Map<String, Object> errorMap = new HashMap<>();
    errorMap.put("message", exception.toString());
    errorMap.put("code", exception.getClass().getSimpleName());
    errorMap.put("details", "Cause: " + exception.getCause() + ", Stacktrace: " + Log.getStackTraceString(exception));
    return errorMap;
  }
}
