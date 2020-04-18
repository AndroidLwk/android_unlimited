package com.wuxiantao.wxt.net.http;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MyTrustManager
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-13 上午11:40
 * Description:${DESCRIPTION}  如果需要对证书进行校验，需要这里去实现，如果不实现的话是不安全
 */
public class MyTrustManager implements X509TrustManager {

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        if (chain == null){
            throw new CertificateException("checkServerTrusted: X509Certificate array is null");
        }
        if (chain.length < 1){
            throw new CertificateException("checkServerTrusted: X509Certificate is empty");
        }
        if (!(null != authType && authType.equals("ECDHE_RSA"))){
            throw new CertificateException("checkServerTrusted: AuthType is not ECDHE_RSA");
        }
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) {

    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
