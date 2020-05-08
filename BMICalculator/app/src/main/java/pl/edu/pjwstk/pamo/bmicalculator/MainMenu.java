package pl.edu.pjwstk.pamo.bmicalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMenu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMenu extends Fragment {

    public MainMenu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MainMenu.
     */

    public static MainMenu newInstance() {
        MainMenu fragment = new MainMenu();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.button_bmi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MainMenu.this)
                        .navigate(R.id.action_mainMenu_to_calculateBMI);
            }
        });

        view.findViewById(R.id.button_calories).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MainMenu.this)
                        .navigate(R.id.action_mainMenu_to_calculateCalories);

            }
        });

        view.findViewById(R.id.button_what_to_eat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MainMenu.this)
                        .navigate(R.id.action_mainMenu_to_coronaRecipe);

            }
        });

        view.findViewById(R.id.button_corona_quiz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MainMenu.this)
                        .navigate(R.id.action_mainMenu_to_coronaQuiz);

            }
        });

        view.findViewById(R.id.button_corona_statistics).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MainMenu.this)
                        .navigate(R.id.action_mainMenu_to_coronaStats);

            }
        });


    }

}
