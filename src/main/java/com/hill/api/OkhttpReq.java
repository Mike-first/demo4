package com.hill.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OkhttpReq {

    public static String cookie(String url, String userAgent) {
        Request cookieReq = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .header("User-Agent", userAgent)
                .build();
        return sendReq(cookieReq).get("cookie");
    }

    public static Map<String, String> cityReq(String url, String userAgent, String cookies) {
        Request reqWithCookies = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .header("User-Agent", userAgent)
                .header("Cookie", cookies)
                .build();
        return sendReq(reqWithCookies);
    }

    public static Map<String, String> sendReq(Request req) {
        OkHttpClient client = new OkHttpClient();
        Map<String, String> res = new HashMap<>();

        try (Response resp = client.newCall(req).execute()) {
            if (!resp.isSuccessful()) {
                throw new IOException("Unexpected code " + resp);
            }
            res.put(Keys.RESP_CODE.key, String.valueOf(resp.code()));
            res.put(Keys.BODY.key, resp.body().string());
            res.put(Keys.COOKIES.key, resp.headers("Set-Cookie").toString());
        } catch (IOException ignored) {
        } finally {
            client.connectionPool().evictAll();
            client.dispatcher().executorService().shutdown();
        }

        return res;
    }

    public enum Keys {
        RESP_CODE("code"), BODY("body"), COOKIES("cookie");
        public final String key;

        Keys(String key) {
            this.key = key;
        }
    }
}
