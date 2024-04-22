package com.victorashino.appgaseta.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.victorashino.appgaseta.R;
import com.victorashino.appgaseta.controller.FuelController;
import com.victorashino.appgaseta.databinding.ActivityGasetaBinding;
import com.victorashino.appgaseta.model.Fuel;
import com.victorashino.appgaseta.utils.UtilGasEta;

import java.util.List;

public class GasEtaActivity extends AppCompatActivity {

    FuelController controller;

    Fuel gasolineFuel;
    Fuel ethanolFuel;

    double priceGasoline;
    double priceEthanol;
    String recomendation;

    List<Fuel> data;

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

        controller = new FuelController(GasEtaActivity.this);

        data = controller.getListData();

        Fuel altObject = data.get(1);

        altObject.setFuel("**ETHANOL**");
        altObject.setFuelPrice(4.97);
        altObject.setRecommendation("**ABASTECER COM GASOLINA**");
        controller.modify(altObject);

        controller.delete(43);

        binding.btnCalcule.setOnClickListener(view -> {

            boolean isDataOk = true;

            if (TextUtils.isEmpty(binding.editGasoline.getText())) {
                binding.editGasoline.setError("* Obrigatorio");
                binding.editGasoline.requestFocus();
                isDataOk = false;
            }

            if (TextUtils.isEmpty(binding.editEthanol.getText())) {
                binding.editEthanol.setError("* Obrigatorio");
                binding.editEthanol.requestFocus();
                isDataOk = false;
            }

            if (isDataOk) {
                priceGasoline = Double.parseDouble(binding.editGasoline.getText().toString());
                priceEthanol = Double.parseDouble(binding.editEthanol.getText().toString());
                recomendation = UtilGasEta.calculeBestOption(priceGasoline, priceEthanol);
                binding.textResult.setText(recomendation);

                binding.btnSave.setEnabled(true);
            } else {
                Toast.makeText(GasEtaActivity.this, "Digite os dados obrigatórios!", Toast.LENGTH_LONG).show();
                binding.btnSave.setEnabled(false);
            }
        });

        binding.btnClear.setOnClickListener(view -> {
            binding.editGasoline.setText("");
            binding.editEthanol.setText("");

            binding.btnSave.setEnabled(false);

            controller.clear();
        });

        binding.btnSave.setOnClickListener(view -> {

            ethanolFuel = new Fuel();
            gasolineFuel = new Fuel();

            gasolineFuel.setFuel("Gasoline");
            gasolineFuel.setFuelPrice(priceGasoline);

            ethanolFuel.setFuel("Ethanol");
            ethanolFuel.setFuelPrice(priceEthanol);

            gasolineFuel.setRecommendation(UtilGasEta.calculeBestOption(priceGasoline, priceEthanol));
            ethanolFuel.setRecommendation(UtilGasEta.calculeBestOption(priceGasoline, priceEthanol));

            controller.save(gasolineFuel);
            controller.save(ethanolFuel);
        });

        binding.btnDone.setOnClickListener(view -> {
            Toast.makeText(GasEtaActivity.this, "Volte Sempre", Toast.LENGTH_LONG).show();
            finish();
        });
    }
}
