package com.diabin.latte.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.WeakHashMap;

/**
 * 配置文件的存储或者获取
 */
public class ConfigRator {
    private static final WeakHashMap<String, Object> LATTE_CONFIGS = new WeakHashMap<>();

    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private ConfigRator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    private static class Holder {
        public static final ConfigRator INSTANCE = new ConfigRator();
    }

    public final void configure() {
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    /**
     * 线程安全的懒汉单例模式
     * @return
     */
    public static ConfigRator getInstance() {
        return Holder.INSTANCE;
    }

    final WeakHashMap<String,Object> getLatteConfigs(){
        return LATTE_CONFIGS;
    }

    public final  ConfigRator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(),host);
        return this;
    }
    private void checkConfiguration(){
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady){
            throw new RuntimeException("configuration is not ready,call configure");
        }
    }
    @SuppressWarnings("unchecked")   //告诉编译器类型没有经过检测
    final  <T> T getConfiguration(Enum<ConfigType> key){
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());

    }
   public final  ConfigRator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
   }
    /**
     * 初始化字体图标
     */
    private void initIcons(){
          if (ICONS.size() >0){
             final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
              for (int i = 1; i < ICONS.size(); i++) {
                  initializer.with(ICONS.get(i));
              }
          }
    }
}
