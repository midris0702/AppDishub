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
public class PengelolaFragment extends Fragment implements View.OnClickListener {

    LinearLayout tuposi, trans, bus, pad;


    public PengelolaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pengelola, container, false);
        tuposi = view.findViewById(R.id.ll_g1);
        trans = view.findViewById(R.id.ll_g2);
        bus = view.findViewById(R.id.ll_g3);
        pad = view.findViewById(R.id.ll_pad);

        tuposi.setOnClickListener(this);
        trans.setOnClickListener(this);
        bus.setOnClickListener(this);
        pad.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

        String id = "";
        String page= "5";
        switch (v.getId()) {
            case R.id.ll_g1:
                id = "1";
                break;
            case R.id.ll_g2:
                id = "2";
                break;
            case R.id.ll_g3:
                id = "3";
                break;
        }
        switch (v.getId()) {
            case R.id.ll_g1:
            case R.id.ll_g2:
            case R.id.ll_g3:
                Intent i = new Intent(getContext(), GambaranActivity.class);
                i.putExtra("menu", id);
                i.putExtra("page", page);
                startActivity(i);
                break;
            case R.id.ll_pad:
                i = new Intent(getContext(), LoginActivity.class);
                i.putExtra("level","7");
                startActivity(i);
                break;


        }

    }
}
