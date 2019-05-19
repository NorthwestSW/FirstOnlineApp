package com.diabin.latte.app;

import android.content.Context;

import java.util.WeakHashMap;

public final class Latte {
    public static ConfigRator init(Context context){
        getConfigrations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return ConfigRator.getInstance();
    }

    private static WeakHashMap<String,Object> getConfigrations(){
        return ConfigRator.getInstance().getLatteConfigs();
    }
}
