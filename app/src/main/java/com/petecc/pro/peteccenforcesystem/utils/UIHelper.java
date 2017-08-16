package com.petecc.pro.peteccenforcesystem.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.petecc.pro.peteccenforcesystem.application.Appcontext;

import butterknife.internal.Utils;

public class UIHelper {

	private static Application mApplication;
	private UIHelper() {
	}
	public static void init(Application application) {
		if(mApplication==null) {
			mApplication = application;
		}
	}

	/**
	 * @return 返回 Application 对象
	 */
	public static Application getApplication() {
			return mApplication;
	}


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
		new AlertDialog.Builder(getApplication()).setTitle(title).setMessage(rmsg)
				.setNegativeButton("知道了", null).create().show();
	}
	
	/**
	 * 显示进度条
	 * @param context
	 * @param title
	 * @param message
	 */
	public static final void showProgressDialog(Context context, String title,
												String message) {
		dismissDialog();
		if (TextUtils.isEmpty(title)) {
			title = "请稍候";
		}
		if (TextUtils.isEmpty(message)) {
			message = "正在加载...";
		}
		mProgressDialog = ProgressDialog.show(UIHelper.getApplication(), title, message);
	}

	/**
	 * 关闭进度条
	 */
	public static final void dismissDialog() {
		if (mProgressDialog != null) {
			mProgressDialog.dismiss();
			mProgressDialog = null;
		}
	}
	
	public static void showToast(int resId) {
        String text = getApplication().getString(resId);
        showToast(text);
    }

    public static void showToast(String text) {
        try {
            Toast.makeText(getApplication(), text, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.i("uihelper", e.toString());
        }
    }
	
	/**
	 * 打印消息并且用Toast显示消息
	 * 
	 * @param activity
	 * @param message
	 * @param logLevel
	 * 填d, w, e分别代表debug, warn, error; 默认是debug
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
	 * @param activity
	 * @param message
	 *            填d, w, e分别代表debug, warn, error; 默认是debug
	 */
	public static final void toastMessage(final Activity activity,
			final String message) {
		toastMessage(activity, message, null);
	}
	
	

	
	/**
	 * 添加网页的点击图片展示支持
	 */
	@SuppressLint("SetJavaScriptEnabled")
	public static void addWebImageShow(WebView wv) {
		wv.getSettings().setJavaScriptEnabled(true);
//		wv.addJavascriptInterface(new OnWebViewImageListener() {
//
//			@Override
//			public void onImageClick(String bigImageUrl) {
//				if (bigImageUrl != null){
//					//to do something
//				}
//			}
//		}, "mWebViewImageListener");
	}
	
	/**
	 * 获取webviewClient对象
	 * 
	 * @return
	 */
	public static WebViewClient getWebViewClient() {
		return new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
			
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
			}
			
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				
			}
			
		};
	}
	

	/**
	 * 调用系统安装了的应用分享
	 * @param context
	 * @param title
	 * @param url
	 */
	public static void showShareMore(Activity context, final String title,
									 final String url) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, "分享：" + title);
		intent.putExtra(Intent.EXTRA_TEXT, title + " " + url);
		context.startActivity(Intent.createChooser(intent, "选择分享"));
	}
}
