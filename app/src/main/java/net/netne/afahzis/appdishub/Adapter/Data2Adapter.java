package net.netne.afahzis.appdishub.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.netne.afahzis.appdishub.DataActivity;
import net.netne.afahzis.appdishub.GraphActivity;
import net.netne.afahzis.appdishub.R;
import net.netne.afahzis.appdishub.ViewActivity;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

/**
 * reshape by iwan on 14/12/17.
 */

public class Data2Adapter extends ArrayAdapter{
    private Context c;

    List<String> ID = new ArrayList<String>();
    List<String> KEGIATAN = new ArrayList<String>();
    List<String> BULAN = new ArrayList<String>();
    List<String> DANA = new ArrayList<String>();

    TextView txtDana,txtJudul,txtBulan;
    LinearLayout  txtStatistik;

    DecimalFormat mataUangIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
    DecimalFormatSymbols formatRupiah = new DecimalFormatSymbols();
    String bulan[] = {"Januari","Februari", "Maret","April","Mei","Juni","Juli","Agustus","September","Oktober","November","Desember"};

    public Data2Adapter(Context c, List<String> ID, List<String> KEGIATAN, List<String> BULAN, List<String> DANA) {
        super(c, R.layout.row_pad_item, ID);
        this.c = c;
        this.ID = ID;
        this.KEGIATAN = KEGIATAN;
        this.BULAN = BULAN;
        this.DANA = DANA;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(c);
        View rowView;
        rowView = inflater.inflate(R.layout.row_pad_item, null, true);
        txtJudul = (TextView) rowView.findViewById(R.id.etKegiatan);
        txtBulan = (TextView) rowView.findViewById(R.id.spBulan);
        txtDana = (TextView) rowView.findViewById(R.id.etDana);
        txtStatistik = (LinearLayout) rowView.findViewById(R.id.etStatistik);
        final TextView txtId= (TextView)rowView.findViewById(R.id.txtId);

        formatRupiah.setCurrencySymbol("Rp. ");
        formatRupiah.setMonetaryDecimalSeparator(',');
        formatRupiah.setGroupingSeparator('.');
        mataUangIndonesia.setDecimalFormatSymbols(formatRupiah);

        txtId.setText(ID.get(position));

        txtJudul.setText(KEGIATAN.get(position));
        txtDana.setText(mataUangIndonesia.format(Double.valueOf(DANA.get(position))));
        txtBulan.setText(bulan[Integer.valueOf(BULAN.get(position))].toString());

        txtStatistik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(c, GraphActivity.class);
                i.putExtra("level",((ViewActivity)getContext()).level);
                i.putExtra("id",ID.get(position));
                c.startActivity(i);
            }
        });



        return rowView;
    }
}
