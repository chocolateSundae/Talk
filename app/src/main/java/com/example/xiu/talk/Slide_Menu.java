package com.example.xiu.talk;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * Created by xiu on 16/5/1.
 */
public class Slide_Menu extends HorizontalScrollView {

    private int screenWidth;
    private int slideMax = 100;
    private int menuWidth;
    private boolean isOpen = false;
    private boolean once = false;

    public Slide_Menu(Context context , AttributeSet attributeSet){
        super(context,attributeSet);
        screenWidth = ScreenUtils.getScreenWidth(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(!once){
            ViewGroup wrap = (LinearLayout) this.getChildAt(0);
            ViewGroup menu = (ViewGroup) wrap.getChildAt(0);
            ViewGroup mainContent = (ViewGroup)wrap.getChildAt(1);

            slideMax = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, slideMax, wrap
                            .getResources().getDisplayMetrics());

            menuWidth = menu.getLayoutParams().width = screenWidth - slideMax;
            mainContent.getLayoutParams().width = screenWidth;
            once = true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        this.scrollTo(menuWidth,0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }

    public void toggle(){
        if(isOpen){
            this.smoothScrollTo(menuWidth,0);
            isOpen = false;
        }else{
            this.smoothScrollTo(0,0);
            isOpen = true;
        }
    }

}
