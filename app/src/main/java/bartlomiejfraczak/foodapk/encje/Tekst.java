package bartlomiejfraczak.foodapk.encje;

public class Tekst {
    private int id;
    private String nazwa;

    public Tekst(int id, String nazwa) {
        this.id = id;
        this.nazwa = nazwa;
    }

    public Tekst() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Override
    public String toString() {
        return "Tekst{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                '}';
    }
}
