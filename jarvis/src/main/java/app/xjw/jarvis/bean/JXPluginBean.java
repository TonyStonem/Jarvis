package app.xjw.jarvis.bean;

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

/**
 * Created by xjw on 2017/12/6 15:37
 * Email 1521975316@qq.com
 */

public class JXPluginBean {

    private String packageName;
    private String path;
    private DexClassLoader classLoader;
    private Resources resources;
    private String defultActivity;
    private PackageInfo packageInfo;
    private AssetManager assetManager;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public DexClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(DexClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public String getDefultActivity() {
        return defultActivity;
    }

    public void setDefultActivity(String defultActivity) {
        this.defultActivity = defultActivity;
    }

    public PackageInfo getPackageInfo() {
        return packageInfo;
    }

    public void setPackageInfo(PackageInfo packageInfo) {
        this.packageInfo = packageInfo;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public JXPluginBean(String packageName, String path, DexClassLoader classLoader, Resources resources, String defultActivity, PackageInfo packageInfo, AssetManager assetManager) {
        this.packageName = packageName;
        this.path = path;
        this.classLoader = classLoader;
        this.resources = resources;
        this.defultActivity = defultActivity;
        this.packageInfo = packageInfo;
        this.assetManager = assetManager;
    }
}
