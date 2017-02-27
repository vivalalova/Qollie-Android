package com.qollie.eits.qollieandroid.viewpager;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.qollie.eits.qollieandroid.R;

/**
 * Created by lili on 2016/4/14.
 */
public class SimpleGuidePage
{
	Activity activity;
	int resource = R.mipmap.ic_launcher;

	public SimpleGuidePage(Activity activity)
	{
		this.activity = activity;
	}

	public SimpleGuidePage(Activity activity, int resource)
	{
		this.activity = activity;
		this.resource = resource;
	}

	public View getView()
	{
		LayoutInflater lf = activity.getLayoutInflater().from(activity);
		View view1 = lf.inflate(R.layout.item_viewpager_base, null);
		ImageView imgGuild1 = (ImageView) view1.findViewById(R.id.imgGuild);
		imgGuild1.setImageResource(resource);
		return view1;
	}
}
