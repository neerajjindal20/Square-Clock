package com.squareclock.animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

public class AnimationUtils {

    public static final int DROP_BOX_COLLAPSED_TRANSLATION = 400;
    private static final int SQUARE_ROTATION_INTERVAL = 1000;
    private static final int SQUARE_TRANSLATION_INTERVAL = 500;

    public static void setDropBoxTranslation(View dropBox, float translationY) {
        ObjectAnimator translateY = ObjectAnimator.ofFloat(dropBox, "translationY", translationY);
        translateY.start();
    }

    public static void rotateSquare(View squareView) {
        ObjectAnimator squareObjectAnimator = ObjectAnimator.ofFloat(squareView,
                "rotation", 0f, 359f);
        squareObjectAnimator.setDuration(SQUARE_ROTATION_INTERVAL);
        squareObjectAnimator.setInterpolator(new LinearInterpolator());
        squareObjectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        squareObjectAnimator.start();
    }

    public static Animation animateSquareToPosition(final View squareView, int positionX, int positionY) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) squareView.getLayoutParams();
        final int leftMarginStart = params.leftMargin;
        final int leftMarginEnd = positionX;
        final int topMarginStart = params.topMargin;
        final int topMarginEnd = positionY;
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) squareView.getLayoutParams();
                // interpolate the proper value
                params.leftMargin = leftMarginStart + (int) ((leftMarginEnd - leftMarginStart) * interpolatedTime);
                params.topMargin = topMarginStart + (int) ((topMarginEnd - topMarginStart) * interpolatedTime);
                squareView.setLayoutParams(params);
            }
        };
        animation.setDuration(SQUARE_TRANSLATION_INTERVAL);
        squareView.startAnimation(animation);
        return animation;
    }
}
