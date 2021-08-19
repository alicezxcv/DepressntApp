package com.example.app;

public class PHQ9_Questionnaire {

    public static String questionnaire [] = {
            "Little interest or pleasure in doing things?",
            "Feeling down, depressed, or hopeless?",
            "Trouble falling or staying asleep, or sleeping too much?",
            "Feeling tired or having little energy?",
            "Poor appetite or overeating?",
            "Feeling bad about yourself — or that you are a failure or have let yourself or your family down?",
            "Trouble concentrating on things, such as reading the newspaper or watching television?",
            "Moving or speaking so slowly that other people could have noticed?\nOr so fidgety or restless that you have been moving a lot more than usual?",
            "Thoughts that you would be better off dead, or thoughts of hurting yourself in some way?"
    };

    public String getQuestion(int a) {
        String question = questionnaire[a];
        return question;
    };
}
