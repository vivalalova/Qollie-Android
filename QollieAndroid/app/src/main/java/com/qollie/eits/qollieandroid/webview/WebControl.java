package com.qollie.eits.qollieandroid.webview;

import android.app.Activity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by lili on 2016/1/8.
 */
public class WebControl
{

	public WebControl(Activity activity, String url, WebView webView, LoadingListener loadingListener)
	{

		webView.loadUrl("about:blank");
		if (checkIsPic(url))
		{
			setPic(activity, webView, loadingListener);
		}
		else
		{
			setGeneralUrl(activity, webView, loadingListener);
		}
		showWeb(webView, url);
	}

	public void showWeb(WebView webView, String url)
	{

		webView.loadUrl(url);
	}

	private Boolean checkIsPic(String url)
	{

		String[] okFileExtensions = new String[]{"jpg", "png", "gif", "jpeg"};
		for (String extension : okFileExtensions)
		{
			if (url.toLowerCase().endsWith(extension))
			{
				return true;
			}
		}
		return false;
	}

	private Boolean checkHasWord(String longString, String word)
	{

		if (longString.indexOf(word) != -1)
		{
			return true;
		}
		return false;
	}

	private void setPic(Activity activity, WebView webView, LoadingListener loadingListener)
	{

		webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
		webView.setWebViewClient(new WebViewController(activity, loadingListener));
	}

	private void setGeneralUrl(Activity activity, WebView webView, LoadingListener loadingListener)
	{

		webView.getSettings().setLoadWithOverviewMode(true);
		webView.getSettings().setUseWideViewPort(true);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setDomStorageEnabled(true);
		webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		webView.setWebViewClient(new WebViewController(activity, loadingListener));
	}

	public interface LoadingListener
	{
		void stopProcess();

		void beforeProcess();
	}
}
