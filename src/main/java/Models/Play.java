package Models;

public class Play {
    public String id;
    public String title;

    public Play(String title) {
        id = this.toString() + title;
        this.title = title;
    }
}