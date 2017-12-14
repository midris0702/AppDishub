package net.netne.afahzis.appdishub;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.netne.afahzis.appdishub.Adapter.DataAdapter;
import net.netne.afahzis.appdishub.Adapter.LaporanAdapter;
import net.netne.afahzis.appdishub.Server.AppVar;
import net.netne.afahzis.appdishub.Server.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LaporanActivity extends AppCompatActivity {

    Toolbar toolbar;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    private ProgressDialog progressDialog;
    String level;
    LaporanAdapter adapter;

    List<String> ID = new ArrayList<String>();
    List<String> KEGIATAN = new ArrayList<String>();
    List<String> BULAN = new ArrayList<String>();
    List<String> FISIK = new ArrayList<String>();
    List<String> KEUANGAN = new ArrayList<String>();

    ListView laporan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        laporan = (ListView)findViewById(R.id.listLaporan);

        level = getIntent().getExtras().getString("level");
        sharedpreferences = getSharedPreferences(AppVar.PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);
        getData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_settings :
                editor.remove(AppVar.SET_LOGIN);
                editor.remove(AppVar.USER_ID);
                editor.remove(AppVar.USER_LEVEL);
                editor.commit();
                finish();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void getData() {
        new DataItem().execute(
                AppVar.PREF_NAME,
                AppVar.KEY_LAPORAN,
                sharedpreferences.getString(AppVar.USER_LEVEL,null)
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
                params.put(AppVar.FUNC_LEVEL, args[2]);
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
                    JSONArray jsonArray = json.getJSONArray("hasil");
                    if (jsonArray.length() == 0) {
                        Toast.makeText(LaporanActivity.this,"Hasil tidak ditemukan",Toast.LENGTH_LONG).show();
                    } else {
                        ID.clear();
                        KEGIATAN.clear();
                        BULAN.clear();
                        FISIK.clear();
                        KEUANGAN.clear();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject isiArray = jsonArray.getJSONObject(i);
                            String Id = isiArray.getString("var_id");
                            String Kegiatan = isiArray.getString("var_kegiatan");
                            String Bulan = isiArray.getString("var_bulan");
                            String Fisik = isiArray.getString("var_fisik");
                            String Keuanga = isiArray.getString("var_keuangan");

                            ID.add(Id);
                            KEGIATAN.add(Kegiatan);
                            BULAN.add(Bulan);
                            FISIK.add(Fisik);
                            KEUANGAN.add(Keuanga);
                        }
                        getAllData();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(LaporanActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void getAllData() {
        laporan.setAdapter(null);
        adapter = new LaporanAdapter(this, ID,KEGIATAN,BULAN,FISIK,KEUANGAN);
        laporan.setAdapter(adapter);
    }

    public void Update(String bulan, String fisik, String kegiatan,String Id, String keuangan) {
        progressDialog = ProgressDialog.show(LaporanActivity.this, "Please wait.",
                "Sedang mengirim data..!", true);
        new CekAsyncUpdate().execute(
                AppVar.PREF_NAME,
                AppVar.KEY_UP_LAPOR,
                bulan,
                fisik,
                kegiatan,
                keuangan,
                sharedpreferences.getString(AppVar.USER_ID,null),
                Id
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
                params.put(AppVar.FUNC_BULAN, args[2]);
                params.put(AppVar.FUNC_FISIK, args[3]);
                params.put(AppVar.FUNC_KEGIATAN, args[4]);
                params.put(AppVar.FUNC_KEUANGAN, args[5]);
                params.put(AppVar.FUNC_ID_USER, args[6]);
                params.put(AppVar.FUNC_ID, args[7]);
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
                        Toast.makeText(LaporanActivity.this, "Berhasil diUpdate.", Toast.LENGTH_SHORT).show();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(LaporanActivity.this, "Data tidak berhasil diUpdate", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(LaporanActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                }

            }
            progressDialog.dismiss();
        }
    }


}
