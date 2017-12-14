package net.netne.afahzis.appdishub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import net.netne.afahzis.appdishub.Server.AppVar;

public class LaporanLoginActivity extends AppCompatActivity implements View.OnClickListener{

    String level;
    LinearLayout ll_login1,ll_login2,ll_login3;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_login);
        ll_login1 = (LinearLayout)findViewById(R.id.ll_login1);
        ll_login2 = (LinearLayout)findViewById(R.id.ll_login2);
        ll_login3 = (LinearLayout)findViewById(R.id.ll_login3);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        level=getIntent().getExtras().getString("level");

        ll_login1.setOnClickListener(this);
        ll_login2.setOnClickListener(this);
        ll_login3.setOnClickListener(this);
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
    public void onClick(View v){
        Intent i = new Intent(getApplicationContext(), LaporanLoginActivity.class);
        switch (v.getId()) {
            case R.id.ll_login1:
                i = new Intent(getApplicationContext(), RePassActivity.class);
                i.putExtra("user", "admin9");
                i.putExtra("level", level);
                break;
            case R.id.ll_login2:
                i = new Intent(getApplicationContext(), RePassActivity.class);
                i.putExtra("user", "admin10");
                i.putExtra("level", level);
                break;
            case R.id.ll_login3:
                i = new Intent(getApplicationContext(), RePassActivity.class);
                i.putExtra("user", "admin11");
                i.putExtra("level", level);
                break;
        }startActivity(i);
    }
}
