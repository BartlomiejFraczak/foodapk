package bartlomiejfraczak.foodapk.encje;

public class Uzytkownik {

    private int id;
    private String login;
    private String hasloHash;

    public Uzytkownik(int id, String login, String haslo) {
        this.id = id;
        this.login = login;
        this.hasloHash = haslo;
    }

    public Uzytkownik(String login, String hasloHash) {
        this.id = 0;
        this.login = login;
        this.hasloHash = hasloHash;
    }

    @Override
    public String toString() {
        return "Uzytkownik{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", hasloHash='" + hasloHash + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHasloHash() {
        return hasloHash;
    }

    public void setHasloHash(String hasloHash) {
        this.hasloHash = hasloHash;
    }
}
