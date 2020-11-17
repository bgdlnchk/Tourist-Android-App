package com.my.admin.tourist;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemList extends Fragment {

    Button mSave;
    EditText mEdit1,mEdit2,mEdit3,mEdit4,mEdit5;
    SharedPreferences mSavedNotes;
    InterstitialAd mListAd;

    public ItemList() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View list_view = inflater.inflate(R.layout.fragment_item_list,container,false);

        mListAd = new InterstitialAd(getContext());
        //Change Ad ID on your in a folder res/values/strings
        mListAd.setAdUnitId(getString(R.string.list_Ad_ID));
        mListAd.loadAd(new AdRequest.Builder().build());
        mListAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                mListAd.loadAd(new AdRequest.Builder().build());
            }
        });

        mSave = list_view.findViewById(R.id.savebutton);
        mEdit1 = list_view.findViewById(R.id.editText);
        mEdit2 = list_view.findViewById(R.id.editText2);
        mEdit3 = list_view.findViewById(R.id.editText3);
        mEdit4 = list_view.findViewById(R.id.editText4);
        mEdit5 = list_view.findViewById(R.id.editText5);
        mSavedNotes = getActivity().getSharedPreferences("notes", Context.MODE_PRIVATE);

        mEdit1.setText(mSavedNotes.getString("tag",""));
        mEdit2.setText(mSavedNotes.getString("tag2",""));
        mEdit3.setText(mSavedNotes.getString("tag3",""));
        mEdit4.setText(mSavedNotes.getString("tag4",""));
        mEdit5.setText(mSavedNotes.getString("tag5",""));

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListAd.isLoaded()){
                    mListAd.show();
                }

                if(mEdit1.getText().length()>0){
                    makeTag(mEdit1.getText().toString());
                    ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mEdit1.getWindowToken(),0);
                }

                if(mEdit2.getText().length()>0){
                    makeTag2(mEdit2.getText().toString());
                    ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mEdit2.getWindowToken(),0);
                }

                if(mEdit3.getText().length()>0){
                    makeTag3(mEdit3.getText().toString());
                    ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mEdit3.getWindowToken(),0);
                }

                if(mEdit4.getText().length()>0){
                    makeTag4(mEdit4.getText().toString());
                    ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mEdit4.getWindowToken(),0);
                }

                if(mEdit5.getText().length()>0){
                    makeTag5(mEdit5.getText().toString());
                    ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mEdit5.getWindowToken(),0);
                }

                Toast.makeText(getContext(), R.string.toast_string ,Toast.LENGTH_SHORT).show();

            }
        });
        return list_view;
    }

    //This methods used to change words in TextViews
    private void makeTag(String tag){
        SharedPreferences.Editor preferencesEditor = mSavedNotes.edit();
        preferencesEditor.putString("tag",tag);
        preferencesEditor.apply();
    }
    private void makeTag2(String tag2){
        SharedPreferences.Editor preferencesEditor2 = mSavedNotes.edit();
        preferencesEditor2.putString("tag2",tag2);
        preferencesEditor2.apply();
    }
    private void makeTag3(String tag3){
        SharedPreferences.Editor preferencesEditor3 = mSavedNotes.edit();
        preferencesEditor3.putString("tag3",tag3);
        preferencesEditor3.apply();
    }
    private void makeTag4(String tag4){
        SharedPreferences.Editor preferencesEditor4 = mSavedNotes.edit();
        preferencesEditor4.putString("tag4",tag4);
        preferencesEditor4.apply();
    }
    private void makeTag5(String tag5){
        SharedPreferences.Editor preferencesEditor5 = mSavedNotes.edit();
        preferencesEditor5.putString("tag5",tag5);
        preferencesEditor5.apply();
    }

}
