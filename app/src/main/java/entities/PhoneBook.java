package entities;

/**
 * Created by Dion on 2/6/2016.
 */
public class PhoneBook {



    private long id;
    private String naam;
    public static String telefoonNummer;

    public PhoneBook (long id, String naam, String telefoonNummer){
        this.id = id;
        this.naam = naam;
        this.telefoonNummer = telefoonNummer;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public static String getTelefoonNummer() {
        return telefoonNummer;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setTelefoonNummer(String telefoonNummer) {
        this.telefoonNummer = telefoonNummer;
    }
}
