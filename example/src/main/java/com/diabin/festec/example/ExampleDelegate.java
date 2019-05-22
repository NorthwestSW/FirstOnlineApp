package com.diabin.festec.example;

import android.os.Bundle;
import android.view.View;

import com.diabin.latte.delegates.LatteDelegate;
import com.diabin.latte.ui.LatteLoader;

/**
 * 测试类
 */
public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onBindView(Bundle saveInstanceState, View rootView) {
        testrestClient();
    }

    private void testrestClient() {
        LatteLoader.showLoading(getContext());

    }

    @Override
    public void post(Runnable runnable) {

    }
}
