package com.example.dexter_permission.DexterPermission;

import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class DexterMultiplePermissionListener implements MultiplePermissionsListener {
    private final Dexter_Activity dexterActivity;

    public DexterMultiplePermissionListener(Dexter_Activity dexterActivity) {
        this.dexterActivity = dexterActivity;
    }

    @Override
    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
        for (PermissionGrantedResponse response : multiplePermissionsReport.getGrantedPermissionResponses()) {
            dexterActivity.showPermissionGranted(response.getPermissionName());
        }

        for (PermissionDeniedResponse response : multiplePermissionsReport.getDeniedPermissionResponses()) {
            if (response.isPermanentlyDenied()) {
                dexterActivity.handlePermanentDeniedPermission(response.getPermissionName());
                return;
            }
            dexterActivity.showPermissionDenied(response.getPermissionName());
        }
    }

    @Override
    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
        dexterActivity.showPermissionRationale(permissionToken);
    }
}
