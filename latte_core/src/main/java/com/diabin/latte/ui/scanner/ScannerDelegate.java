package com.diabin.latte.ui.scanner;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.diabin.latte.R;
import com.diabin.latte.delegates.LatteDelegate;
import com.diabin.latte.utils.callback.CallbackManager;
import com.diabin.latte.utils.callback.CallbackType;
import com.diabin.latte.utils.callback.IGlobalCallback;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class ScannerDelegate extends LatteDelegate implements ZBarScannerView.ResultHandler {

    ScanView mScanView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (mScanView == null) {
//            mScanView = new ScanView(getContext());
//        }
//        mScanView.setAutoFocus(true);
//        mScanView.setResultHandler(this);

    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_scan_style;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mScanView = (ScanView) rootView.findViewById(R.id.scan_view);
        mScanView.setAutoFocus(true);
        mScanView.setResultHandler(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mScanView != null) {
            mScanView.startCamera();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mScanView != null) {
            mScanView.stopCameraPreview();
            mScanView.stopCamera();
        }
    }

    @Override
    public void handleResult(Result result) {
        TestBean testBean = new TestBean();
        testBean.age = 26;
        testBean.name = "馮軍";
        testBean.pass = result.getContents();
        @SuppressWarnings("unchecked")
        final IGlobalCallback<TestBean> callback = CallbackManager
                .getInstance()
                .getCallback(CallbackType.ON_SCAN);
        if (callback != null) {
            callback.executeCallback(testBean);
        }
        getSupportDelegate().pop();
    }

    @Override
    public void post(Runnable runnable) {

    }
}
