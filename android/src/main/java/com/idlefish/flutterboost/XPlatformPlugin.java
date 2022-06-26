package com.idlefish.flutterboost;
import android.app.Activity;
import android.app.ActivityManager.TaskDescription;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.HapticFeedbackConstants;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.Window;

import java.util.List;

import io.flutter.embedding.engine.systemchannels.PlatformChannel;
import io.flutter.plugin.common.ActivityLifecycleListener;

public class XPlatformPlugin implements ActivityLifecycleListener {
    public static final int DEFAULT_SYSTEM_UI = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

    private  Activity activity;
    private final PlatformChannel platformChannel;
    private PlatformChannel.SystemChromeStyle currentTheme;
    private int mEnabledOverlays;

    private final PlatformChannel.PlatformMessageHandler mPlatformMessageHandler = new PlatformChannel.PlatformMessageHandler() {
        @Override
        public void playSystemSound(@NonNull PlatformChannel.SoundType soundType) {
            XPlatformPlugin.this.playSystemSound(soundType);
        }

        @Override
        public void vibrateHapticFeedback(@NonNull PlatformChannel.HapticFeedbackType feedbackType) {
            XPlatformPlugin.this.vibrateHapticFeedback(feedbackType);
        }

        @Override
        public void setPreferredOrientations(int androidOrientation) {
            setSystemChromePreferredOrientations(androidOrientation);
        }

        @Override
        public void setApplicationSwitcherDescription(@NonNull PlatformChannel.AppSwitcherDescription description) {
            setSystemChromeApplicationSwitcherDescription(description);
        }

        @Override
        public void showSystemOverlays(@NonNull List<PlatformChannel.SystemUiOverlay> overlays) {
            setSystemChromeEnabledSystemUIOverlays(overlays);
        }

        @Override
        public void restoreSystemUiOverlays() {
            restoreSystemChromeSystemUIOverlays();
        }

        @Override
        public void setSystemUiOverlayStyle(@NonNull PlatformChannel.SystemChromeStyle systemUiOverlayStyle) {
            setSystemChromeSystemUIOverlayStyle(systemUiOverlayStyle);
        }

        @Override
        public void popSystemNavigator() {
            XPlatformPlugin.this.popSystemNavigator();
        }

        @Override
        public CharSequence getClipboardData(@Nullable PlatformChannel.ClipboardContentFormat format) {
            return XPlatformPlugin.this.getClipboardData(format);
        }

        @Override
        public void setClipboardData(@NonNull String text) {
            XPlatformPlugin.this.setClipboardData(text);
        }
    };

    public XPlatformPlugin(Activity activity, PlatformChannel platformChannel) {
        this.activity = activity;
        this.platformChannel = platformChannel;
        this.platformChannel.setPlatformMessageHandler(mPlatformMessageHandler);

        mEnabledOverlays = DEFAULT_SYSTEM_UI;
    }

    private void playSystemSound(PlatformChannel.SoundType soundType) {
        if (soundType == PlatformChannel.SoundType.CLICK) {
            View view = activity.getWindow().getDecorView();
            view.playSoundEffect(SoundEffectConstants.CLICK);
        }
    }

