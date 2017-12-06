package app.xjw.jarvis;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import app.xjw.jarvis.bean.JXPluginBean;
import app.xjw.jarvis.utils.JXClassLoader;


/**
 * Created by xjw on 2017/12/6 15:19
 * Email 1521975316@qq.com
 */

public class JXPluginManager {

    private JXPluginManager(Context context) {
        mOutPutDexPath = context.getDir(OUTPUT_DEX_DIR, Context.MODE_PRIVATE).getAbsolutePath();
        mContext = context.getApplicationContext();
    }

    private static final String OUTPUT_DEX_DIR = "jarvis_output";
    private static JXPluginManager ins;
    private Context mContext;
    private String mOutPutDexPath;
    private Map<String, JXPluginBean> mPluginInfos = new HashMap<>();

    public static JXPluginManager get(Context context) {
        if (ins == null) {
            synchronized (JXPluginManager.class) {
                if (ins == null) {
                    ins = new JXPluginManager(context);
                }
            }
        }
        return ins;
    }

    public JXPluginBean loadAPK(String dexPath) {
        PackageInfo pi = mContext.getPackageManager().getPackageArchiveInfo(dexPath, PackageManager.GET_ACTIVITIES);
        if (pi == null) return null;
        return preparePlugin(dexPath, pi);
    }

    private JXPluginBean preparePlugin(String dexPath, PackageInfo pi) {
        JXPluginBean bean = mPluginInfos.get(pi.packageName);
        if (bean != null) return bean;

        JXClassLoader loader = getLoader(dexPath);
        AssetManager am = getAM(dexPath);
        Resources res = getRes(am);

        bean = new JXPluginBean(pi.applicationInfo.packageName, dexPath, loader, res, null, pi, am);
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

    private JXClassLoader getLoader(String dexPath) {
        return new JXClassLoader(dexPath, mOutPutDexPath, null, mContext.getClassLoader()).get();
    }

    public void startActivity(Context context) {
        startActivityForResult(context);
    }

    public void startActivityForResult(Context context) {

    }
}
