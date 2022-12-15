package bartlomiejfraczak.foodapk.modele;

import java.util.List;

import bartlomiejfraczak.foodapk.encje.Tekst;

public class TekstModel {
    private static TekstModel instancja;

    public static TekstModel getInstancja() {
        if (instancja == null) {
            return new TekstModel();
        } else return instancja;
    }

    private TekstModel() {

    }

}
