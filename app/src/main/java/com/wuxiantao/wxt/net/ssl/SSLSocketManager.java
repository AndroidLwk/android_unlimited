package com.wuxiantao.wxt.net.ssl;

import android.content.Context;

import com.wuxiantao.wxt.app.BaseApplication;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.Collection;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SSLSocketManager
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-13 上午11:51
 * Description:${DESCRIPTION} 证书管理类
 */
public class SSLSocketManager {

//    //获取SSLSocketFactory
//    public static SSLSocketFactory createCertificates() {
//        SSLContext mSSLContext = null;
//        InputStream in = null;
//        try {
//            //证书工厂类，生成证书
//            CertificateFactory mCertificateFactory = CertificateFactory.getInstance("X.509");
//            //将ca证书导入输入流
//            in = BaseApplication.getAppContext().getAssets().open("pem/wuxiantao.pem");
//            LogUtils.loge(testReadX509CerFile(in));
//            Certificate ca = mCertificateFactory.generateCertificate(in);
//            //keystore添加证书内容和密码
//            KeyStore mKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
//            mKeyStore.load(null, null);
//            //生成证书，添加别名
//            mKeyStore.setCertificateEntry("alias",ca);
//            //信任管理器工厂
//            String type = TrustManagerFactory.getDefaultAlgorithm();
//            TrustManagerFactory mTrustManagerFactory = TrustManagerFactory.getInstance(type);
//            mTrustManagerFactory.init(mKeyStore);
//            //构建一个ssl上下文，加入ca证书格式
//            mSSLContext = SSLContext.getInstance("TLS");
//            //参数:添加受信任证书和生成随机数
//            mSSLContext.init(null, mTrustManagerFactory.getTrustManagers(), new SecureRandom());
//        }
//        catch (KeyStoreException e){
//            e.printStackTrace();
//            LogUtils.loge("KeyStoreException===========>");
//        }
//        catch (NoSuchAlgorithmException e){
//            LogUtils.loge("NoSuchAlgorithmException===========>");
//        }
//        catch (IOException e){
//            e.printStackTrace();
//            LogUtils.loge("IOException===========>");
//        }
//        catch (CertificateException e){
//            e.printStackTrace();
//            LogUtils.loge("CertificateException===========>");
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            LogUtils.loge("Exception===========>");
//        }finally {
//            try {
//                if (in != null){
//                    in.close();
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//        return mSSLContext == null ? null : mSSLContext.getSocketFactory();
//    }
//
//
//    //读取*.cer公钥证书文件， 获取公钥证书信息
//    @SuppressLint("SimpleDateFormat")
//    private static String testReadX509CerFile(InputStream in) {
//        StringBuilder sb = new StringBuilder();
//        Format format = new SimpleDateFormat("yyyy/MM/dd");
//        try {
//            // 创建X509工厂类
//            CertificateFactory cf = CertificateFactory.getInstance("X.509");
//            // 创建证书对象
//            X509Certificate oCert = (X509Certificate) cf.generateCertificate(in);
//            in.close();
//            // 获得证书版本
//            sb.append("证书版本:")
//                    .append(oCert.getVersion())
//                    .append("\n证书序列号:")
//                    .append(oCert.getSerialNumber())
//                    .append("\n证书生效日期:")
//                    .append(format.format(oCert.getNotBefore()))
//                    .append("\n证书失效日期:")
//                    .append(format.format(oCert.getNotAfter()))
//                    .append("\n证书拥有者:").append(oCert.getSubjectDN()
//                    .getName()).append("\n证书颁发者:")
//                    .append(oCert.getIssuerDN().getName())
//                    .append("\n证书签名算法:")
//                    .append(oCert.getSigAlgName());
//        } catch (CertificateException e) {
//            e.printStackTrace();
//            sb.append(BaseApplication.getAppContext().getResources().getString(R.string.cer_resolve_error));
//        } catch (IOException e) {
//            e.printStackTrace();
//            sb.append(BaseApplication.getAppContext().getResources().getString(R.string.cer_resolve_error));
//        }
//        return sb.toString();
//    }

    //获取SSLSocketFactory
    public static TrustManager[] createTrustManagers() {
        InputStream in = null;
        try {
            //证书工厂类，生成证书
            CertificateFactory mCertificateFactory = CertificateFactory.getInstance("X.509");
            //将ca证书导入输入流
            Context context = BaseApplication.getAppContext();
            in = context.getResources().getAssets().open("pem/wuxiantao.pem");

            // 将证书保存到 KeyStore 中
            char[] pw = "password".toCharArray();
            //keystore添加证书内容和密码
            KeyStore mKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            mKeyStore.load(null, pw);
            Collection<? extends Certificate> mCertificates = mCertificateFactory.generateCertificates(in);
            if (mCertificates.isEmpty()){
                throw new IllegalArgumentException("expected non-empty set of trusted certificates");
            }
            int index = 0;
            for (Certificate certificate : mCertificates){
                String certificateAlias = String.valueOf(index++);
                //生成证书，添加别名
                mKeyStore.setCertificateEntry(certificateAlias, certificate);
            }
            // 使用包含自签名证书的 KeyStore 构建一个 X509TrustManager
            KeyManagerFactory mKeyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            mKeyManagerFactory.init(mKeyStore,pw);
            //信任管理器工厂
            TrustManagerFactory mTrustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            mTrustManagerFactory.init(mKeyStore);
            TrustManager[]  mTrustManagers = mTrustManagerFactory.getTrustManagers();
            if (mTrustManagers.length != 1 || !(mTrustManagers[0] instanceof X509TrustManager)){
                throw new IllegalStateException("Unexpected default trust managers:"+ Arrays.toString(mTrustManagers));
            }
            in.close();
            return mTrustManagers;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (in != null){
                    in.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }


    public static SSLSocketFactory createSSLSocketFactory(TrustManager[]  mTrustManagers){
        //构建一个ssl上下文，加入ca证书格式
        SSLContext mSSLContext = null;
        try {
            mSSLContext = SSLContext.getInstance("TLS");
            //参数:添加受信任证书和生成随机数
            mSSLContext.init(null, new TrustManager[]{mTrustManagers[0]}, new SecureRandom());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return mSSLContext != null ? mSSLContext.getSocketFactory() : null;
    }

}
