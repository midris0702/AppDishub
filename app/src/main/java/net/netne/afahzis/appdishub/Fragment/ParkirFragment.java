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
public class ParkirFragment extends Fragment implements View.OnClickListener{

    LinearLayout gambarUmum, tuposi, parkir, galeri, pad;

    public ParkirFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_parkir, container, false);
        gambarUmum =view.findViewById(R.id.ll_e1);
        tuposi=view.findViewById(R.id.ll_e2);
        parkir=view.findViewById(R.id.ll_e3);
        galeri=view.findViewById(R.id.ll_e4);
        pad=view.findViewById(R.id.ll_pad);

        gambarUmum.setOnClickListener(this);
        tuposi.setOnClickListener(this);
        parkir.setOnClickListener(this);
        galeri.setOnClickListener(this);
        pad.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        String id = "";
        String page="6";
        switch (v.getId()){
            case R.id.ll_e1 : id = "1"; break;
            case R.id.ll_e2: id = "2" ; break;
            case R.id.ll_e3: id = "3" ; break;
            case R.id.ll_e4: id = "4" ; break;

        }
        switch (v.getId()){
            case R.id.ll_e1:
            case R.id.ll_e2:
            case R.id.ll_e3:
            case R.id.ll_e4:
                Intent i = new Intent(getContext(), GambaranActivity.class);
                i.putExtra("page", page);
                i.putExtra("menu",id);
                startActivity(i);
                break;
            case R.id.ll_pad :
                i = new Intent(getContext(), LoginActivity.class);
                i.putExtra("level","5");
                startActivity(i);
                break;


        }
    }

}
