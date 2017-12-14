package net.netne.afahzis.appdishub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import net.netne.afahzis.appdishub.Server.AppVar;

public class AngkutanPADActivity extends AppCompatActivity implements View.OnClickListener{

    Toolbar toolbar;
    LinearLayout ll_grafik2,ll_grafik1,ll_grafik3,ll_grafik4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_angkutan_pad);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ll_grafik1 = (LinearLayout) findViewById(R.id.ll_grafik1);
        ll_grafik2 = (LinearLayout)findViewById(R.id.ll_grafik2);
        ll_grafik3 = (LinearLayout)findViewById(R.id.ll_grafik3);
        ll_grafik4 = (LinearLayout)findViewById(R.id.ll_grafik4);

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
        }startActivity(i);
    }
}
