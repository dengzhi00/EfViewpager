package com.dzm.vp;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * @author 邓治民
 *         data 2018/1/2 上午9:56
 */

public class TestTransformer implements ViewPager.PageTransformer  {

    private static final float DEFAULT_MAX_ROTATE = 45f;
    private float mMaxRotate = DEFAULT_MAX_ROTATE;

    private int pPix;

    public TestTransformer(int mPx) {
        this.pPix = mPx;
        Log.d("pWidth","    "+pPix);
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void transformPage(View view, float position) {
        view.setPivotY(view.getHeight() / 2);
        if (position < -1) { // [-Infinity,-1)
            view.setPivotX(0);
            view.setRotationY(1 * mMaxRotate);

        } else if (position <= 1) { // [-1,1]
            if (position < 0)//[0,-1]
            {
                view.setPivotX(0);
            } else//[1,0]
            {
                view.setPivotX(view.getWidth());
            }
            view.setRotationY(-position * mMaxRotate);


        } else { // (1,+Infinity]
            view.setPivotX(view.getWidth());
            view.setRotationY(-1 * mMaxRotate);
        }

//        Log.d("pageTransform",view.toString()+"    "+position+"   "+view.getWidth());

        view.setTranslationX(-(view.getWidth() - pPix - 40)*position);
    }
}
//-0.5810811
//0.47297296
//1.527027