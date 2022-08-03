package com.idlefish.flutterboost.containers;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.idlefish.flutterboost.*;
import io.flutter.Log;
import io.flutter.embedding.android.FlutterView;
import io.flutter.embedding.android.SplashScreen;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.renderer.OnFirstFrameRenderedListener;

/**
 * {@code View} that displays a {@link SplashScreen} until a given {@link FlutterView}
 * renders its first frame.
 */
public class FlutterSplashView extends FrameLayout {
    private static String TAG = "FlutterSplashView";
    private FlutterEngine mFlutterEngine;

    @Nullable
    private SplashScreen splashScreen;
    @Nullable
    private XFlutterView flutterView;
    @Nullable
    private View splashScreenView;
    @Nullable
    private Bundle splashScreenState;
    @Nullable
    private String transitioningIsolateId;
    @Nullable
    private String previousCompletedSplashIsolate;

    private boolean hasRendered=false;

    @NonNull
    private final FlutterView.FlutterEngineAttachmentListener flutterEngineAttachmentListener = new FlutterView.FlutterEngineAttachmentListener() {
        @Override
        public void onFlutterEngineAttachedToFlutterView(@NonNull FlutterEngine engine) {
            flutterView.removeFlutterEngineAttachmentListener(this);
//            displayFlutterViewWithSplash(flutterView, splashScreen);
//            splashScreenTransitionNeededNow();
        }

        @Override
        public void onFlutterEngineDetachedFromFlutterView() {
        }
    };

    @NonNull
    private final OnFirstFrameRenderedListener onFirstFrameRenderedListener = new OnFirstFrameRenderedListener() {
        @Override
        public void onFirstFrameRendered() {
            if (splashScreen != null) {
                transitionToFlutter();
            }
        }
    };

    @NonNull
    private final Runnable onTransitionComplete = new Runnable() {
        @Override
        public void run() {
            removeView(splashScreenView);
            previousCompletedSplashIsolate = transitioningIsolateId;
        }
    };

    public FlutterSplashView(@NonNull Context context) {
        this(context, null, 0);
    }

