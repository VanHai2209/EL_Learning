package com.example.myapplication.view.fragment;



import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;

import com.example.myapplication.dialog.DialogEditProfile;
import com.example.myapplication.model.GetInforResponse;

import com.example.myapplication.viewModel.FrgProfileViewModel;
import com.example.myapplication.viewModel.HomeViewModel;

public class ProfileFragment extends Fragment {
    String email, token_login;
    SharedPreferences sharedPreferences;
    TextView txtUsername, txtName, txtEmail, txtPhone, txtAddress, txtGender, txtBirthday;
    AppCompatButton btnEditProfile, btnChangePass;
    FrgProfileViewModel frgProfileViewModel;
    HomeViewModel homeViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        //frgProfileViewModel = new FrgProfileViewModel(getContext(), homeViewModel.getData());
        frgProfileViewModel = new FrgProfileViewModel(getContext());
        sharedPreferences = getActivity().getSharedPreferences("EL_Learning", Context.MODE_PRIVATE);
        token_login = sharedPreferences.getString("Token_Login",null);
        email = sharedPreferences.getString("Email", null);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getInforUser(email, token_login);
        homeViewModel.getData().observe(getViewLifecycleOwner(), new Observer<GetInforResponse>() {
            @Override
            public void onChanged(GetInforResponse getInforResponse) {
                txtUsername.setText(getInforResponse.getDataUser().getUsername());
                txtName.setText(getInforResponse.getDataUser().getName());
                txtBirthday.setText(getInforResponse.getDataUser().getBirthday());
                txtGender.setText(frgProfileViewModel.checkGender(getInforResponse.getDataUser().getGender()));
                txtAddress.setText(getInforResponse.getDataUser().getAddress());
                txtPhone.setText(getInforResponse.getDataUser().getTelephone());
                txtEmail.setText(getInforResponse.getDataUser().getEmail());
            }
        });

        txtUsername = view.findViewById(R.id.txtUserName);
        txtName = view.findViewById(R.id.txtName);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtPhone = view.findViewById(R.id.txtPhone);
        txtAddress = view.findViewById(R.id.txtAddress);
        txtGender = view.findViewById(R.id.txtGender);
        txtBirthday = view.findViewById(R.id.txtBirthday);
        btnChangePass = view.findViewById(R.id.btn_changePasswords);

        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frgProfileViewModel.changePass();
            }
        });
        btnEditProfile = view.findViewById(R.id.btn_editProfile);
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogEditProfile dialogEditProfile = new DialogEditProfile(getContext(), homeViewModel.getData(), new DialogEditProfile.DialogCallback() {
                    @Override
                    public void onCancelClicked() {

                    }

                    @Override
                    public void onSaveClicked() {

                    }
                });
                dialogEditProfile.setCanceledOnTouchOutside(false);
                dialogEditProfile.show();
            }
        });
        return view;
    }

}