package com.idlefish.flutterboost.containers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.idlefish.flutterboost.FlutterBoost;
import com.idlefish.flutterboost.FlutterBoostPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import io.flutter.Log;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.android.FlutterView;
import io.flutter.embedding.android.RenderMode;
import io.flutter.embedding.engine.FlutterEngine;

import static com.idlefish.flutterboost.containers.FlutterActivityLaunchConfigs.ACTIVITY_RESULT_KEY;
import static com.idlefish.flutterboost.containers.FlutterActivityLaunchConfigs.NATIVE_PAGE_URL_KEY;
import static com.idlefish.flutterboost.containers.FlutterActivityLaunchConfigs.DEFAULT_BACKGROUND_MODE;
import static com.idlefish.flutterboost.containers.FlutterActivityLaunchConfigs.EXTRA_BACKGROUND_MODE;
import static com.idlefish.flutterboost.containers.FlutterActivityLaunchConfigs.EXTRA_CACHED_ENGINE_ID;
import static com.idlefish.flutterboost.containers.FlutterActivityLaunchConfigs.EXTRA_DESTROY_ENGINE_WITH_ACTIVITY;
import static com.idlefish.flutterboost.containers.FlutterActivityLaunchConfigs.EXTRA_UNIQUE_ID;
import static com.idlefish.flutterboost.containers.FlutterActivityLaunchConfigs.EXTRA_URL;
import static com.idlefish.flutterboost.containers.FlutterActivityLaunchConfigs.EXTRA_URL_PARAM;

public class FlutterBoostActivity extends FlutterActivity implements FlutterViewContainer {
    private static final String TAG = "FlutterBoostActivity";
    private FlutterView flutterView;
    private FlutterViewContainerObserver observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        observer = FlutterBoostPlugin.ContainerShadowNode.create(this, FlutterBoost.instance().getPlugin());
        observer.onCreateView();
    }

    private void findFlutterView(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup vp = (ViewGroup) view;
            for (int i = 0; i < vp.getChildCount(); i++) {
                View child = vp.getChildAt(i);
                if (child instanceof FlutterView) {
                    flutterView = (FlutterView) child;
                    return;
                } else {
                    findFlutterView(child);
                }
            }
        }
    }

    // @Override
    public void detachFromFlutterEngine() {
        /**
         * Override and do nothing.
         * 
         * The idea here is to avoid releasing delegate when
         * a new FlutterActivity is attached in Flutter2.0.
         */
    }

    @Override
    public void onResume() {
        if (flutterView == null) {
            findFlutterView(getWindow().getDecorView());
        }

        super.onResume();
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) {
            if (FlutterBoost.instance().isAppInBackground() &&
                    !FlutterBoost.instance().getPlugin().isTopContainer(getUniqueId())) {
                Log.w(TAG, "Unexpected activity lifecycle event on Android Q. " +
                        "See https://issuetracker.google.com/issues/185693011 for more details.");
                return;
            }
        }
        observer.onAppear(InitiatorLocation.Others);
        ActivityAndFragmentPatch.onResumeAttachToFlutterEngine(flutterView,
                getFlutterEngine(), this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        getFlutterEngine().getLifecycleChannel().appIsResumed();
        observer.onDisappear(InitiatorLocation.Others);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) {
            if (FlutterBoost.instance().isAppInBackground() &&
                    !FlutterBoost.instance().getPlugin().isTopContainer(getUniqueId())) {
                Log.w(TAG, "Unexpected activity lifecycle event on Android Q. " +
                        "See https://issuetracker.google.com/issues/185693011 for more details.");
                return;
            }
        }
        ActivityAndFragmentPatch.onPauseDetachFromFlutterEngine(flutterView, getFlutterEngine());
        getFlutterEngine().getLifecycleChannel().appIsResumed();
    }

    @Override
    protected void onDestroy() {
        // Get engine before |super.onDestroy| callback.
        FlutterEngine engine = getFlutterEngine();
        super.onDestroy();
        engine.getLifecycleChannel().appIsResumed();
        observer.onDestroyView();
    }

    @Override
    public void onBackPressed() {
        ActivityAndFragmentPatch.onBackPressed();
    }

    @Override
    public RenderMode getRenderMode() {
        return ActivityAndFragmentPatch.getRenderMode();
    }

    @Override
    public Activity getContextActivity() {
        return this;
    }

    @Override
    public void finishContainer(Map<String, Object> result) {
        if (result != null) {
            Intent intent = new Intent();
            intent.putExtra(ACTIVITY_RESULT_KEY, new HashMap<String, Object>(result));
            setResult(Activity.RESULT_OK, intent);
        }
        finish();
    }

    @Override
    public String getUrl() {
        return getIntent().getStringExtra(EXTRA_URL);
    }

    @Override
    public Map<String, Object> getUrlParams() {
        return (HashMap<String, Object>)getIntent().getSerializableExtra(EXTRA_URL_PARAM);
    }

    @Override
    public String getUniqueId() {
        return getIntent().getStringExtra(EXTRA_UNIQUE_ID);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            String pageName = data.getStringExtra(NATIVE_PAGE_URL_KEY);
            Map<String, Object> result = (HashMap<String, Object>)data.getSerializableExtra(ACTIVITY_RESULT_KEY);
            FlutterBoost.instance().getPlugin().onNativeResult(pageName, result, null);
        }
    }

    public static class CachedEngineIntentBuilder {
        private final Class<? extends FlutterBoostActivity> activityClass;
        private final String cachedEngineId;
        private boolean destroyEngineWithActivity = false;
        private String backgroundMode = DEFAULT_BACKGROUND_MODE;
        private String url;
        private HashMap<String, Object> params;
        private String uniqueId;

        public CachedEngineIntentBuilder(
                Class<? extends FlutterBoostActivity> activityClass, String cachedEngineId) {
            this.activityClass = activityClass;
            this.cachedEngineId = cachedEngineId;
        }


        public FlutterBoostActivity.CachedEngineIntentBuilder destroyEngineWithActivity(boolean destroyEngineWithActivity) {
            this.destroyEngineWithActivity = destroyEngineWithActivity;
            return this;
        }


        public FlutterBoostActivity.CachedEngineIntentBuilder backgroundMode(io.flutter.embedding.android.FlutterActivityLaunchConfigs.BackgroundMode backgroundMode) {
            this.backgroundMode = backgroundMode.name();
            return this;
        }

        public FlutterBoostActivity.CachedEngineIntentBuilder url(String url) {
            this.url = url;
            return this;
        }

        public FlutterBoostActivity.CachedEngineIntentBuilder urlParams(Map<String, Object> params) {
            this.params = (params instanceof HashMap) ? (HashMap)params : new HashMap<String, Object>(params);
            return this;
        }

        public FlutterBoostActivity.CachedEngineIntentBuilder uniqueId(String uniqueId) {
            this.uniqueId = uniqueId;
            return this;
        }

        public Intent build(Context context) {
            return new Intent(context, activityClass)
                    .putExtra(EXTRA_CACHED_ENGINE_ID, cachedEngineId)
                    .putExtra(EXTRA_DESTROY_ENGINE_WITH_ACTIVITY, destroyEngineWithActivity)
                    .putExtra(EXTRA_BACKGROUND_MODE, backgroundMode)
                    .putExtra(EXTRA_URL, url)
                    .putExtra(EXTRA_URL_PARAM, params)
                    .putExtra(EXTRA_UNIQUE_ID, uniqueId != null ? uniqueId : UUID.randomUUID().toString());
        }
    }

}
