package com.example.zarataxi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class DirectionFragment extends Fragment implements View.OnClickListener {

    private Context contexto;
    private TextInputEditText editextOrigen,editextDestino;
    private Button buttonOptional;
    private TextInputLayout inputTextOrigen,inputTextDestino;
    @Override
    public void onAttach (Context context) {
        super.onAttach(context);
        contexto = context;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_direction, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editextOrigen = view.findViewById(R.id.editextOrigen);
        editextDestino = view.findViewById(R.id.editextDestino);

         inputTextOrigen = view.findViewById(R.id.inputTextOrigen);
         inputTextDestino = view.findViewById(R.id.inputTextDestino);
         inputTextOrigen.setEndIconOnClickListener(this);
         inputTextDestino.setEndIconOnClickListener(this);
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Toast.makeText(contexto, "atras", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        };

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        buttonOptional = view.findViewById(R.id.buttonOptional);

            buttonOptional.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(!buttonOptional.getText().toString().equalsIgnoreCase("Enviar")){
/*
                    NavHostFragment.findNavController(DirectionFragmentCercalia)
                            .navigate(R.id.action_directionFragmentCercalia_to_optionServicesFragment);

*/
                } else {

                    NavHostFragment.findNavController(DirectionFragment.this)
                                                .navigate(R.id.action_directionFragment_to_verificationFragment);


                }

            }
        });

         actionEditText(editextOrigen,R.drawable.icono_n_blue, R.color.nitax1);
         actionEditText(editextDestino,R.drawable.icono_n_red, R.color.nitax2);

    }

    private void actionEditText(final TextInputEditText textInputEditText, final Integer drawable, final Integer color){

        textInputEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                // Check if edittext is empty
                if (!TextUtils.isEmpty(s) && textInputEditText.getText().toString().length() >= 3) {

                 /*   homeButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(color)));
                    jobButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(color)));
                    favoriteButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(color)));
*/
                } else {

                    //recyclerView.setVisibility(View.INVISIBLE);

                }

            }
        });

    }

    @Override
    public void onClick(View view) {

        Bundle bundle = new Bundle();

        if (view.getId() == inputTextOrigen.getEndIconMode()) {
            bundle.putString("amount", "origin");
        } else {
            bundle.putString("amount", "destino");
        }

        NavHostFragment.findNavController(DirectionFragment.this)
                .navigate(R.id.action_directionFragment_to_mapsFragment, bundle);

    }
}