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
 */
public class BermotorFragment extends Fragment implements View.OnClickListener {

    LinearLayout uptd, tuposi, prosedur, komponen, kendaraan, galeri, pad;

    public BermotorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bermotor, container, false);
                uptd = view.findViewById(R.id.ll_d1);
        tuposi= view.findViewById(R.id.ll_d2);
        prosedur = view.findViewById(R.id.ll_d3);
        komponen = view.findViewById(R.id.ll_d4);
        kendaraan= view.findViewById(R.id.ll_d5);
        galeri = view.findViewById(R.id.ll_d6);
        pad = view.findViewById(R.id.ll_pad);

        uptd.setOnClickListener(this);
        tuposi.setOnClickListener(this);
        prosedur.setOnClickListener(this);
        komponen.setOnClickListener(this);
        kendaraan.setOnClickListener(this);
        galeri.setOnClickListener(this);
        pad.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        String id = "";
        switch (v.getId()){
            case R.id.ll_d1 : id = "4.1"; break;
            case R.id.ll_d2: id = "4.2" ; break;
            case R.id.ll_d3: id = "4.3" ; break;
            case R.id.ll_d4: id = "4.4" ; break;
            case R.id.ll_d5: id = "4.5" ; break;
            case R.id.ll_d6: id = "4.6" ; break;
        }
        switch (v.getId()){
            case R.id.ll_d1:
            case R.id.ll_d2:
            case R.id.ll_d3:
            case R.id.ll_d4:
            case R.id.ll_d5:
            case R.id.ll_d6:
                Intent i = new Intent(getContext(), GambaranActivity.class);
                i.putExtra("menu",id);
                startActivity(i);
                break;
            case R.id.ll_pad :
                i = new Intent(getContext(), LoginActivity.class);
                i.putExtra("level","4");
                startActivity(i);
                break;


        }

    }

}
