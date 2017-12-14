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
import android.widget.RelativeLayout;

import net.netne.afahzis.appdishub.Server.AppVar;

public class SubAngkutanActivity extends AppCompatActivity implements View.OnClickListener{

    Toolbar toolbar;
    LinearLayout ll_retribusi,ll_oplet,ll_taxi,ll_bus,ll_logout;
    RelativeLayout ll_grafik2,ll_grafik1,ll_grafik3,ll_grafik4;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_angkutan);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        ll_retribusi = (LinearLayout)findViewById(R.id.ll_retribusi);
        ll_oplet = (LinearLayout)findViewById(R.id.ll_oplet);
        ll_taxi= (LinearLayout)findViewById(R.id.ll_taxi);
        ll_bus = (LinearLayout)findViewById(R.id.ll_bus);
        ll_logout = (LinearLayout)findViewById(R.id.ll_logout);
        ll_grafik1 = (RelativeLayout)findViewById(R.id.ll_grafik1);
        ll_grafik2 = (RelativeLayout)findViewById(R.id.ll_grafik2);
        ll_grafik3 = (RelativeLayout)findViewById(R.id.ll_grafik3);
        ll_grafik4 = (RelativeLayout)findViewById(R.id.ll_grafik4);
        sharedpreferences = getSharedPreferences(AppVar.PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ll_logout.setOnClickListener(this);
        ll_bus.setOnClickListener(this);
        ll_taxi.setOnClickListener(this);
        ll_oplet.setOnClickListener(this);
        ll_retribusi.setOnClickListener(this);
        ll_grafik1.setOnClickListener(this);
        ll_grafik2.setOnClickListener(this);
        ll_grafik3.setOnClickListener(this);
        ll_grafik4.setOnClickListener(this);
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
    public void onClick(View view){
        Intent i = new Intent(getApplicationContext(), SubAngkutanActivity.class);
        switch (view.getId()){
            case R.id.ll_retribusi :
                i = new Intent(getApplicationContext(), DataActivity.class);
                i.putExtra("level","12");
                break;
            case R.id.ll_oplet :
                i = new Intent(getApplicationContext(), DataActivity.class);
                i.putExtra("level","13");
                break;
            case R.id.ll_taxi :
                i = new Intent(getApplicationContext(), DataActivity.class);
                i.putExtra("level","14");
                break;
            case  R.id.ll_bus :
                i = new Intent(getApplicationContext(), DataActivity.class);
                i.putExtra("level","15");
                break;
            case R.id.ll_grafik1 :
                i = new Intent(getApplicationContext(), GraphActivity.class);
                i.putExtra("level","12");
                break;
            case R.id.ll_grafik2 :
                i = new Intent(getApplicationContext(), GraphActivity.class);
                i.putExtra("level","13");
                break;
            case R.id.ll_grafik3 :
                i = new Intent(getApplicationContext(), GraphActivity.class);
                i.putExtra("level","14");
                break;
            case  R.id.ll_grafik4 :
                i = new Intent(getApplicationContext(), GraphActivity.class);
                i.putExtra("level","15");
                break;
            case R.id.ll_logout:
                editor.remove(AppVar.SET_LOGIN);
                editor.remove(AppVar.USER_ID);
                editor.remove(AppVar.USER_LEVEL);
                editor.commit();
                i = new Intent(getApplicationContext(),MainActivity.class);
                finish();
                break;
        }startActivity(i);
    }
}
