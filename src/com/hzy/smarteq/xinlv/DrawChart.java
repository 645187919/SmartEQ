package  com.hzy.smarteq.xinlv;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

public class DrawChart extends View {
	private int CHARTH = 200;
	private int CHARTW = 460;
	private int OFFSET_LEFT = 10;
	private int OFFSET_TOP = 20;
	private int X_INTERVAL = 20;
	
	private List<Point> plist;
	MainActivity activity;
	public DrawChart(Context context) {
		super(context);
		plist = new ArrayList<Point>();
		activity=(MainActivity) context;
		Log.i("DrawChart", "DrawChart1");
		//initPlist();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Log.i("DrawChart", "onDraw2");
		drawTable(canvas);
		prepareLine();
		drawCurve(canvas);
	}
	/***
	 * 画出波浪线的表格以及虚线
	 * @param canvas
	 */
	private void drawTable(Canvas canvas){
		Log.i("DrawChart", "drawTable3");
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		
		//画外框
		paint.setStyle(Paint.Style.STROKE);
		Rect chartRec = new Rect(OFFSET_LEFT, OFFSET_TOP,CHARTW+OFFSET_LEFT, CHARTH+OFFSET_TOP);
		canvas.drawRect(chartRec, paint);
		
		//画左边的文字
		Path textPath = new Path();
		paint.setStyle(Paint.Style.FILL);
		textPath.moveTo(30, 420);
		textPath.lineTo(30, 300);
		paint.setTextSize(15);
		paint.setAntiAlias(true);
		canvas.drawTextOnPath("信号强度 [dBm]", textPath, 0, 0, paint);
        //画表格中的虚线
        Path path = new Path();     
        PathEffect effects = new DashPathEffect(new float[]{2,2,2,2},1);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(false);
        paint.setPathEffect(effects); 		
		for(int i = 1 ; i<10 ; i++){
			path.moveTo(OFFSET_LEFT, OFFSET_TOP + CHARTH/10*i);  
	        path.lineTo(OFFSET_LEFT+CHARTW,OFFSET_TOP + CHARTH/10*i); 
	        canvas.drawPath(path, paint);
		}
	}
	
	private void drawCurve(Canvas canvas){
		Log.i("DrawChart", "drawCurve4");
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setStrokeWidth(3);
		paint.setAntiAlias(true);
		if(plist.size() >= 2){
			for(int i = 0; i<plist.size()-1; i++){
				canvas.drawLine(plist.get(i).x, plist.get(i).y, plist.get(i+1).x, plist.get(i+1).y, paint);
			}
		}
	}
	
	private void prepareLine(){
		Log.i("DrawChart", "prepareLine5");
		int py=120;
		if(activity.getHeartbeat()!=0&&activity.getHeartbeat()>30&&activity.getHeartbeat()<150){
			 py = OFFSET_TOP + (int)(Math.random()*(activity.getHeartbeat() - OFFSET_TOP));
		}
		Point p = new Point(OFFSET_LEFT + CHARTW,py);
		
		if(plist.size() > 24){
			Log.i("prepareLine", "plist=="+plist.get(0).x);
			plist.remove(0);
			for(int i = 0; i<23; i++){
				if(i == 0) plist.get(i).x -= (X_INTERVAL - 2);
				else plist.get(i).x -= X_INTERVAL;
			}
			plist.add(p);
		}
		else{
			for(int i = 0; i<plist.size()-1; i++){
				plist.get(i).x -= X_INTERVAL;
			}
			
			plist.add(p);
		}

	}
}
