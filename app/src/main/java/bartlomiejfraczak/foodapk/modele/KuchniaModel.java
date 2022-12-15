package bartlomiejfraczak.foodapk.modele;

import java.util.Arrays;
import java.util.List;

import bartlomiejfraczak.foodapk.encje.Kuchnia;

public class KuchniaModel {
    public static final Kuchnia AFRICAN = new Kuchnia("african", "African");
    public static final Kuchnia AMERICAN = new Kuchnia("american", "American");
    public static final Kuchnia BRITISH = new Kuchnia("british", "British");
    public static final Kuchnia CAJUN = new Kuchnia("cajun", "Cajun");
    public static final Kuchnia CARIBBEAN = new Kuchnia("caribbean", "Caribbean");
    public static final Kuchnia CHINESE = new Kuchnia("chinese", "Chinese");
    public static final Kuchnia EASTERN_EUROPEAN = new Kuchnia("eastern+european", "Eastern european");
    public static final Kuchnia EUROPEAN = new Kuchnia("european", "European");
    public static final Kuchnia FRENCH = new Kuchnia("french", "French");
    public static final Kuchnia GERMAN = new Kuchnia("german", "German");
    public static final Kuchnia GREEK = new Kuchnia("greek", "Greek");
    public static final Kuchnia INDIAN = new Kuchnia("indian", "Indian");
    public static final Kuchnia IRISH = new Kuchnia("irish", "Irish");
    public static final Kuchnia ITALIAN = new Kuchnia("italian", "Italian");
    public static final Kuchnia JAPANESE = new Kuchnia("japanese", "Japanese");
    public static final Kuchnia JEWISH = new Kuchnia("jewish", "Jewish");
    public static final Kuchnia KOREAN = new Kuchnia("korean", "Korean");
    public static final Kuchnia LATIN_AMERICAN = new Kuchnia("latin+american", "Latin american");
    public static final Kuchnia MEDITERRANEAN = new Kuchnia("mediterranean", "Mediterranean");
    public static final Kuchnia MEXICAN = new Kuchnia("mexican", "Mexican");
    public static final Kuchnia MIDDLE_EASTERN = new Kuchnia("middle+eastern", "Middle eastern");
    public static final Kuchnia NORDIC = new Kuchnia("nordic", "Nordic");
    public static final Kuchnia SOUTHERN = new Kuchnia("southern", "Southern");
    public static final Kuchnia SPANISH = new Kuchnia("spanish", "Spanish");
    public static final Kuchnia THAI = new Kuchnia("thai", "Thai");
    public static final Kuchnia VIETNAMESE = new Kuchnia("vietnamese", "Vietnamese");


    public static List<Kuchnia> getLista() {
        return Arrays.asList(AFRICAN, AMERICAN, BRITISH, CAJUN, CARIBBEAN, CHINESE, EASTERN_EUROPEAN, EUROPEAN, FRENCH, GERMAN, GREEK, INDIAN, IRISH, ITALIAN, JAPANESE, JEWISH, KOREAN, LATIN_AMERICAN, MEDITERRANEAN, MEXICAN, MIDDLE_EASTERN, NORDIC, SOUTHERN, SPANISH, THAI, VIETNAMESE);
    }

}
