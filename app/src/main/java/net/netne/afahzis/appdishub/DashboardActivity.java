package net.netne.afahzis.appdishub;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.netne.afahzis.appdishub.Server.AppVar;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout ll_lihat_data, ll_lihat_grafik, ll_ubah_pass, ll_logout,ll_input_data;
    Toolbar toolbar;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    String level,inputLabel;
    TextView txtInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        ll_lihat_data = (LinearLayout)findViewById(R.id.ll_lihat_data);
        ll_input_data = (LinearLayout)findViewById(R.id.ll_input_data);
        ll_lihat_grafik = (LinearLayout)findViewById(R.id.ll_lihat_grafik);
        ll_ubah_pass =(LinearLayout)findViewById(R.id.ll_ubah_password);
        ll_logout = (LinearLayout)findViewById(R.id.ll_logout);
        txtInput = (TextView)findViewById(R.id.txtinput);

        level=getIntent().getExtras().getString("level");

        switch (level){
            case "1" :
                inputLabel="";
                break;
            case "2" :
                inputLabel=" Pemakaian Mobil Derek";
                break;
            case "4" :
                inputLabel=" Retribusi Peng.Ken.Bermotor";
                break;
            case "5" :
                inputLabel=" Retribusi Pelayanan Parkir diTepi Umum";
                break;
            case "6" :
                inputLabel=" Retribusi Pelayanan Kepelabuhan";
                break;
            case "7" :
                inputLabel=" Penjualan Tiket Bus TMP (PAP)";
                break;
            default:
                inputLabel="";
                break;
        }
        txtInput.setText(txtInput.getText().toString() + inputLabel);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        sharedpreferences = getSharedPreferences(AppVar.PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        ll_logout.setOnClickListener(this);
        ll_lihat_data.setOnClickListener(this);
        ll_lihat_grafik.setOnClickListener(this);
        ll_ubah_pass.setOnClickListener(this);
        ll_input_data.setOnClickListener(this);

        if(level.equals("9")){
            ll_lihat_data.setVisibility(View.GONE);
            ll_lihat_grafik.setVisibility(View.GONE);
            ll_input_data.setVisibility(View.VISIBLE);
            ll_logout.setVisibility(View.GONE);
        }else {
            ll_lihat_data.setVisibility(View.VISIBLE);
            ll_lihat_grafik.setVisibility(View.VISIBLE);
            ll_input_data.setVisibility(View.GONE);
            ll_logout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return (super.onOptionsItemSelected(menuItem));
    }


    @Override
    public void onClick(View v) {
        Intent i = new Intent(getApplicationContext(), DashboardActivity.class);
        switch (v.getId()){
            case R.id.ll_logout :
                editor.remove(AppVar.SET_LOGIN);
                editor.remove(AppVar.USER_ID);
                editor.remove(AppVar.USER_LEVEL);
                editor.commit();
                i = new Intent(getApplicationContext(),MainActivity.class);
                finish();
                break;
            case R.id.ll_lihat_data :
                if(level.equals("5")){
                    i = new Intent(getApplicationContext(), ParkirActivity.class);
                }else{
                    i = new Intent(getApplicationContext(), DataActivity.class);
                }
                i.putExtra("level",level);
                break;
            case R.id.ll_lihat_grafik :
                i = new Intent(getApplicationContext(), GraphActivity.class);
                i.putExtra("level",level);
                break;
            case R.id.ll_ubah_password :
                i = new Intent(getApplicationContext(), RePassActivity.class);
                break;
            case R.id.ll_input_data :
                i= new Intent(getApplicationContext(),LaporanLoginActivity.class);
                i.putExtra("level",level);
                break;
        }startActivity(i);
    }
}

