package com.qollie.eits.qollieandroid.webview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by lili on 2015/11/21.
 */
public class WebViewController extends WebViewClient
{

	Activity activity;
	WebControl.LoadingListener loadingListener;

	public WebViewController(Activity activity, WebControl.LoadingListener loadingListener)
	{

		this.activity = activity;
		this.loadingListener = loadingListener;
	}

	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url)
	{

		Log.e("webviewUrl", url);
		view.loadUrl(url);

		return true;
	}

	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon)
	{
		// TODO Auto-generated method stub

		super.onPageStarted(view, url, favicon);
		loadingListener.beforeProcess();
	}

	@Override
	public void onPageFinished(WebView view, String url)
	{
		// TODO Auto-generated method stub
		super.onPageFinished(view, url);

		loadingListener.stopProcess();
	}
}
