package com.example.dragAndDrop;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.roomwrapperimplement.R;

public class DragnDropMainActivity extends AppCompatActivity {


    ViewGroup rootLayout;
    TextView tvHello  , tvWorld;

    int _xDelta;
    int _yDelta;
    int _xDelta2;
    int _yDelta2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drag_and_drop_activity_main);

        rootLayout  = findViewById(R.id.root_layout);
        tvHello =  rootLayout.findViewById(R.id.tv_hello);
        tvWorld =  rootLayout.findViewById(R.id.tv_world);

        RelativeLayout.LayoutParams layoutParamsofHello = new RelativeLayout.LayoutParams( 150 , 150 );
        tvHello.setLayoutParams(layoutParamsofHello);


        RelativeLayout.LayoutParams layoutParamsofWorld  = new RelativeLayout.LayoutParams(100 , 100);
       // layoutParamsofWorld.addRule(RelativeLayout.BELOW, R.id.tv_hello);
        tvWorld.setLayoutParams(layoutParamsofWorld);


        tvHello.setOnTouchListener(new ChoiceTouchListner());


        tvWorld.setOnTouchListener(new ChoiceTouchListner());

    }

    private class ChoiceTouchListner implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            final int X = (int) motionEvent.getRawX();
            final int Y = (int) motionEvent.getRawY();

            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK){
                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lParams =(RelativeLayout.LayoutParams)view.getLayoutParams();
                    _xDelta = X - lParams.leftMargin;
                    _yDelta = Y - lParams.topMargin;
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams =(RelativeLayout.LayoutParams)view.getLayoutParams();
                    layoutParams.leftMargin = X - _xDelta;
                    layoutParams.topMargin = Y - _yDelta;
                    layoutParams.rightMargin = -250;
                    layoutParams.bottomMargin = -250;
                    view.setLayoutParams(layoutParams);
                    break;
            }

            rootLayout.invalidate();

            return true;
        }
    }
}
