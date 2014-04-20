package br.com.usp.ime.ombudsmanadm.task;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import br.com.usp.ime.ombudsmanadm.util.LoginParser;
import br.com.usp.ime.ombudsmanadm.util.LoginResponse;
import br.com.usp.ime.ombudsmanadm.util.WebClient;

public class LoginTask extends AsyncTask<String, Objects, String> {
    private static final String URL_STOA = "https://maxwell.stoa.usp.br/plugin/stoa/authenticate/";
    private LoginCallback callback;
    private Context context;
    private ProgressDialog progress;

    public interface LoginCallback {
        public void onLoginReturn(LoginResponse response);
    }

    public LoginTask(LoginCallback callback) {
        this.callback = callback;
        this.context = (Context) callback;
    }


	@Override
    protected void onPreExecute() {
    	progress = ProgressDialog.show(context, "Verificando usuário", "Realizando login...", true, true);
    }

    @Override
    protected String doInBackground(String... param) {
        WebClient webClient = new WebClient(URL_STOA);

        Map<String, String> params = new HashMap<String, String>();
        params.put("usp_id", param[0]);
        params.put("password", param[1]);

        return webClient.postHttps(params);
    }

    @Override
    protected void onPostExecute(String jsonResponse) {
        progress.dismiss();
        LoginResponse loginResponse = new LoginParser().toLoginResponse(jsonResponse);
        callback.onLoginReturn(loginResponse);
    }
}
