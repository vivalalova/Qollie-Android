package com.qollie.eits.qollieandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.qollie.eits.qollieandroid.webview.WebControl;

public class CompanyWebActivity extends AppCompatActivity
{

	public static final String webNameKey = "WED_NAME";
	public static final String webUrlKey = "WED_URL";
//	public ArrayListProgressBar progressBar;
	//	private RelativeLayout rrProgressHouse;
	private TextView txtActionBarTitle;
	private WebView webView;
	private String webName = "";
	private String webUrl = "";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setParentContentView();
		getData();
		initView();
		setWebView();
	}

	public void setParentContentView()
	{
		setContentView(R.layout.activity_webview);
	}

	private void getData()
	{

		Bundle extras = getIntent().getExtras();
		if (extras != null)
		{
			webName = extras.getString(webNameKey);
			webUrl = extras.getString(webUrlKey);
			Log.e("webUrl",""+webUrl);
		}
	}



	private void initActionBar()
	{
		ImageButton imbLeft = (ImageButton) findViewById(R.id.imbLeft);
		if (imbLeft != null)
		{
			imbLeft.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					finish();
				}
			});
		}

		ImageButton btnFunction = (ImageButton) findViewById(R.id.btnFunction);
		btnFunction.setImageResource(R.mipmap.icon_refresh);
		btnFunction.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				webView.reload();
			}
		});
		txtActionBarTitle = (TextView) findViewById(R.id.txtActionBarTitle);
		setTitle(webName);
	}

	public void setTitle(String title)
	{
		if (txtActionBarTitle != null)
		{
			txtActionBarTitle.setText(title);
		}
	}

	private void initView()
	{
		webView = (WebView) findViewById(R.id.webView);
		initActionBar();
	}

	private void setWebView()
	{
		if (webView != null && webUrl != null )
		{
			WebControl.LoadingListener loadingListener = new WebControl.LoadingListener()
			{

				@Override
				public void stopProcess()
				{
//					progressBar.dismiss();
				}

				@Override
				public void beforeProcess()
				{
//					progressBar.show();
				}
			};
			webView.setVisibility(View.VISIBLE);
			WebControl webControl = new WebControl(this, webUrl, webView, loadingListener);
		}
	}
}
