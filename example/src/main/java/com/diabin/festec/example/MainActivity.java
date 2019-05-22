package com.diabin.festec.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView testTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testTV = (TextView) findViewById(R.id.testApp);

    }

    /**
     * 测试编译生成代码
     *
     * @param view
     */
    public void testAsscos(View view) {

    }
}
