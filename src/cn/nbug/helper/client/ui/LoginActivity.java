package cn.nbug.helper.client.ui;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import cn.nbug.helper.client.HomeListener;
import cn.nbug.helper.client.R;
import cn.nbug.helper.util.DialogUtil;
import cn.nbug.helper.util.HttpUtil;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	EditText etName, etPass;
	Button bnLogin, bnCancel,bnzhuce;
	DrawerLayout mdDrawerLayout;
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Login");
		//设置应用图标是否可点
		actionBar.setDisplayShowHomeEnabled(false);
		
		etName = (EditText) findViewById(R.id.userEditText);
		etPass = (EditText) findViewById(R.id.pwdEditText);
		bnLogin = (Button) findViewById(R.id.bnLogin);
		bnCancel = (Button) findViewById(R.id.bnCancel);
//		bnzhuce = (Button)findViewById(R.id.inRegister);
		bnCancel.setOnClickListener(new HomeListener(this));
		bnLogin.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (validate())  
				{
					Log.d("loginActivity","validate结束,验证结果为:"+validate());
					if (loginPro())  
					{
						Log.d("loginActivity","loginPro结束,验证结果为:"+loginPro());
						Intent intent = new Intent(LoginActivity.this
							, HelperClientActivity.class);
						startActivity(intent);
						finish();
					}
					else
					{
						DialogUtil.showDialog(LoginActivity.this
							, "对不起，登录失败！", false);
					}
				}else {
					DialogUtil.showDialog(LoginActivity.this, "用户名或密码不能为空", false);
				}
			}
		});
//		Toast.makeText(this, "bnzhuce:"+bnzhuce, Toast.LENGTH_LONG).show();
/*		bnzhuce.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
				startActivity(intent);
			}
		});*/
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflatorInflater = new MenuInflater(this);
		inflatorInflater.inflate(R.menu.login_menu, menu);;
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem MI){

		int key = MI.getItemId();
		switch (key) {
		case R.id.inRegister:
			Toast.makeText(this, R.id.inRegister, Toast.LENGTH_LONG).show();
			Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
			startActivity(intent);
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(MI);
		}
	}
	private boolean validate()
	{
		String username = etName.getText().toString().trim();
		if (username.equals(""))
		{
			return false;
		}
		String pwd = etPass.getText().toString().trim();
		if (pwd.equals(""))
		{
			return false;
		}
		
		return true;
	}
	private JSONObject query(String username, String password)
			throws Exception
		{
			Map<String, String> map = new HashMap<String, String>();
			map.put("user", username);
			map.put("pass", password);
			String url = HttpUtil.BASE_URL+"loginServlet";
			Log.d("loginActivity","url:"+url+"map:"+map.toString());
			return new JSONObject(HttpUtil.postRequest(url,map));
		}
	private boolean loginPro()
	{
		String username = etName.getText().toString();
		String pwd = etPass.getText().toString();
		JSONObject jsonObj;
		try
		{
			jsonObj = query(username, pwd);
			int num = jsonObj.length();
			if (num > 0)
			{
				return true;
			}
		}
		catch (Exception e)
		{
			DialogUtil.showDialog(this
				, "服务器，网络异常！", false);
			e.printStackTrace();
		}

		return false;
	}


	
}
