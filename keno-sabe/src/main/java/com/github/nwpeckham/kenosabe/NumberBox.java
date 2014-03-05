package com.github.nwpeckham.kenosabe;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by Nathan on 3/4/14.
 */
public class NumberBox {

    private RectF _backgroundRect;
    private Paint _backgroundPaint = new Paint();

    private int _borderWidth = 5;
    private RectF _borderRect;
    private Paint _borderPaint = new Paint();

    private String _text;
    private Paint _textPaint = new Paint();

    public NumberBox(RectF box){

        _borderPaint.setColor(Color.BLACK);
        _backgroundPaint.setColor(Color.LTGRAY);

        _borderRect = box;
        _backgroundRect = new RectF(_borderRect);
        _backgroundRect.inset(_borderWidth, _borderWidth);

        _text = "Hi";
        _textPaint.setColor(Color.BLACK);
        _textPaint.setTextSize(55);
        _textPaint.setTextAlign(Paint.Align.CENTER);

    }

    public NumberBox(float left, float top, float right, float bottom){
        this(new RectF(left, top, right, bottom));
    }

    // Draw the box
    public void drawBox(Canvas canvas){
        canvas.drawRoundRect(_borderRect,5,5, _borderPaint);
        canvas.drawRoundRect(_backgroundRect, 1, 1, _backgroundPaint);
        canvas.drawText(_text,_borderRect.centerX(),_borderRect.centerY()+13,_textPaint);
    }

    public void setBackgroundColor(int color){
        _backgroundPaint.setColor(color);
    }

    public void setBorderColor(int color){
        _borderPaint.setColor(color);
    }

    public void setText(String text){
        _text = text;
    }



}
