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

import net.netne.afahzis.appdishub.Server.AppVar;

public class MenuSUbActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout ll_d1, ll_d2, ll_d3, ll_d4, ll_d5, ll_d6, ll_d7,ll_d8;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_sub);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        sharedpreferences = getSharedPreferences(AppVar.PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ll_d1 = findViewById(R.id.ll_pad1);
        ll_d2 = findViewById(R.id.ll_pad2);
        ll_d3 = findViewById(R.id.ll_pad3);
        ll_d4 = findViewById(R.id.ll_pad4);
        ll_d5 = findViewById(R.id.ll_pad5);
        ll_d6 = findViewById(R.id.ll_pad6);
        ll_d7 = findViewById(R.id.ll_pad7);
        ll_d8 = findViewById(R.id.ll_pad8);

        ll_d1.setOnClickListener(this);
        ll_d2.setOnClickListener(this);
        ll_d3.setOnClickListener(this);
        ll_d4.setOnClickListener(this);
        ll_d5.setOnClickListener(this);
        ll_d6.setOnClickListener(this);
        ll_d7.setOnClickListener(this);
        ll_d8.setOnClickListener(this);
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
        String id = "";
        switch (v.getId()) {
            case R.id.ll_pad1:
                Intent i = new Intent(this, AngkutanPADActivity.class);
                startActivity(i);
                break;
            case R.id.ll_pad2:
                i = new Intent(this, GraphActivity.class);
                i.putExtra("level", "2");
                startActivity(i);
                break;
            case R.id.ll_pad3:
                i = new Intent(this, GraphActivity.class);
                i.putExtra("level", "3");
                startActivity(i);
                break;
            case R.id.ll_pad4:
                i = new Intent(this, GraphActivity.class);
                i.putExtra("level", "4");
                startActivity(i);
                break;
            case R.id.ll_pad5:
                i = new Intent(this, GraphActivity.class);
                i.putExtra("level", "5");
                startActivity(i);
                break;
            case R.id.ll_pad6:
                i = new Intent(this, GraphActivity.class);
                i.putExtra("level", "6");
                startActivity(i);
                break;
            case R.id.ll_pad7:
                i = new Intent(this, GraphActivity.class);
                i.putExtra("level", "7");
                startActivity(i);
                break;
            case R.id.ll_pad8:
                editor.remove(AppVar.SET_LOGIN);
                editor.remove(AppVar.USER_ID);
                editor.remove(AppVar.USER_LEVEL);
                editor.commit();
                i = new Intent(getApplicationContext(),MainActivity.class);
                finish();
                break;


        }

    }
}
