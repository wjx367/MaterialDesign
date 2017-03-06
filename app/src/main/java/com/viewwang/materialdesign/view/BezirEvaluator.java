package com.viewwang.materialdesign.view;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Title:BezirEvaluator
 * Package:com.viewwang.materialdesign.view
 * Description:TODO
 * Author: wjx@tomcat360.com
 * Date: 2017/3/6 0006 13:48
 * Version: V1.0.0
 * 版本号修改日期修改人修改内容
 */

public class BezirEvaluator implements TypeEvaluator<PointF> {
    private PointF pointF1;
    private PointF pointF2;

    public BezirEvaluator(PointF pointF1,PointF pointF2) {
        this.pointF1 = pointF1;
        this.pointF2 = pointF2;
    }

    @Override
    public PointF evaluate(float v, PointF pointF, PointF t1) {
        return BezierUtil.CalculateBezierPointForCubic(v,pointF,pointF1,pointF2,t1);
    }


}