    private void vibrateHapticFeedback(PlatformChannel.HapticFeedbackType feedbackType) {
        View view = activity.getWindow().getDecorView();
        switch (feedbackType) {
            case STANDARD:
                view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                break;
            case LIGHT_IMPACT:
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                break;
            case MEDIUM_IMPACT:
                view.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP);
                break;
            case HEAVY_IMPACT:
                // HapticFeedbackConstants.CONTEXT_CLICK from API level 23.
                view.performHapticFeedback(6);
                break;
            case SELECTION_CLICK:
                view.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK);
                break;
        }
    }

    private void setSystemChromePreferredOrientations(int androidOrientation) {
        activity.setRequestedOrientation(androidOrientation);
    }

    private void setSystemChromeApplicationSwitcherDescription(PlatformChannel.AppSwitcherDescription description) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }

        // Linter refuses to believe we're only executing this code in API 28 unless we use distinct if blocks and
        // hardcode the API 28 constant.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P && Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            activity.setTaskDescription(new TaskDescription(description.label));
        }
        if (Build.VERSION.SDK_INT >= 28) {
            TaskDescription taskDescription = new TaskDescription(description.label, 0, description.color);
            activity.setTaskDescription(taskDescription);
        }
    }

    private void setSystemChromeEnabledSystemUIOverlays(List<PlatformChannel.SystemUiOverlay> overlaysToShow) {
        // Start by assuming we want to hide all system overlays (like an immersive game).
        int enabledOverlays = DEFAULT_SYSTEM_UI
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

        if (overlaysToShow.size() == 0) {
            enabledOverlays |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        }

        // Re-add any desired system overlays.
        for (int i = 0; i < overlaysToShow.size(); ++i) {
            PlatformChannel.SystemUiOverlay overlayToShow = overlaysToShow.get(i);
            switch (overlayToShow) {
                case TOP_OVERLAYS:
                    enabledOverlays &= ~View.SYSTEM_UI_FLAG_FULLSCREEN;
                    break;
                case BOTTOM_OVERLAYS:
                    enabledOverlays &= ~View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
                    enabledOverlays &= ~View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
                    break;
            }
        }

        mEnabledOverlays = enabledOverlays;
        updateSystemUiOverlays();
    }

    private void updateSystemUiOverlays(){
        activity.getWindow().getDecorView().setSystemUiVisibility(mEnabledOverlays);
        if (currentTheme != null) {
            setSystemChromeSystemUIOverlayStyle(currentTheme);
        }
    }

    private void restoreSystemChromeSystemUIOverlays() {
        updateSystemUiOverlays();
    }

    private void setSystemChromeSystemUIOverlayStyle(PlatformChannel.SystemChromeStyle systemChromeStyle) {
        Window window = activity.getWindow();
        View view = window.getDecorView();
        int flags = view.getSystemUiVisibility();
        // You can change the navigation bar color (including translucent colors)
        // in Android, but you can't change the color of the navigation buttons until Android O.
        // LIGHT vs DARK effectively isn't supported until then.
        // Build.VERSION_CODES.O
        if (Build.VERSION.SDK_INT >= 26) {
            if (systemChromeStyle.systemNavigationBarIconBrightness != null) {
                switch (systemChromeStyle.systemNavigationBarIconBrightness) {
                    case DARK:
                        //View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                        flags |= 0x10;
                        break;
                    case LIGHT:
                        flags &= ~0x10;
                        break;
                }
            }
            if (systemChromeStyle.systemNavigationBarColor != null) {
                window.setNavigationBarColor(systemChromeStyle.systemNavigationBarColor);
            }
        }
        // Build.VERSION_CODES.M
        if (Build.VERSION.SDK_INT >= 23) {
            if (systemChromeStyle.statusBarIconBrightness != null) {
                switch (systemChromeStyle.statusBarIconBrightness) {
                    case DARK:
                        // View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                        flags |= 0x2000;
                        break;
                    case LIGHT:
                        flags &= ~0x2000;
                        break;
                }
            }
            if (systemChromeStyle.statusBarColor != null) {
                window.setStatusBarColor(systemChromeStyle.statusBarColor);
            }
        }
        if (systemChromeStyle.systemNavigationBarDividerColor != null) {
            // Not availible until Android P.
            // window.setNavigationBarDividerColor(systemNavigationBarDividerColor);
        }
        view.setSystemUiVisibility(flags);
        currentTheme = systemChromeStyle;
    }

    private void popSystemNavigator() {
        activity.finish();
    }

    private CharSequence getClipboardData(PlatformChannel.ClipboardContentFormat format) {
        ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = clipboard.getPrimaryClip();
        if (clip == null)
            return null;

        if (format == null || format == PlatformChannel.ClipboardContentFormat.PLAIN_TEXT) {
            return clip.getItemAt(0).coerceToText(activity);
        }

        return null;
    }

    private void setClipboardData(String text) {
        ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("text label?", text);
        clipboard.setPrimaryClip(clip);
    }

    @Override
    public void onPostResume() {
        updateSystemUiOverlays();
    }
    public void release(){
        this.activity=null;
    }
}