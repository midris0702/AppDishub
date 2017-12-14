package net.netne.afahzis.appdishub;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.netne.afahzis.appdishub.Server.AppVar;
import net.netne.afahzis.appdishub.Server.JSONParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    Toolbar toolbar;
    private ProgressDialog progressDialog;
    EditText etUsername, etPassword;
    CardView btnLogin;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    String level = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = (EditText) findViewById(R.id.username);
        etPassword = (EditText) findViewById(R.id.password);
        btnLogin = (CardView) findViewById(R.id.btnLogin);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        sharedpreferences = getSharedPreferences(AppVar.PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        level = getIntent().getExtras().getString("level");

        String login = sharedpreferences.getString(AppVar.USER_ID, null);
        String userLevel = sharedpreferences.getString(AppVar.USER_LEVEL, null);
        if (login != null && userLevel.equals(level) && !userLevel.equals("8") && !(userLevel.equals("9")||userLevel.equals("10")||userLevel.equals("11")) &&!userLevel.equals("1")) {
            Intent i = new Intent(LoginActivity.this, DashboardActivity.class);
            i.putExtra("level", level);
            startActivity(i);
            finish();
        } else if (login != null && level.equals("8") && userLevel.equals("8")) {
            Intent i = new Intent(LoginActivity.this, MenuSUbActivity.class);
            i.putExtra("level", level);
            startActivity(i);
            finish();
        } else if (login != null && level.equals("9") && (userLevel.equals("9")||userLevel.equals("10")||userLevel.equals("11")) && !userLevel.equals("8")) {
            Intent i = new Intent(LoginActivity.this, LaporanActivity.class);
            i.putExtra("level", level);
            startActivity(i);
            finish();
        } else if (login != null && level.equals("1") && userLevel.equals("1")) {
            Intent i = new Intent(LoginActivity.this, SubAngkutanActivity.class);
            i.putExtra("level", level);
            startActivity(i);
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekInfo();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    public void cekInfo() {
        progressDialog = ProgressDialog.show(this, "Please wait.",
                "Sedang mengambil data..!", true);
        new CekAsync().execute(
                AppVar.PREF_NAME,
                AppVar.KEY_LOGIN,
                etUsername.getText().toString(),
                etPassword.getText().toString(),
                level
        );
    }

    class CekAsync extends AsyncTask<String, String, JSONObject> {
        JSONParser jsonParser = new JSONParser();
        private final String LOGIN_URL = AppVar.URL_SERVER;

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected JSONObject doInBackground(String... args) {

            try {

                HashMap<String, String> params = new HashMap<>();
                params.put(AppVar.KEY_API, args[0]);
                params.put(AppVar.KEY_FUNCTION, args[1]);
                params.put(AppVar.FUNC_EMAIL, args[2]);
                params.put(AppVar.FUNC_PASSWORD, args[3]);
                params.put(AppVar.FUNC_LEVEL, args[4]);
                Log.d("request", "starting");

                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL, "POST", params);

                if (json != null) {
                    Log.d("JSON result", json.toString());
                    return json;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(JSONObject json) {
            if (json != null) {
//                Toast.makeText(getApplicationContext(),json.toString(),Toast.LENGTH_SHORT).show();

                try {
                    JSONObject parentObject = new JSONObject(json.toString());
                    JSONObject userDetails = parentObject.getJSONObject("hasil");
                    String success = userDetails.getString("success");
                    String id = "", getLevel = "";
                    if (success.equals("1")) {
                        id = userDetails.getString("var_id");
                        getLevel = userDetails.getString("var_level");

                        editor.remove(AppVar.USER_ID);
                        editor.remove(AppVar.USER_LEVEL);

                        editor.putString(AppVar.SET_LOGIN, "1");
                        editor.putString(AppVar.USER_ID, id);
                        editor.putString(AppVar.USER_LEVEL, getLevel);

                        editor.commit();
                        if (level.equals("8")) {
                            Intent i = new Intent(LoginActivity.this, MenuSUbActivity.class);
                            i.putExtra("level", level);
                            startActivity(i);
                        } else if (level.equals("9")) {
                            Intent i = new Intent(LoginActivity.this, LaporanActivity.class);
                            i.putExtra("level", level);
                            startActivity(i);
                        } else if (level.equals("1")) {
                            Intent i = new Intent(LoginActivity.this, SubAngkutanActivity.class);
                            i.putExtra("level", level);
                            startActivity(i);
                        } else {
                            Intent i = new Intent(LoginActivity.this, DashboardActivity.class);
                            i.putExtra("level", level);
                            startActivity(i);
                        }

                        finish();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }

            }
            progressDialog.dismiss();
        }
    }
}
