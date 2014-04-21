package br.com.usp.ime.ombudsmanadm.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.usp.ime.ombudsmanadm.R;
import br.com.usp.ime.ombudsmanadm.model.dao.UserDAO;
import br.com.usp.ime.ombudsmanadm.model.vo.User;
import br.com.usp.ime.ombudsmanadm.preferences.Preferences;
import br.com.usp.ime.ombudsmanadm.task.LoginTask;
import br.com.usp.ime.ombudsmanadm.util.LoginResponse;


public class LoginActivity extends Activity implements LoginTask.LoginCallback {

    public static final String USER = "user";
    private Button btnLogin;
    private EditText uspNumber, password;
    private User user;
    private Preferences preferences;
    private UserDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = new Preferences(this);
        dao = new UserDAO(this);

        if (preferences.getLoggedUser()) {
            user = dao.getUniqueUser();
        }

        btnLogin = (Button) findViewById(R.id.btn_login);
        uspNumber = (EditText) findViewById(R.id.usp_number);
        password = (EditText) findViewById(R.id.password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoginTask(LoginActivity.this).execute(uspNumber.getText().toString(), password.getText().toString());
            }
        });
    }

    @Override
    public void onLoginReturn(LoginResponse jsonResult) {
        if (jsonResult.getStatus() == false) {
            Toast.makeText(this, "Verifique sua conexão de dados. Não foi possível efetuar o login." + jsonResult.getError(), Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Login efetuado com sucesso!", Toast.LENGTH_LONG).show();
            preferences.setLoggedUser(true);
            user = jsonResult.getUser();
            dao.insert(user);

            callIncidentActivity();
        }
    }

    private void callIncidentActivity() {
        Intent incident = new Intent(this, IncidentActivity.class);
        incident.putExtra(USER, user);
        startActivity(incident);
    }
}
