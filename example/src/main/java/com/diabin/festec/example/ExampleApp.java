package com.diabin.festec.example;

import android.app.Application;

import com.diabin.latte.app.Latte;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

public class ExampleApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .configure();
    }
}
