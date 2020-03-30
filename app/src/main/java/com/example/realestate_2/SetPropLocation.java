package com.example.realestate_2;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import com.example.realestate_2.placeautosuggestadapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;
import java.util.Locale;




public class SetPropLocation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    SearchView searchView;
    SupportMapFragment mapFragment;
    String addre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_prop_location);

//auto complete location search view
        final AutoCompleteTextView autoCompleteTextView= findViewById(R.id.location_seller);
        autoCompleteTextView.setAdapter(new placeautosuggestadapter(SetPropLocation.this,android.R.layout.simple_list_item_1));




//mark location on map when a location is enter in a search bar
        Button search=findViewById(R.id.search_seller);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                    String searching=autoCompleteTextView.getText().toString();
                    String latlng = getLocationFromAddress(searching).toString();
                    String searched = latlng;
                    String[] vals = searched.split(",");
                    LatLng loca = new LatLng(Double.parseDouble(vals[0]), Double.parseDouble(vals[1]));
                    mMap.addMarker(new MarkerOptions().position(loca).title(searching));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(loca));
                    TextView textView = findViewById(R.id.address);
                    addre = getCityname(Double.parseDouble(vals[0]), Double.parseDouble(vals[1]));
                    textView.setText(addre);
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),"enter location in search bar", Toast.LENGTH_LONG).show();
                }
            }
        });




        final Intent confirming = new Intent(SetPropLocation.this, PropSellForm.class);
        final Button confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                confirming.putExtra("address", addre);
                startActivity(confirming);
                finish();
            }
        });


        // to check that which page is calling this function
//        Intent typo=getIntent();
//        String typi=typo.getStringExtra("type");
//        if (typi.equals("penthouse")) {
//            final Intent confirming = new Intent(seller.this, penthouse.class);
//
//
//            final Button confirm = findViewById(R.id.confirm);
//            confirm.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    confirming.putExtra("detail", addre);
//
//                    startActivity(confirming);
//                    finish();
//
//                }
//            });
//        }

//        if (typi.equals("flat")) {
//            final Intent confirming = new Intent(seller.this, flat.class);
//
//
//            final Button confirm = findViewById(R.id.confirm);
//            confirm.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    confirming.putExtra("detail", addre);
//
//                    startActivity(confirming);
//                    finish();
//
//                }
//            });
//        }

//        if (typi.equals("banglow")) {
//            final Intent confirming = new Intent(seller.this, banglow.class);
//
//
//            final Button confirm = findViewById(R.id.confirm);
//            confirm.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    confirming.putExtra("detail", addre);
//
//                    startActivity(confirming);
//                    finish();
//
//                }
//            });
//        }


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


//to get city name from latitudes and longitudes

    private String getCityname(Double lattitude, Double longitude) {

        String mycityname = " ";

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        String add = null;
        try {
            List<Address> addresses = geocoder.getFromLocation(lattitude,longitude, 1);
            add = addresses.get(0).getAddressLine(0);
            mycityname = addresses.get(0).getLocality();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return add;
    }










    //get location from address
    public String getLocationFromAddress(String strAddress) {

        Geocoder coder = new Geocoder(this);
        List<Address> address;

        try {
            address = coder.getFromLocationName(strAddress, 1);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            double lat = location.getLatitude();
            double lng = location.getLongitude();

            return lat + "," + lng;

        } catch (Exception e) {
            return null;
        }
    }







    @Override
    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng saddar = new LatLng(24.8539874   ,67.0124112);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(saddar));


    }



}

