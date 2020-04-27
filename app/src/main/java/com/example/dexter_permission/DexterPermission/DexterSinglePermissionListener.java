package com.example.dexter_permission.DexterPermission;

import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class DexterSinglePermissionListener implements PermissionListener {

    private Dexter_Activity dexterActivity;

    public DexterSinglePermissionListener(Dexter_Activity activity) {
        this.dexterActivity = activity;
    }

    @Override
    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
        dexterActivity.showPermissionGranted(permissionGrantedResponse.getPermissionName());
    }

    @Override
    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
        if (permissionDeniedResponse.isPermanentlyDenied()) {
            dexterActivity.handlePermanentDeniedPermission(permissionDeniedResponse.getPermissionName());
            return;
        }

        dexterActivity.showPermissionDenied(permissionDeniedResponse.getPermissionName());
    }

    @Override
    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
        dexterActivity.showPermissionRationale(permissionToken);
    }
}
