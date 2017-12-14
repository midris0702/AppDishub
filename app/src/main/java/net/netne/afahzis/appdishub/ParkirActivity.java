package net.netne.afahzis.appdishub;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import net.netne.afahzis.appdishub.Adapter.LaporanAdapter;
import net.netne.afahzis.appdishub.Adapter.ParkirAdapter;
import net.netne.afahzis.appdishub.Server.AppVar;
import net.netne.afahzis.appdishub.Server.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParkirActivity extends AppCompatActivity {

    Toolbar toolbar;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    private ProgressDialog progressDialog;
    String level;
    ParkirAdapter adapter;

    List<String> ID = new ArrayList<String>();
    List<String> KEGIATAN = new ArrayList<String>();
    List<String> BULAN = new ArrayList<String>();
    List<String> FISIK = new ArrayList<String>();

    ListView listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parkir);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        listData = (ListView)findViewById(R.id.listData);

        sharedpreferences = getSharedPreferences(AppVar.PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        level = getIntent().getExtras().getString("level");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getData();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    public void getData() {
        new DataItem().execute(
                AppVar.PREF_NAME,
                AppVar.KEY_PARKIR,
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
                        Toast.makeText(ParkirActivity.this,"Hasil tidak ditemukan",Toast.LENGTH_LONG).show();
                    } else {
                        ID.clear();
                        KEGIATAN.clear();
                        BULAN.clear();
                        FISIK.clear();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject isiArray = jsonArray.getJSONObject(i);
                            String Id = isiArray.getString("var_id");
                            String Kegiatan = isiArray.getString("var_kegiatan");
                            String Bulan = isiArray.getString("var_bulan");
                            String Fisik = isiArray.getString("var_fisik");

                            ID.add(Id);
                            KEGIATAN.add(Kegiatan);
                            BULAN.add(Bulan);
                            FISIK.add(Fisik);
                        }
                        getAllData();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ParkirActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void getAllData() {
        listData.setAdapter(null);
        adapter = new ParkirAdapter(this, ID,BULAN,KEGIATAN,FISIK);
        listData.setAdapter(adapter);
    }

    public void Update(String fisik, String kegiatan,String Id) {
        progressDialog = ProgressDialog.show(ParkirActivity.this, "Please wait.",
                "Sedang mengirim data..!", true);
        new CekAsyncUpdate().execute(
                AppVar.PREF_NAME,
                AppVar.KEY_UP_PARKIR,
                fisik,
                kegiatan,
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
                params.put(AppVar.FUNC_TARGET, args[2]);
                params.put(AppVar.FUNC_TERCAPAI, args[3]);
                params.put(AppVar.FUNC_ID, args[4]);
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
                        Toast.makeText(ParkirActivity.this, "Berhasil diUpdate.", Toast.LENGTH_SHORT).show();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(ParkirActivity.this, "Data tidak berhasil diUpdate", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ParkirActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                }

            }
            progressDialog.dismiss();
        }
    }
}
