package com.example.dexter_permission.DexterPermission;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.dexter_permission.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class Dexter_Activity extends AppCompatActivity {
    TextView storageTextView, contactTextView, cameraTextView;

    DexterMultiplePermissionListener dexterMultiplePermissionListener;

    DexterSinglePermissionListener dexterSinglePermissionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dexter);

        storageTextView = findViewById(R.id.storageTextId);
        contactTextView = findViewById(R.id.contactTextId);
        cameraTextView = findViewById(R.id.cameraTextId);

        dexterSinglePermissionListener = new DexterSinglePermissionListener(this);
        dexterMultiplePermissionListener = new DexterMultiplePermissionListener(this);
    }


    public void cameraPermission(View view) {
        Dexter.withContext(Dexter_Activity.this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(dexterSinglePermissionListener)
                .check();
    }

    public void storagePermission(View view) {
        Dexter.withContext(Dexter_Activity.this)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(dexterSinglePermissionListener)
                .check();
    }

    public void contactPermission(View view) {
        Dexter.withContext(Dexter_Activity.this)
                .withPermission(Manifest.permission.READ_CONTACTS)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        contactTextView.setText("Permission Granted");
                        contactTextView.setTextColor(ContextCompat.getColor(Dexter_Activity.this, R.color.grantedColor));
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        if (permissionDeniedResponse.isPermanentlyDenied()) {
                            contactTextView.setText("Permission Denied Permanently");
                            contactTextView.setTextColor(ContextCompat.getColor(Dexter_Activity.this, R.color.permanentDeniedColor));
                            openSettings();
                            return;
                        }
                        contactTextView.setText("Permission Denied");
                        contactTextView.setTextColor(ContextCompat.getColor(Dexter_Activity.this, R.color.deniedColor));

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                })
                .check();

    }

    public void AllPermission(View view) {
        Dexter.withContext(Dexter_Activity.this)
                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS)
                .withListener(dexterMultiplePermissionListener)
                .check();
    }


    public void showPermissionGranted(String permission) {
        switch (permission) {
            case Manifest.permission.CAMERA:
                cameraTextView.setText("Permission Granted");
                cameraTextView.setTextColor(ContextCompat.getColor(this, R.color.grantedColor));
                break;

            case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                storageTextView.setText("Permission Granted");
                storageTextView.setTextColor(ContextCompat.getColor(this, R.color.grantedColor));
                break;
            case Manifest.permission.READ_CONTACTS:
                contactTextView.setText("Permission Granted");
                contactTextView.setTextColor(ContextCompat.getColor(this, R.color.grantedColor));
                break;
        }
    }

    public void showPermissionDenied(String permission) {
        switch (permission) {
            case Manifest.permission.CAMERA:
                cameraTextView.setText("Permission Denied");
                cameraTextView.setTextColor(ContextCompat.getColor(this, R.color.deniedColor));
                break;

            case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                storageTextView.setText("Permission Denied");
                storageTextView.setTextColor(ContextCompat.getColor(this, R.color.deniedColor));
                break;
            case Manifest.permission.READ_CONTACTS:
                contactTextView.setText("Permission Denied");
                contactTextView.setTextColor(ContextCompat.getColor(this, R.color.deniedColor));
                break;
        }
    }


    // When user request same permission again
    public void showPermissionRationale(final PermissionToken token) {
        new AlertDialog.Builder(this)
                .setTitle("We need this permission")
                .setMessage("Please allow this permission to continue")
                .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        token.continuePermissionRequest();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        token.cancelPermissionRequest();
                        dialog.dismiss();
                    }
                })
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        token.cancelPermissionRequest();
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public void handlePermanentDeniedPermission(String permissionRequest) {
        switch (permissionRequest) {
            case Manifest.permission.CAMERA:
                cameraTextView.setText("Permission Denied Permanently");
                cameraTextView.setTextColor(ContextCompat.getColor(this, R.color.permanentDeniedColor));
                break;

            case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                storageTextView.setText("Permission Denied Permanently");
                storageTextView.setTextColor(ContextCompat.getColor(this, R.color.permanentDeniedColor));
                break;
            case Manifest.permission.READ_CONTACTS:
                contactTextView.setText("Permission Denied Permanently");
                contactTextView.setTextColor(ContextCompat.getColor(this, R.color.permanentDeniedColor));
                break;
        }


        openSettings();
    }


    public void openSettings() {
        new AlertDialog.Builder(this)
                .setTitle("We need this permission")
                .setMessage("Please allow to open settings")
                .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO Open Settings

                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                        dialog.dismiss();



                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }
}
