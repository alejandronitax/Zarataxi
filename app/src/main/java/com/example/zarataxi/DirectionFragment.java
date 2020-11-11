package com.example.zarataxi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DirectionFragment extends Fragment implements View.OnClickListener {

    private Context contexto;
    private Button buttonOptional;
    private RecyclerView recyclerView;
    private FloatingActionButton homeButton, jobButton, favoriteButton;
    private Bundle bundle;
    private  List<DirectionItem> directionItems;

    @Override
    public void onAttach (@NonNull Context context) {
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

        TextInputEditText editextOrigen = view.findViewById(R.id.editextOrigen);
        TextInputEditText editextDestino = view.findViewById(R.id.editextDestino);
        TextInputLayout inputTextOrigen = view.findViewById(R.id.inputTextOrigen);
        TextInputLayout inputTextDestino = view.findViewById(R.id.inputTextDestino);
        directionItems = new ArrayList<>();

        bundle = getArguments();
        String originDestiny = bundle.getString("origin");
        String endDestiny = bundle.getString("destiny");

        Toast.makeText(contexto, "" + bundle, Toast.LENGTH_SHORT).show();

        if (!bundle.isEmpty()) {

  /*          if (!originDestiny.isEmpty()) {
                editextOrigen.setText(originDestiny);
            }

            if (!endDestiny.isEmpty()) {
                editextOrigen.setText(endDestiny);
            }*/
        }

        inputTextOrigen.setEndIconOnClickListener(new SingleClickListener() {
            @Override
            public void performClick(View v) {
                bundle.putString("maps", "maps1");
                NavHostFragment.findNavController(DirectionFragment.this)
                        .navigate(R.id.action_directionFragment_to_mapsFragment, bundle);
            }
        });

        inputTextDestino.setEndIconOnClickListener(new SingleClickListener() {
            @Override
            public void performClick(View v) {
                bundle.putString("maps", "maps1");
                bundle.putBoolean("endDestiny",true);
                NavHostFragment.findNavController(DirectionFragment.this)
                        .navigate(R.id.action_directionFragment_to_mapsFragment, bundle);

            }
        });

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

      //  ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        buttonOptional = view.findViewById(R.id.buttonOptional);

            buttonOptional.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(buttonOptional.getText().toString().equalsIgnoreCase("Enviar")){

                    /*NavHostFragment navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment);
                    NavController navController = navHostFragment.getNavController();
                    navController.navigate(R.id.action_directionFragmentCercalia_to_optionServicesFragment);*/

                    NavHostFragment.findNavController(DirectionFragment.this)
                            .navigate(R.id.action_directionFragment_to_optionServicesFragment2);

                } else {

                    bundle.putString("maps", "maps1");
                    NavHostFragment.findNavController(DirectionFragment.this)
                                                .navigate(R.id.action_directionFragment_to_mapsFragment,bundle);

                }

            }
        });

        actionEditText(editextOrigen,R.drawable.icono_n_blue, R.color.nitax1);
        actionEditText(editextDestino,R.drawable.icono_n_red, R.color.nitax2);

        recyclerView.setAdapter(new MyAdapterDirection(directionItems, new MyAdapterDirection.MiOnItemClickListener() {
            @Override
            public void miOnItemClick(int posicionItem) {

                //textInputLayoutIni.setText(directionItems.get(posicionItem).getText());

            }

        }));

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
                if (!TextUtils.isEmpty(s) && Objects.requireNonNull(textInputEditText.getText()).toString().length() >= 3) {

                    recyclerView.setVisibility(View.VISIBLE);

                    Toast.makeText(contexto, "paso", Toast.LENGTH_SHORT).show();

                    homeButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(color)));
                    jobButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(color)));
                    favoriteButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(color)));

                    directionItems.add(new DirectionItem(drawable, "fsdf"));
                    directionItems.add(new DirectionItem(drawable, "fsdf2"));
                    directionItems.add(new DirectionItem(drawable, "fsdf3"));
                    directionItems.add(new DirectionItem(drawable, "fsdf4"));
                    directionItems.add(new DirectionItem(drawable, "fsdf5"));

                } else {

                    recyclerView.setVisibility(View.INVISIBLE);

                }

            }
        });

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.homeButton:
                Toast.makeText(contexto, "home", Toast.LENGTH_SHORT).show();
                break;

            case R.id.jobButton:

            case R.id.favoriteButton:
                Toast.makeText(contexto, "jobButton", Toast.LENGTH_SHORT).show();
                break;

        }

    }
}