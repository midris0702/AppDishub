package net.netne.afahzis.appdishub;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import net.netne.afahzis.appdishub.Fragment.AngkutanFragment;
import net.netne.afahzis.appdishub.Fragment.BermotorFragment;
import net.netne.afahzis.appdishub.Fragment.DasarHukumFragment;
import net.netne.afahzis.appdishub.Fragment.HomeFragment;
import net.netne.afahzis.appdishub.Fragment.LalulintasFragment;
import net.netne.afahzis.appdishub.Fragment.ParkirFragment;
import net.netne.afahzis.appdishub.Fragment.PengelolaFragment;
import net.netne.afahzis.appdishub.Fragment.PlabuhanFragment;
import net.netne.afahzis.appdishub.Fragment.ProfileFragment;
import net.netne.afahzis.appdishub.Fragment.SaranFragment;
import net.netne.afahzis.appdishub.Fragment.SecretaryFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FrameLayout mFragment;
    Fragment frg = null;
    FragmentTransaction transaction = null;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragment = (FrameLayout) findViewById(R.id.frameMain);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        openFragment(new HomeFragment());
        navigationView.getMenu().getItem(0).setChecked(true);
        getSupportActionBar().setTitle("Home");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            if (navigationView.getMenu().getItem(0).isChecked() == false) {
                openFragment(new HomeFragment());
                getSupportActionBar().setTitle("Home");
                navigationView.getMenu().getItem(0).setChecked(true);
            }else{
                AlertDialog.Builder notif = new AlertDialog.Builder(MainActivity.this);
                notif.setTitle("Keluar Aplikasi");
                notif.setMessage("Apakah Anda ingin menutup Aplikasi ini?");
                notif.setPositiveButton("Keluar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                notif.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                notif.create();
                notif.show();
            }
        }else{
            if (navigationView.getMenu().getItem(0).isChecked() == false) {
                openFragment(new HomeFragment());
                getSupportActionBar().setTitle("Home");
                navigationView.getMenu().getItem(0).setChecked(true);
            }else{
                AlertDialog.Builder notif = new AlertDialog.Builder(MainActivity.this);
                notif.setTitle("Keluar Aplikasi");
                notif.setMessage("Apakah Anda ingin menutup Aplikasi ini?");
                notif.setPositiveButton("Keluar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                notif.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                notif.create();
                notif.show();
            }
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            if (navigationView.getMenu().getItem(0).isChecked() == false) {
                openFragment(new HomeFragment());
                getSupportActionBar().setTitle(item.getTitle().toString());
            }
        } else if (id == R.id.nav_profile) {
            if (navigationView.getMenu().getItem(1).isChecked() == false) {
                openFragment(new ProfileFragment());
                getSupportActionBar().setTitle(item.getTitle().toString());
            }
        } else if (id == R.id.nav_angkutan) {
            if (navigationView.getMenu().getItem(2).isChecked() == false) {
                openFragment(new AngkutanFragment());
                getSupportActionBar().setTitle(item.getTitle().toString());
            }
        } else if (id == R.id.nav_lalulintas) {
            if (navigationView.getMenu().getItem(3).isChecked() == false) {
                openFragment(new LalulintasFragment());
                getSupportActionBar().setTitle(item.getTitle().toString());
            }
        } else if (id == R.id.nav_saran) {
            if (navigationView.getMenu().getItem(4).isChecked() == false) {
                openFragment(new SaranFragment());
                getSupportActionBar().setTitle(item.getTitle().toString());
            }
        } else if (id == R.id.nav_pengujian) {
            if (navigationView.getMenu().getItem(5).isChecked() == false) {
                openFragment(new BermotorFragment());
                getSupportActionBar().setTitle(item.getTitle().toString());
            }
        } else if (id == R.id.nav_parkir) {
            if (navigationView.getMenu().getItem(6).isChecked() == false) {
                openFragment(new ParkirFragment());
                getSupportActionBar().setTitle(item.getTitle().toString());
            }
        } else if (id == R.id.nav_pelabuhan) {
            if (navigationView.getMenu().getItem(7).isChecked() == false) {
                openFragment(new PlabuhanFragment());
                getSupportActionBar().setTitle(item.getTitle().toString());
            }

        } else if (id == R.id.nav_pengelola) {
            if (navigationView.getMenu().getItem(1).isChecked() == false) {
                openFragment(new PengelolaFragment());
                getSupportActionBar().setTitle(item.getTitle().toString());
            }
        } else if (id == R.id.nav_secretary) {
            if (navigationView.getMenu().getItem(2).isChecked() == false) {
                openFragment(new SecretaryFragment());
                getSupportActionBar().setTitle(item.getTitle().toString());
            }
        } else if (id == R.id.nav_hukum) {
            if (navigationView.getMenu().getItem(1).isChecked() == false) {
                openFragment(new DasarHukumFragment());
                getSupportActionBar().setTitle(item.getTitle().toString());
            }
        } else if (id == R.id.nav_pad) {
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            i.putExtra("level", "8");
            startActivity(i);
        } else if (id == R.id.nav_laporan) {
            Intent i = new Intent(MainActivity.this, DashboardActivity.class);
            i.putExtra("level", "9");
            startActivity(i);
        } else if (id == R.id.nav_info) {
            Intent i = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openFragment(Fragment frag) {
        frg = frag;
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in_right, R.anim.falde_out_left, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.replace(R.id.frameMain, frg);
        transaction.commit();
    }
}
