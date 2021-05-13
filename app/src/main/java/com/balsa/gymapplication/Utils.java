package com.balsa.gymapplication;

import java.util.ArrayList;

public class Utils {
    private static ArrayList<Training> allTrainings;
    private static ArrayList<TrainingPlan> plans;

    public static ArrayList<Training> getAllTrainings() {
        return allTrainings;
    }

    public static ArrayList<TrainingPlan> getPlans() {
        return plans;
    }

    public static boolean removePlan(TrainingPlan plan) {
        return plans.remove(plan);
    }

    public static boolean addPlan(TrainingPlan plan) {
        if (plans == null) {
            plans = new ArrayList<>();
        }
        return plans.add(plan);
    }

    public static void initTrainings() {
        if (allTrainings == null) {
            allTrainings = new ArrayList<>();
        }

        Training pushUp = new Training(1, "Push up", "An excercise that helps and builds your chest,it's an excercise where you face floor and push your body away from flor and repeat that",
                "industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                "https://static.vecteezy.com/system/resources/previews/000/162/096/non_2x/man-doing-push-up-vector-illustration.jpg");
        allTrainings.add(pushUp);

        Training squat = new Training(2, "Squat", "An excercise that helps and builds your legs.",
                "industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
                , "https://i.pinimg.com/originals/e4/43/66/e44366f034aa014b057c0a33364c0090.jpg");
        allTrainings.add(squat);

        Training legPress = new Training(3, "Leg press", "An excercise that helps and builds your legs what to say ...lorem ipsum :D.",
                "industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
                , "https://barbend.com/wp-content/uploads/2019/04/Leg-Press-Guide.jpg");
        allTrainings.add(legPress);

        Training pullUps = new Training(4, "Pull up", "An excercise that helps and builds your back.",
                "industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
                , "https://images.livemint.com/img/2021/01/05/1140x641/iStock-1016624158_1609837023485_1609837041210.jpg");
        allTrainings.add(pullUps);

        Training pectorales = new Training(5, "Pectorals", "An excercise that helps and builds your Chest.",
                "industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
                , "https://julienquaglierini.com/wp-content/uploads/2017/09/Comment-muscler-le-haut-des-pectoraux.jpg");
        allTrainings.add(pectorales);
    }
}
