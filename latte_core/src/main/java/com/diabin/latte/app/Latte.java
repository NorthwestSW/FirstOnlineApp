package com.diabin.latte.app;

import android.content.Context;

import java.util.HashMap;

public final class Latte {

    public static Configurator init(Context context){
        getConfigrations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<String,Object> getConfigrations(){
        return Configurator.getInstance().getLatteConfigs();
    }

    public static  Context getApplication(){
        return (Context) getConfigrations().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}
