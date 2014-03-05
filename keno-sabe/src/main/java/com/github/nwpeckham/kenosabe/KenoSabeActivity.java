package com.github.nwpeckham.kenosabe;

import com.github.nwpeckham.kenosabe.util.SystemUiHider;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class KenoSabeActivity extends Activity implements View.OnClickListener, GestureDetector.OnGestureListener {

    protected GestureDetector gestureScanner;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    private Button next,previous;

    private ViewFlipper vf;
    private Animation animFlipInNext,animFlipOutNext, animFlipInPrevious, animFlipOutPrevious;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_numbers);
        gestureScanner = new GestureDetector(this);


        final NumberPickerView contentView = (NumberPickerView)findViewById(R.id.number_picker);

        vf = (ViewFlipper)findViewById(R.id.viewControlFlipper);
        animFlipInNext = AnimationUtils.loadAnimation(this, R.anim.flipinnext);
        animFlipOutNext = AnimationUtils.loadAnimation(this, R.anim.flipoutnext);
        animFlipInPrevious = AnimationUtils.loadAnimation(this, R.anim.flipinprevious);
        animFlipOutPrevious = AnimationUtils.loadAnimation(this, R.anim.flipoutprevious);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    //@Override
    public void onClick(View v) {
        if (v == next) {
            vf.setInAnimation(animFlipInNext);
            vf.setOutAnimation(animFlipOutNext);
            vf.showNext();
        }
        if (v == previous) {
            vf.setInAnimation(animFlipInPrevious);
            vf.setOutAnimation(animFlipOutPrevious);
            vf.showPrevious();
        }
    }
    //this is the part to handle Gesture Listener
    @Override
    public boolean onTouchEvent(MotionEvent me){
        return gestureScanner.onTouchEvent(me);
    }
    public boolean onDown(MotionEvent e){
        return true;
    }
    //FLING gesture listener
    public boolean onFling(MotionEvent e1,MotionEvent e2,float velocityX,float velocityY){
        try {
            if(e1.getX() > e2.getX() && Math.abs(e1.getX() - e2.getX()) > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                Toast.makeText(this.getApplicationContext(), "Left", Toast.LENGTH_SHORT).show();
                vf.setInAnimation(animFlipInPrevious);
                vf.setOutAnimation(animFlipOutPrevious);
                vf.showPrevious();
            }else if (e1.getX() < e2.getX() && e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                Toast.makeText(this.getApplicationContext(), "Right", Toast.LENGTH_SHORT).show();
                vf.setInAnimation(animFlipInNext);
                vf.setOutAnimation(animFlipOutNext);
                vf.showNext();
            }
        } catch (Exception e) {
            // nothing
        }
        return true;

    }

    public boolean onScroll(MotionEvent e1,MotionEvent e2,float distanceX,float distanceY){
        return true;
    }
    public void onLongPress(MotionEvent e){}
    public void onShowPress(MotionEvent e){}
    public boolean onSingleTapUp(MotionEvent e){ return true;}


}
