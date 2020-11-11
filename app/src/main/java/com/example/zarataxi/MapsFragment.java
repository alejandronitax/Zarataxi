package com.example.zarataxi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.FloatingWindow;
import androidx.navigation.fragment.NavHostFragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.icu.text.Transliterator;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.maps.android.ui.BubbleIconFactory;
import com.google.maps.android.ui.IconGenerator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.content.Context.LOCATION_SERVICE;

public class MapsFragment extends Fragment {

    private FusedLocationProviderClient fusedLocationClient;
    private LocationManager locationManager;
    private GoogleMap mMap;
    private Context contexto;
    Location myLocation = null;
    Location destinationLocation = null;
    protected LatLng start = null;
    protected LatLng end = null;
    private final static int LOCATION_REQUEST_CODE = 23;
    boolean locationPermission = false;
    private Marker markerInicial,markerFinal;
    private FloatingActionButton myUbi;
    private boolean access;

    private Bundle datosRecuperados;


    @Override
    public void onAttach (Context context) {
        super.onAttach(context);
        contexto = context;
    }

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {

            mMap = googleMap;
            getMyLocation();

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view;
        datosRecuperados = getArguments();

        Toast.makeText(contexto, ""+datosRecuperados.toString(), Toast.LENGTH_SHORT).show();

        if(!datosRecuperados.isEmpty()){

            if (!datosRecuperados.getString("maps").equalsIgnoreCase("maps1")) {
                view = inflater.inflate(R.layout.fragment_maps2, container, false);
            } else {
                view = inflater.inflate(R.layout.fragment_maps1, container, false);
            }
        } else {

            view = inflater.inflate(R.layout.fragment_maps1, container, false);

        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
//        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MapsFragment.this)
                        .navigate(R.id.action_mapsFragment_to_directionFragment);
            }
        });

        locationManager = (LocationManager) contexto.getSystemService(LOCATION_SERVICE);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(contexto);//initi

        myUbi = view.findViewById(R.id.myUbi);

            Button button_destiny = view.findViewById(R.id.button_destiny);
            button_destiny.setOnClickListener(new SingleClickListener() {
                @Override
                public void performClick(View v) {
                    Bundle bundle = new Bundle();

                    if(datosRecuperados.getBoolean("endDestiny")){

                        bundle.putString("destiny", "prueba1");

                    } else {

                        bundle.putString("origin", "prueba2");

                    }
                    NavHostFragment.findNavController(MapsFragment.this)
                            .navigate(R.id.action_mapsFragment_to_directionFragment,bundle);
                }
            });


        // recogemos los argumentos del otro framento con getArguments().getString("amount")

    }

    //to get user location
    private void getMyLocation() {
        if (ActivityCompat.checkSelfPermission(contexto, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(contexto, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        } else {

            // Deshabilita el boton predeterminado de Google de centrar en la ubicacion.
            mMap.setMyLocationEnabled(false);

            // Deshabilita los botones de la barra de herramientas predetermiandos de Google
            mMap.getUiSettings().setMapToolbarEnabled(false);

            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                //check the cordantes
                                myLocation = location;
                                start = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());

                                LatLng ltlng = new LatLng(location.getLatitude(), location.getLongitude());

                                centerCamera(ltlng);


/*                                IconGenerator mBubbleFactory = new IconGenerator(contexto);
                                mBubbleFactory.setContentView(mImageView);
                                mImageView.setImageBitmap(.....);*/



                                /*mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconBitmap)).position(MyPosition).title("My current position"));
*/

                                markerInicial = mMap.addMarker(new MarkerOptions()
                                        .position(start)
                                        .title(start.toString())
                                        //.icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                                        .draggable(true)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.chico_morenito)));

                            } else {

                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });


            //get destination location when user click on map
            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {

                    end = latLng;

                    //mMap.clear();

                    //start = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
                    //start route finding
                    //Findroutes(start,end);
                    Toast.makeText(contexto, end.toString(), Toast.LENGTH_SHORT).show();

                }
            });


            mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {

                @Override
                public void onMarkerDragStart(Marker marker) {
                    //updateInfo("drag start", marker);

                }

                @Override
                public void onMarkerDrag(Marker marker) {
                    LatLng p = marker.getPosition();
                    //updateInfo(String.format(Locale.US, "drag %.1f %.1f", p.latitude, p.longitude), marker);
                }

                @Override
                public void onMarkerDragEnd(Marker marker) {
                    //updateInfo("drag end", marker);

                    end = marker.getPosition();
                    centerCamera(end);

                    String positionText = marker.getPosition().toString();
                    marker.setTitle(positionText);



                }
            });

            myUbi.setOnClickListener(new SingleClickListener() {
                @Override
                public void performClick(View v) {
                    markerInicial.setPosition(start);
                    centerCamera(start);

                }
            });

        }

    }

/*
    private void setMarkerDragListener(GoogleMap map) {

        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {

            @Override
            public void onMarkerDragStart(Marker marker) {
                updateInfo("drag start", marker);
            }

            @Override
            public void onMarkerDrag(Marker marker) {
                LatLng p = marker.getPosition();
                updateInfo(String.format(Locale.US, "drag %.1f %.1f", p.latitude, p.longitude), marker);
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                updateInfo("drag end", marker);
            }
        });
    }
*/
    private void centerCamera(LatLng position){
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                position, 16f);
        mMap.animateCamera(cameraUpdate);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case LOCATION_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //if permission granted.
                    locationPermission = true;
                    getMyLocation();

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }


}