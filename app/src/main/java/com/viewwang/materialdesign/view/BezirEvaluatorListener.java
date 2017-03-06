package com.viewwang.materialdesign.view;

import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.widget.ImageView;

/**
 * Title:BezirEvaluatorListener
 * Package:com.viewwang.materialdesign.view
 * Description:TODO
 * Author: wjx@tomcat360.com
 * Date: 2017/3/6 0006 14:20
 * Version: V1.0.0
 * 版本号修改日期修改人修改内容
 */
public class BezirEvaluatorListener implements ValueAnimator.AnimatorUpdateListener {
    ImageView targer;
    public BezirEvaluatorListener(ImageView targer) {
        this.targer = targer;
    }

    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        if(valueAnimator.getAnimatedValue()!=null){
            PointF pointF = (PointF) valueAnimator.getAnimatedValue();
            targer.setX(pointF.x);
            targer.setY(pointF.y);
            targer.setAlpha(1-valueAnimator.getAnimatedFraction());//动画执行的百分比
        }


    }
}
