package com.phone1000.groupproject.http;

import android.os.Handler;
import android.os.Message;

import com.phone1000.groupproject.view.IjsonView;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by ${USER_NAME} on 2016/9/6.
 */
public class JsonHttpUtils {
    private static ExecutorService executor;
    public final  static int REQUEST_METHOD_GET = 0;
   public final  static  int REQUEST_METHOD_POST = 1;
    public static JsonHttpUtils newInstance() {
        return new JsonHttpUtils();
    }

    public static void load(String url, Map<String, String> map, IjsonView callback,int requestmethod) {
        //判断当前的excutor是否为空
        if (executor == null) {
            //创建一个数量为五的线程池
            executor = Executors.newFixedThreadPool(5);
        }
        //每调用一次方法就开启一个线程下载Json数据
        executor.execute(new JsonHttpThread(url, map, callback, requestmethod));


    }

    static class JsonHttpThread implements Runnable {
        private String urlString;
        private Map<String, String> params;
        private IjsonView callback;
        private int requestMethod;
        private Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                callback.getJsonString((String) msg.obj);
            }
        };

        public JsonHttpThread(String url, Map<String, String> params, IjsonView callback,int requestMethod) {
            this.urlString = url;
            this.params = params;
            this.callback = callback;
            this.requestMethod = requestMethod;
        }

        @Override
        public void run() {
            HttpURLConnection conn = null;
            InputStream is = null;
            BufferedReader br = null;
            //首先判断请求的参数是否为空
            String content = "";
            try {
                URL url = new URL(urlString);
                conn = (HttpURLConnection) url.openConnection();

                //判断当前的方法为get请求还是POST请求
                /**
                 *当前请为POST请求
                 */
            //判断输入的参数是否为空,如不为空将参数拼接起来
                if(params != null){
                    content = getParams(params);

                }
                /**
                 * 判断请求方法是POST还是GET
                 */
                if (requestMethod == JsonHttpUtils.REQUEST_METHOD_POST) {
                    URL url1 = new URL(urlString);
                    conn = (HttpURLConnection) url1.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    OutputStream os = conn.getOutputStream();
                    os.write(content.getBytes());
                    os.flush();
                    os.close();
                } else {
                    URL url1 = new URL(urlString+content);
                    conn = (HttpURLConnection) url1.openConnection();
                    conn.setRequestMethod("GET");
                }
                conn.connect();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    is = conn.getInputStream();
                    br = new BufferedReader(new InputStreamReader(is));
                    String str = "";
                    StringBuffer stringBuffer1 = new StringBuffer();
                    while ((str = br.readLine()) != null) {
                        stringBuffer1.append(str);
                    }
                    Message message = Message.obtain();
                    String jsonStrings = stringBuffer1.toString();
                    message.obj = jsonStrings;
                    handler.sendMessage(message);
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                conn.disconnect();
                closeStream(is);
                closeStream(br);
            }
        }


        /**
         * 获取参数的一个方法
         *
         * @param
         * @return
         */
        public String getParams(Map<String,String> parmas) {
            StringBuffer stringBuffer = new StringBuffer();
            Set<String> mapSet = parmas.keySet();
            //将参数还有valuel连接起来
            for (String key : mapSet) {
                String values = parmas.get(key);
                stringBuffer.append(key).append("=").append(values).append("&");
            }
            String str = stringBuffer.toString();
            return str.substring(0, str.lastIndexOf("&"));
        }

        /**
         * 关闭流的方法
         */
        public void closeStream(Closeable stream) {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

