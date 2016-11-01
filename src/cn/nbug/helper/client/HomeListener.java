package cn.nbug.helper.client;

import cn.nbug.helper.client.ui.HelperClientActivity;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;


public class HomeListener implements OnClickListener
{
	private Activity activity;
	public HomeListener(Activity activity)
	{
		this.activity = activity;
	}
	@Override
	public void onClick(View source)
	{
		Intent i = new Intent(activity , HelperClientActivity.class);
		activity.startActivity(i);
	}
}
