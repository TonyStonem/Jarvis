package app.xjw.jarvis;

import java.util.HashMap;
import java.util.Map;

import dalvik.system.DexClassLoader;

/**
 * Created by xjw on 2017/12/6 15:40
 * Email 1521975316@qq.com
 */

public class JIClassLoader extends DexClassLoader {

    private static final Map<String, JIClassLoader> loaders = new HashMap<>();

    private final String dexPath;
    private final String outputPath;
    private final ClassLoader parent;

    public JIClassLoader(String dexPath, String optimizedDirectory,
                         String librarySearchPath, ClassLoader parent) {
        super(dexPath, optimizedDirectory, librarySearchPath, parent);
        this.dexPath = dexPath;
        this.outputPath = optimizedDirectory;
        this.parent = parent;
    }

    public JIClassLoader get() {
        JIClassLoader loader = loaders.get(dexPath);
        if (loader != null) return loader;
        loader = new JIClassLoader(dexPath, outputPath, null, parent);
        loaders.put(dexPath, loader);
        return loader;
    }

}
