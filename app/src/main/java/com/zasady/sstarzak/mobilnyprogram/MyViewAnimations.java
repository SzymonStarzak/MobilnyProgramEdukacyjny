package com.zasady.sstarzak.mobilnyprogram;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * Created by sstarzak on 2015-01-03.
 */
public class MyViewAnimations {

    public static void myRotateAnimation(View view, long time) {
        RotateAnimation ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);
        ra.setDuration(time);
        view.startAnimation(ra);
    }

    public static void myAlphaAnimation(View view, long time) {

        AlphaAnimation al = new AlphaAnimation(1, 0);
        al.setDuration(time);
        view.startAnimation(al);
    }

    public static void myBlinkAnimation(final View view, final long time, final int repeat, final int blink_color, final int color) {

        final GradientDrawable gd;
        if(view.getBackground() != null)
           gd = (GradientDrawable) view.getBackground();
        else
          gd = new GradientDrawable();


        CountDownTimer cdt = new CountDownTimer(time, time / repeat) {
            int c = 0;

            @Override
            public void onTick(long l) {
                if (c++ % 2 == 0) {
                    gd.setColor(blink_color);
                    view.setBackground(gd);
                } else {
                    gd.setColor(color);
                    view.setBackground(gd);
                }

            }

            @Override
            public void onFinish() {
                gd.setColor(color);
                view.setBackground(gd);
            }
        }.start();
    }

    public static void myScaleAlphaAnimation(View view, long time) {
        AnimationSet as = new AnimationSet(true);
        //as.setDuration(time);

        ScaleAnimation sa = new ScaleAnimation(1, 2, 1, 2);
        sa.setDuration(time);
        AlphaAnimation al = new AlphaAnimation(1, 0);
        al.setDuration(time);

        as.addAnimation(sa);
        as.addAnimation(al);

        view.startAnimation(as);
    }

    public static void myScaleAnimation(View view, long time) {
        ScaleAnimation sa = new ScaleAnimation(1, 1, 1, 2);
        sa.setDuration(time);
        view.startAnimation(sa);
    }

    public static void myTimerShakeAnimation(View view, long time) {
        ScaleAnimation sa = new ScaleAnimation(1, 1.4f, 1, 1.4f);
        sa.setDuration(time);
        view.startAnimation(sa);
    }

    public static void myNegativeScaleAnimation(final View view, final long time) {
        ScaleAnimation sa = new ScaleAnimation(1, 1, 1, .01f);
        sa.setDuration(time);
        view.startAnimation(sa);
        sa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ScaleAnimation sa = new ScaleAnimation(1, 1, 0.01f, 1);
                sa.setDuration(time / 2);
                view.startAnimation(sa);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    public static void myJumpingAnimation(final View view, final long time, final long height) {
        TranslateAnimation tr = new TranslateAnimation(0, 0, 0, -height);
        tr.setDuration(time / 2);
        view.startAnimation(tr);
        tr.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                TranslateAnimation tr = new TranslateAnimation(0, 0, -height, 0);
                tr.setDuration(time / 2);
                view.startAnimation(tr);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public static void myEnglishAnimation(final View view, final long time) {
        TranslateAnimation tr = new TranslateAnimation(0, -view.getWidth(), 0, 0);
        tr.setDuration(time / 2);
        view.startAnimation(tr);
        tr.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                TranslateAnimation tr = new TranslateAnimation(-view.getWidth(), 0, 0, 0);
                tr.setDuration(time / 2);
                view.startAnimation(tr);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public static void myWrongAnswerShakerAnimation(final View view, long time, int repeat, final int color) {
        final GradientDrawable gd = (GradientDrawable) view.getBackground();

        TranslateAnimation tr = new TranslateAnimation(-5, 5, -5, 5);
        tr.setDuration(time);
        tr.setRepeatCount(repeat);
        tr.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                gd.setColor(Color.RED);
                view.setBackground(gd);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                gd.setColor(color);
                view.setBackground(gd);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(tr);
    }

    public static void myHangmanShakerAnimation(View view, long time, int repeat) {

        TranslateAnimation tr = new TranslateAnimation(-5, 5, -5, 5);
        tr.setDuration(time);
        tr.setRepeatCount(repeat);
        view.startAnimation(tr);
    }
}
