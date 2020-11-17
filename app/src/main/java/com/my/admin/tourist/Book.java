package com.my.admin.tourist;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class Book extends Fragment {
    String mItems[] = new String[] {};
    Fragment mFragment;
    FragmentTransaction mFragmentTransaction;
    InterstitialAd mBookAd;

    public Book() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View book_view = inflater.inflate(R.layout.fragment_book,container,false);

        mItems = getResources().getStringArray(R.array.system);
        ListView listView = book_view.findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1, mItems);

        mBookAd = new InterstitialAd(getContext());
        //Change Ad ID on your in a folder res/values/strings
        mBookAd.setAdUnitId(getString(R.string.book_Ad_ID));
        mBookAd.loadAd(new AdRequest.Builder().build());
        mBookAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                mBookAd.loadAd(new AdRequest.Builder().build());
            }
        });

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (mBookAd.isLoaded()) {
                    mBookAd.show();
                }
                //This switch used for opening books pages from a ListView
                switch(i){
                    case 0:
                        mFragment = new ThirdFragment();
                        mFragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        mFragmentTransaction.replace(R.id.mainFrame,mFragment);
                        mFragmentTransaction.commit();
                        break;
                    case 1:
                        mFragment = new FirstFragment();
                        mFragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        mFragmentTransaction.replace(R.id.mainFrame,mFragment);
                        mFragmentTransaction.commit();
                        break;
                    case 2:
                        mFragment = new FifthFragment();
                        mFragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        mFragmentTransaction.replace(R.id.mainFrame,mFragment);
                        mFragmentTransaction.commit();
                        break;
                    case 3:
                        mFragment= new SecondFragment();
                        mFragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        mFragmentTransaction.replace(R.id.mainFrame,mFragment);
                        mFragmentTransaction.commit();
                        break;
                    case 4:
                        mFragment = new NinthFragment();
                        mFragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        mFragmentTransaction.replace(R.id.mainFrame,mFragment);
                        mFragmentTransaction.commit();
                        break;
                    case 5:
                        mFragment = new EighthFragment();
                        mFragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        mFragmentTransaction.replace(R.id.mainFrame,mFragment);
                        mFragmentTransaction.commit();
                        break;
                    case 6:
                        mFragment = new FourthFragment();
                        mFragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        mFragmentTransaction.replace(R.id.mainFrame,mFragment);
                        mFragmentTransaction.commit();
                        break;
                    case 7:
                        mFragment = new SixthFragment();
                        mFragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        mFragmentTransaction.replace(R.id.mainFrame,mFragment);
                        mFragmentTransaction.commit();
                        break;
                    case 8:
                        mFragment = new SeventhFragment();
                        mFragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        mFragmentTransaction.replace(R.id.mainFrame,mFragment);
                        mFragmentTransaction.commit();
                        break;
                }
            }
        });
        return book_view;
    }

}
