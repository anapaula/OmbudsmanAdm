package br.com.usp.ime.ombudsmanadm.util;

import java.io.IOException;
import java.io.InputStream;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class WebClient {

    private final String url;
    private Map<String, String> params = new HashMap<>();
    
    public WebClient(String url) {
        this.url = url;
    }
    
    /**
     * Executa o metodo HTTP GET
     * @return
     * @throws ConnectionException
     */
    public InputStream get() throws ConnectionException {
        HttpClient request = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        get.addHeader("Content-type", "application/json");
        get.addHeader("Accept", "application/json");

        try {
        	Log.d(WebClient.class.getSimpleName(), "realizando a consulta a url " + url);
            HttpResponse response = request.execute(get);
            
            StatusLine status = response.getStatusLine();
            if (status.getStatusCode() == 200) {
            	Log.d(WebClient.class.getSimpleName(), "retorno com sucesso da url " + url);
                return response.getEntity().getContent();
            } else {
            	Log.e(WebClient.class.getSimpleName(), "erro na consulta a url " + url);
            	throw new ConnectionException("O retorno da requisicao foi diferente de OK(200)");
            }
        } catch (IOException e) {
        	throw new ConnectionException("Ocorreu um erro na requisicao", e);
        }
    }
    
	public String post(String json) throws ConnectionException {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		
		try {
			post.setEntity(new StringEntity(json));
			post.setHeader("Accept", "application/json");
			post.setHeader("Content-type", "application/json");
			HttpResponse response = httpClient.execute(post);
			
			if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity());
            } else {
            	throw new ConnectionException("O retorno da requisicao foi diferente de OK(200)");
            }
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void addParams(String key, String value) {
		params.put(key, value);
	}
	
	public String postHttps(Map<String, String> inParams) {
        String jsonResponse = "";
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();

        for (Map.Entry<String, String> entry : inParams.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, new TrustManager[]{
                    new X509TrustManager() {
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {
                        }

                        public void checkServerTrusted(X509Certificate[] chain, String authType) {
                        }

                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            }, null);

            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            HttpsURLConnection.setDefaultSSLSocketFactory(ctx.getSocketFactory());

            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(this.url);

            request.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = client.execute(request);

            jsonResponse = EntityUtils.toString(response.getEntity());
            Log.i("Ouvidoria", jsonResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResponse;

    }
}
