package com.github.nwpeckham.kenosabe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Nathan on 2/26/14.
 */
public class NumberPicker extends SurfaceView implements SurfaceHolder.Callback
{
    private NumberPickerThread _thread;
    private RectF _boxRect = new RectF(0,0,60,60);
    private Paint _numberPaint = new Paint();
    /**
     * Parameterized constructor for number picker class*
     */

    public NumberPicker(Context context, AttributeSet attrSet) {
        super(context, attrSet);
        getHolder().addCallback(this);
        _numberPaint.setColor(Color.BLACK);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.DKGRAY);
        for(int col = 1; col< 11; col++){
            for(int row = 1; row < 9; row++){
                float x = col * _boxRect.width() * 1.5f;
                float y = row * _boxRect.height() * 1.5f;
                _boxRect.set(x,y,x+60,y+60);
                canvas.drawRoundRect(_boxRect,5,5,_numberPaint);
            }
        }
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)

    {
        _thread = new NumberPickerThread(holder, this);
        _thread.setRunning(true);
        _thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)

    {
        _thread.setRunning(false);
        boolean retry = true;
        while (retry)
        {
            try
            {
                _thread.join();
                retry = false;
            } catch (Exception e)
            {
                //
            }
        }
    }

}
