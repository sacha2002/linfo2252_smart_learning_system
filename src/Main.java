import models.Course;
import models.English;
import models.French;
import models.Spanish;

import java.io.InputStreamReader;
import java.util.Scanner;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String course;
        Course AvailableCourse = new Course();

        // Boucle infinie pour demander l'entrée
        while (true) {
            System.out.print("Which courses do you want to learn?, type exit to quit     ");
            course = scanner.nextLine();

            // Vérifier si l'utilisateur souhaite quitter
            if (course.equalsIgnoreCase("exit")) {
                System.out.println("program finished!");
                break;
            }
            if (!AvailableCourse.getMyquestions().contains(course)){
                System.out.println("This course is not available!");

            }
            else {
                if (course.equals("ENGLISH")){
                    English english = new English();
                } else if (course.equals("SPANISH")) {
                    Spanish spanish = new Spanish();
                }
                else{
                    French french = new French();
                }
            }



        }

        scanner.close();

    }
}