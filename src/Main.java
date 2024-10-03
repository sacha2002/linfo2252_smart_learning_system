import models.Exercises.Exercise;
import models.Exercises.ListeningExercise;
import models.Rank;
import models.courses.Course;
import models.courses.English;
import models.courses.French;
import models.courses.Spanish;

import java.util.*;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String course;

        Map<String,Course> available_courses =  new HashMap<>(); // this logic should be in the controller i think

        List<Exercise> spanishGrammarExercises = List.of(
                new Exercise("¿Cuál es la conjugación correcta del verbo 'ser' en presente?", Rank.GOLD),
                new Exercise("¿Cuándo se utiliza el subjuntivo en español?",  Rank.GOLD),
                new Exercise("Corrige la oración: 'El niño jugaba mientras sus padres cocinaban'.",  Rank.GOLD)
        );

        List<Exercise> frenchListeningExercises = List.of(
                new ListeningExercise("Écoute et répète la phrase suivante: 'Bonjour, comment ça va?'", Rank.GOLD, "greeting.mp3"),
                new ListeningExercise("Écoute le dialogue et identifie le nombre de personnes présentes.", Rank.GOLD, "conversation.mp3"),
                new ListeningExercise("Écoute ce discours et réponds aux questions sur le contenu.", Rank.GOLD, "speech.mp3")
        );


        available_courses.putIfAbsent("spanish",new Spanish(1200,spanishGrammarExercises));
        available_courses.putIfAbsent("french",new French(1200,frenchListeningExercises));

        // Boucle infinie pour demander l'entrée
        while (true) {
            System.out.print("Which courses do you want to learn?, type exit to quit     ");
            course = scanner.nextLine();

            // Vérifier si l'utilisateur souhaite quitter
            if (course.equalsIgnoreCase("exit")) {
                System.out.println("program finished!");
                break;
            } else {

               Course selectedCourse = available_courses.get(course);

                if (selectedCourse != null) {
                    // Course found, print exercises
                    System.out.println("Exercises for " + selectedCourse.getName() + ":");
                    for (Exercise exercise : selectedCourse.getExercises()) {
                        System.out.println("- " + exercise.getText());
                    }
                } else {
                    System.out.println("Sorry, that course is not available.");
                }
            }



        }

        scanner.close();

    }
}