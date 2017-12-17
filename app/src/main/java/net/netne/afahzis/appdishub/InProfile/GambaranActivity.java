package net.netne.afahzis.appdishub.InProfile;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.netne.afahzis.appdishub.R;

import java.lang.reflect.Field;

public class GambaranActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView txtDeskripsi;
    LinearLayout layout_Citra;
    RelativeLayout gambar;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gambaran);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        txtDeskripsi = (TextView)findViewById(R.id.txtDeskripsi);
        layout_Citra = (LinearLayout)findViewById(R.id.layout_Citra);
        gambar = (RelativeLayout)findViewById(R.id.gambar);
        img = (ImageView)findViewById(R.id.imgWisata);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        String menu = getIntent().getExtras().getString("menu");
        String page = getIntent().getExtras().getString("page");
        if(((menu.equals("7")||menu.equals("9"))&& page.equals("1"))||(page.equals("7")&&menu.equals("6"))||(page.equals("4")&&menu.equals("6"))){
            txtDeskripsi.setVisibility(View.GONE);
            gambar.setVisibility(View.VISIBLE);
        }else{
            txtDeskripsi.setVisibility(View.VISIBLE);
            gambar.setVisibility(View.GONE);
        }
        switch (page){
            case "1":
                switch (menu){
                    case "1" :
                        getSupportActionBar().setTitle("Gambaran Umum");
                        txtDeskripsi.setText(R.string.profile_1);
                        break;
                    case "2" :
                        getSupportActionBar().setTitle("TUPOSI INSTANSI");
                        txtDeskripsi.setText(R.string.profile_2);
                        break;
                    case "3" :
                        getSupportActionBar().setTitle("STRATEGI KEBIJAKAN");
                        txtDeskripsi.setText(R.string.profile_3);
                        break;
                    case "4" :
                        getSupportActionBar().setTitle("5 Citra Manusia");
                        txtDeskripsi.setText(R.string.profile_4);
                        break;
                    case "5" :
                        getSupportActionBar().setTitle("VISI DAN MISI");
                        txtDeskripsi.setText(R.string.profile_5);
                        break;
                    case "6" :
                        getSupportActionBar().setTitle("FUNGSI UPTD");
                        txtDeskripsi.setText(R.string.profile_6);
                        break;
                    case "7" :
                        getSupportActionBar().setTitle("DASAR HUKUM");
                        img.setImageResource(R.drawable.dasarhukum);
                        break;
                    case "8" :
                        getSupportActionBar().setTitle("TUJUAN");
                        txtDeskripsi.setText(R.string.profile_8);
                        break;
                    case "9" :
                        getSupportActionBar().setTitle("STRUKTUR");
                        img.setImageResource(R.drawable.struktur);
                        break;
                }
                break;
            case "2" :
                switch (menu){
                    case "1" :
                        getSupportActionBar().setTitle("SEKSI ANGKUTAN ORANG");
                        txtDeskripsi.setText(R.string.angkutan_1);
                        break;
                    case "2" :
                        getSupportActionBar().setTitle("SEKSI ANGKUTAN BARANG, TERMINAL DAN PERAIRAN");
                        txtDeskripsi.setText(R.string.angkutan_2);
                        break;
                    case "3" :
                        getSupportActionBar().setTitle("SEKSI BIMBINGAN DAN PENYULUHAN ANGKUTAN");
                        txtDeskripsi.setText(R.string.angkutan_3);
                        break;
                }
                break;
            case "3" :
                switch (menu){
                    case "1" :
                        getSupportActionBar().setTitle("SEKSI MANAJEMEN DAN KJLL");
                        txtDeskripsi.setText(R.string.lalu_1);
                        break;
                    case "2" :
                        getSupportActionBar().setTitle("SEKSI PEK. DAN FAS. LLJ DAN PERAIRAN");
                        txtDeskripsi.setText(R.string.lalu_2);
                        break;
                    case "3" :
                        getSupportActionBar().setTitle("SEKSI PENGAWASAN LLJ");
                        txtDeskripsi.setText(R.string.lalu_3);
                        break;
                }
                break;
            case "4" :
                switch (menu){
                    case "1" :
                        getSupportActionBar().setTitle("SEKSI KES. SARPRAS JALAN");
                        txtDeskripsi.setText(R.string.sarpras_1);
                        break;
                    case "2" :
                        getSupportActionBar().setTitle("SEKSI PENERANGAN JALAN");
                        txtDeskripsi.setText(R.string.sarpras_2);
                        break;
                    case "3" :
                        getSupportActionBar().setTitle("SEKSI TEKNIS JALAN DAN PERAIRAN");
                        txtDeskripsi.setText(R.string.sarpras_3);
                        break;
                }
                break;
            case "5" :
                switch (menu){
                    case "1" :
                        getSupportActionBar().setTitle("INFORMASI UPTD PKB PEKANBARU");
                        txtDeskripsi.setText(R.string.PKB_1);
                        break;
                    case "2" :
                        getSupportActionBar().setTitle("TUPOSI");
                        txtDeskripsi.setText(R.string.PKB_2);
                        break;
                    case "3" :
                        getSupportActionBar().setTitle("PROSEDUR PENGURUSAN UJI BERKALA");
                        //txtDeskripsi.setText(R.string.sarpras_3);
                        break;
                    case "4" :
                        getSupportActionBar().setTitle("KOMPONEN UJI BERKALA");
                        //txtDeskripsi.setText(R.string.sarpras_3);
                        break;
                    case "5" :
                        getSupportActionBar().setTitle("JLN. KENDARAAN WAJIB UJI DAN PELASI");
                        //txtDeskripsi.setText(R.string.sarpras_3);
                        break;
                    case "6" :
                        getSupportActionBar().setTitle("GALERI");
                        //txtDeskripsi.setText(R.string.sarpras_3);
                        break;
                }
                break;
            case "6" :
                switch (menu){
                    case "1" :
                        getSupportActionBar().setTitle("GAMBARAN UMUM UPTD PARKIR");
                        txtDeskripsi.setText(R.string.parkir_1);
                        break;
                    case "2" :
                        getSupportActionBar().setTitle("TUPOSI");
                        //txtDeskripsi.setText(R.string.sarpras_2);
                        break;
                    case "3" :
                        getSupportActionBar().setTitle("PERHITUNGAN PARKIR");
                        //txtDeskripsi.setText(R.string.sarpras_3);
                        break;
                    case "4" :
                        getSupportActionBar().setTitle("GALERI");
                        //txtDeskripsi.setText(R.string.sarpras_3);
                        break;
                }
                break;
            case "7" :
                switch (menu){
                    case "1" :
                        getSupportActionBar().setTitle("GAMBARAN UMUM UPTD PSD");
                        txtDeskripsi.setText(R.string.sungai_1);
                        break;
                    case "2" :
                        getSupportActionBar().setTitle("TUPOSI");
                        txtDeskripsi.setText(R.string.sungai_2);
                        break;
                    case "3" :
                        getSupportActionBar().setTitle("FASILITAS");
                        txtDeskripsi.setText(R.string.sungai_3);
                        break;
                    case "4" :
                        getSupportActionBar().setTitle("KRITERIA RETRIBUSI");
                        txtDeskripsi.setText(R.string.sungai_4);
                        break;
                    case "5" :
                        getSupportActionBar().setTitle("DATA ANGKATAN PELABUHAN");
                        //txtDeskripsi.setText(R.string.sungai_4);
                        break;
                    case "6" :
                        getSupportActionBar().setTitle("REKAP KEPADATAN PELABUHAN");
                        img.setImageResource(R.drawable.rekap_pelabuhan);
                        break;
                    case "7" :
                        getSupportActionBar().setTitle("GALERI");
                        //img.setImageResource(R.drawable.rekap_pelabuhan);
                        break;
                }
                break;
            case "8" :
                switch (menu){
                    case "1" :
                        getSupportActionBar().setTitle("TUPOSI");
                        txtDeskripsi.setText(R.string.uptd_angkut_1);
                        break;
                    case "2" :
                        getSupportActionBar().setTitle("TRANS METRO PEKANBARU");
                        txtDeskripsi.setText(R.string.uptd_angkut_2);
                        break;
                    case "3" :
                        getSupportActionBar().setTitle("BUS AIR SENAPELAN");
                        txtDeskripsi.setText(R.string.uptd_angkut_3);
                        break;
                }
                break;
            case "9" :
                switch (menu){
                    case "1" :
                        getSupportActionBar().setTitle("URAIAN TUGAS SEKRETARIAT");
                        txtDeskripsi.setText(R.string.secretary_1);
                        break;
                    case "2" :
                        getSupportActionBar().setTitle("URAIAN TUGAS SUBBAG UMUM");
                        txtDeskripsi.setText(R.string.secretary_2);
                        break;
                    case "3" :
                        getSupportActionBar().setTitle("URAIAN TUGAS SUBBAG KEUANGAN");
                        txtDeskripsi.setText(R.string.secretary_3);
                        break;
                    case "4" :
                        getSupportActionBar().setTitle("URAIAN TUGAS SUBBAG PENYUSUNAN PROGRAM");
                        txtDeskripsi.setText(R.string.secretary_4);
                        break;
                }
                break;

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
