package com.my.admin.tourist;


import android.os.Bundle;
import android.os.SystemClock;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


/**
 * A simple {@link Fragment} subclass.
 */
public class Timer extends Fragment {

    private Button mStartButton;
    private Chronometer mChronometer;
    private long mTimeWhenStopped = 0;
    private InterstitialAd mTimerAd;

    public Timer() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View timer_view = inflater.inflate(R.layout.fragment_timer,container,false);


        mTimerAd = new InterstitialAd(getContext());
        //Change Ad ID on your in a folder res/values/strings
        mTimerAd.setAdUnitId(getString(R.string.timer_Ad_ID));
        mTimerAd.loadAd(new AdRequest.Builder().build());
        mTimerAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                mTimerAd.loadAd(new AdRequest.Builder().build());
            }
        });

        mStartButton = timer_view.findViewById(R.id.start_button);
        Button mPauseButton = timer_view.findViewById(R.id.pause_button);
        Button mResetButton = timer_view.findViewById(R.id.reset_button);
        mChronometer = timer_view.findViewById(R.id.chronometer1);

        mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long time = SystemClock.elapsedRealtime() - chronometer.getBase();
                int h = (int) (time /3600000);
                int m = (int) (time - h*3600000)/60000;
                int s = (int) (time - h*3600000 - m*60000)/1000;
                String t = (h < 10 ? "0"+h: h)+":"+(m < 10 ? "0"+m: m)+":"+ (s < 10 ? "0"+s: s);
                chronometer.setText(t);
            }
        });

        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.setText(R.string.time);

        //Timer stop method
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mChronometer.setBase(SystemClock.elapsedRealtime() + mTimeWhenStopped);
                mChronometer.start();
                mStartButton.setClickable(false);
            }
        });

        //Timer pause method
        mPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTimerAd.isLoaded()){
                    mTimerAd.show();
                }
                mTimeWhenStopped = mChronometer.getBase() - SystemClock.elapsedRealtime();
                mChronometer.stop();
                mStartButton.setClickable(true);
            }
        });

        //Timer reset method
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mChronometer.stop();
                mChronometer.setBase(SystemClock.elapsedRealtime());
                mChronometer.setText(R.string.time);
                mTimeWhenStopped = 0;
                mStartButton.setClickable(true);
            }
        });
        return timer_view;
    }

}
