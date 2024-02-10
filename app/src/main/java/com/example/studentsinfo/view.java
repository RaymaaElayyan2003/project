package com.example.studentsinfo;

import android.view.MotionEvent;

public interface view {


    void onClick(view v);

    void onLongClick(view v);

    void onFocusChange(view v, boolean hasFocus);

    void onAttachedToWindow();

    void onDetachedFromWindow();


    void onLayout(boolean changed, int left, int top, int right, int bottom);


    boolean onTouchEvent(MotionEvent event);

   // boolean dispatchTouchEvent(MotionEventevent);
}