package  com.hzy.smarteq.xinlv;

import android.util.Log;

public abstract class ImageProcessing {
	  private static int decodeYUV420SPtoRedSum(byte[] yuv420sp, int width, int height) {
	        if (yuv420sp==null) return 0;
	       
	        final int frameSize = width * height;

	        int sum = 0;
	        for (int j = 0, yp = 0; j <height; j++) {
	            int uvp = frameSize + (j >> 1) * width, u = 0, v = 0;
	            for (int i = 0; i <width; i++, yp++) {
	                int y = (0xff & ((int) yuv420sp[yp])) - 16;
	                
	                if (y <0) y = 0;
	                
	                if ((i & 1) == 0) {
	                    v = (0xff & yuv420sp[uvp++]) - 128;
	                    u = (0xff & yuv420sp[uvp++]) - 128;
	                }
	                int  r=(int) (y+1.14*v);
	                int  g=(int) (y - 0.394*u - 0.581*v);
	                int  b=(int) (u+2.203*v);
	                if(r>170){
	                	sum+=(int) ((r * 0.3)+(g * 0.59) + (b * 0.11));
	                }
	            }
	        }
	        return sum;
	    }
	   
	   
	    public static int decodeYUV420SPtoRedAvg(byte[] yuv420sp, int width, int height) {
	        if (yuv420sp==null) return 0;
	        final int frameSize = width * height;
	        int sum = decodeYUV420SPtoRedSum(yuv420sp,width,height);
	        Log.i("decode", "decode=="+sum);
	        Log.i("decode", "frameSize=="+frameSize);
	        sum=sum/frameSize;
	        return sum;
	    }
	    
	 /* //这个公式用来算亮度
	    public static double getRGB(BufferedImage image){
	    		int x=image.getWidth();
	    		int y=image.getHeight();
	    		long sum=0;
	    		for(int i=1;i<x;i=i+2){
	    			for(int j=1;j<y;j=j+2){
	    				//循环获取每个像素的RGB值
	    				int rgb=image.getRGB(i, j);
	    				//单独获取R,G,B
	    				int r =(rgb & 0xff0000 ) >> 16 ;
	    				int g= (rgb & 0xff00 ) >> 8 ;
	    				int b= (rgb & 0xff );
	    				sum+=((r*0.3)+(g*0.59)+(b*0.11));	
	    			}
	    		}
	            return  sum;
	        }
	    //这个算峰值数，15秒里探测的峰值数count乘以4，就是一分钟的心率
	    public  int getCount(){
	    		int count=0;
	    		for (int i=0;i<imageRGB.size();i++) {
	    			if(i>2&&i<imageRGB.size()-2){
	    				if(imageRGB.get(i)>imageRGB.get(i+1)&&imageRGB.get(i)>imageRGB.get(i-1)){
	    					count++;
	    				}
	    			}
	    		}	
	    		return count;
	    	}*/
}
