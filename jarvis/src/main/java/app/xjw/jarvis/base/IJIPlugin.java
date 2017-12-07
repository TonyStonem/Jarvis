package app.xjw.jarvis.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

import app.xjw.jarvis.bean.JIPluginBean;

/**
 * Created by xjw on 2017/12/7 10:01
 * Email 1521975316@qq.com
 */

public interface IJIPlugin {

    public void onCreate(Bundle savedInstanceState);

    public void onStart();

//    public void onRestart();

//    public void onActivityResult(int requestCode, int resultCode, Intent data);

    public void onResume();

    public void onPause();

    public void onStop();

    public void onDestroy();

    public void attach(Activity proxyActivity, JIPluginBean pluginPackage);

//    public void onSaveInstanceState(Bundle outState);

//    public void onNewIntent(Intent intent);

//    public void onRestoreInstanceState(Bundle savedInstanceState);

    public boolean onTouchEvent(MotionEvent event);

//    public boolean onKeyUp(int keyCode, KeyEvent event);

//    public void onWindowAttributesChanged(ViewGroup.LayoutParams params);

//    public void onWindowFocusChanged(boolean hasFocus);

    public void onBackPressed();

//    public boolean onCreateOptionsMenu(Menu menu);

//    public boolean onOptionsItemSelected(MenuItem item);


}
