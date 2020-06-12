package cn.edu.sc.scu_internet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpPostActivity extends AppCompatActivity {
    private HttpURLConnection conn;
    private Button btnGet;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_post);
        btnGet = findViewById(R.id.btnGet);
        textView=findViewById(R.id.txtView);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trustAllHosts();
                sendRequestHttpURLConnection();
            }
        });

    }

    public void sendRequestHttpURLConnection() {
        Toast.makeText(HttpPostActivity.this, "启动线程", Toast.LENGTH_LONG).show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                try {
                    // url = new URL("http://10.67.0.228:8080/MyWebProjec/MyServle");
                    url = new URL("http://guolin.tech/api/china");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(50000);
                    connection.setRequestMethod("get");

  //                  connection.connect();
//                    DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
//                    dos.writeBytes("username=admin&password=123");
//                    dos.flush();
//                    dos.close();

                    final StringBuffer stringBuffer = new StringBuffer();
                    String readLine = new String();
                    BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    while ((readLine = responseReader.readLine()) != null) {
                        stringBuffer.append(readLine);
                    }
                    responseReader.close();
                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(stringBuffer);
                        }
                    });
                    Log.i("AAA",stringBuffer.toString());


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        ).start();
    }

    private static void trustAllHosts() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[] {};
            }
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException
            {
                Log.i("skyapp", "checkClientTrusted");
            }
            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                Log.i("skyapp", "checkServerTrusted");
            }
        } };
        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}