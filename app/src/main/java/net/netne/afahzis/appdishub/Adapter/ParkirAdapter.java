package net.netne.afahzis.appdishub.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import net.netne.afahzis.appdishub.DataActivity;
import net.netne.afahzis.appdishub.ParkirActivity;
import net.netne.afahzis.appdishub.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

/**
 * Reshape by Iwan on 14/12/17.
 */

public class ParkirAdapter extends ArrayAdapter {
    private Context c;

    List<String> ID = new ArrayList<String>();
    List<String> KEGIATAN = new ArrayList<String>();
    List<String> BULAN = new ArrayList<String>();
    List<String> DANA = new ArrayList<String>();

    TextView txtDana,txtJudul,txtBulan;

    DecimalFormat mataUangIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
    DecimalFormatSymbols formatRupiah = new DecimalFormatSymbols();
    String bulan[] = {"Januari","Februari", "Maret","April","Mei","Juni","Juli","Agustus","September","Oktober","November","Desember"};

    public ParkirAdapter(Context c, List<String> ID, List<String> KEGIATAN, List<String> BULAN, List<String> DANA) {
        super(c, R.layout.row_parkir, ID);
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
        rowView = inflater.inflate(R.layout.row_parkir, null, true);
        txtJudul = (TextView) rowView.findViewById(R.id.etKegiatan);
        txtBulan = (TextView) rowView.findViewById(R.id.spBulan);
        txtDana = (TextView) rowView.findViewById(R.id.etDana);
        Button btnEdit =(Button)rowView.findViewById(R.id.btnEdit);

        formatRupiah.setCurrencySymbol("Rp. ");
        formatRupiah.setMonetaryDecimalSeparator(',');
        formatRupiah.setGroupingSeparator('.');
        mataUangIndonesia.setDecimalFormatSymbols(formatRupiah);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = ((ParkirActivity)getContext()).getLayoutInflater().inflate(R.layout.inflater_parkir, null);
                final EditText etKegiatan = (EditText) v.findViewById(R.id.etKegiatan);
                final TextView etBulan = (TextView) v.findViewById(R.id.spBulan);
                final EditText etDana = (EditText) v.findViewById(R.id.etDana);
                etKegiatan.setText(BULAN.get(position) );
                etBulan.setText(bulan[Integer.valueOf(KEGIATAN.get(position))].toString());
                etDana.setText(DANA.get(position));
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle("Update Data");
                alert.setView(v);
                etDana.setText(DANA.get(position));
                alert.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ((ParkirActivity)getContext()).Update(etKegiatan.getText().toString(),etDana.getText().toString(),ID.get(position));
                    }
                });
                alert.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alert.create();
                alert.show();
            }
        });

        txtJudul.setText(bulan[Integer.valueOf(KEGIATAN.get(position))].toString());
        txtDana.setText(mataUangIndonesia.format(Double.valueOf(DANA.get(position))));
        txtBulan.setText(mataUangIndonesia.format(Double.valueOf(BULAN.get(position))));

        return rowView;
    }
}
