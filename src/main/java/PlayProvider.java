import Models.Play;

import java.time.LocalDate;

public class PlayProvider {
    private final Play[] plays;
    private Play bookedPlay;

    public PlayProvider() {
        plays = new Play[] {
                new Play(
                        "Aladdin",
                        "Ron Clements",
                        new String[]{"Scott Weinger", "Robin Williams"},
                        """
                                Aladdín (Mena Massoud) es un joven ladrón de buen corazón que vive en la ciudad árabe de Agrabah junto a su mono acompañante Abu.
                                Un día rescata y se hace amigo de la princesa Jasmín (Naomi Scott), quien se escabulló del palacio para explorar la ciudad, cansada de su vida sobre-protegida.
                                """,
                        LocalDate.of(2025, 10, 12),
                        "15:30 hrs"
                ),
                new Play(
                        "Toy Story",
                        "John Lasseter",
                        new String[]{"Tom Hanks", "Tim Allen"},
                        """
                                La historia sigue las aventuras de un grupo de juguetes vivientes, en particular del vaquero Woody y el guardián espacial Buzz Lightyear.
                                Si bien al principio rivalizan entre sí, conforme transcurre la trama se van volviendo amigos.
                                """,
                        LocalDate.of(2025, 10, 15),
                        "17:00 hrs"
                ),
        };
    }

    public Play[] getPlays() {
        return plays;
    }

    public void setPlay(Play play) {
        bookedPlay = play;
    }

    public Play getBookedPlay() {
        return bookedPlay;
    }
}
