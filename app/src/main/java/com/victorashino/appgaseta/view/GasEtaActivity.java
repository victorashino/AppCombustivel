package com.victorashino.appgaseta.view;

import android.os.Bundle;
import android.text.TextUtils;
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
import com.victorashino.appgaseta.model.Fuel;
import com.victorashino.appgaseta.utils.UtilGasEta;

public class GasEtaActivity extends AppCompatActivity {

    Fuel gasolineFuel;
    Fuel ethanolFuel;

    EditText editGasoline;
    EditText editEthanol;

    TextView textResult;

    Button btnCalcule;
    Button btnClear;
    Button btnSave;
    Button btnDone;

    double priceGasoline;
    double priceEthanol;
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

        editGasoline = binding.editGasoline;
        editEthanol = binding.editEthanol;

        textResult = binding.textResult;

        btnCalcule = binding.btnCalcule;
        btnClear = binding.btnClear;
        btnSave = binding.btnSave;
        btnDone = binding.btnDone;

        btnCalcule.setOnClickListener(view -> {

            boolean isDataOk = true;

            if (TextUtils.isEmpty(editGasoline.getText())) {
                editGasoline.setError("* Obrigatorio");
                editGasoline.requestFocus();
                isDataOk = false;
            }

            if (TextUtils.isEmpty(editEthanol.getText())) {
                editEthanol.setError("* Obrigatorio");
                editEthanol.requestFocus();
                isDataOk = false;
            }

            if (isDataOk) {
                priceGasoline = Double.parseDouble(editGasoline.getText().toString());
                priceEthanol = Double.parseDouble(editEthanol.getText().toString());
                recomendation = UtilGasEta.calculeBestOption(priceGasoline, priceEthanol);
                textResult.setText(recomendation);
            } else {
                Toast.makeText(GasEtaActivity.this, "Digite os dados obrigatórios!", Toast.LENGTH_LONG).show();
            }
        });

        btnClear.setOnClickListener(view -> {
            editGasoline.setText("");
            editEthanol.setText("");
        });

        btnSave.setOnClickListener(view -> {

            // TODO: Desabilitar o botão salvar
            ethanolFuel = new Fuel();
            gasolineFuel = new Fuel();

            gasolineFuel.setFuel("Gasoline");
            gasolineFuel.setFuelPrice(priceGasoline);

            ethanolFuel.setFuel("Ethano");
            ethanolFuel.setFuelPrice(priceEthanol);

            gasolineFuel.setRecomendation(UtilGasEta.calculeBestOption(priceGasoline, priceEthanol));
            ethanolFuel.setRecomendation(UtilGasEta.calculeBestOption(priceGasoline, priceEthanol));
        });

        btnDone.setOnClickListener(view -> {
            Toast.makeText(GasEtaActivity.this, "Volte Sempre", Toast.LENGTH_LONG).show();
            finish();
        });
    }
}
