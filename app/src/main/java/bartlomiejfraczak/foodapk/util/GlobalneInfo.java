package bartlomiejfraczak.foodapk.util;

import java.util.Locale;

import bartlomiejfraczak.foodapk.encje.Uzytkownik;

public class GlobalneInfo {

    private static GlobalneInfo instancja;
    private String region;
    private Uzytkownik zalogowanyUzytkownik;
    private boolean czyUzytkownikZalogowany;

    private GlobalneInfo() {
        region = Locale.getDefault().getLanguage();
        czyUzytkownikZalogowany = false;
    }

    public static GlobalneInfo getInstancja() {
        if (instancja == null) {
            instancja = new GlobalneInfo();
        }
        return instancja;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Uzytkownik getZalogowanyUzytkownik() {
        return zalogowanyUzytkownik;
    }

    public void setZalogowanyUzytkownik(Uzytkownik zalogowanyUzytkownik) {
        this.zalogowanyUzytkownik = zalogowanyUzytkownik;
    }

    public boolean getCzyUzytkownikZalogowany() {
        return czyUzytkownikZalogowany;
    }

    public void setCzyUzytkownikZalogowany(boolean czyUzytkownikZalogowany) {
        this.czyUzytkownikZalogowany = czyUzytkownikZalogowany;
        if (!czyUzytkownikZalogowany) {
            zalogowanyUzytkownik = null;
        }
    }

    public void setCzyUzytkownikZalogowany(boolean czyUzytkownikZalogowany, int id) {
        this.zalogowanyUzytkownik.setId(id);
        this.czyUzytkownikZalogowany = czyUzytkownikZalogowany;
    }
}
