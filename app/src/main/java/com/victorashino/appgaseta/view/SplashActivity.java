package com.victorashino.appgaseta.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.victorashino.appgaseta.R;
import com.victorashino.appgaseta.data.GasEtaDB;

public class SplashActivity extends AppCompatActivity {

    public static final int TIME_OUT_SPLASH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        comutSplashScreen();
    }

    private void comutSplashScreen() {

        GasEtaDB db = new GasEtaDB(SplashActivity.this);

        new Handler().postDelayed(() -> {
            Intent mainScreen = new Intent(SplashActivity.this, GasEtaActivity.class);
            startActivity(mainScreen);
            finish();
        }, TIME_OUT_SPLASH);
    }
}