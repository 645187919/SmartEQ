package  com.hzy.smarteq.xinlv;

import java.util.Timer;
import java.util.TimerTask;

import com.hzy.smarteq.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements SurfaceHolder.Callback,PreviewCallback{
	
	private DrawChart view;
	/**
	 * 定时分析摄像头数据的定时器
	 */
	private Timer timer = new Timer(); 
	private TimerTask task; 
	public static TextView textView;
  @SuppressLint("HandlerLeak")
private Handler handler=new Handler(){
	  public void handleMessage(Message msg) {
		  //调用相机回调接口由于MainActivity已经实现了回调接口，所以MainActivity.this即可
	   camera.setOneShotPreviewCallback(MainActivity.this);
	   view.invalidate();
	  };
  };
	private static Camera camera = null;
	private Button  oprenCamer,closeCamer;
    private static int heartbeat=0;
    public static void setHeratbeat(int heart){
    	heartbeat=heart;
    }
    public static int getHeartbeat(){
    	return heartbeat;
    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xinlv_layout);
		//通过IDfindview控件
		oprenCamer=(Button) findViewById(R.id.open);
		closeCamer=(Button) findViewById(R.id.close);
		textView=(TextView) findViewById(R.id.heart_text);
		oprenCamer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//摄像头开始捕获预览帧数据
				 camera.startPreview();
				 //获取相机当前设置参数
				 Camera.Parameters parameter = camera.getParameters();  
				 //启动闪光灯。
				 parameter.setFlashMode(Parameters.FLASH_MODE_TORCH); 
				camera.setParameters(parameter);
				//开启定时器
				startTimer();
			}
		});
		closeCamer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//获取摄像头参数
				Camera.Parameters parameter = camera.getParameters();  
				//设置摄像头参数 关闭闪光灯
				 parameter.setFlashMode(Parameters.FLASH_MODE_OFF); 
				 
				camera.setParameters(parameter);
				pauseTimer();
			}
		});
		init();
	}
	 public void onPause() {
	        super.onPause();
	        //暂停定时器
	        pauseTimer();
	        //将相机的回调接口设为NULL即不再接受回调数据
	        camera.setPreviewCallback(null);
	        camera.stopPreview();
	        //断开相机并释放对象
	        camera.release();
	        camera = null;
	    }
	 /***
	  * 开始测试方法
	  */
	public void startTimer(){
		if(timer==null){
			timer=new Timer();
		}
		if(task==null){
			task = new TimerTask() { 
			    @Override
			    public void run() { 
			        // TODO Auto-generated method stub 
			        Message message = new Message(); 
			        message.what = 1; 
			        handler.sendMessage(message); 
			    } 
			};
		}
		if(timer!=null&&task!=null){
			timer.schedule(task, 500, 500);
		}
	}
	 /***
	  * 结束测试方法
	  */
	public void pauseTimer(){
		if(timer!=null){
			timer.cancel();
			timer=null;
		}
		if(task!=null){
			task.cancel();
			task=null;
		}
	}
 @Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	//打开摄像头
	 camera = Camera.open();
	 
}
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
	}
	/***
	 * 摄像头数据回调接口
	 */
	@Override
	public void onPreviewFrame(byte[] data, Camera camera) {
		Log.i("onPreviewFrame", "onPreviewFrame数据接口回调");
        if (data == null) throw new NullPointerException();
        //获取摄像头数据的尺寸
        Camera.Size size = camera.getParameters().getPreviewSize();
        //如果获取不到摄像头数据尺寸抛出异常
        if (size == null) throw new NullPointerException();
        //获取摄像头数据长度与高度
        int width = size.width;
        int height = size.height;
        //接口回调并获取到相机数据后调用YUV转换RGB方法将数据进行转换。
        int imgAvg = ImageProcessing.decodeYUV420SPtoRedAvg(data.clone(), height, width);
        Log.i("imgAvg", "imgAvg========"+imgAvg);
        //限定心率值范围，只有值在合理的也就是40到150之间的值才会显示出来
        if(imgAvg>40&&imgAvg<151){
        	//将Imagvg写入图像方法中做出不规则波线
        	setHeratbeat(imgAvg);
        	textView.setText(""+imgAvg+"次/m");
        }else{
        	setHeratbeat(0);
        	textView.setText("请将手指覆盖摄像头");
        }
    }
	  private void init() {  
		  //初始化曲线图
	        LinearLayout layout=(LinearLayout) findViewById(R.id.root);  
	        view = new DrawChart(this);  
	        view.invalidate();  
	        layout.addView(view);
	    }  
}
