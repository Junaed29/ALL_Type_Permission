package com.example.dexter_permission;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dexter_permission.DexterPermission.Dexter_Activity;
import com.example.dexter_permission.TedPermission.Ted_Activity;
import com.karumi.dexter.DexterActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dexterPermission(View view) {
        startActivity(new Intent(MainActivity.this, Dexter_Activity.class));
    }

    public void tedPermission(View view) {
        startActivity(new Intent(MainActivity.this, Ted_Activity.class));
    }
}
