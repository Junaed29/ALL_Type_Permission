package com.example.dexter_permission.TedPermission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dexter_permission.R;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class Ted_Activity extends AppCompatActivity {


    TextView storageTextView, contactTextView, cameraTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ted);

        storageTextView = findViewById(R.id.storageTedTextId);
        contactTextView = findViewById(R.id.contactTedTextId);
        cameraTextView = findViewById(R.id.cameraTedTextId);


    }

    public void cameraPermission(View view) {
        TedPermission.with(this)
                .setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        cameraTextView.setText("Permission Granted");
                        cameraTextView.setTextColor(ContextCompat.getColor(Ted_Activity.this, R.color.grantedColor));
                    }

                    @Override
                    public void onPermissionDenied(List<String> deniedPermissions) {
                        cameraTextView.setText("Permission Denied");
                        cameraTextView.setTextColor(ContextCompat.getColor(Ted_Activity.this, R.color.deniedColor));
                    }
                })
                .setRationaleTitle("We need this permission")
                .setRationaleMessage("Please allow this permission to continue")
                .setDeniedTitle("Permission denied")
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setGotoSettingButtonText("Settings")
                .setPermissions(Manifest.permission.CAMERA)
                .check();
    }

    public void storagePermission(View view) {
        TedPermission.with(this)
                .setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        storageTextView.setText("Permission Granted");
                        storageTextView.setTextColor(ContextCompat.getColor(Ted_Activity.this, R.color.grantedColor));
                    }

                    @Override
                    public void onPermissionDenied(List<String> deniedPermissions) {
                        storageTextView.setText("Permission Denied");
                        storageTextView.setTextColor(ContextCompat.getColor(Ted_Activity.this, R.color.deniedColor));
                    }
                })
                .setRationaleTitle("We need this permission")
                .setRationaleMessage("Please allow this permission to continue")
                .setDeniedTitle("Permission denied")
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setGotoSettingButtonText("Settings")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }

    public void contactPermission(View view) {
        /* TODO   TedPermission*/
        TedPermission.with(this)
                .setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        contactTextView.setText("Permission Granted");
                        contactTextView.setTextColor(ContextCompat.getColor(Ted_Activity.this, R.color.grantedColor));
                    }

                    @Override
                    public void onPermissionDenied(List<String> deniedPermissions) {
                        contactTextView.setText("Permission Denied");
                        contactTextView.setTextColor(ContextCompat.getColor(Ted_Activity.this, R.color.deniedColor));
                    }
                })
                .setRationaleTitle("We need this permission")
                .setRationaleMessage("Please allow this permission to continue")
                .setDeniedTitle("Permission denied")
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setGotoSettingButtonText("Settings")
                .setPermissions(Manifest.permission.READ_CONTACTS)
                .check();
    }

    public void AllPermission(View view) {
        TedPermission.with(this)
                .setRationaleTitle("We need this permission")
                .setRationaleMessage("Please allow this permission to continue")
                .setDeniedTitle("Permission denied")
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setGotoSettingButtonText("Settings")
                .setPermissions(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_CONTACTS)
                .setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        Toast.makeText(Ted_Activity.this, "Granted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionDenied(List<String> deniedPermissions) {
                        for (String per : deniedPermissions){
                            Toast.makeText(Ted_Activity.this, per, Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .check();
    }
}
