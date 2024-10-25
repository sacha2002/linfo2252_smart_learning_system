package models.Exercises;

import models.Rank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExerciseData {

    private static final Map<String,List<Exercise>> exerciseLookup = new HashMap<>();

    // Separate lists for each language and rank
    private static final List<Exercise> englishBronzeExercises = new ArrayList<>();
    private static final List<Exercise> englishSilverExercises = new ArrayList<>();
    private static final List<Exercise> englishGoldExercises = new ArrayList<>();

    private static final List<Exercise> spanishBronzeExercises = new ArrayList<>();
    private static final List<Exercise> spanishSilverExercises = new ArrayList<>();
    private static final List<Exercise> spanishGoldExercises = new ArrayList<>();

    private static final List<Exercise> frenchBronzeExercises = new ArrayList<>();
    private static final List<Exercise> frenchSilverExercises = new ArrayList<>();
    private static final List<Exercise> frenchGoldExercises = new ArrayList<>();

    static {
        // English Exercises
        englishBronzeExercises.add(new GrammarExercise("What is the past tense of 'go'?", Rank.BRONZE, "went", "It's not 'goed'."));
        englishBronzeExercises.add(new GrammarExercise("Fill in the blank: 'She ___ (be) my best friend.'", Rank.BRONZE, "is", "Think of the present tense."));
        exerciseLookup.putIfAbsent("englishBronze",englishBronzeExercises);

        englishSilverExercises.add(new ListeningExercise("Listen to the following sentence: 'The quick brown fox jumps over the lazy dog.'", Rank.SILVER, "The quick brown fox jumps over the lazy dog.", "Pay attention to the pronunciation.", "path/to/english/audio1.mp3"));
        englishSilverExercises.add(new ListeningExercise("Listen to: 'Can you believe it?' and answer: What is the question about?", Rank.SILVER, "Can you believe it?", "Focus on the intonation.", "path/to/english/audio2.mp3"));
        exerciseLookup.putIfAbsent("englishSilver",englishSilverExercises);

        englishGoldExercises.add(new GrammarExercise("Identify the error in this sentence: 'He don't like ice cream.'", Rank.GOLD, "doesn't", "Think about subject-verb agreement."));
        englishGoldExercises.add(new GrammarExercise("Correct the sentence: 'They goes to school every day.'", Rank.GOLD, "go", "Check the subject-verb agreement."));
        exerciseLookup.putIfAbsent("englishGold",englishGoldExercises);

        // Spanish Exercises
        spanishBronzeExercises.add(new GrammarExercise("¿Cuál es el pasado del verbo 'ir'?", Rank.BRONZE, "fue", "Es un verbo irregular."));
        spanishBronzeExercises.add(new GrammarExercise("Completa la frase: 'Nosotros ___ (vivir) en Madrid.'", Rank.BRONZE, "vivimos", "Piensa en el presente."));
        exerciseLookup.putIfAbsent("spanishBronze",spanishBronzeExercises);

        spanishSilverExercises.add(new ListeningExercise("Escucha la siguiente frase: 'El zorro marrón rápido salta sobre el perro perezoso.'", Rank.SILVER, "El zorro marrón rápido salta sobre el perro perezoso.", "Presta atención a la pronunciación.", "path/to/spanish/audio1.mp3"));
        spanishSilverExercises.add(new ListeningExercise("Escucha: '¿Te gustaría ir al cine?' y responde: ¿Qué pregunta se hace?", Rank.SILVER, "¿Te gustaría ir al cine?", "Escucha la entonación.", "path/to/spanish/audio2.mp3"));
        exerciseLookup.putIfAbsent("spanishSilver",spanishSilverExercises);

        spanishGoldExercises.add(new GrammarExercise("Encuentra el error en esta frase: 'Él no gusta el helado.'", Rank.GOLD, "le gusta", "Recuerda que 'gustar' requiere un pronombre."));
        spanishGoldExercises.add(new GrammarExercise("Corrige la frase: 'Ellos come pizza todos los días.'", Rank.GOLD, "comen", "Revisa el acuerdo sujeto-verbo."));
        exerciseLookup.putIfAbsent("spanishGold",spanishGoldExercises);

        // French Exercises
        frenchBronzeExercises.add(new GrammarExercise("Quel est le passé du verbe 'aller'?", Rank.BRONZE, "est allé", "C'est un verbe irrégulier."));
        frenchBronzeExercises.add(new GrammarExercise("Complétez la phrase: 'Nous ___ (être) en France.'", Rank.BRONZE, "sommes", "Pensez au présent."));
        exerciseLookup.putIfAbsent("frenchBronze",frenchBronzeExercises);

        frenchSilverExercises.add(new ListeningExercise("Écoutez la phrase suivante: 'Le rapide renard brun saute par-dessus le chien paresseux.'", Rank.SILVER, "Le rapide renard brun saute par-dessus le chien paresseux.", "Faites attention à la prononciation.", "path/to/french/audio1.mp3"));
        frenchSilverExercises.add(new ListeningExercise("Écoutez: 'Aimeriez-vous aller au cinéma?' et répondez: Quelle question est posée?", Rank.SILVER, "Aimeriez-vous aller au cinéma?", "Écoutez l'intonation.", "path/to/french/audio2.mp3"));
        exerciseLookup.putIfAbsent("frenchSilver",frenchSilverExercises);

        frenchGoldExercises.add(new GrammarExercise("Trouvez l'erreur dans cette phrase: 'Il n'aime pas la glace.'", Rank.GOLD, "n'aime pas", "Pensez à l'accord sujet-verbe."));
        frenchGoldExercises.add(new GrammarExercise("Corrigez la phrase: 'Ils mange une pizza chaque jour.'", Rank.GOLD, "mangent", "Vérifiez l'accord sujet-verbe."));
        exerciseLookup.putIfAbsent("frenchGold",frenchGoldExercises);
    }

    // Getters for Exercises by Language and Rank
    public static List<Exercise> getEnglishBronzeExercises() {
        return new ArrayList<>(englishBronzeExercises);
    }

    public static List<Exercise> getEnglishSilverExercises() {
        return new ArrayList<>(englishSilverExercises);
    }

    public static List<Exercise> getEnglishGoldExercises() {
        return new ArrayList<>(englishGoldExercises);
    }

    public static List<Exercise> getSpanishBronzeExercises() {
        return new ArrayList<>(spanishBronzeExercises);
    }

    public static List<Exercise> getSpanishSilverExercises() {
        return new ArrayList<>(spanishSilverExercises);
    }

    public static List<Exercise> getSpanishGoldExercises() {
        return new ArrayList<>(spanishGoldExercises);
    }

    public static List<Exercise> getFrenchBronzeExercises() {
        return new ArrayList<>(frenchBronzeExercises);
    }

    public static List<Exercise> getFrenchSilverExercises() {
        return new ArrayList<>(frenchSilverExercises);
    }

    public static List<Exercise> getFrenchGoldExercises() {
        return new ArrayList<>(frenchGoldExercises);
    }

    public static Map<String,List<Exercise>> getExerciseLookup(){
        return exerciseLookup;
    }
}
