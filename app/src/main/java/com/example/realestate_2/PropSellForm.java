package com.example.realestate_2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PropSellForm extends AppCompatActivity {

    //views
    private Intent get_addr;
    private EditText set_addr;
    private TextView floors_qty, rooms_qty, mRatingScale;
    private ImageView img1,img2,img3,img4;
    private Spinner spin;
    private EditText  price;
    private CheckBox sale, rent, balcony, elevator;
    private RatingBar mRatingBar;
    private ProgressBar prog_bar;

    //variables
    private Uri fb_img_uri;
    private Integer floor_count = 0, room_count = 0, rating_value=0;
    private Bitmap bitmap_1,bitmap_2;
    private String rating_comment = "" ,address_line,area;
    private String rent_sale = "None";
    private String balcony_status = "No";
    private String elevator_status = "No";
    private Uri img1_uri, img2_uri,fb_img1_uri,fb_img2_uri;

    //firebase
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;



    //================================================= ON CREATE ===================================//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prop_sell_form);

        //firebase instances
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("prop_for_sell");
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference().child("sell_media");

        //initialize views
        prog_bar = (ProgressBar) findViewById(R.id.progressBar2);
        prog_bar.setVisibility(View.INVISIBLE);
        floors_qty = (TextView) findViewById(R.id.floors_qty);
        rooms_qty = (TextView) findViewById(R.id.rooms_qty);
        price = (EditText) findViewById(R.id.price);
        mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
        mRatingScale = (TextView) findViewById(R.id.tvRatingScale);
        floors_qty = (TextView) findViewById(R.id.floors_qty);
        rooms_qty = (TextView) findViewById(R.id.rooms_qty);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);

        //checkboxes values
        sale = (CheckBox) findViewById(R.id.for_sale);
        rent = (CheckBox) findViewById(R.id.for_rent);
        balcony = (CheckBox) findViewById(R.id.balcony);
        elevator = (CheckBox) findViewById(R.id.elevator);

        //to set initial scroll to top
        final ScrollView scrollView = (ScrollView) findViewById(R.id.scroll_flat_form);
        scrollView.smoothScrollTo(0, 0);

        //intent for seting location/address
        get_addr = getIntent();
        String address = get_addr.getStringExtra("address");
        address_line = address;
        set_addr = (EditText)findViewById(R.id.address_submit);
        set_addr.setText(address);

        // area sizes
        Integer[] users = {800, 1300, 1600, 2200};
        spin = (Spinner) findViewById(R.id.flat_area);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        area = spin.getSelectedItem().toString();

        //rating bar star select
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

            }
        });
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingScale.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        rating_comment ="Not so good";
                        rating_value = 1;
                        break;
                    case 2:
                        rating_comment = "Need some improvement";
                        rating_value = 2;
                        break;
                    case 3:
                        rating_comment = "Good";
                        rating_value = 3;
                        break;
                    case 4:
                        rating_comment = "Great";
                        rating_value = 4;
                        break;
                    case 5:
                        rating_comment = "Perfect";
                        rating_value = 5;
                        break;
                    default:
                        mRatingScale.setText("");
                }
                mRatingScale.setText(rating_comment);

            }
        });


    }


//  ==================== FUNCTIONS ============================ //

    public void addlocation(View view) {
        Intent sell = new Intent(PropSellForm.this, SetPropLocation.class);
        startActivity(sell);
    }

    public void addimage1(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult(Intent.createChooser(intent,"Complete with"),1);

    }

    public void addimage2(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult(Intent.createChooser(intent,"Complete with"),2);
    }

    public void add_floor(View view) {
        floors_qty = (TextView) findViewById(R.id.floors_qty);
        floor_count += 1;
        floors_qty.setText(floor_count.toString());
    }

    public void add_room(View view) {

        rooms_qty = (TextView) findViewById(R.id.rooms_qty);
        room_count += 1;
        rooms_qty.setText(room_count.toString());
    }

    public void remove_room(View view) {

        rooms_qty = (TextView) findViewById(R.id.rooms_qty);
        if (room_count != 0) {
            room_count -= 1;
        }
        rooms_qty.setText(room_count.toString());
    }

    public void remove_floor(View view) {

        floors_qty = (TextView) findViewById(R.id.floors_qty);
        if (floor_count != 0) {
            floor_count -= 1;
        }
        floors_qty.setText(floor_count.toString());
    }


