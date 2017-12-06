package app.xjw.jarvis.utils;

import java.util.HashMap;
import java.util.Map;

import dalvik.system.DexClassLoader;

/**
 * Created by xjw on 2017/12/6 15:40
 * Email 1521975316@qq.com
 */

public class JXClassLoader extends DexClassLoader {

    private static final Map<String, JXClassLoader> loaders = new HashMap<>();

    private final String dexPath;
    private final String outputPath;
    private final ClassLoader parent;

    public JXClassLoader(String dexPath, String optimizedDirectory,
                         String librarySearchPath, ClassLoader parent) {
        super(dexPath, optimizedDirectory, librarySearchPath, parent);
        this.dexPath = dexPath;
        this.outputPath = optimizedDirectory;
        this.parent = parent;
    }

    public JXClassLoader get() {
        JXClassLoader loader = loaders.get(dexPath);
        if (loader != null) return loader;
        loader = new JXClassLoader(dexPath, outputPath, null, parent);
        loaders.put(dexPath, loader);
        return loader;
    }

}
