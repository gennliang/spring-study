package com.study.spring.utils;

import org.apache.http.Consts;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/*
 * 进行httpClient的post请求
 */
public class HttpClientUtil {
	/**
	 * 
	 * @param url
	 *            要提交的目标url
	 * @param map
	 *            参数集合
	 * @param charset
	 *            编码
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 * @throws URISyntaxException
	 */
	public static String postJson(String url, String params) throws ParseException, IOException, URISyntaxException {
		return postJson(url, params, 2000, 2000);
	}

	public static String postJson(String url, String params, Integer socketTimeout, Integer connectTimeout) throws ParseException, IOException, URISyntaxException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		RequestBuilder requestBuilder = RequestBuilder.post().setUri(new URI(url));
		requestBuilder.addHeader("Content-Type", "application/json;charset=UTF-8");
		requestBuilder.setEntity(new StringEntity(params, Consts.UTF_8));
		if (0 < socketTimeout && 0 < connectTimeout) {
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();//设置请求和传输超时时间
            requestBuilder.setConfig(requestConfig);
        }
		CloseableHttpResponse response = httpClient.execute(requestBuilder.build());
		if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
			return EntityUtils.toString(response.getEntity(), "UTF-8");
		}
		return null;
	}
}