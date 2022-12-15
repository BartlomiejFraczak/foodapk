package bartlomiejfraczak.foodapk.modele;

import bartlomiejfraczak.foodapk.encje.PrzepisSzczegolowy;

public class PrzepisSzczegolowyModel {
    private static PrzepisSzczegolowyModel instancja;
    private PrzepisSzczegolowy przepisSzczegolowy;

    public static PrzepisSzczegolowyModel getInstancja() {
        if (instancja == null) {
            instancja = new PrzepisSzczegolowyModel();
        }
        return instancja;
    }

    public PrzepisSzczegolowy getPrzepisSzczegolowy() {
        return przepisSzczegolowy;
    }

    public void setPrzepisSzczegolowy(PrzepisSzczegolowy przepisSzczegolowy) {
        this.przepisSzczegolowy = przepisSzczegolowy;
    }
}
