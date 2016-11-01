/**
 *
 */
package cn.nbug.helper.util;


import cn.nbug.helper.client.ui.HelperClientActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.View;

public class DialogUtil
{
	public static void showDialog(final Context ctx
		, String msg , boolean goHome)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx)
			.setMessage(msg).setCancelable(false);
		if(goHome)
		{
			builder.setPositiveButton("确认", new OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					Intent i = new Intent(ctx,HelperClientActivity.class);
					ctx.startActivity(i);
				}
			});
		}
		else
		{
			builder.setPositiveButton("取消", null);
		}
		builder.create().show();
	}
	public static void showDialog(Context ctx , View view)
	{
		new AlertDialog.Builder(ctx)
			.setView(view).setCancelable(false)
			.setPositiveButton("确认", null)
			.create()
			.show();
	}
}
