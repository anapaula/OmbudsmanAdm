package br.com.usp.ime.ombudsmanadm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpConnector {

    private final String url;
    
    public HttpConnector(String url) {
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
                HttpEntity entity = response.getEntity();
                return convertInputStreamToString(entity.getContent());
            } else {
            	throw new ConnectionException("O retorno da requisicao foi diferente de OK(200)");
            }
        } catch (IOException e) {
        	throw new ConnectionException("Ocorreu um erro na requisicao", e);
        }
    }
    
    private String convertInputStreamToString(InputStream stream) throws IOException {
    	StringBuilder builder = new StringBuilder();
    	BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
    	
    	String line = null;
		while((line = reader.readLine()) != null) {
			builder.append(line);
		}
    	return builder.toString();
    }
}
