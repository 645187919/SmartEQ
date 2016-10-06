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
	 * ��ʱ��������ͷ���ݵĶ�ʱ��
	 */
	private Timer timer = new Timer(); 
	private TimerTask task; 
	public static TextView textView;
  @SuppressLint("HandlerLeak")
private Handler handler=new Handler(){
	  public void handleMessage(Message msg) {
		  //��������ص��ӿ�����MainActivity�Ѿ�ʵ���˻ص��ӿڣ�����MainActivity.this����
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
		//ͨ��IDfindview�ؼ�
		oprenCamer=(Button) findViewById(R.id.open);
		closeCamer=(Button) findViewById(R.id.close);
		textView=(TextView) findViewById(R.id.heart_text);
		oprenCamer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//����ͷ��ʼ����Ԥ��֡����
				 camera.startPreview();
				 //��ȡ�����ǰ���ò���
				 Camera.Parameters parameter = camera.getParameters();  
				 //��������ơ�
				 parameter.setFlashMode(Parameters.FLASH_MODE_TORCH); 
				camera.setParameters(parameter);
				//������ʱ��
				startTimer();
			}
		});
		closeCamer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//��ȡ����ͷ����
				Camera.Parameters parameter = camera.getParameters();  
				//��������ͷ���� �ر������
				 parameter.setFlashMode(Parameters.FLASH_MODE_OFF); 
				 
				camera.setParameters(parameter);
				pauseTimer();
			}
		});
		init();
	}
	 public void onPause() {
	        super.onPause();
	        //��ͣ��ʱ��
	        pauseTimer();
	        //������Ļص��ӿ���ΪNULL�����ٽ��ܻص�����
	        camera.setPreviewCallback(null);
	        camera.stopPreview();
	        //�Ͽ�������ͷŶ���
	        camera.release();
	        camera = null;
	    }
	 /***
	  * ��ʼ���Է���
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
	  * �������Է���
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
	//������ͷ
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
	 * ����ͷ���ݻص��ӿ�
	 */
	@Override
	public void onPreviewFrame(byte[] data, Camera camera) {
		Log.i("onPreviewFrame", "onPreviewFrame���ݽӿڻص�");
        if (data == null) throw new NullPointerException();
        //��ȡ����ͷ���ݵĳߴ�
        Camera.Size size = camera.getParameters().getPreviewSize();
        //�����ȡ��������ͷ���ݳߴ��׳��쳣
        if (size == null) throw new NullPointerException();
        //��ȡ����ͷ���ݳ�����߶�
        int width = size.width;
        int height = size.height;
        //�ӿڻص�����ȡ��������ݺ����YUVת��RGB���������ݽ���ת����
        int imgAvg = ImageProcessing.decodeYUV420SPtoRedAvg(data.clone(), height, width);
        Log.i("imgAvg", "imgAvg========"+imgAvg);
        //�޶�����ֵ��Χ��ֻ��ֵ�ں����Ҳ����40��150֮���ֵ�Ż���ʾ����
        if(imgAvg>40&&imgAvg<151){
        	//��Imagvgд��ͼ�񷽷���������������
        	setHeratbeat(imgAvg);
        	textView.setText(""+imgAvg+"��/m");
        }else{
        	setHeratbeat(0);
        	textView.setText("�뽫��ָ��������ͷ");
        }
    }
	  private void init() {  
		  //��ʼ������ͼ
	        LinearLayout layout=(LinearLayout) findViewById(R.id.root);  
	        view = new DrawChart(this);  
	        view.invalidate();  
	        layout.addView(view);
	    }  
}