// =================================== ON ACTIVITY RESUME =============================//

    /**
     * Dispatch incoming result to the correct fragment.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode==RESULT_OK) {

            prog_bar.setVisibility(View.VISIBLE);
            Toast.makeText(PropSellForm.this,"Uploading image ...",Toast.LENGTH_SHORT).show();
            img1_uri = data.getData();
            Toast.makeText(PropSellForm.this,"this = "+img1_uri,Toast.LENGTH_SHORT).show();
            try {
                bitmap_1 = MediaStore.Images.Media.getBitmap(getContentResolver(), img1_uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ImageView imageView = findViewById(R.id.img1);
            imageView.setImageBitmap(bitmap_1);

            final StorageReference img1_ref = storageReference.child(img1_uri.getLastPathSegment());
            img1_ref.putFile(img1_uri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    img1_ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            fb_img1_uri = uri;
                            Toast.makeText(PropSellForm.this,"Done uploading !",Toast.LENGTH_SHORT).show();
                            prog_bar.setVisibility(View.GONE);

                        }
                    });
                }
            });


        }

        else if (requestCode == 2) {
            prog_bar.setVisibility(View.VISIBLE);
            Toast.makeText(PropSellForm.this,"Uploading image ...",Toast.LENGTH_SHORT).show();
            img2_uri = data.getData();
            try {
                bitmap_2 = MediaStore.Images.Media.getBitmap(getContentResolver(), img2_uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ImageView imageView = findViewById(R.id.img2);
            imageView.setImageBitmap(bitmap_2);

            final StorageReference img2_ref = storageReference.child(img2_uri.getLastPathSegment());
            img2_ref.putFile(img2_uri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    img2_ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            fb_img2_uri = uri;
                            Toast.makeText(PropSellForm.this,"Done uploading !",Toast.LENGTH_SHORT).show();
                            prog_bar.setVisibility(View.GONE);
                        }
                    });
                }
            });

        }
    }


//    ================================== FORM SUBMIT BUTTON ==================================//

    public void submit(View view){

        if (sale.isChecked()) {
            rent_sale = "Sale";
        }
        if (rent.isChecked()) {
            rent_sale = "Rent";
        }
        if (balcony.isChecked()) {
            balcony_status = "Yes";
        }
        if (elevator.isChecked()) {
            elevator_status = "Yes";
        }


        if (set_addr.getText().toString().equals("")){
            Toast.makeText(this, "Please enter Address !", Toast.LENGTH_SHORT).show();
            return;
        }


        //both sale rent checked
        if (sale.isChecked() == true && rent.isChecked() == true) {
            Toast.makeText(this, "Cannot select both : FOR SALE or FOR RENT", Toast.LENGTH_SHORT).show();
            return;
        }


        // both sell rent unchk
        if (sale.isChecked() == false && rent.isChecked() == false) {
            Toast.makeText(this, "Select any one : FOR SALE or FOR RENT", Toast.LENGTH_SHORT).show();
            return;
        }

        //both sale rent checked
        if (sale.isChecked() == true && rent.isChecked() == true) {
            Toast.makeText(this, "Cannot select both : FOR SALE or FOR RENT", Toast.LENGTH_SHORT).show();
            return;
        }

        //check price given
        if (price.getText().toString().equals("")){
            Toast.makeText(this, "Please provide property price !", Toast.LENGTH_SHORT).show();
            return;
        }

        if (rating_comment.equals("")){
            Toast.makeText(this, "Please provide property rating !", Toast.LENGTH_SHORT).show();
            return;
        }

        // check room not zero
        if (floors_qty.getText().toString().equals("0")){
            Toast.makeText(this, "Please provide number of floors!", Toast.LENGTH_SHORT).show();
            return;
        }

        //chek floor not zero
        if (rooms_qty.getText().toString().equals("0")){
            Toast.makeText(this, "Please enter number of rooms !", Toast.LENGTH_SHORT).show();
            return;
        }

        //check image1
        if (bitmap_1 == null  ){
            Toast.makeText(this, "Picture 1 not provided !", Toast.LENGTH_SHORT).show();
            return;
        }

        if (bitmap_2 == null  ){
            Toast.makeText(this, "Picture 2 not provided !", Toast.LENGTH_SHORT).show();
            return;
        }

//        send data to fb_db:
        SellProp prop_data = new SellProp(address_line,rent_sale,rating_comment,elevator_status,balcony_status,price.getText().toString(),area,rating_value.toString(),floor_count.toString(),room_count.toString(),fb_img1_uri.toString(),fb_img2_uri.toString());
        databaseReference.push().setValue(prop_data);


        Intent tomain_activity = new Intent(PropSellForm.this, MainActivity.class);
        Toast.makeText(PropSellForm.this,"Your form has been submited",Toast.LENGTH_SHORT).show();
        startActivity(tomain_activity);

    }



}
