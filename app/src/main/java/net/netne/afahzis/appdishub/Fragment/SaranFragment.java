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
public class SaranFragment extends Fragment implements View.OnClickListener {
    LinearLayout sarpras, penerangan, teknis, pad;


    public SaranFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_saran, container, false);
        sarpras = view.findViewById(R.id.ll_c1);
        penerangan= view.findViewById(R.id.ll_c2);
        teknis = view.findViewById(R.id.ll_c3);
        pad = view.findViewById(R.id.ll_pad);

        sarpras.setOnClickListener(this);
        penerangan.setOnClickListener(this);
        teknis.setOnClickListener(this);
        pad.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        String id = "";
        switch (v.getId()){
            case R.id.ll_c1 : id = "3.1"; break;
            case R.id.ll_c2: id = "3.2" ; break;
            case R.id.ll_c3: id = "3.3" ; break;
        }
        switch (v.getId()){
            case R.id.ll_c1:
            case R.id.ll_c2:
            case R.id.ll_c3:
                Intent i = new Intent(getContext(), GambaranActivity.class);
                i.putExtra("menu",id);
                startActivity(i);
                break;
            case R.id.ll_pad :
                i = new Intent(getContext(), LoginActivity.class);
                i.putExtra("level","3");
                startActivity(i);
                break;


        }

    }

}
