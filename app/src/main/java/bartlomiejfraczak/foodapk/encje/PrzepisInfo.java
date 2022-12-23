package bartlomiejfraczak.foodapk.encje;

public class PrzepisInfo {

    private int id;
    private int uzytkownikId;
    private int przepisId;
    private boolean ulubiony;
    private String notatka;

    public PrzepisInfo() {
    }

    public PrzepisInfo(int uzytkownikId, int przepisId, boolean ulubiony, String notatka) {
        this.uzytkownikId = uzytkownikId;
        this.przepisId = przepisId;
        this.ulubiony = ulubiony;
        this.notatka = notatka;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUzytkownikId() {
        return uzytkownikId;
    }

    public void setUzytkownikId(int uzytkownikId) {
        this.uzytkownikId = uzytkownikId;
    }

    public int getPrzepisId() {
        return przepisId;
    }

    public void setPrzepisId(int przepisId) {
        this.przepisId = przepisId;
    }

    public boolean isUlubiony() {
        return ulubiony;
    }

    public void setUlubiony(boolean ulubiony) {
        this.ulubiony = ulubiony;
    }

    public String getNotatka() {
        return notatka;
    }

    public void setNotatka(String notatka) {
        this.notatka = notatka;
    }


}
