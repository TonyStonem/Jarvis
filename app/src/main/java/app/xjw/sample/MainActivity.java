package app.xjw.sample;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import app.xjw.jarvis.JIPluginManager;
import app.xjw.jarvis.bean.JIIntent;
import app.xjw.jarvis.utils.JIUtils;

public class MainActivity extends AppCompatActivity {

    public class PluginItem {
        String path;
        PackageInfo pi;
        String launcherActivityName;
        String launcherServiceName;
    }

    private List<PluginItem> beanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        if (beanList.size() > 0) {
            PluginItem item = beanList.get(0);
            JIIntent intent = new JIIntent(item.pi.packageName, item.launcherActivityName);
            JIPluginManager.get(this).startActivity(this, intent);
        }

    }

    private void initData() {
        String s = Environment.getExternalStorageDirectory() + "sample_plugin";
        File file = new File(s);
        File[] list = file.listFiles();
        if (list == null || list.length <= 0) return;
        for (File f : list) {
            PluginItem item = new PluginItem();
            item.path = f.getAbsolutePath();
            item.pi = JIUtils.getPackageInfo(MainActivity.this, item.path);
            if (item.pi.activities != null && item.pi.activities.length > 0) {
                item.launcherActivityName = item.pi.activities[0].name;
            }
            if (item.pi.services != null && item.pi.services.length > 0) {
                item.launcherServiceName = item.pi.services[0].name;
            }
            beanList.add(item);
            JIPluginManager.get(MainActivity.this).loadAPK(item.path);
        }
    }
}