    public FlutterSplashView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlutterSplashView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setSaveEnabled(true);
        if (mFlutterEngine == null) {
            mFlutterEngine = NewFlutterBoost.instance().engineProvider();
        }
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState savedState = new SavedState(superState);
        savedState.previousCompletedSplashIsolate = previousCompletedSplashIsolate;
        savedState.splashScreenState = splashScreen != null ? splashScreen.saveSplashScreenState() : null;
        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        previousCompletedSplashIsolate = savedState.previousCompletedSplashIsolate;
        splashScreenState = savedState.splashScreenState;
    }

    /**
     * Displays the given {@code splashScreen} on top of the given {@code flutterView} until
     * Flutter has rendered its first frame, then the {@code splashScreen} is transitioned away.
     * <p>
     * If no {@code splashScreen} is provided, this {@code FlutterSplashView} displays the
     * given {@code flutterView} on its own.
     */
    public void displayFlutterViewWithSplash(@NonNull XFlutterView flutterView, @Nullable SplashScreen splashScreen) {
        // If we were displaying a previous FlutterView, remove it.
        if (this.flutterView != null) {
            this.flutterView.removeOnFirstFrameRenderedListener(onFirstFrameRenderedListener);
            removeView(this.flutterView);
        }
        // If we were displaying a previous splash screen View, remove it.
        if (splashScreenView != null) {
            removeView(splashScreenView);
        }

        // Display the new FlutterView.
        this.flutterView = flutterView;
        addView(flutterView);

        this.splashScreen = splashScreen;

        // Display the new splash screen, if needed.
        if (splashScreen != null) {


            splashScreenView = splashScreen.createSplashView(getContext(), splashScreenState);
            splashScreenView.setBackgroundColor(Color.WHITE);
            addView(this.splashScreenView);
            flutterView.addOnFirstFrameRenderedListener(onFirstFrameRenderedListener);


//            if (splashScreen != null) {
//                if (this.isSplashScreenNeededNow()) {
//                    Log.v(TAG, "Showing splash screen UI.");
//                    this.splashScreenView = splashScreen.createSplashView(this.getContext(), this.splashScreenState);
//                    this.addView(this.splashScreenView);
//                    flutterView.addOnFirstFrameRenderedListener(this.onFirstFrameRenderedListener);
//                } else if (this.isSplashScreenTransitionNeededNow()) {
//                    Log.v(TAG, "Showing an immediate splash transition to Flutter due to previously interrupted transition.");
//                    this.splashScreenView = splashScreen.createSplashView(this.getContext(), this.splashScreenState);
//                    this.addView(this.splashScreenView);
//                    this.transitionToFlutter();
//                } else if (!flutterView.isAttachedToFlutterEngine()) {
//                    Log.v(TAG, "FlutterView is not yet attached to a FlutterEngine. Showing nothing until a FlutterEngine is attached.");
//                    flutterView.addFlutterEngineAttachmentListener(this.flutterEngineAttachmentListener);
//                }
//            }
        }
    }





    /**
     * Returns true if current conditions require a splash UI to be displayed.
     * <p>
     * This method does not evaluate whether a previously interrupted splash transition needs
     * to resume. See {@link #isSplashScreenTransitionNeededNow()} to answer that question.
     */
    private boolean isSplashScreenNeededNow() {
        return flutterView != null
                && flutterView.isAttachedToFlutterEngine()
                && !flutterView.hasRenderedFirstFrame()
                && !hasSplashCompleted();
    }

    /**
     * Returns true if a previous splash transition was interrupted by recreation, e.g., an
     * orientation change, and that previous transition should be resumed.
     * <p>
     * Not all splash screens are capable of remembering their transition progress. In those
     * cases, this method will return false even if a previous visual transition was
     * interrupted.
     */
    private boolean isSplashScreenTransitionNeededNow() {
        return flutterView != null
                && flutterView.isAttachedToFlutterEngine()
                && splashScreen != null
                && splashScreen.doesSplashViewRememberItsTransition()
                && wasPreviousSplashTransitionInterrupted();
    }

    /**
     * Returns true if a splash screen was transitioning to a Flutter experience and was then
     * interrupted, e.g., by an Android configuration change. Returns false otherwise.
     * <p>
     * Invoking this method expects that a {@code flutterView} exists and it is attached to a
     * {@code FlutterEngine}.
     */
    private boolean wasPreviousSplashTransitionInterrupted() {
        if (flutterView == null) {
            throw new IllegalStateException("Cannot determine if previous splash transition was " +
                    "interrupted when no FlutterView is set.");
        }
        if (!flutterView.isAttachedToFlutterEngine()) {
            throw new IllegalStateException("Cannot determine if previous splash transition was "
                    + "interrupted when no FlutterEngine is attached to our FlutterView. This question "
                    + "depends on an isolate ID to differentiate Flutter experiences.");
        }
        return flutterView.hasRenderedFirstFrame() && !hasSplashCompleted();
    }

    /**
     * Returns true if a splash UI for a specific Flutter experience has already completed.
     * <p>
     * A "specific Flutter experience" is defined as any experience with the same Dart isolate
     * ID. The purpose of this distinction is to prevent a situation where a user gets past a
     * splash UI, rotates the device (or otherwise triggers a recreation) and the splash screen
     * reappears.
     * <p>
     * An isolate ID is deemed reasonable as a key for a completion event because a Dart isolate
     * cannot be entered twice. Therefore, a single Dart isolate cannot return to an "un-rendered"
     * state after having previously rendered content.
     */
    private boolean hasSplashCompleted() {
        if (flutterView == null) {
            throw new IllegalStateException("Cannot determine if splash has completed when no FlutterView "
                    + "is set.");
        }
        if (!flutterView.isAttachedToFlutterEngine()) {
            throw new IllegalStateException("Cannot determine if splash has completed when no "
                    + "FlutterEngine is attached to our FlutterView. This question depends on an isolate ID "
                    + "to differentiate Flutter experiences.");
        }

        // A null isolate ID on a non-null FlutterEngine indicates that the Dart isolate has not
        // been initialized. Therefore, no frame has been rendered for this engine, which means
        // no splash screen could have completed yet.
        return flutterView.getAttachedFlutterEngine().getDartExecutor().getIsolateServiceId() != null
                && flutterView.getAttachedFlutterEngine().getDartExecutor().getIsolateServiceId().equals(previousCompletedSplashIsolate);
    }

    /**
     * Transitions a splash screen to the Flutter UI.
     * <p>
     * This method requires that our FlutterView be attached to an engine, and that engine have
     * a Dart isolate ID. It also requires that a {@code splashScreen} exist.
     */
    private void transitionToFlutter() {
        transitioningIsolateId = flutterView.getAttachedFlutterEngine().getDartExecutor().getIsolateServiceId();
        Log.v(TAG, "Transitioning splash screen to a Flutter UI. Isolate: " + transitioningIsolateId);
        splashScreen.transitionToFlutter(onTransitionComplete);
    }

    public static class SavedState extends BaseSavedState {
        public static Creator CREATOR = new Creator() {
            @Override
            public SavedState createFromParcel(Parcel source) {
                return new SavedState(source);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };

        private String previousCompletedSplashIsolate;
        private Bundle splashScreenState;

        SavedState(Parcelable superState) {
            super(superState);
        }

        SavedState(Parcel source) {
            super(source);
            previousCompletedSplashIsolate = source.readString();
            splashScreenState = source.readBundle(getClass().getClassLoader());
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeString(previousCompletedSplashIsolate);
            out.writeBundle(splashScreenState);
        }
    }




    public void onAttach() {
        Debuger.log("BoostFlutterView onAttach");

        flutterView.attachToFlutterEngine(mFlutterEngine);

    }


    public void onDetach() {
        Debuger.log("BoostFlutterView onDetach");

        flutterView.detachFromFlutterEngine();

    }

}