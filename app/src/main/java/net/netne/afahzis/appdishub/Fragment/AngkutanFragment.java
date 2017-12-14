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
 * Reshape by Iwan on 14/12/2017
 */
public class AngkutanFragment extends Fragment implements View.OnClickListener {

    LinearLayout btn1, btn2, btn3, btn4;


    public AngkutanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_angkutan, container, false);
        btn1 = (LinearLayout) view.findViewById(R.id.ll_a1);
        btn2 = (LinearLayout) view.findViewById(R.id.ll_a2);
        btn3 = (LinearLayout) view.findViewById(R.id.ll_a3);
        btn4 = (LinearLayout) view.findViewById(R.id.ll_pad);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        String id = "";
        String page="2";
        switch (v.getId()) {
            case R.id.ll_a1:
                id = "1";
                break;
            case R.id.ll_a2:
                id = "2";
                break;
            case R.id.ll_a3:
                id = "3";
                break;
        }
        switch (v.getId()) {
            case R.id.ll_a1:
            case R.id.ll_a2:
            case R.id.ll_a3:
                Intent i = new Intent(getContext(), GambaranActivity.class);
                i.putExtra("page", page);
                i.putExtra("menu", id);
                startActivity(i);
                break;
            case R.id.ll_pad:
                i = new Intent(getContext(), LoginActivity.class);
                i.putExtra("level","1");
                startActivity(i);
                break;
        }


    }

}
