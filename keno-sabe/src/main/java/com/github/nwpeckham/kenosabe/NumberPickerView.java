package com.github.nwpeckham.kenosabe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by Nathan on 2/26/14.
 */
public class NumberPickerView extends SurfaceView implements SurfaceHolder.Callback
{
    private NumberPickerThread _thread;
    private NumberBox[] _rects = new NumberBox[80];

    private int _screenWidth;
    //private int _screenHeight;

    // Variables that control the way the boxes will appear
    private int _boxWidth;
    private int _boxHeight;
    private int _boxPadding = 15;
    private Paint _boxBorderPaint = new Paint();
    private Paint _boxBackgroundPaint = new Paint();


      /**
     * Parameterized constructor for number picker class*
     */

    public NumberPickerView(Context context, AttributeSet attrSet) {
        super(context, attrSet);
        getHolder().addCallback(this);

        _boxBorderPaint.setColor(Color.BLACK);
        _boxBackgroundPaint.setColor(Color.LTGRAY);

        _screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        //_screenHeight = context.getResources().getDisplayMetrics().heightPixels;
        _boxWidth = (_screenWidth - (11*_boxPadding))/10;
        _boxHeight = _boxWidth;

        for(int i=0;i<80;i++){
            int col = i % 10;
            int row = i / 10;
            int x = _boxPadding * (col + 1) + _boxWidth * col;
            int y = _boxPadding * (row + 1) + _boxHeight * row;
            _rects[i] = new NumberBox(x, y, x+_boxWidth,y+_boxHeight);
            _rects[i].setText((new Integer(i + 1).toString()));
        }


    }

    public void onClick(View view){

    }


    @Override
    public void onMeasure(int x, int y){
        int width = (_boxWidth*10)+(_boxPadding*11);
        int height = (_boxHeight*8)+(_boxPadding*9);
        setMeasuredDimension(width,height);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        for(int i = 0; i< 80; i++){
            _rects[i].drawBox(canvas);
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
