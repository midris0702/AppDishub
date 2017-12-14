package net.netne.afahzis.appdishub;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import net.netne.afahzis.appdishub.Adapter.DataAdapter;
import net.netne.afahzis.appdishub.Server.AppVar;
import net.netne.afahzis.appdishub.Server.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataActivity extends AppCompatActivity {

    Toolbar toolbar;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    ListView listData;
    private ProgressDialog progressDialog;

    List<String> ID = new ArrayList<String>();
    List<String> KEGIATAN = new ArrayList<String>();
    List<String> BULAN = new ArrayList<String>();
    List<String> DANA = new ArrayList<String>();
    DataAdapter adapter;
    int first=0,onFocus=0;
    EditText txtDana,txtJudul;
    Spinner txtBulan;

    String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        listData = (ListView)findViewById(R.id.listData);
        Button btnSave = (Button)findViewById(R.id.btnSave);
        txtJudul = (EditText) findViewById(R.id.etKegiatan);
        txtBulan = (Spinner) findViewById(R.id.spBulan);
        txtDana = (EditText) findViewById(R.id.etDana);

        sharedpreferences = getSharedPreferences(AppVar.PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        level = getIntent().getExtras().getString("level");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getData();

        btnSave.setOnClickListener(new View.OnClickListener() {
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
        progressDialog = ProgressDialog.show(DataActivity.this, "Please wait.",
                "Sedang mengirim data..!", true);
        new CekAsync().execute(
                AppVar.PREF_NAME,
                AppVar.KEY_SAVE,
                String.valueOf(txtBulan.getSelectedItemPosition()),
                txtJudul.getText().toString(),
                txtDana.getText().toString(),
                level,
                sharedpreferences.getString(AppVar.USER_ID,null)

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
                params.put(AppVar.FUNC_BULAN, args[2]);
                params.put(AppVar.FUNC_KEGIATAN, args[3]);
                params.put(AppVar.FUNC_DANA, args[4]);
                params.put(AppVar.FUNC_ID, args[5]);
                params.put(AppVar.FUNC_ID_USER, args[6]);
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
                    if (success.equals("1")) {
                        getData();
                        txtBulan.setSelection(0);
                        txtDana.setText("");
                        txtJudul.setText("");
                        Toast.makeText(DataActivity.this, "Berhasil diSimpan.", Toast.LENGTH_SHORT).show();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(DataActivity.this, "Data tidak berhasil diUpdate", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }

            }
            progressDialog.dismiss();
        }
    }

    public void getData() {
        new DataItem().execute(
                AppVar.PREF_NAME,
                AppVar.KEY_ALL_DATA,
                level
        );
    }

    private class DataItem extends AsyncTask<String, String, JSONObject> {
        JSONParser jsonParser = new JSONParser();
        private final String LOGIN_URL = AppVar.URL_SERVER;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            try {

                HashMap<String, String> params = new HashMap<>();
                params.put(AppVar.KEY_API, args[0]);
                params.put(AppVar.KEY_FUNCTION, args[1]);
                params.put(AppVar.FUNC_ID, args[2]);
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

        @Override
        protected void onPostExecute(JSONObject json) {
            if (json != null) {
                try {
                    ID.clear();
                    KEGIATAN.clear();
                    DANA.clear();
                    BULAN.clear();
                    JSONArray jsonArray = json.getJSONArray("hasil");
                    if (jsonArray.length() == 0) {
                        Toast.makeText(DataActivity.this,"Hasil tidak ditemukan",Toast.LENGTH_LONG).show();
                        listData.setAdapter(null);
                    } else {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject isiArray = jsonArray.getJSONObject(i);
                            String id = isiArray.getString("var_id");
                            String kegiatan = isiArray.getString("var_kegiatan");
                            String bulan = isiArray.getString("var_bulan");
                            String dana = isiArray.getString("var_dana");

                            ID.add(id);
                            KEGIATAN.add(kegiatan);
                            DANA.add(dana);
                            BULAN.add(bulan);
                        }
                        getAllData();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(DataActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void getAllData() {
        adapter = new DataAdapter(this, ID,KEGIATAN,BULAN,DANA);
        listData.setAdapter(adapter);
    }

    public void Update(String Kegiatan,String bulan, String dana,String id) {
        progressDialog = ProgressDialog.show(DataActivity.this, "Please wait.",
                "Sedang mengirim data..!", true);
        new CekAsyncUpdate().execute(
                AppVar.PREF_NAME,
                AppVar.KEY_UBAH,
                Kegiatan,
                bulan,
                dana,
                id
        );
    }

    class CekAsyncUpdate extends AsyncTask<String, String, JSONObject> {
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
                params.put(AppVar.FUNC_KEGIATAN, args[2]);
                params.put(AppVar.FUNC_BULAN, args[3]);
                params.put(AppVar.FUNC_DANA, args[4]);
                params.put(AppVar.FUNC_ID, args[5]);
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
                    if (success.equals("1")) {
                        getData();
                        Toast.makeText(DataActivity.this, "Berhasil diUpdate.", Toast.LENGTH_SHORT).show();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(DataActivity.this, "Data tidak berhasil diUpdate", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(DataActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                }

            }
            progressDialog.dismiss();
        }
    }
}
