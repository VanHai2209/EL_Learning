package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProfileFragment extends Fragment {
    DialogEditProfile dialogEditProfile;
    DialogChangePass dialogChangePass;
    AppCompatButton btnEditProfile, btnChangePass;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        btnChangePass = view.findViewById(R.id.btn_changePasswords);
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogChangePass = new DialogChangePass(getActivity());
                dialogChangePass.setCanceledOnTouchOutside(false);
                dialogChangePass.show();
            }
        });
        btnEditProfile = view.findViewById(R.id.btn_editProfile);
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogEditProfile = new DialogEditProfile(getActivity());
                dialogEditProfile.setCanceledOnTouchOutside(false);
                dialogEditProfile.show();
            }
        });
        return view;
    }
}