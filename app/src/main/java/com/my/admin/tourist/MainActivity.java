package com.my.admin.tourist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.MobileAds;
import com.kobakei.ratethisapp.RateThisApp;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Integrate RateThisApp
        RateThisApp.onCreate(this);
        RateThisApp.showRateDialogIfNeeded(this);
        //Set the parameters when RateThisApp Window will be shown
        RateThisApp.Config config = new RateThisApp.Config(0, 2);
        RateThisApp.init(config);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Starts from Book Fragment
        Fragment mStartFragment = new Book();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrame,mStartFragment);
        ft.commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_book);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_instagram) {
            Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
            //Paste here your Instagram link
            myWebLink.setData(Uri.parse("https://www.instagram.com/hiking_apps/?hl=en"));
            startActivity(myWebLink);
            return true;
        }else if(id == R.id.action_rate){
            Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
            myWebLink.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.my.admin.tourist"));
            startActivity(myWebLink);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment mCurrentFragment;

        if (id == R.id.nav_list) {
            mCurrentFragment = new ItemList();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame,mCurrentFragment);
            ft.commit();

        } else if (id == R.id.nav_compass) {
            mCurrentFragment = new Compass();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame,mCurrentFragment);
            ft.commit();

        } else if (id == R.id.nav_timer) {
            mCurrentFragment = new Timer();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame,mCurrentFragment);
            ft.commit();

        } else if (id == R.id.nav_flashlight) {
            mCurrentFragment = new FlashLight();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame,mCurrentFragment);
            ft.commit();

        } else if (id == R.id.nav_book) {
            mCurrentFragment = new Book();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame,mCurrentFragment);
            ft.commit();

        } else if (id == R.id.nav_send) {
            Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
            myWebLink.setData(Uri.parse("https://www.instagram.com/hiking_app/?hl=uk"));
            startActivity(myWebLink);

        } else if (id == R.id.nav_share) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT,
                    "https://play.google.com/store/apps/collection/cluster?clp=igNAChkKEzczNTc1MDk5OTM3NTk0OTUzMDAQCBgDEiEKG2NvbS5jb21wYW55LmFkbWluLmZvdW5kZXIzMxABGAMYAQ%3D%3D:S:ANO1ljLJ4OE");
            shareIntent.setType("text/plain");
            startActivity(shareIntent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
