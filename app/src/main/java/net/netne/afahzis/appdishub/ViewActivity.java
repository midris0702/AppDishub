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
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import net.netne.afahzis.appdishub.Adapter.Data2Adapter;
import net.netne.afahzis.appdishub.Adapter.DataAdapter;
import net.netne.afahzis.appdishub.InProfile.GambaranActivity;
import net.netne.afahzis.appdishub.Server.AppVar;
import net.netne.afahzis.appdishub.Server.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewActivity extends AppCompatActivity {

    Toolbar toolbar;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    private ProgressDialog progressDialog;
    ListView listData;

    List<String> ID = new ArrayList<String>();
    List<String> KEGIATAN = new ArrayList<String>();
    List<String> BULAN = new ArrayList<String>();
    List<String> DANA = new ArrayList<String>();
    Data2Adapter adapter;
    int first=0;

    public String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        listData = (ListView)findViewById(R.id.listData);
        sharedpreferences = getSharedPreferences(AppVar.PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
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
                AppVar.KEY_ALL_DATA1,
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
                        Toast.makeText(ViewActivity.this,"Hasil tidak ditemukan",Toast.LENGTH_LONG).show();
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
                    Toast.makeText(ViewActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void getAllData() {
        adapter = new Data2Adapter(this, ID,KEGIATAN,BULAN,DANA);
        if(first==0){
            listData.setAdapter(adapter);
            first=1;
        }else{
            adapter.notifyDataSetChanged();
        }
    }
}
