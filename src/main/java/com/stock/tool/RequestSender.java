package com.stock.tool;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class RequestSender {

    public String sendRequest(String requestUrl, String requestMethod) throws Exception {
        StringBuffer buffer = null;
        URL url;

        SSLContext sslContext = SSLContext.getInstance("SSL");

        TrustManager[] tm = {new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }};

        sslContext.init(null, tm, new java.security.SecureRandom());

        SSLSocketFactory ssf = sslContext.getSocketFactory();

        url = new URL(requestUrl);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();


        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);
        conn.setRequestMethod(requestMethod);
        conn.setSSLSocketFactory(ssf);
        conn.connect();

        InputStream is = conn.getInputStream();

        InputStreamReader isr = new InputStreamReader(is, "utf-8");
        BufferedReader br = new BufferedReader(isr);
        buffer = new StringBuffer();
        String line = null;
        while ((line = br.readLine()) != null) {
            buffer.append(line);
        }

        return buffer.toString();
    }
}
