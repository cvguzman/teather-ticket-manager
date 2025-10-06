package Models;

public class Play {
    public String id;
    public String title;
    public String directorName;
    public String[] cast;
    public String sinopsis;
    public final int price = 5000;

    public Play(String title, String directorName, String[] cast, String sinopsis) {
        id = this.toString() + title;
        this.title = title;
        this.directorName = directorName;
        this.cast = cast;
        this.sinopsis = sinopsis;
    }
}