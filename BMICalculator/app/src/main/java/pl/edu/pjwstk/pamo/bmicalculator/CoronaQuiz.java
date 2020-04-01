package pl.edu.pjwstk.pamo.bmicalculator;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import pl.edu.pjwstk.pamo.bmicalculator.quiz.CSVReader;
import pl.edu.pjwstk.pamo.bmicalculator.quiz.Question;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CoronaQuiz#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoronaQuiz extends Fragment {

    private static final int QUESTIONS = 6;
    private static final int ANSWERS = 4;
    private List<String> fileNameList;
    private List<String> quizAnswersList;
    private List<Question> questions;
    private String correctAnswer;
    private int totalGuesses; // number of guesses made
    private int correctAnswers; // number of correct guesses
    private SecureRandom random; // used to randomize the quiz

    private TextView questionNumberTextView; // shows current question #
    private ImageView imageView; // displays an image
    private TextView answerTextView; // displays correct answer
    private TextView questionTextView; // displays current question

    private Button[] answers;

    public CoronaQuiz() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CoronaQuiz.
     */
    public static CoronaQuiz newInstance() {
        return new CoronaQuiz();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_corona_quiz, container, false);

        initializeFields();
        findViews(view);
        questionNumberTextView.setText(getString(R.string.question, 1, QUESTIONS));

        resetQuiz();
        return view;
    }

    public void resetQuiz() {
        AssetManager assets = getActivity().getAssets();

        fileNameList.clear(); // empty list of image file names

        try {
            for (String path : Objects.requireNonNull(assets.list("quiz")))

                fileNameList.add(path.replace(".png", ""));
        } catch (IOException exception) {
            Log.e(this.getClass().getName(), "Error loading image file names", exception);
        }

        correctAnswers = 0; // reset the number of correct answers made
        totalGuesses = 0; // reset the total number of guesses the user made
        quizAnswersList.clear(); // clear prior list of quiz countries

        int counter = 1;

        while (counter <= QUESTIONS) {
            String filename = fileNameList.get(counter-1);
            quizAnswersList.add(filename); // add the file to the list
            ++counter;
        }

        loadNextQuestion(); // start the quiz
    }

    private void loadNextQuestion() {
        // get file name of the next flag and remove it from the list
        String nextImage = quizAnswersList.remove(0);
        correctAnswer = nextImage; // update the correct answer
        answerTextView.setText(""); // clear answerTextView

        // display current question number
        questionNumberTextView.setText(getString(
                R.string.question, (correctAnswers + 1), QUESTIONS));

        int questionNo = Integer.parseInt(nextImage.substring(nextImage.lastIndexOf("_") + 1));
        Question question = questions.get(questionNo - 1);

        questionTextView.setText(question.getQuestion());

        int i = 0;
        for (Map.Entry<String, Boolean> answer : question.getAnswers().entrySet()) {
            answers[i].setEnabled(true);
            answers[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            answers[i].setText(answer.getKey());
            if (answer.getValue()) {
                correctAnswer = answer.getKey();
            }
            i++;
        }

        // use AssetManager to load next image from assets folder
        AssetManager assets = getActivity().getAssets();

        // get an InputStream to the asset representing the next flag
        // and try to use the InputStream
        try (InputStream stream =
                     assets.open("quiz" + "/" + nextImage + ".png")) {
            // load the asset as a Drawable and display on the flagImageView
            Drawable flag = Drawable.createFromStream(stream, nextImage);
            imageView.setImageDrawable(flag);

        } catch (IOException exception) {
            Log.e(this.getClass().getName(), "Error loading " + nextImage, exception);
        }

    }

    private void findViews(View view) {
        imageView = view.findViewById(R.id.flagImageView);
        answerTextView = view.findViewById(R.id.answerTextView);
        questionTextView = view.findViewById(R.id.questionTextView);
        questionNumberTextView = view.findViewById(R.id.questionNumberTextView);

        answers = new Button[ANSWERS];
        answers[0] = view.findViewById(R.id.button1);
        answers[1] = view.findViewById(R.id.button2);
        answers[2] = view.findViewById(R.id.button3);
        answers[3] = view.findViewById(R.id.button4);

        for (Button button : answers) {
            button.setOnClickListener(guessButtonListener);
        }
    }

    private void initializeFields() {
        fileNameList = new ArrayList<>();
        quizAnswersList = new ArrayList<>();
        questions = CSVReader.getQuestions(getResources().openRawResource(R.raw.quiz));
    }

    // called when a guess Button is touched
    private View.OnClickListener guessButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button guessButton = ((Button) v);
            String guess = guessButton.getText().toString();
            String answer = correctAnswer;
            ++totalGuesses; // increment number of guesses the user has made

            if (guess.equals(answer)) { // if the guess is correct
                ++correctAnswers; // increment the number of correct answers

                // display correct answer in green text
                answerTextView.setText(answer);
                answerTextView.setTextColor(getResources().getColor(R.color.correctAnswer));

                disableButtons(); // disable all guess Buttons

                // if the user has correctly answered all questions
                if (correctAnswers == QUESTIONS) {
                    answerTextView.setText(getString(R.string.results,
                            totalGuesses,
                            (correctAnswers * (100 / (double) totalGuesses))));
                    answerTextView.setTextColor(getResources().getColor(R.color.correctAnswer));
                } else {
                    try {
                        Thread.sleep(250);
                        loadNextQuestion();
                    } catch (InterruptedException e) {
                        Log.e(this.getClass().getName(), e.getMessage());
                    }

                }
            } else {
                // display "Incorrect!" in red
                answerTextView.setText(R.string.incorrect_answer);
                answerTextView.setTextColor(getResources().getColor(R.color.wrongAnswer));
                guessButton.setEnabled(false); // disable incorrect answer
                guessButton.setTextColor(getResources().getColor(R.color.wrongAnswer));
            }
        }

    };

    // utility method that disables all answer Buttons
    private void disableButtons() {
        for (Button button : answers) {
            button.setEnabled(false);
        }
    }

}
