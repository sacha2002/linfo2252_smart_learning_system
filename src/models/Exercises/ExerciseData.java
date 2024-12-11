package models.Exercises;

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
    private static final List<Exercise> englishPlatinumExercises = new ArrayList<>();
    private static final List<Exercise> englishDiamondExercises = new ArrayList<>();

    private static final List<Exercise> spanishBronzeExercises = new ArrayList<>();
    private static final List<Exercise> spanishSilverExercises = new ArrayList<>();
    private static final List<Exercise> spanishGoldExercises = new ArrayList<>();
    private static final List<Exercise> spanishPlatinumExercises = new ArrayList<>();
    private static final List<Exercise> spanishDiamondExercises = new ArrayList<>();

    private static final List<Exercise> frenchBronzeExercises = new ArrayList<>();
    private static final List<Exercise> frenchSilverExercises = new ArrayList<>();
    private static final List<Exercise> frenchGoldExercises = new ArrayList<>();
    private static final List<Exercise> frenchPlatinumExercises = new ArrayList<>();
    private static final List<Exercise> frenchDiamondExercises = new ArrayList<>();

    static {
        // English Exercises
        englishBronzeExercises.add(new GrammarExercise("What is the past tense of 'go'?", Rank.BRONZE, "went", "It's not 'goed'."));
        englishBronzeExercises.add(new GrammarExercise("Fill in the blank: 'She ___ (be) my best friend.'", Rank.BRONZE, "is", "Think of the present tense."));
        exerciseLookup.putIfAbsent("ENGLISHBronze",englishBronzeExercises);

        englishSilverExercises.add(new ListeningExercise("Listen to the following sentence: 'The quick brown fox jumps over the lazy dog.'", Rank.SILVER, "The quick brown fox jumps over the lazy dog.", "Pay attention to the pronunciation.", "path/to/english/audio1.mp3"));
        englishSilverExercises.add(new ListeningExercise("Listen to: 'Can you believe it?' and answer: What is the question about?", Rank.SILVER, "Can you believe it?", "Focus on the intonation.", "path/to/english/audio2.mp3"));
        exerciseLookup.putIfAbsent("ENGLISHSilver",englishSilverExercises);

        englishGoldExercises.add(new GrammarExercise("Identify the error in this sentence: 'He don't like ice cream.'", Rank.GOLD, "doesn't", "Think about subject-verb agreement."));
        englishGoldExercises.add(new GrammarExercise("Correct the sentence: 'They goes to school every day.'", Rank.GOLD, "go", "Check the subject-verb agreement."));
        exerciseLookup.putIfAbsent("ENGLISHGold",englishGoldExercises);

        englishPlatinumExercises.add(new GrammarExercise("slang for sausage", Rank.PLATINUM, "glizzy", "meme word"));
        englishPlatinumExercises.add(new GrammarExercise("Transform this sentence into passive voice: 'They completed the project on time.'", Rank.PLATINUM, "The project was completed on time.", "Focus on the verb structure."));
        exerciseLookup.putIfAbsent("ENGLISHPlatinum", englishPlatinumExercises);

        englishDiamondExercises.add(new GrammarExercise("whats updog?", Rank.DIAMOND, "the ceiling", "whats up"));
        englishDiamondExercises.add(new ListeningExercise("Listen to the audio and summarize the main idea.", Rank.DIAMOND, "Main idea summary", "Pay attention to details.", "path/to/english/diamond_audio1.mp3"));
        exerciseLookup.putIfAbsent("ENGLISHDiamond", englishDiamondExercises);

        // Spanish Exercises
        spanishBronzeExercises.add(new GrammarExercise("¿Cuál es el pasado del verbo 'ir'?", Rank.BRONZE, "fue", "Es un verbo irregular."));
        spanishBronzeExercises.add(new GrammarExercise("Completa la frase: 'Nosotros ___ (vivir) en Madrid.'", Rank.BRONZE, "vivimos", "Piensa en el presente."));
        exerciseLookup.putIfAbsent("SPANISHBronze",spanishBronzeExercises);

        spanishSilverExercises.add(new ListeningExercise("Escucha la siguiente frase: 'El zorro marrón rápido salta sobre el perro perezoso.'", Rank.SILVER, "El zorro marrón rápido salta sobre el perro perezoso.", "Presta atención a la pronunciación.", "path/to/spanish/audio1.mp3"));
        spanishSilverExercises.add(new ListeningExercise("Escucha: '¿Te gustaría ir al cine?' y responde: ¿Qué pregunta se hace?", Rank.SILVER, "¿Te gustaría ir al cine?", "Escucha la entonación.", "path/to/spanish/audio2.mp3"));
        exerciseLookup.putIfAbsent("SPANISHSilver",spanishSilverExercises);

        spanishGoldExercises.add(new GrammarExercise("Encuentra el error en esta frase: 'Él no gusta el helado.'", Rank.GOLD, "le gusta", "Recuerda que 'gustar' requiere un pronombre."));
        spanishGoldExercises.add(new GrammarExercise("Corrige la frase: 'Ellos come pizza todos los días.'", Rank.GOLD, "comen", "Revisa el acuerdo sujeto-verbo."));
        exerciseLookup.putIfAbsent("SPANISHGold",spanishGoldExercises);

        spanishPlatinumExercises.add(new GrammarExercise("Reescribe esta oración en voz pasiva: 'Ellos construyeron el edificio en un año.'", Rank.PLATINUM, "El edificio fue construido en un año.", "Céntrate en la estructura del verbo."));
        spanishPlatinumExercises.add(new GrammarExercise("Quien es el mas grande??", Rank.PLATINUM, "BOCA", "la mitad mas uno"));
        exerciseLookup.putIfAbsent("SPANISHPlatinum", spanishPlatinumExercises);

        spanishDiamondExercises.add(new GrammarExercise("quien gambeteo a 4 ingleses?", Rank.DIAMOND, "maradona", "la mano de dios"));
        spanishDiamondExercises.add(new ListeningExercise("Escucha el audio y resume la idea principal.", Rank.DIAMOND, "Resumen de la idea principal", "Presta atención a los detalles.", "path/to/spanish/diamond_audio1.mp3"));
        exerciseLookup.putIfAbsent("SPANISHDiamond", spanishDiamondExercises);

        // French Exercises
        frenchBronzeExercises.add(new GrammarExercise("Quel est le passé du verbe 'aller'?", Rank.BRONZE, "est allé", "C'est un verbe irrégulier."));
        frenchBronzeExercises.add(new GrammarExercise("Complétez la phrase: 'Nous ___ (être) en France.'", Rank.BRONZE, "sommes", "Pensez au présent."));
        exerciseLookup.putIfAbsent("FRENCHBronze",frenchBronzeExercises);

        frenchSilverExercises.add(new ListeningExercise("Écoutez la phrase suivante: 'Le rapide renard brun saute par-dessus le chien paresseux.'", Rank.SILVER, "Le rapide renard brun saute par-dessus le chien paresseux.", "Faites attention à la prononciation.", "path/to/french/audio1.mp3"));
        frenchSilverExercises.add(new ListeningExercise("Écoutez: 'Aimeriez-vous aller au cinéma?' et répondez: Quelle question est posée?", Rank.SILVER, "Aimeriez-vous aller au cinéma?", "Écoutez l'intonation.", "path/to/french/audio2.mp3"));
        exerciseLookup.putIfAbsent("FRENCHSilver",frenchSilverExercises);

        frenchGoldExercises.add(new GrammarExercise("Trouvez l'erreur dans cette phrase: 'Il n'aime pas la glace.'", Rank.GOLD, "n'aime pas", "Pensez à l'accord sujet-verbe."));
        frenchGoldExercises.add(new GrammarExercise("Corrigez la phrase: 'Ils mange une pizza chaque jour.'", Rank.GOLD, "mangent", "Vérifiez l'accord sujet-verbe."));
        exerciseLookup.putIfAbsent("FRENCHGold",frenchGoldExercises);

        frenchPlatinumExercises.add(new GrammarExercise("Complétez la phrase: 'Nous ___ (être) en France.'", Rank.PLATINUM, "sommes", "Pensez au présent."));
        frenchPlatinumExercises.add(new GrammarExercise("Transformez cette phrase en voix passive : 'Ils ont terminé le projet à temps.'", Rank.PLATINUM, "Le projet a été terminé à temps.", "Concentrez-vous sur la structure verbale."));
        exerciseLookup.putIfAbsent("FRENCHPlatinum", frenchPlatinumExercises);

        frenchDiamondExercises.add(new ListeningExercise("Écoutez: 'Aimeriez-vous aller au cinéma?' et répondez: Quelle question est posée?", Rank.DIAMOND, "Aimeriez-vous aller au cinéma?", "Écoutez l'intonation.", "path/to/french/audio2.mp3"));
        frenchDiamondExercises.add(new ListeningExercise("Écoutez l'audio et résumez l'idée principale.", Rank.DIAMOND, "Résumé de l'idée principale", "Faites attention aux détails.", "path/to/french/diamond_audio1.mp3"));
        exerciseLookup.putIfAbsent("FRENCHDiamond", frenchDiamondExercises);
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

    public static List<Exercise> getAllEnglishExercises() {
        List<Exercise> allEnglishExercises = new ArrayList<>();
        allEnglishExercises.addAll(englishBronzeExercises);
        allEnglishExercises.addAll(englishSilverExercises);
        allEnglishExercises.addAll(englishGoldExercises);
        allEnglishExercises.addAll(englishPlatinumExercises);
        allEnglishExercises.addAll(englishDiamondExercises);
        return allEnglishExercises;
    }

    public static List<Exercise> getAllSpanishExercises() {
        List<Exercise> allSpanishExercises = new ArrayList<>();
        allSpanishExercises.addAll(spanishBronzeExercises);
        allSpanishExercises.addAll(spanishSilverExercises);
        allSpanishExercises.addAll(spanishGoldExercises);
        allSpanishExercises.addAll(spanishPlatinumExercises);
        allSpanishExercises.addAll(spanishDiamondExercises);
        return allSpanishExercises;
    }

    public static List<Exercise> getAllFrenchExercises() {
        List<Exercise> allFrenchExercises = new ArrayList<>();
        allFrenchExercises.addAll(frenchBronzeExercises);
        allFrenchExercises.addAll(frenchSilverExercises);
        allFrenchExercises.addAll(frenchGoldExercises);
        allFrenchExercises.addAll(frenchPlatinumExercises);
        allFrenchExercises.addAll(frenchDiamondExercises);
        return allFrenchExercises;
    }

    public static List<Exercise> getRankExercices(String course, String rank){
        return exerciseLookup.get(course+rank);

    }


    public static Map<String,List<Exercise>> getExerciseLookup(){
        return exerciseLookup;
    }
}
