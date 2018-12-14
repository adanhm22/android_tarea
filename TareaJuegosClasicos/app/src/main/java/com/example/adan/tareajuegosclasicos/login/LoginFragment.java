package com.example.adan.tareajuegosclasicos.login;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.adan.tareajuegosclasicos.R;
import com.example.adan.tareajuegosclasicos.modelo.Persona;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends android.support.v4.app.Fragment {

    private OnFragmentInteractionListener mListener;
    private EditText email,password;

    public LoginFragment() {
        // Required empty public constructor
    }

    private void setmListener(OnFragmentInteractionListener listener){
        this.mListener= listener;
    }

    public static LoginFragment newInstance(OnFragmentInteractionListener listener){
        LoginFragment lf = new LoginFragment();
        lf.setmListener(listener);
        return lf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_login,container,false);
        email=view.findViewById(R.id.emailLogin);
        password=view.findViewById(R.id.passwordLogin);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email=view.findViewById(R.id.emailLogin);
        password=view.findViewById(R.id.passwordLogin);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void cambiarARegistro(View vista){
        mListener.cambiarARegistro(vista);
    }
    public void login(View vista){
        Persona persona = new Persona();
        persona.setEmail(email.getText().toString());
        persona.setPassword(password.getText().toString());
        mListener.login(persona);
    }
}
