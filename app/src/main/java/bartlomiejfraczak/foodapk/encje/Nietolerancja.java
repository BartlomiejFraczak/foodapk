package bartlomiejfraczak.foodapk.encje;

public class Nietolerancja {

    private String id;
    private String nazwa;

    public Nietolerancja(String id, String nazwa) {
        this.id = id;
        this.nazwa = nazwa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}
