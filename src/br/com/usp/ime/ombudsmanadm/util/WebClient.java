package br.com.usp.ime.ombudsmanadm.util;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class WebClient {

    private final String url;
    
    public WebClient(String url) {
        this.url = url;
    }
    
    /**
     * Executa o metodo HTTP GET
     * @return
     * @throws ConnectionException
     */
    public String get() throws ConnectionException {
        HttpClient request = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        get.addHeader("Content-type", "application/json");
        get.addHeader("Accept", "application/json");

        try {
            HttpResponse response = request.execute(get);
            
            StatusLine status = response.getStatusLine();
            if (status.getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity());
            } else {
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
}
