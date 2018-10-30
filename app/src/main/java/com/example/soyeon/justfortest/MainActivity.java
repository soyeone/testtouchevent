package com.example.soyeon.justfortest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    public TextView hexValue,rgbValue;
    public ImageView selectedImage,color_display;
    public String rgbcolor,hexcolor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView2(this));
    }


    public class MyView2 extends View {

        //화면에 글씨 출력하기 위한 paint 객체
        Paint textP;
        //터치한 곳의 x,y 좌표
        int touchedX, touchedY;
        //발생한 이벤트의 종류
        String eventType="";
        //화면에 원을 그리기 위한 페인트 객체
        Paint circleP;


        public MyView2(Context context) {
            super(context);
            init();
        }

        //초기화하는 메소드
        private void init() {
            //Paint 객체 생성 및 필요한 값 세팅하기
            textP = new Paint();
            textP.setTextSize(20);
            textP.setColor(Color.YELLOW);
            circleP = new Paint();
            circleP.setColor(Color.RED);
            circleP.setStyle(Paint.Style.FILL);
        }
        //화면 그리는 메소드
        @Override
        protected void onDraw(Canvas canvas) {
            //터치한 곳의 좌표 출력하기
            canvas.drawText("x :"+touchedX, 0, 20, textP);
            canvas.drawText("y :"+touchedY, 0, 40, textP);
            canvas.drawText("eventType :"+eventType, 0, 60, textP);
            //터치한 곳에 원그리기
            canvas.drawCircle(touchedX, touchedY, 50, circleP);
        }

        //터치를 입력받기 위해서
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            //인자로 전달되는 객체에 터치한 곳의 좌표와 이벤트 종류에 대한 정보가 들어있다.

            //터치한 곳의 좌표를 얻어오기(float값으로 리턴)
            //안드로이드는 정수값만 지원한다.
            touchedX = (int)event.getX();
            touchedY = (int)event.getY();

            //이벤트 정보 읽어오기
            //getAction()하면 이벤트에 따라 상수값이 반환된다.
            switch(event.getAction()){
                case MotionEvent.ACTION_DOWN : eventType="액션 다운!"; break;
                case MotionEvent.ACTION_MOVE : eventType="액션 무브!"; break;
                case MotionEvent.ACTION_UP : eventType="액션 업!"; break;
            }


  /*Log.e("x 좌표:", Integer.toString(touchedX));
  Log.e("y 좌표:", Integer.toString(touchedY));*/

            //지금 그려진 화면을 무효화 하고 화면을 갱신한다(onDraw호출).
            invalidate();

            //true를 리턴하는 것은 단순 다운터치만이 아니라는 의미.
            return true;
        }
    }

}
