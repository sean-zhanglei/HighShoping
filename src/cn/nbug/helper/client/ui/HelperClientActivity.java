package cn.nbug.helper.client.ui;

//import java.lang.reflect.Field;

import java.lang.reflect.Field;

import cn.nbug.helper.client.Callbacks;
import cn.nbug.helper.client.R;
import cn.nbug.helper.client.fragments.secondFragment;
import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
//import android.view.ViewConfiguration;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class HelperClientActivity extends FragmentActivity
	implements Callbacks
	{
	private ActionBarDrawerToggle mActionBarDrawerToggle;
	private DrawerLayout mdDrawerLayout;
	private NavigationView mNavigationView;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		mdDrawerLayout = (DrawerLayout)findViewById(R.id.id_drawerlayout);
		mNavigationView = (NavigationView)findViewById(R.id.navigation);
		mdDrawerLayout.setDrawerShadow(R.drawable.ic_launcher, GravityCompat.START);
		final android.app.ActionBar actionBar = getActionBar();
/*		//设置应用图标是否可点
		actionBar.setDisplayShowHomeEnabled(true);
		//设置是否显示应用图标
		actionBar.setHomeButtonEnabled(true);
		//设置是否显示向左箭头并且可点
		actionBar.setDisplayHomeAsUpEnabled(true);*/
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
		
		View view = LayoutInflater.from(HelperClientActivity.this).inflate(R.layout.navigation_header, null);
		mNavigationView.addHeaderView(view);
		//操作头像
		//		view.findViewById(R.id.oooo).setOncickListener(this);
		View view1 = LayoutInflater.from(HelperClientActivity.this).inflate(R.menu.menu_navigation, null);
		mNavigationView.addView(view1);
		//操作选项
		//切换
        mActionBarDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mdDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_launcher,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
        	@Override
            public void onDrawerClosed(View view) {
                getActionBar().setTitle("");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        	@Override
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle("");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mdDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        setupDrawerContent(mNavigationView);
		getOverflowMenu();
	}
//	强制弹出溢出菜单
	private void getOverflowMenu() {
        try {
           ViewConfiguration config = ViewConfiguration.get(this);
           Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
           if(menuKeyField != null) {
               menuKeyField.setAccessible(true);
               menuKeyField.setBoolean(config, false);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
	private void setupDrawerContent(NavigationView navigationView){
		navigationView.setNavigationItemSelectedListener(new 
				NavigationView.OnNavigationItemSelectedListener() {
			
			@Override
			public boolean onNavigationItemSelected(MenuItem item) {
					item.setChecked(true);
					Fragment fragmentItem = null;
					int i = item.getItemId();
					//设置选中项,传递item对应的fragment的id
					if (i == R.id.inRegister0) {
						//先写一个样例
						fragmentItem = new secondFragment();
						
					}/*else if(i == R.id.inRegister1  ){
						fragmentID = 1;
						
					}else if (i == R.id.inRegister2 ) {
						fragmentID = 2;
						
					}else if (i == R.id.inRegister3  ) {
						
						fragmentID = 3;
					}else {
						
						fragmentID = 4;
					}*/
					mdDrawerLayout.closeDrawers();
					HelperClientActivity.this.onSelected(fragmentItem);
					return true;
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflatorInflater = new MenuInflater(this);
		inflatorInflater.inflate(R.menu.main_top_menu, menu);
		return super.onCreateOptionsMenu(menu);
		
	}
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mdDrawerLayout.isDrawerOpen(mNavigationView);
        //如果抽屉为弹出状态就隐藏设置选项
        menu.findItem(R.id.ti1).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
		switch (item.getItemId()) {
		case R.id.ti1:
			Toast.makeText(this, "菜单1", Toast.LENGTH_SHORT).show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	//异步
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mActionBarDrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }
    //切换fragment,替换掉主Activity
	public void onSelected(Fragment fragment)
	{			
		Fragment newfragment = null;
		FragmentManager fragmentManager = getSupportFragmentManager();
		//按照ID找到fragment
		newfragment = fragment;
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		// Replace whatever is in the fragment_container view with this fragment,// and add the transaction to the back stack
		fragmentTransaction.replace(R.id.main_fragment, newfragment);
//		fragmentTransaction.addToBackStack(null);
		// Commit the transaction
		fragmentTransaction.commit();
			
		}

	@Override
	public void onItemSelected(Integer id, Bundle bundle) {
		// TODO Auto-generated method stub
		
	}
	

}
