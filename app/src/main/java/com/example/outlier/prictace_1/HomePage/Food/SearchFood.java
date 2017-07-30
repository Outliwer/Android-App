package com.example.outlier.prictace_1.HomePage.Food;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by outlier on 2017/7/16.
 */

public class SearchFood {
    public static String GetInfomation(String city,String need) throws Exception {
        String address = "http://route.showapi.com/52-26?showapi_appid=42305&q=" + need +
                "&region=" + city + "&scope=2&page_size=&page_num=&showapi_sign=c069130238404775894ba39caf3ac094";
            URL u = new URL(address);
            InputStream in = u.openStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try {
                byte buf[] = new byte[1024];
                int read = 0;
                while ((read = in.read(buf)) > 0) {
                    out.write(buf, 0, read);
                }
            } finally {
                if (in != null) {
                    in.close();
                }
            }
            byte b[] = out.toByteArray();
        return new String(b, "utf-8");
    }

}
