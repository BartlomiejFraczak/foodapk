package bartlomiejfraczak.foodapk.modele;

import java.util.ArrayList;
import java.util.List;

import bartlomiejfraczak.foodapk.encje.Przepis;
import bartlomiejfraczak.foodapk.encje.PrzepisSzczegolowy;

public class PrzepisModel {
    private static PrzepisModel instancja;
    private List<Przepis> przepisy;

    public static PrzepisModel getInstancja() {
        if (instancja == null) {
            instancja = new PrzepisModel();
        }
        return instancja;
    }

    public List<Przepis> getPrzepisy() {
        return przepisy;
    }

    public void setPrzepisy(List<Przepis> przepisy) {
        this.przepisy = przepisy;
    }


    public void setPrzepisySzczegolowe(List<PrzepisSzczegolowy> przepisySzczegolowe) {
        if (przepisySzczegolowe == null || przepisySzczegolowe.isEmpty()) return;
        List<Przepis> wynik = new ArrayList<>();
        wynik.addAll(przepisySzczegolowe);
        this.przepisy = wynik;
    }

}
