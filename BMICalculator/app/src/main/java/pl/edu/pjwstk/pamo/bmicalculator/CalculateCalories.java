package pl.edu.pjwstk.pamo.bmicalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class CalculateCalories extends Fragment {

    enum Gender { WOMAN, MAN }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculate_calories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCalculateButton(view);
        
    }

    private void setCalculateButton(@NonNull View view) {
        final EditText heightInput = view.findViewById(R.id.editText_height);
        final EditText massInput = view.findViewById(R.id.editText_mass);
        final EditText ageInput = view.findViewById(R.id.editText_age);
        final RadioButton manRadio =  view.findViewById(R.id.radioButton_man);
        final RadioButton womanRadio =  view.findViewById(R.id.radioButton_woman);
        final TextView res = view.findViewById(R.id.textView_result);

        final Gender gender = manRadio.isChecked() ? Gender.MAN : Gender.WOMAN;

        view.findViewById(R.id.button_calculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String calculated = kcalToString(massInput.getText().toString(), heightInput.getText().toString(), ageInput.getText().toString(), gender);
                res.setText(calculated);
            }
        });
    }

    private String kcalToString(String massString, String heightString, String ageString, Gender gender) {
        double mass = Double.parseDouble(massString);
        double height = Double.parseDouble(heightString);
        int age = Integer.parseInt(ageString);

        return String.valueOf(calculate(mass, height, age, gender));
    }

    private double calculate(double mass, double height, int age, Gender gender) {
        if (gender.equals(Gender.MAN)) {
            return 66.47 + 13.7*mass + 5.0*height - 6.76*age;
        } else {
            return 655.1 + 9.567*mass + 1.85*height - 4.68*age;
        }
    }
}
