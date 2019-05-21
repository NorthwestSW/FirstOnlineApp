package com.diabin.latte.net;

import com.diabin.latte.app.ConfigType;
import com.diabin.latte.app.Latte;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * retrofit+OkHttp
 */
public class RestCreator {
    /**
     * 参数容器
     */
    private static final class ParamsHolder {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    /**
     * 构建全局Retrofit客户端
     */
    private static final class RetrofitHolder {
        private static final String BASE_URL = (String) Latte.getConfigrations().get(ConfigType.API_HOST);
        /**
         * 初始化retrofit
         */
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                //转换器
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .build();
    }

    /**
     * 构建OkHttp
     * 后期扩展：//日志拦截器，请求头，cookier管理，https证书配置
     */
    private static final class OKHttpHolder {
        //超时时间
        public static final int TIME_OUT = 60;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * Service接口
     */
    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }
}
