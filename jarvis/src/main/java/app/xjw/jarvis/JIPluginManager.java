package app.xjw.jarvis;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.text.TextUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import app.xjw.jarvis.bean.JIAll;
import app.xjw.jarvis.bean.JIIntent;
import app.xjw.jarvis.bean.JIPluginBean;


/**
 * Created by xjw on 2017/12/6 15:19
 * Email 1521975316@qq.com
 */

public class JIPluginManager {

    private JIPluginManager(Context context) {
        mOutPutDexPath = context.getDir(OUTPUT_DEX_DIR, Context.MODE_PRIVATE).getAbsolutePath();
        mContext = context.getApplicationContext();
    }

    /**
     * success
     */
    public static final int START_RESULT_SUCCESS = 0;

    /**
     * not found
     */
    public static final int START_RESULT_NO_PKG = 1;

    /**
     * not found
     */
    public static final int START_RESULT_NO_CLASS = 2;

    /**
     * type error
     */
    public static final int START_RESULT_TYPE_ERROR = 3;

    private static final String OUTPUT_DEX_DIR = "jarvis_output";
    private static JIPluginManager ins;
    private Context mContext;
    private String mOutPutDexPath;
    private Map<String, JIPluginBean> mPluginInfos = new HashMap<>();

    private int mState = JIAll.IF_INTERNAL;

    public static JIPluginManager get(Context context) {
        if (ins == null) {
            synchronized (JIPluginManager.class) {
                if (ins == null) {
                    ins = new JIPluginManager(context);
                }
            }
        }
        return ins;
    }

    public JIPluginBean loadAPK(String dexPath) {
        PackageInfo pi = mContext.getPackageManager().getPackageArchiveInfo(dexPath, PackageManager.GET_ACTIVITIES);
        if (pi == null) return null;
        mState = JIAll.IF_EXTERNAL;
        return preparePlugin(dexPath, pi);
    }

    private JIPluginBean preparePlugin(String dexPath, PackageInfo pi) {
        JIPluginBean bean = mPluginInfos.get(pi.packageName);
        if (bean != null) return bean;

        JIClassLoader loader = getLoader(dexPath);
        AssetManager am = getAM(dexPath);
        Resources res = getRes(am);

        bean = new JIPluginBean(pi.applicationInfo.packageName, dexPath, loader, res, null, pi, am);
        mPluginInfos.put(pi.packageName, bean);

        return bean;
    }

    private AssetManager getAM(String dexPath) {
        try {
            AssetManager am = AssetManager.class.newInstance();
            Method method = am.getClass().getDeclaredMethod("addAssetPath", String.class);
            method.invoke(am, dexPath);
            return am;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Resources getRes(AssetManager am) {
        try {
            Resources res = mContext.getResources();
            return new Resources(am, res.getDisplayMetrics(), res.getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private JIClassLoader getLoader(String dexPath) {
        return new JIClassLoader(dexPath, mOutPutDexPath, null, mContext.getClassLoader()).get();
    }

    public void startActivity(Context context, JIIntent intent) {
        startActivityForResult(context, intent, -1);
    }

    public int startActivityForResult(Context context, JIIntent intent, int code) {

        if (mState == JIAll.IF_INTERNAL) {
            intent.setClassName(context, intent.getPluginClass());
            performStartActivityForResult(context, intent, code);
            return START_RESULT_SUCCESS;
        }

        String packageName = intent.getPluginPackage();
        if (TextUtils.isEmpty(packageName)) {
            throw new NullPointerException("not find packageName.");
        }

        JIPluginBean jib = mPluginInfos.get(packageName);

        if (jib == null) {
            return START_RESULT_NO_PKG;
        }

        String className = getPluginActivityFullPath(intent, jib);
        Class<?> pluginClass = loadPluginClass(className, jib.getClassLoader());

        if (pluginClass == null) {
            return START_RESULT_NO_CLASS;
        }



        return START_RESULT_SUCCESS;
    }

    private Class<?> loadPluginClass(String className,ClassLoader loader) {
        try {
            return Class.forName(className, true, loader);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void performStartActivityForResult(Context context, JIIntent intent, int code) {
        if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, code);
        }else {
            context.startActivity(intent);
        }
    }

    private String getPluginActivityFullPath(JIIntent intent, JIPluginBean bean) {
        String className = intent.getPluginClass();
        className = TextUtils.isEmpty(className) ? bean.getDefultActivity() : className;
        if (className.startsWith(".")) {
            className = intent.getPluginPackage() + className;
        }
        return className;
    }

}
