package net.netne.afahzis.appdishub.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import net.netne.afahzis.appdishub.DataActivity;
import net.netne.afahzis.appdishub.LaporanActivity;
import net.netne.afahzis.appdishub.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Reshape by iwan on 14/12/17.
 */

public class LaporanAdapter extends ArrayAdapter {
    private Context c;

    List<String> ID = new ArrayList<String>();
    List<String> KEGIATAN = new ArrayList<String>();
    List<String> BULAN = new ArrayList<String>();
    List<String> FISIK = new ArrayList<String>();
    List<String> KEUANGAN = new ArrayList<String>();

    TextView txtJudul,txtBulan,txtFisik,txtKeuangan;

    DecimalFormat mataUangIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
    DecimalFormatSymbols formatRupiah = new DecimalFormatSymbols();
    String bulan[] = {"Januari","Februari", "Maret","April","Mei","Juni","Juli","Agustus","September","Oktober","November","Desember"};

    public LaporanAdapter(Context c, List<String> ID, List<String> KEGIATAN, List<String> BULAN, List<String> FISIK,List<String> KEUANGAN) {
        super(c, R.layout.row_laporan, ID);
        this.c = c;
        this.ID = ID;
        this.KEGIATAN = KEGIATAN;
        this.BULAN = BULAN;
        this.FISIK = FISIK;
        this.KEUANGAN = KEUANGAN;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(c);
        View rowView;
        rowView = inflater.inflate(R.layout.row_laporan, null, true);
        txtJudul = (TextView) rowView.findViewById(R.id.kegiatan);
        txtBulan = (TextView) rowView.findViewById(R.id.tvBulan);
        txtFisik = (TextView) rowView.findViewById(R.id.tvFisik);
        txtKeuangan = (TextView) rowView.findViewById(R.id.tvKeuangan);
        Button btnEdit =(Button)rowView.findViewById(R.id.btnEdit);
        final TextView txtId= (TextView)rowView.findViewById(R.id.txtId);

        formatRupiah.setCurrencySymbol("Rp. ");
        formatRupiah.setMonetaryDecimalSeparator(',');
        formatRupiah.setGroupingSeparator('.');
        mataUangIndonesia.setDecimalFormatSymbols(formatRupiah);

        txtId.setText(ID.get(position));
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = ((LaporanActivity)getContext()).getLayoutInflater().inflate(R.layout.inflater_laporan, null);
                final Spinner etBulan = (Spinner) v.findViewById(R.id.spBulan);
                final EditText etDana = (EditText) v.findViewById(R.id.spFisik);
                final EditText etDana2 = (EditText) v.findViewById(R.id.etDana);
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle("Update Data | "+KEGIATAN.get(position));
                alert.setView(v);
                etBulan.setSelection(Integer.valueOf(BULAN.get(position)));
                etDana.setText(FISIK.get(position));
                etDana2.setText(KEUANGAN.get(position));
                alert.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ((LaporanActivity)getContext()).Update(String.valueOf(etBulan.getSelectedItemPosition()),etDana.getText().toString(),KEGIATAN.get(position),txtId.getText().toString(),etDana2.getText().toString());
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

        txtJudul.setText(KEGIATAN.get(position));
        txtBulan.setText(bulan[Integer.valueOf(BULAN.get(position))].toString());
        txtFisik.setText(FISIK.get(position)+"%");
        txtKeuangan.setText(KEUANGAN.get(position)+"%");


        return rowView;
    }
}
