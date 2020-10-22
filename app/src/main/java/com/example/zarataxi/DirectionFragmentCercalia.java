package com.example.zarataxi;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class DirectionFragmentCercalia extends Fragment implements View.OnClickListener  {

    private Context contexto;
    private RecyclerView recyclerView;
    private FloatingActionButton homeButton, jobButton, favoriteButton;

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
        return inflater.inflate(R.layout.fragment_direcction_cercalia, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(contexto);
        recyclerView.setLayoutManager(layoutManager);

        homeButton = view.findViewById(R.id.homeButton);
        jobButton = view.findViewById(R.id.jobButton);
        favoriteButton = view.findViewById(R.id.favoriteButton);

        homeButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.nitax1)));
        jobButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.nitax1)));
        favoriteButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.nitax1)));

        homeButton.setOnClickListener(this);
        jobButton.setOnClickListener(this);
        favoriteButton.setOnClickListener(this);

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Toast.makeText(contexto, "atras", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        };

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);


        final List<DirectionItem> directionItems = new ArrayList<>();

        directionItems.add(new DirectionItem(R.drawable.icono_n_red, "fsdf"));
        directionItems.add(new DirectionItem(R.drawable.icono_n_red, "fsdf2"));
        directionItems.add(new DirectionItem(R.drawable.icono_n_red, "fsdf3"));
        directionItems.add(new DirectionItem(R.drawable.icono_n_red, "fsdf4"));
        directionItems.add(new DirectionItem(R.drawable.icono_n_red, "fsdf5"));

        recyclerView.setAdapter(new MyAdapterDirection(directionItems, new MyAdapterDirection.MiOnItemClickListener() {
            @Override
            public void miOnItemClick(int posicionItem) {

                //textInputLayoutIni.setText(directionItems.get(posicionItem).getText());

            }

        }));

    }



    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.homeButton:
                Toast.makeText(contexto, "home", Toast.LENGTH_SHORT).show();
                break;

            case R.id.jobButton:
                Toast.makeText(contexto, "jobButton", Toast.LENGTH_SHORT).show();
                break;

            case R.id.favoriteButton:
                Toast.makeText(contexto, "favoriteButton", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
