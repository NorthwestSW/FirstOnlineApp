package com.diabin.festec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import com.diabin.latte.delegates.LatteDelegate;
import com.diabin.latte.ui.camera.RequestCodes;
import com.diabin.latte.ui.loader.LatteLoader;
import com.diabin.latte.ui.scanner.ScannerDelegate;
import com.diabin.latte.ui.scanner.TestBean;
import com.diabin.latte.utils.callback.CallbackManager;
import com.diabin.latte.utils.callback.CallbackType;
import com.diabin.latte.utils.callback.IGlobalCallback;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 测试类
 */
public class ExampleDelegate extends LatteDelegate {

    @BindView(R.id.icon_test)
    AppCompatButton iconTest;


    @Override
    public Object setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onBindView(Bundle saveInstanceState, View rootView) {
        testrestClient();
        CallbackManager.getInstance()
                .addCallback(CallbackType.ON_SCAN, new IGlobalCallback<TestBean>() {
                    @Override
                    public void executeCallback(@Nullable TestBean args) {
                        Toast.makeText(getContext(), "得到的二维码是" + args.name + " 奈年齡 " + args.age + "數量 " + args.pass, Toast.LENGTH_LONG).show();
                    }
                });
    }

    @OnClick(R.id.icon_test)
    void testBtn() {
//        startScan();
        this.getSupportDelegate().start(new CamaerDelegate());
    }

    //扫描二维码(不直接调用)
    void startScan() {
        startScanWithCheck(this.getParentDelegate());
//        if (getParentDelegate() != null) {
//
//        } else {
//            Toast.makeText(_mActivity, "对象为空", Toast.LENGTH_SHORT).show();
//        }

    }

    private void testrestClient() {
        LatteLoader.showLoading(getContext());

    }

    @Override
    public void post(Runnable runnable) {

    }
}
