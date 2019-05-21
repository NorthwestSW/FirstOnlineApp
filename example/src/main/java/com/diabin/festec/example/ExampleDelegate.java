package com.diabin.festec.example;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.diabin.latte.delegates.LatteDelegate;
import com.diabin.latte.net.RestClient;
import com.diabin.latte.net.callback.IFailure;
import com.diabin.latte.net.callback.ISuccess;

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
        RestClient.builder()
                .url("http://news.baidu.com")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response + "", Toast.LENGTH_SHORT).show();
                    }
                }).failure(new IFailure() {
            @Override
            public void onFailure() {

            }
        }).build().get();

    }
}
