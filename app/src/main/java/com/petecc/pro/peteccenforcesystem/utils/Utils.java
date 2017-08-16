package com.petecc.pro.peteccenforcesystem.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

/**
 * 工具类
 * @author liyy
 *
 */
public class Utils {

	private static final String TAG = "SDK_Sample.Util";

	private static Dialog mProgressDialog;
	private static Toast mToast;
	/**
	 * 显示Dialog
	 * @param context
	 * @param msg
	 * @param title
	 */
	public static final void showResultDialog(Context context, String msg,
											  String title) {
		if(msg == null) return;
		String rmsg = msg.replace(",", "\n");
		Log.d("Util", rmsg);
		new AlertDialog.Builder(context).setTitle(title).setMessage(rmsg)
				.setNegativeButton("知道了", null).create().show();
	}
	
	public static final void showProgressDialog(Context context, String title,
												String message) {
		dismissDialog();
		if (TextUtils.isEmpty(title)) {
			title = "请稍候";
		}
		if (TextUtils.isEmpty(message)) {
			message = "正在加载...";
		}
		mProgressDialog = ProgressDialog.show(context, title, message);
	}

	public static final void dismissDialog() {
		if (mProgressDialog != null) {
			mProgressDialog.dismiss();
			mProgressDialog = null;
		}
	}

	/**
	 * 打印消息并且用Toast显示消息
	 * 
	 * @param activity
	 * @param message
	 * @param logLevel
	 *            填d, w, e分别代表debug, warn, error; 默认是debug
	 */
	public static final void toastMessage(final Activity activity,
										  final String message, String logLevel) {
		if ("w".equals(logLevel)) {
			Log.w("sdkDemo", message);
		} else if ("e".equals(logLevel)) {
			Log.e("sdkDemo", message);
		} else {
			Log.d("sdkDemo", message);
		}
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (mToast != null) {
					mToast.cancel();
					mToast = null;
				}
				mToast = Toast.makeText(activity, message, Toast.LENGTH_SHORT);
				mToast.show();
			}
		});
	}

	/**
	 * 打印消息并且用Toast显示消息
	 * 
	 * @param activity
	 * @param message
	 *            填d, w, e分别代表debug, warn, error; 默认是debug
	 */
	public static final void toastMessage(final Activity activity,
			final String message) {
		toastMessage(activity, message, null);
	}
	
	/**
	 * 获取客户端版本号
	 * @return
	 * @throws Exception
	 */
	public static String getVersionName(Context context) {
		// 获取packagemanager的实例
		try {
			PackageManager packageManager = context.getPackageManager();
			// getPackageName()是你当前类的包名，0代表是获取版本信息
			PackageInfo packInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			String version = packInfo.versionName;
			return version;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 获取客户端版本号
	 * @return
	 * @throws Exception
	 */
	public static int getVersionCode(Context context) {
		// 获取packagemanager的实例
		try {
			PackageManager packageManager = context.getPackageManager();
			// getPackageName()是你当前类的包名，0代表是获取版本信息
			PackageInfo packInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			return packInfo.versionCode;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}
	
	/**
	 * 存储
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void write(Context context, String key, String value) {
    	if (null == context) {
            return;
        }
    	SharedPreferences pref = context.getSharedPreferences("update", Context.MODE_APPEND);
        Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }
	
	/**
	 * 从sp中读取数据
	 * @param context
	 * @param key
	 * @return
	 */
    public static String read(Context context, String key) {
    	if (null == context) {
            return null;
        }
    	SharedPreferences pref = context.getSharedPreferences("update", Context.MODE_APPEND);
        return pref.getString(key, "");
    }
    
    
    /**
	 * 获取设备imei
	 * @param context
	 * @return
	 */
    public static String getDeviceIMEI(Context context) {
    	TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId() == null ?"":tm.getDeviceId();
    }
    /**
     * 获取设备手机号
     * @param context
     * @return
     */
    public static String getDevicePhone(Context context) {
    	TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getLine1Number() == null ?"":tm.getLine1Number();
    }
    
}
