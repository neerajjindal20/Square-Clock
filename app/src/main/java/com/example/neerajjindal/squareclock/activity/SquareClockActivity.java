package com.example.neerajjindal.myapplication.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.neerajjindal.myapplication.R;
import com.example.neerajjindal.myapplication.animation.AnimationUtils;
import com.example.neerajjindal.myapplication.domain.viewmodel.DateTimeViewModel;
import dagger.android.AndroidInjection;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;

public class SquareClockActivity extends Activity {

    @BindView(R.id.squareView)
    TextView squareView;

    @BindView(R.id.dropBox)
    View dropBox;

    private int initialX;
    private int initialY;

    private int destinationX = 0;
    private int destinationY;

    private int _xDelta;
    private int _yDelta;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    DateTimeViewModel dateTimeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidInjection.inject(this);
        ButterKnife.bind(this);

        AnimationUtils.setDropBoxTranslation(dropBox, AnimationUtils.DROP_BOX_COLLAPSED_TRANSLATION);

        FrameLayout.LayoutParams lParams = (FrameLayout.LayoutParams) squareView.getLayoutParams();
        saveInitialPosition(lParams.leftMargin, lParams.topMargin);

        AnimationUtils.rotateSquare(squareView);

        setListeners();

        compositeDisposable.add(
                dateTimeViewModel.getDateTime().subscribe(
                        time -> squareView.setText(time),
                        throwable -> squareView.setText(throwable.getMessage())
                ));
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setListeners() {
        squareView.setOnTouchListener((v, event) -> {
            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    AnimationUtils.setDropBoxTranslation(dropBox, 0);
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) v.getLayoutParams();
                    _xDelta = X - layoutParams.leftMargin;
                    _yDelta = Y - layoutParams.topMargin;
                    break;
                case MotionEvent.ACTION_UP:
                    getDestinationCoordinates();
                    if (squareView.getBottom() > dropBox.getTop() + getQuarterSquare()) {
                        AnimationUtils.animateSquareToPosition(squareView, destinationX, destinationY);
                    } else if (squareView.getBottom() > dropBox.getTop()) {
                        Animation animation = AnimationUtils.animateSquareToPosition(squareView, initialX, initialY);
                        animation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                AnimationUtils.animateSquareToPosition(squareView, destinationX, destinationY);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                    } else {
                        AnimationUtils.setDropBoxTranslation(dropBox, AnimationUtils.DROP_BOX_COLLAPSED_TRANSLATION);
                        AnimationUtils.animateSquareToPosition(squareView, initialX, initialY);
                    }
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    FrameLayout.LayoutParams layoutParamsMove = (FrameLayout.LayoutParams) v.getLayoutParams();
                    layoutParamsMove.leftMargin = X - _xDelta;
                    layoutParamsMove.topMargin = Y - _yDelta;
                    v.setLayoutParams(layoutParamsMove);
                    break;
            }
            return true;
        });
    }

    private void saveInitialPosition(int x, int y) {
        this.initialX = x;
        this.initialY = y;
    }

    private void getDestinationCoordinates() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int height = displayMetrics.heightPixels;
        destinationY = height - (((dropBox.getBottom() - dropBox.getTop()) / 2) + (squareView.getMeasuredHeight() / 2));
    }

    private int getQuarterSquare() {
        return squareView.getMeasuredHeight() / 4;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}
