package com.example.zarataxi;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialCalendar;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OptionServicesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OptionServicesFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Context contexto;
    private Button button_count;
    private DialogFragment nuevoFragmento;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        contexto = context;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OptionServicesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OptionServicesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OptionServicesFragment newInstance(String param1, String param2) {
        OptionServicesFragment fragment = new OptionServicesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option_services, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm");

        String formattedHora = df2.format(calendar.getTime());
        String formattedDate = df.format(calendar.getTime());

        Button button_reloj = view.findViewById(R.id.button_reloj);
        Button button_calendar = view.findViewById(R.id.button_calendar);
        Button button_more_car = view.findViewById(R.id.button_more_car);
        Button button_less_car = view.findViewById(R.id.button_less_car);
        button_count = view.findViewById(R.id.button_count);


        button_reloj.setText(formattedHora);
        button_calendar.setText(formattedDate);

        button_reloj.setOnClickListener(this);
        button_calendar.setOnClickListener(this);
        button_more_car.setOnClickListener(this);
        button_less_car.setOnClickListener(this);

        Spinner spinner = view.findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(contexto,
                R.array.credit_cards, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        

        switch (view.getId()) {

            case R.id.button_reloj:

                nuevoFragmento = new DialogTimeFragment();
                nuevoFragmento.show(getActivity().getSupportFragmentManager(), "dialogo1");

                break;
            case R.id.button_calendar:
                Toast.makeText(contexto, "calendar", Toast.LENGTH_SHORT).show();
                nuevoFragmento = new CalendarFragment();
                nuevoFragmento.show(getActivity().getSupportFragmentManager(), "dialogo2");
                break;

            case R.id.button_more_car:

/*                button_count.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), resource, null));
                button_count*/
                Toast.makeText(contexto, "2", Toast.LENGTH_SHORT).show();
                button_count.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.dos, 0);
                break;

            case R.id.button_less_car:
                button_count.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.uno, 0);
                break;

            default:

                break;

        }

    }
}