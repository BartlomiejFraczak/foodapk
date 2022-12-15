package bartlomiejfraczak.foodapk.modele;

public class UzytkownikModel {
    private static UzytkownikModel instancja;

    public static UzytkownikModel getInstancja() {
        if (instancja == null) {
            return new UzytkownikModel();
        } else return instancja;
    }

    public String haslo2hasloHash(String haslo) {
        return "?"; //todo
    }

}
