package net.netne.afahzis.appdishub.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import net.netne.afahzis.appdishub.InProfile.GambaranActivity;
import net.netne.afahzis.appdishub.LoginActivity;
import net.netne.afahzis.appdishub.R;

/**
 * A simple {@link Fragment} subclass.
 * Reshape by Iwan on 15/12/2017
 */
public class PlabuhanFragment extends Fragment implements View.OnClickListener{
    LinearLayout PSD, tuposi, fasilitas, kriteria,data, rekap, galeri, pad;


    public PlabuhanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_plabuhan, container, false);
        PSD = view.findViewById(R.id.ll_f1);
        tuposi = view.findViewById(R.id.ll_f2);
        fasilitas = view.findViewById(R.id.ll_f3);
        kriteria = view.findViewById(R.id.ll_f4);
        data = view.findViewById(R.id.ll_f5);
        rekap = view.findViewById(R.id.ll_f6);
        galeri = view.findViewById(R.id.ll_f7);
        pad = view.findViewById(R.id.ll_pad);

        PSD.setOnClickListener(this);
        tuposi.setOnClickListener(this);
        fasilitas.setOnClickListener(this);
        kriteria.setOnClickListener(this);
        data.setOnClickListener(this);
        rekap.setOnClickListener(this);
        galeri.setOnClickListener(this);
        pad.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        String id = "";
        String page= "7";
        switch (v.getId()) {
            case R.id.ll_f1:
                id = "1";
                break;
            case R.id.ll_f2:
                id = "2";
                break;
            case R.id.ll_f3:
                id = "3";
                break;
            case R.id.ll_f4:
                id = "4";
                break;
            case R.id.ll_f5:
                id = "5";
                break;
            case R.id.ll_f6:
                id = "6";
                break;
            case R.id.ll_f7:
                id = "7";
                break;

        }
        switch (v.getId()) {
            case R.id.ll_f1:
            case R.id.ll_f2:
            case R.id.ll_f3:
            case R.id.ll_f4:
            case R.id.ll_f5:
            case R.id.ll_f6:
            case R.id.ll_f7:
                Intent i = new Intent(getContext(), GambaranActivity.class);
                i.putExtra("menu", id);
                i.putExtra("page", page);
                startActivity(i);
                break;
            case R.id.ll_pad:
                i = new Intent(getContext(), LoginActivity.class);
                i.putExtra("level","6");
                startActivity(i);
                break;


        }
    }

}
