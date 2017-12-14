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
 * Reshape by Iwan on 14/12/2017
 */
public class ProfileFragment extends Fragment {

    LinearLayout btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        btn1 = (LinearLayout) view.findViewById(R.id.btn1);
        btn2 = (LinearLayout) view.findViewById(R.id.btn2);
        btn3 = (LinearLayout) view.findViewById(R.id.btn3);
        btn4 = (LinearLayout) view.findViewById(R.id.btn4);
        btn5 = (LinearLayout) view.findViewById(R.id.btn5);
        btn6 = (LinearLayout) view.findViewById(R.id.btn6);
        btn7 = (LinearLayout) view.findViewById(R.id.btn7);
        btn8 = (LinearLayout) view.findViewById(R.id.btn8);
        btn9 = (LinearLayout) view.findViewById(R.id.btn9);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), GambaranActivity.class);
                i.putExtra("page","1");
                i.putExtra("menu","1");
                startActivity(i);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), GambaranActivity.class);
                i.putExtra("page","1");
                i.putExtra("menu","2");
                startActivity(i);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), GambaranActivity.class);
                i.putExtra("page","1");
                i.putExtra("menu","3");
                startActivity(i);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), GambaranActivity.class);
                i.putExtra("page","1");
                i.putExtra("menu","4");
                startActivity(i);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), GambaranActivity.class);
                i.putExtra("page","1");
                i.putExtra("menu","5");
                startActivity(i);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), GambaranActivity.class);
                i.putExtra("page","1");
                i.putExtra("menu","6");
                startActivity(i);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), GambaranActivity.class);
                i.putExtra("page","1");
                i.putExtra("menu","7");
                startActivity(i);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), GambaranActivity.class);
                i.putExtra("page","1");
                i.putExtra("menu","8");
                startActivity(i);
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), GambaranActivity.class);
                i.putExtra("page","1");
                i.putExtra("menu","9");
                startActivity(i);
            }
        });

        return view;
    }

}
