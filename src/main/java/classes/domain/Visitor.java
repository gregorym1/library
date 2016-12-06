package classes.domain;

/**
 * Created by Vitalii on 04.11.2016.
 */
public class Visitor {
    private String name;
    private String lastname;
    private String pasport;
    private String year;

    public Visitor(String name, String lastname, String pasport, String year){
        this.name = name;
        this.lastname = lastname;
        this.pasport = pasport;
        this.year = year;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPasport() {
        return pasport;
    }

    public void setPasport(String pasport) {
        this.pasport = pasport;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
