package com.viewwang.materialdesign.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.viewwang.materialdesign.R;

import java.util.Random;

/**
 * Title:demo1View
 * Package:com.viewwang.materialdesign.view
 * Description:TODO
 * Author: wjx@tomcat360.com
 * Date: 2017/3/6 0006 11:17
 * Version: V1.0.0
 * 版本号修改日期修改人修改内容
 */

public class demo1View extends RelativeLayout {

    private Interpolator[] interpolators;
    private Drawable[] drawable;
    private int mWidth;
    private int mHight;
    private int dheight;
    private int dwidth;
    private LayoutParams lp;
    private Random random= new Random();

    public demo1View(Context context) {
        super(context);
        init();
    }



    public demo1View(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public demo1View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        drawable = new Drawable[3];
        Drawable red = getResources().getDrawable(R.drawable.pl_red);
        Drawable blue = getResources().getDrawable(R.drawable.pl_blue);
        Drawable yellow = getResources().getDrawable(R.drawable.pl_yellow);
        drawable[0] = red;
        drawable[1] = blue;
        drawable[2] = yellow;
        dheight = red.getIntrinsicHeight();
        dwidth = red.getIntrinsicWidth();
        lp = new LayoutParams(dwidth,dheight);
        lp.addRule(CENTER_HORIZONTAL);
        lp.addRule(ALIGN_PARENT_BOTTOM);
        interpolators = new Interpolator[4];
        interpolators[0] = new LinearInterpolator();
        interpolators[1] = new DecelerateInterpolator();
        interpolators[2] = new AccelerateDecelerateInterpolator();
        interpolators[3] = new AccelerateInterpolator();


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHight = getMeasuredHeight();
    }


    public void addHeard(){
        final ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(drawable[random.nextInt(3)]);
        imageView.setLayoutParams(lp);
        addView(imageView);
        //动画 透明度增加 放大
        ObjectAnimator alpha = ObjectAnimator.ofFloat(imageView, View.ALPHA,0.2f,1f);
        ObjectAnimator scalex = ObjectAnimator.ofFloat(imageView,View.SCALE_X,0.2f,1f);
        ObjectAnimator scaley = ObjectAnimator.ofFloat(imageView,View.SCALE_Y,0.2f,1f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.setInterpolator(new LinearInterpolator());
        set.playTogether(alpha,scalex,scaley);

        //轨迹动画   消失
        BezirEvaluator bezirEvaluator =new BezirEvaluator(getPoint(2),getPoint(1));
        ValueAnimator valueAnimator = ValueAnimator.ofObject(bezirEvaluator,new PointF((mWidth-dwidth)/2,mHight-dheight),new PointF(random.nextInt(mHight),0));
        valueAnimator.setDuration(10000);
        valueAnimator.setInterpolator(interpolators[random.nextInt(4)]);
        valueAnimator.addUpdateListener(new BezirEvaluatorListener(imageView));

        AnimatorSet finalset = new AnimatorSet();
        finalset.playSequentially(set);
        finalset.playSequentially(set,valueAnimator);

        finalset.addListener(new Animator.AnimatorListener() {
           @Override
           public void onAnimationStart(Animator animator) {

           }

           @Override
           public void onAnimationEnd(Animator animator) {
            removeView(imageView);
           }

           @Override
           public void onAnimationCancel(Animator animator) {

           }

           @Override
           public void onAnimationRepeat(Animator animator) {

           }
       });

        finalset.start();


    }

    private PointF getPoint(int i) {
        PointF pointF =new PointF();
        pointF.x = random.nextInt(mWidth);
        pointF.y = random.nextInt(mHight)/i;
        return pointF;
    }


}
