package pl.edu.pjwstk.pamo.bmicalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {

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

        final EditText heightInput = view.findViewById(R.id.weight_input);
        final EditText massInput = view.findViewById(R.id.mass_input);
        final TextView res = view.findViewById(R.id.textviev_result);

        view.findViewById(R.id.button_calculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double calculatedResult = calculate(Double.valueOf(massInput.getText().toString()),
                        Double.valueOf(heightInput.getText().toString()));

                res.setText(String.valueOf(calculatedResult));
            }
        });
    }

    private double calculate(Double mass, Double height) {
        double heightM = height / 100;
        return mass / (heightM * heightM);
    }
}
