package com.yang.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class SendCodeUtil {
	//请求路径URL
	private static final String Url="https://api.netease.im/sms/sendcode.action";
	//appkey
	private static final String AppKey="43387152de33a3a51645a2dad61bcafd";
	//应用密匙
	private static final String AppSecret="d259e6f4a546";
	 //随机数
    private static final String Nonce="123456";

    /**
     * 
     * @param data 发送的数据
     * @return 响应的json字符串
     * @throws IOException
     */
    public String httpByPost(Map<String,String> data) throws IOException
    {
    	 String result = "";
    	if(this.Url==null)
    	{
    		return null;
    	}
    	CloseableHttpClient httpclient=HttpClients.createDefault();
    	HttpPost httpPost=new HttpPost(Url);
    	//当前UTC时间戳,单位秒
    	String curTime = String.valueOf((new Date()).getTime() / 1000L);
    	//三个参数拼接的字符串，进行SHA1哈希计算，转化成16进制字符
    	String checkSum=CheckSumUtil.getCheckSum(AppSecret, Nonce, curTime);
    	// 设置请求的header
        httpPost.addHeader("AppKey", AppKey);
        httpPost.addHeader("Nonce", Nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
    	//拼接参数
    	List<NameValuePair> params=new ArrayList<NameValuePair>();
    	for(String key:data.keySet())
    	{
    		String value=data.get(key);
    		params.add(new BasicNameValuePair(key, value));
    	}
    	try {
			//UrlEncodedFormEntity()把输入数据编码成合适的内容,setEntity方法设置请求参数
			httpPost.setEntity(new UrlEncodedFormEntity(params,"utf-8"));
			CloseableHttpResponse response = null;
			try {
				//执行请求
				response= httpclient.execute(httpPost);
				//取出应答字符串
				result=EntityUtils.toString(response.getEntity(),"utf-8");
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = e.getMessage().toString();
			} finally {
				//关闭应答流
				response.close();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.getMessage().toString();
		}
    	//返回应答字符串(json格式)
    	return result;
    }
}
