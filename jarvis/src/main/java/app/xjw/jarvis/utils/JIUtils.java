package app.xjw.jarvis.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by xjw on 2017/12/6 21:02
 * Email 1521975316@qq.com
 */

public class JIUtils {

    public static PackageInfo getPackageInfo(Context context, String path) {
        PackageManager pm = context.getPackageManager();
        return pm.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES | PackageManager.GET_SERVICES);
    }

}
