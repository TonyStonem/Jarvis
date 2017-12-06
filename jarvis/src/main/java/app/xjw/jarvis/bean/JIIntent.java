package app.xjw.jarvis.bean;

import android.content.Intent;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by xjw on 2017/12/6 21:17
 * Email 1521975316@qq.com
 */

public class JIIntent extends Intent {

    private String mPluginPackage;
    private String mPluginClass;

    public JIIntent() {
        super();
    }

    public JIIntent(String pluginPackage) {
        super();
        this.mPluginPackage = pluginPackage;
    }

    public JIIntent(String pluginPackage, String pluginClass) {
        super();
        this.mPluginPackage = pluginPackage;
        this.mPluginClass = pluginClass;
    }

    public JIIntent(String pluginPackage, Class<?> clazz) {
        super();
        this.mPluginPackage = pluginPackage;
        this.mPluginClass = clazz.getName();
    }

    public String getPluginPackage() {
        return mPluginPackage;
    }

    public void setPluginPackage(String pluginPackage) {
        this.mPluginPackage = pluginPackage;
    }

    public String getPluginClass() {
        return mPluginClass;
    }

    public void setPluginClass(String pluginClass) {
        this.mPluginClass = pluginClass;
    }

    public void setPluginClass(Class<?> clazz) {
        this.mPluginClass = clazz.getName();
    }

    @Override
    public Intent putExtra(String name, Parcelable value) {
        setupExtraClassLoader(value);
        return super.putExtra(name, value);
    }

    @Override
    public Intent putExtra(String name, Serializable value) {
        setupExtraClassLoader(value);
        return super.putExtra(name, value);
    }

    private void setupExtraClassLoader(Object value) {
        ClassLoader pluginLoader = value.getClass().getClassLoader();
//        DLConfigs.sPluginClassloader = pluginLoader;
        setExtrasClassLoader(pluginLoader);
    }


}
