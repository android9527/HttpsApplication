package io.github.android9527.httpsapplication.volley;

import android.content.Context;
import android.util.Log;

import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * https的支持
 */
public class YKTrustManager implements X509TrustManager {

    /* 此处存放服务器证书密钥 */
    private static final String PUB_KEY =
            "30820122300d06092a864886f70d01010105000382010f003082010a0282010100add086cfc3df3bcf54bffb4e044a911cc0eadbab61ead529a96525833a1a00f75df3d746e11666dbdf4ed8594c4f9194456a49a32a3dce999d9679d2cbc59cf9082935517e35a0706f1041ad053b727c9c92a47507d0313cf5b3788c609733255a89d40c6a8b8d1a90f0761e7dacf117e43fe1b5ae093e160f902a42433ebd57f91cf27b88cd46dcebb85aa0b33c6a48771ca445ace6f6668626d60156eecd1fc2feb282809f8f835b5f5c457890694f495fbf1620070b4a18094c44680beafac05c59ba062b2e889cc8e6a5feca13c3e473700858aceeac0e25f2ba0bfdf44b1040a9ecb15a3f7ea91a366baeeed02f0af78f982d5d0db854bf9476db5f15c10203010001";


    private Context mContext;

    public YKTrustManager(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String string) throws CertificateException {
        //To change body of implemented methods use File | Settings | File Templates.
        Log.e("YKTrustManager", "------- checkClientTrusted" + x509Certificates + " " + string);
    }

    /*  https协商中获取的服务器证书链(chain)将自动传入该方法 */
    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        Log.e("YKTrustManager", "------- checkServerTrusted" + chain + " " + authType);

//
////        ECDHE_RSA
        if (chain == null) {
            throw new IllegalArgumentException("checkServerTrusted: X509Certificate array is null");
        }
        if (!(chain.length > 0)) {
            throw new IllegalArgumentException("checkServerTrusted: X509Certificate is empty");
        }

        /* authType为建立SSL链接时使用的非对称加密算法，RSA为业界主流算法 */
//        if (!(null != authType && authType.equalsIgnoreCase("RSA"))) {
//            throw new CertificateException("checkServerTrusted: AuthType is not RSA");
//        }

        /* 执行SSL/TLS常规性检查 */
        try {
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("X509");
            tmf.init((KeyStore) null);
            for (TrustManager trustManager : tmf.getTrustManagers()) {
                ((X509TrustManager) trustManager).checkServerTrusted(chain, authType);
            }

        } catch (Exception e) {
            throw new CertificateException(e);
        }
//
//        /* 将编码后的密钥转换成16进制形式的大整数 */
//
//        RSAPublicKey pubkey = (RSAPublicKey) chain[0].getPublicKey();
//
//        String encoded = new BigInteger(1, pubkey.getEncoded()).toString(16);
//
//        /* 比较预埋证书密钥和服务器证书密钥是否一致 */
//
//        final boolean expected = PUB_KEY.equalsIgnoreCase(encoded);
//
//        if (!expected) {
//            throw new CertificateException("checkServerTrusted: Expected public key: "
//                    + PUB_KEY + ", got public key:" + encoded);
//        }
    }


    public boolean isClientTrusted(final X509Certificate[] chain) {
        return true;
    }

    public boolean isServerTrusted(final X509Certificate[] chain) {
        return true;
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
//        X509Certificate[] certificates = new X509Certificate[1];
//        InputStream[] inputStreams = new InputStream[1];
//        try {
//            inputStreams[0] = mContext.getAssets().open("server.cer");
////            certificates[0] = context.getAssets().open("gsorganizationvalsha2g2r1.crt");;
//            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509", "BC");
//            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
//            keyStore.load(null);
//            int index = 0;
//            for (InputStream inputStream : inputStreams) {
//                Certificate ca = certificateFactory.generateCertificate(inputStream);
//                Log.d("Longer", "ca=" + ((X509Certificate) ca).getSubjectDN());
//                Log.d("Longer", "key=" + ((X509Certificate) ca).getPublicKey());
//                certificates[index] = (X509Certificate) ca;
//                index++;
//                try {
//                    if (inputStream != null) {
//                        inputStream.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                String certificateAlias = Integer.toString(index);
//                keyStore.setCertificateEntry(certificateAlias, ca);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return certificates;
        return new X509Certificate[]{};
    }

    public static void allowAllSSL(Context context) {
        HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                // 跳过域名验证
                return true;
            }
        };

        HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);

        try {
//            javax.net.ssl.SSLSocketFactory socketFactory = new YKTLSSocketFactory(context);
            javax.net.ssl.SSLSocketFactory socketFactory = YKTLSSocketFactory.getSslSocketFactory(context);
            HttpsURLConnection.setDefaultSSLSocketFactory(socketFactory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}