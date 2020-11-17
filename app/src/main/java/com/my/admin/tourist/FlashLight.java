package com.my.admin.tourist;


import android.Manifest;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FlashLight extends Fragment {

    ToggleButton mToggleButton;

    public FlashLight() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View flash_view = inflater.inflate(R.layout.fragment_flash_light,container,false);

        mToggleButton = flash_view.findViewById(R.id.flash);

        //I used a CameraManager for FlashLight
        //Request permission for Camera using
        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CAMERA},60);
        final CameraManager cameraManager = (CameraManager) getActivity().getSystemService(Context.CAMERA_SERVICE);

        mToggleButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                try {
                    assert cameraManager != null;
                    String cameraID = cameraManager.getCameraIdList()[0];

                    if (mToggleButton.isChecked()){
                        cameraManager.setTorchMode(cameraID,true);
                    }else {
                        cameraManager.setTorchMode(cameraID,false);
                    }

                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        return flash_view;
    }

}
