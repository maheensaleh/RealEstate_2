package com.example.realestate_2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
//
//import com.example.mapproject.activities.LoginActivity;
//import com.example.mapproject.model.User;
//import com.example.mapproject.sql.DatabaseHelper;
//import com.example.mapproject.utils.PreferenceUtils;
//import com.google.android.material.tabs.TabItem;
//import com.google.android.material.tabs.TabLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

//package com.example.home;

public class MainActivity extends AppCompatActivity
        implements tab1.OnFragmentInteractionListener, tab2.OnFragmentInteractionListener {


    private TabLayout tablayout;
    private ViewPager viewpager;
    private TabItem tabitem1, tabitem2;
    public PageAdapter pageadapter;
    private Toolbar mytoolbar;
    TextView username;
    String profilepic_uri;
    private ImageView profilepic_view;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent taker = getIntent();
        profilepic_uri = taker.getStringExtra("profilepic_uri");
        Toast.makeText(MainActivity.this,"hello"+profilepic_uri,Toast.LENGTH_SHORT).show();

        profilepic_view = (ImageView) findViewById(R.id.profile_image);
        Glide.with(profilepic_view.getContext())
                .load(profilepic_uri)
                .into(profilepic_view);

        mytoolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mytoolbar);   // to set menus options on toolbar
        //listUsers = new UsersListActivity().listUsers;

        username = (TextView) findViewById(R.id.user_name);
        //username.setText(databaseHelper.userNames);

        tablayout = (TabLayout) findViewById(R.id.tablayout);
        tabitem1 = (TabItem) findViewById(R.id.buy);
        tabitem2 = (TabItem) findViewById(R.id.sell);
        viewpager = (ViewPager)findViewById(R.id.myviewpager);

        pageadapter = new PageAdapter(getSupportFragmentManager(),tablayout.getTabCount());
        viewpager.setOffscreenPageLimit(1);
        viewpager.setAdapter(pageadapter);

//        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//
//                viewpager.setCurrentItem(tab.getPosition());
//
//                if (tab.getPosition()==0){
//                    System.out.println("one");
//                    pageadapter.notifyDataSetChanged();
//                }
//                else  if (tab.getPosition()==1){
//                    System.out.println("two");
//                    pageadapter.notifyDataSetChanged();
//                }
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {}
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            public void onPageSelected(int position) {

                System.out.println("position "+position);
            }
        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void add_sell_prop(View view) {
        Toast.makeText(MainActivity.this,"add a property",Toast.LENGTH_SHORT).show();
        Intent gotosell_form = new Intent(MainActivity.this,PropSellForm.class);
        startActivity(gotosell_form);
    }

    public void searchmap(View view) {
//        final Intent buy = new Intent(MainActivity.this, buyer.class);
//        startActivity(buy);
        Toast.makeText(MainActivity.this,"Search a property",Toast.LENGTH_SHORT).show();
    }

    // for option menus
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1,menu);
        return super.onCreateOptionsMenu(menu);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                Toast.makeText(MainActivity.this, "you presses  log out", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();

    }
}







