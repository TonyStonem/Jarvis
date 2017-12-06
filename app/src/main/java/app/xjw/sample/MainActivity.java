package app.xjw.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import app.xjw.jarvis.JXPluginManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JXPluginManager.get(this).loadAPK("");
    }
}
