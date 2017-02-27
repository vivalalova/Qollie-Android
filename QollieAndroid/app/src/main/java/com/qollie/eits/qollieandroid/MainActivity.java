package com.qollie.eits.qollieandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qollie.eits.qollieandroid.viewpager.SimpleGuidePage;
import com.qollie.eits.qollieandroid.webview.WebControl;
import com.qollie.eits.qollieandroid.webview.WebStation;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String webNameKey = "WED_NAME";
    public static final String webUrlKey = "WED_URL";

    private final String database = "DATABASE";
    private final String showGuide = "SHOW_GUIDE";

    private String webUrl = "https://www.qollie.com/";
    private List<View> viewList = new ArrayList<View>();

    private WebView webView;
    private RelativeLayout rrGuide;
    private CirclePageIndicator indicator;
    private PagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private TextView txtStep;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewList();
        initView();
        setValue();
    }

    private void initView() {
        webView = (WebView) findViewById(R.id.webView);
        rrGuide = (RelativeLayout) findViewById(R.id.rrGuide);
        txtStep= (TextView) findViewById(R.id.txtStep);
        viewPager = (ViewPager) findViewById(R.id.vpDisplayPhoto);
        indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        setWebView();


        if(getShowGuide(MainActivity.this)){
            webView.setVisibility(View.GONE);
            showView();
        }else {
            rrGuide.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
        }

        txtStep.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                setShowGuide(MainActivity.this,false);
                rrGuide.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initViewList() {

        viewList = new ArrayList<View>();

		SimpleGuidePage simpleGuidePage = new SimpleGuidePage(this, R.mipmap.guide1);
        SimpleGuidePage simpleGuidePage2 = new SimpleGuidePage(this, R.mipmap.guide2);
		viewList.add(simpleGuidePage.getView());
        viewList.add(simpleGuidePage2.getView());
    }
    public boolean getShowGuide(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(database, 0);
        Boolean isShowGuide=sharedPreferences.getBoolean(showGuide, true);
        return isShowGuide;
    }

    public void setShowGuide(Context context, boolean show_guide_true)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(database, 0);
        sharedPreferences.edit().putBoolean(showGuide, show_guide_true).commit();
    }
    private void setValue() {
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent); // Handle text being sent
            }
        }
    }

    private void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            Log.e("sharedText", "" + sharedText);
            String strCompany = WebStation.getCompany(sharedText);

            Intent intent2 = new Intent(MainActivity.this, CompanyWebActivity.class);
            intent2.putExtra(webNameKey, strCompany);
            intent2.putExtra(webUrlKey, "https://www.qollie.com/search?keyword="+strCompany+"&kind=company&from=normal");
            startActivity(intent2);
        }
    }


    private void setWebView() {
        if (webView != null && webUrl != null) {
            WebControl.LoadingListener loadingListener = new WebControl.LoadingListener() {

                @Override
                public void stopProcess() {
//					progressBar.dismiss();
                }

                @Override
                public void beforeProcess() {
//					progressBar.show();
                }
            };
            webView.setVisibility(View.VISIBLE);
            WebControl webControl = new WebControl(this, webUrl, webView, loadingListener);
        }
    }


    private void showView()
    {
        if (viewList != null && viewList.size() > 0 && viewPager != null)
        {
            setPager();
            viewPager.setAdapter(pagerAdapter);
            indicator.setViewPager(viewPager);
        }
    }

    private void setPager()
    {

        pagerAdapter = new PagerAdapter()
        {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1)
            {

                return arg0 == arg1;
            }

            @Override
            public int getCount()
            {

                return viewList.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object)
            {

                container.removeView(viewList.get(position));
            }

            @Override
            public int getItemPosition(Object object)
            {

                return super.getItemPosition(object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position)
            {

                container.addView(viewList.get(position));
                return viewList.get(position);
            }
        };

        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(4 * density);
        indicator.setPageColor(Color.WHITE);
        indicator.setFillColor(Color.WHITE);
        indicator.setStrokeColor(Color.WHITE);
        indicator.setOnPageChangeListener(getPageChangeListener());
    }

    public ViewPager.OnPageChangeListener getPageChangeListener()
    {
        return new ViewPager.OnPageChangeListener()
        {

            @Override
            public void onPageSelected(int position)
            {

            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        };
    }

}
