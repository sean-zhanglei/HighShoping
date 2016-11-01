package cn.nbug.helper.client.ui;

import cn.nbug.helper.client.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class RegisterActivity extends Activity {
	protected void onCreate(Bundle saveInstanceBundle){
		super.onCreate(saveInstanceBundle);
		setContentView(R.layout.register);
		Button bnzhuce1 = (Button)findViewById(R.id.register);
		bnzhuce1.setOnClickListener(new android.view.View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//调用注册，返回boolean值
				if (true) {
					AlertDialog.Builder builder   =  new AlertDialog.Builder(RegisterActivity.this)
					.setTitle("注册")
					.setMessage("是否跳转到主页");
					setPositiveButton(builder);
					setNegativeButton(builder)
					.create()
					.show();
					
				}
				
			}
		});
	}
	
	public AlertDialog.Builder setPositiveButton(AlertDialog.Builder builder){
		return builder.setPositiveButton("确认", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent i = new Intent(RegisterActivity.this,HelperClientActivity.class);
				startActivity(i);
			}


		});
	}
	public AlertDialog.Builder setNegativeButton(AlertDialog.Builder builder){
		return builder.setNegativeButton("取消", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}

		});
	}
}
