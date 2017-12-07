package app.xjw.jarvis.base.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import app.xjw.jarvis.JIPluginManager;
import app.xjw.jarvis.base.IJIPlugin;
import app.xjw.jarvis.bean.JIAll;
import app.xjw.jarvis.bean.JIIntent;
import app.xjw.jarvis.bean.JIPluginBean;

/**
 * Created by xjw on 2017/12/7 9:56
 * Email 1521975316@qq.com
 */

public class BaseJIPluginActivity extends Activity implements IJIPlugin {

    private Activity mProxyActivity;
    private Activity that;
    private JIPluginBean mPluginBean;
    private int mFrom;
    private JIPluginManager mPluginManager;

    @Override
    public void setContentView(View view) {
        if (mFrom == JIAll.IF_INTERNAL) {
            super.setContentView(view);
        } else {
            mProxyActivity.setContentView(view);
        }
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        if (mFrom == JIAll.IF_INTERNAL) {
            super.setContentView(view, params);
        } else {
            mProxyActivity.setContentView(view, params);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        if (mFrom == JIAll.IF_INTERNAL) {
            super.setContentView(layoutResID);
        } else {
            mProxyActivity.setContentView(layoutResID);
        }
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        if (mFrom == JIAll.IF_INTERNAL) {
            super.addContentView(view, params);
        } else {
            mProxyActivity.addContentView(view, params);
        }
    }

    @Override
    public View findViewById(int id) {
        if (mFrom == JIAll.IF_INTERNAL) {
            return super.findViewById(id);
        } else {
            return mProxyActivity.findViewById(id);
        }
    }

    @Override
    public Intent getIntent() {
        if (mFrom == JIAll.IF_INTERNAL) {
            return super.getIntent();
        } else {
            return mProxyActivity.getIntent();
        }
    }

    @Override
    public ClassLoader getClassLoader() {
        if (mFrom == JIAll.IF_INTERNAL) {
            return super.getClassLoader();
        } else {
            return mProxyActivity.getClassLoader();
        }
    }

    @Override
    public Resources getResources() {
        if (mFrom == JIAll.IF_INTERNAL) {
            return super.getResources();
        } else {
            return mProxyActivity.getResources();
        }
    }

    @Override
    public String getPackageName() {
        if (mFrom == JIAll.IF_INTERNAL) {
            return super.getPackageName();
        } else {
            return mPluginBean.getPackageName();
        }
    }

    @Override
    public LayoutInflater getLayoutInflater() {
        if (mFrom == JIAll.IF_INTERNAL) {
            return super.getLayoutInflater();
        } else {
            return mProxyActivity.getLayoutInflater();
        }
    }

    @Override
    public MenuInflater getMenuInflater() {
        if (mFrom == JIAll.IF_INTERNAL) {
            return super.getMenuInflater();
        } else {
            return mProxyActivity.getMenuInflater();
        }
    }

    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        if (mFrom == JIAll.IF_INTERNAL) {
            return super.getSharedPreferences(name, mode);
        } else {
            return mProxyActivity.getSharedPreferences(name, mode);
        }
    }

    @Override
    public Context getApplicationContext() {
        if (mFrom == JIAll.IF_INTERNAL) {
            return super.getApplicationContext();
        } else {
            return mProxyActivity.getApplicationContext();
        }
    }

    @Override
    public WindowManager getWindowManager() {
        if (mFrom == JIAll.IF_INTERNAL) {
            return super.getWindowManager();
        } else {
            return mProxyActivity.getWindowManager();
        }
    }

    @Override
    public Window getWindow() {
        if (mFrom == JIAll.IF_INTERNAL) {
            return super.getWindow();
        } else {
            return mProxyActivity.getWindow();
        }
    }

    @Override
    public Object getSystemService(String name) {
        if (mFrom == JIAll.IF_INTERNAL) {
            return super.getSystemService(name);
        } else {
            return mProxyActivity.getSystemService(name);
        }
    }

    @Override
    public void finish() {
        if (mFrom == JIAll.IF_INTERNAL) {
            super.finish();
        } else {
            mProxyActivity.finish();
        }
    }

    @Override
    public void attach(Activity proxyActivity, JIPluginBean pluginPackage) {
        mProxyActivity = (Activity) proxyActivity;
        that = mProxyActivity;
        mPluginBean = pluginPackage;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mFrom = savedInstanceState.getInt(JIAll.IF, JIAll.IF_INTERNAL);
        }
        if (mFrom == JIAll.IF_INTERNAL) {
            mProxyActivity = this;
            that = mProxyActivity;
        }
        mPluginManager = JIPluginManager.get(that);
    }

    @Override
    public void onStart() {
        if (mFrom == JIAll.IF_INTERNAL) {
            super.onStart();
        }
    }

    @Override
    public void onResume() {
        if (mFrom == JIAll.IF_INTERNAL) {
            super.onResume();
        }
    }

    @Override
    public void onPause() {
        if (mFrom == JIAll.IF_INTERNAL) {
            super.onPause();
        }
    }

    @Override
    public void onStop() {
        if (mFrom == JIAll.IF_INTERNAL) {
            super.onStop();
        }
    }

    @Override
    public void onDestroy() {
        if (mFrom == JIAll.IF_INTERNAL) {
            super.onDestroy();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mFrom == JIAll.IF_INTERNAL) {
            return super.onTouchEvent(event);
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (mFrom == JIAll.IF_INTERNAL) {
            super.onBackPressed();
        }
    }

    /**
     * @param dlIntent
     */
    public int startPluginActivity(JIIntent dlIntent) {
        return startPluginActivityForResult(dlIntent, -1);
    }

    /**
     * @param dlIntent
     */
    public int startPluginActivityForResult(JIIntent dlIntent, int requestCode) {
        if (mFrom == JIAll.IF_EXTERNAL) {
            if (dlIntent.getPluginPackage() == null) {
                dlIntent.setPluginPackage(mPluginBean.getPackageName());
            }
        }
        return mPluginManager.startActivityForResult(that, dlIntent, requestCode);
    }


}
