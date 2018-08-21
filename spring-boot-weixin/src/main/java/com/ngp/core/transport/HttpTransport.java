package com.ngp.core.transport;


import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class HttpTransport implements Transport {

    private HttpClient httpClient;

    // 连接池的最大连接数
    private  int maxTotal = 200;
    // 连接池按route配置的最大连接数
    private  int maxPerRoute = 200;

    // tcp connect的超时时间
    private  int connectTimeout = 500;
    // 从连接池获取连接的超时时间
    private  int connectRequestTimeout = 500;
    // tcp io的读写超时时间
    private  int socketTimeout = 2000;

    private PoolingHttpClientConnectionManager gcm = null;

    @Autowired
    public HttpTransport() {

        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();

        gcm = new PoolingHttpClientConnectionManager(registry);
        gcm.setMaxTotal(this.maxTotal);
        gcm.setDefaultMaxPerRoute(this.maxPerRoute);

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(this.connectTimeout)                     // 设置连接超时
                .setSocketTimeout(this.socketTimeout)                       // 设置读取超时
                .setConnectionRequestTimeout(this.connectRequestTimeout)    // 设置从连接池获取连接实例的超时
                .build();

        HttpClientBuilder httpClientBuilder = HttpClients.custom();

        this.httpClient = httpClientBuilder
                .setConnectionManager(this.gcm)
                .setDefaultRequestConfig(requestConfig)
                .build();
    }


    @Override
    public String execute(Object object) throws Exception {

        HttpClientRequest httpclientrequest = (HttpClientRequest)object;

        HttpPost httpPost = new HttpPost(httpclientrequest.getUrl());

        //设置heeader
        Map<String,String> headers = httpclientrequest.getHeaders();
        if ( headers != null && headers.size() > 0 ) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.addHeader(entry.getKey(), entry.getValue());
            }
        }

        Map params = httpclientrequest.getParams();
        String contentType = httpclientrequest.getContentType();
        //配置请求参数
        if ( params != null && params.size() > 0 ) {
            if("application/json".equals(contentType)){
                StringEntity se = new StringEntity(httpclientrequest.getJsonStr());
                httpPost.setEntity(se);
            }else{
                HttpEntity entityReq = getUrlEncodedFormEntity(params);
                httpPost.setEntity(entityReq);
            }

        }


        CloseableHttpResponse response = null;
        try {
            response = (CloseableHttpResponse)httpClient.execute(httpPost);
            if (response == null || response.getStatusLine() == null) {
                return null;
            }

            int statusCode = response.getStatusLine().getStatusCode();
            if ( statusCode == HttpStatus.SC_OK ) {
                HttpEntity entityRes = response.getEntity();
                if ( entityRes != null ) {
                    return EntityUtils.toString(entityRes, "UTF-8");
                }
            }
            return null;
        } catch (IOException e) {
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                }
            }
        }
        return null;

    }


    private HttpEntity getUrlEncodedFormEntity(Map<String, Object> params) {
        List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry
                    .getValue().toString());
            pairList.add(pair);
        }
        return new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8"));
    }
}
