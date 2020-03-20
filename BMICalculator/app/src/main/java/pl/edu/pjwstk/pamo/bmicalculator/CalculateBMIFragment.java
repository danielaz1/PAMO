package pl.edu.pjwstk.pamo.bmicalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class CalculateBMIFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCalculateButton(view);
    }

    private void setCalculateButton(@NonNull View view) {
        final EditText heightInput = view.findViewById(R.id.height_input);
        final EditText massInput = view.findViewById(R.id.mass_input);
        final TextView res = view.findViewById(R.id.textviev_result);

        view.findViewById(R.id.button_calculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String calculated = BMIToString(massInput.getText().toString(), heightInput.getText().toString());
                res.setText(calculated);
            }
        });
    }

    private double calculate(Double mass, Double height) {
        double heightM = height / 100;
        return mass / (heightM * heightM);
    }

    private String BMIToString(String mass, String height) {
        double calculated = calculate(Double.valueOf(mass), Double.valueOf(height));
        return String.valueOf(calculated);
    }
}
