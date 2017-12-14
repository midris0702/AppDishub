package net.netne.afahzis.appdishub.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import net.netne.afahzis.appdishub.InProfile.GambaranActivity;
import net.netne.afahzis.appdishub.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecretaryFragment extends Fragment implements View.OnClickListener {

    LinearLayout btn1, btn2, btn3, btn4;


    public SecretaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_secretary, container, false);
        btn1 = (LinearLayout) view.findViewById(R.id.ll_i1);
        btn2 = (LinearLayout) view.findViewById(R.id.ll_i2);
        btn3 = (LinearLayout) view.findViewById(R.id.ll_i3);
        btn4 = (LinearLayout) view.findViewById(R.id.ll_i4);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        String id = "";
        String page="6";
        switch (v.getId()) {
            case R.id.ll_i1:
                id = "1";
                break;
            case R.id.ll_i2:
                id = "2";
                break;
            case R.id.ll_i3:
                id = "3";
                break;
            case R.id.ll_i4:
                id = "4";
                break;
        }
        switch (v.getId()) {
            case R.id.ll_i1:
            case R.id.ll_i2:
            case R.id.ll_i3:
            case R.id.ll_i4:
                Intent i = new Intent(getContext(), GambaranActivity.class);
                i.putExtra("page", page);
                i.putExtra("menu", id);
                startActivity(i);
                break;
        }


    }

}
