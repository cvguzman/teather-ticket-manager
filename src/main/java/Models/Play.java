package Models;

import java.time.LocalDate;

public class Play {
    public String id;
    public String title;
    public String directorName;
    public String[] cast;
    public String sinopsis;
    public LocalDate releaseDate;
    public String showTime;
    public final int price = 5000;

    public Play(String title, String directorName, String[] cast, String sinopsis, LocalDate releaseDate, String showTime) {
        id = this.toString() + title;
        this.title = title;
        this.directorName = directorName;
        this.cast = cast;
        this.sinopsis = sinopsis;
        this.releaseDate = releaseDate;
        this.showTime = showTime;
    }

    public String getCompleteReleaseInfo() {
        return releaseDate + " (" + showTime + ")";
    }
}