package pl.edu.pjwstk.pamo.bmicalculator.quiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {

    public static List<Question> getQuestions(InputStream file) {
        String line = "";
        String cvsSplitBy = ",";

        List<Question> questions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file))) {

            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] strings = line.split(cvsSplitBy);
                Question question = new Question();
                Map<String, Boolean> answers = new HashMap<>();
                question.setId(Integer.parseInt(strings[0]));
                question.setQuestion(strings[1]);
                for(int i=0; i<8; i=i+2)
                    answers.put(strings[i+2], Boolean.valueOf(strings[i+3]));
                question.setAnswers(answers);
                questions.add(question);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return questions;
    }

}
