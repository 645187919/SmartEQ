package com.hzy.smarteq;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class Utils {

	/**
	 * 在屏幕中央显示一个Toast
	 * @param text
	 */
	public static void showToast(Context context, CharSequence text) {
		Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

}
