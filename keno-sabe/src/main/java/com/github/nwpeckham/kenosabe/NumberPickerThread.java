package com.github.nwpeckham.kenosabe;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Nathan on 2/26/14.
 */
public class NumberPickerThread extends Thread {
    private SurfaceHolder _surfaceHolder;
    private NumberPicker _panel;
    private boolean _run = false;


    public NumberPickerThread(SurfaceHolder surfaceHolder, NumberPicker panel) {
        _surfaceHolder = surfaceHolder;
        _panel = panel;
    }


    public void setRunning(boolean run) { //Allow us to stop the thread
        _run = run;
    }


    @Override
    public void run() {
        Canvas c;
        while (_run) {     //When setRunning(false) occurs, _run is
            c = null;      //set to false and loop ends, stopping thread


            try {
                c = _surfaceHolder.lockCanvas(null);
                _panel.onDraw(c);
            } finally {
                if (c != null) {
                    _surfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }
    }
}

