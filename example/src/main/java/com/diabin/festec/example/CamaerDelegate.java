package com.diabin.festec.example;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.diabin.latte.delegates.LatteDelegate;
import com.diabin.latte.utils.callback.CallbackManager;
import com.diabin.latte.utils.callback.CallbackType;
import com.diabin.latte.utils.callback.IGlobalCallback;

import butterknife.BindView;

public class CamaerDelegate extends LatteDelegate {

    @BindView(R.id.custom_auto_photo_layout)
    AutoPhotoLayout mAutoPhotoLayout = null;
    @Override
    public Object setLayout() {
        return R.layout.delegate_camera;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mAutoPhotoLayout.setDelegate(this);
        CallbackManager.getInstance()
                .addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                    @Override
                    public void executeCallback(@Nullable Uri args) {
                        mAutoPhotoLayout.onCropTarget(args);
                    }
                });
    }

    @Override
    public void post(Runnable runnable) {

    }
}
