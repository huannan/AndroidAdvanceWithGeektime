package com.dodola.breakpad;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.sample.breakpad.BreakpadInit;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBreakPad();
        findViewById(R.id.crash_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crash();
                // copy core dump to sdcard
            }
        });
    }

    /**
     * 一般来说，crash捕获初始化都会放到Application中，这里主要是为了大家有机会可以把崩溃文件输出到sdcard中
     * 做进一步的分析
     */
    private void initBreakPad() {
        System.loadLibrary("crash-lib");
        BreakpadInit.initBreakpad(getExternalFilesDir("crashDump").getAbsolutePath());
    }

    public native void crash();
}
