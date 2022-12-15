package bartlomiejfraczak.foodapk.modele;

import java.util.Arrays;
import java.util.List;

import bartlomiejfraczak.foodapk.encje.Nietolerancja;

public class NietolerancjaModel {
    public static final Nietolerancja DAIRY = new Nietolerancja("dairy", "Dairy");
    public static final Nietolerancja EGG = new Nietolerancja("egg", "Egg");
    public static final Nietolerancja GLUTEN = new Nietolerancja("gluten", "Gluten");
    public static final Nietolerancja GRAIN = new Nietolerancja("grain", "Grain");
    public static final Nietolerancja PEANUT = new Nietolerancja("peanut", "Peanut");
    public static final Nietolerancja SEAFOOD = new Nietolerancja("seafood", "Seafood");
    public static final Nietolerancja SESAME = new Nietolerancja("sesame", "Sesame");
    public static final Nietolerancja SHELLFISH = new Nietolerancja("shellfish", "Shellfish");
    public static final Nietolerancja SOY = new Nietolerancja("soy", "Soy");
    public static final Nietolerancja SULFITE = new Nietolerancja("sulfite", "Sulfite");
    public static final Nietolerancja TREE_NUT = new Nietolerancja("tree+nut", "Tree Nut");
    public static final Nietolerancja WHEAT = new Nietolerancja("wheat", "Wheat");

    public static List<Nietolerancja> getLista() {
        return Arrays.asList(DAIRY, EGG, GLUTEN, GRAIN, PEANUT, SEAFOOD, SESAME, SHELLFISH, SOY, SULFITE, TREE_NUT, WHEAT);
    }

}
