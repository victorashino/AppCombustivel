package com.victorashino.appgaseta.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.victorashino.appgaseta.R;
import com.victorashino.appgaseta.databinding.ActivityGasetaBinding;
import com.victorashino.appgaseta.utils.UtilGasEta;

public class GasEtaActivity extends AppCompatActivity {

    EditText editGasolina;
    EditText editEtanol;

    TextView textResult;

    Button btnCalcule;
    Button btnClear;
    Button btnSave;
    Button btnDone;

    double priceGasolina;
    double priceEtanol;
    String recomendation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityGasetaBinding binding = ActivityGasetaBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editGasolina = binding.editGasolina;
        editEtanol = binding.editEtanol;

        textResult = binding.textResult;

        btnCalcule = binding.btnCalcule;
        btnClear = binding.btnClear;
        btnSave = binding.btnSave;
        btnDone = binding.btnDone;

        btnCalcule.setOnClickListener(view -> {

            boolean isDataOk = true;

            if (TextUtils.isEmpty(editGasolina.getText())) {
                editGasolina.setError("* Obrigatorio");
                editGasolina.requestFocus();
                isDataOk = false;
            }

            if (TextUtils.isEmpty(editEtanol.getText())) {
                editEtanol.setError("* Obrigatorio");
                editEtanol.requestFocus();
                isDataOk = false;
            }

            if (isDataOk) {
                priceGasolina = Double.parseDouble(editGasolina.getText().toString());
                priceEtanol = Double.parseDouble(editEtanol.getText().toString());
                recomendation = UtilGasEta.calculeBestOption(priceGasolina, priceEtanol);
                textResult.setText(recomendation);
            } else {
                Toast.makeText(GasEtaActivity.this, "Digite os dados obrigatórios!", Toast.LENGTH_LONG).show();
            }
        });

        btnClear.setOnClickListener(view -> {
            editGasolina.setText("");
            editEtanol.setText("");
        });

        btnSave.setOnClickListener(view -> {

        });

        btnDone.setOnClickListener(view -> {
            Toast.makeText(GasEtaActivity.this, "Volte Sempre", Toast.LENGTH_LONG).show();
            finish();
        });
    }
}
