import Models.Play;
import java.util.Arrays;
import java.util.Scanner;

public class TheaterTicketManager {
    public static void main(String[] args) {
        // :::::: CONSTANTES ::::::

        final Play[] plays = {
                new Play(
                        "Aladin",
                        "Ron Clements",
                        new String[]{"Scott Weinger", "Robin Williams"},
                        """
                        Aladdín (Mena Massoud) es un joven ladrón de buen corazón que vive en la ciudad árabe de Agrabah junto a su mono acompañante Abu.
                        Un día rescata y se hace amigo de la princesa Jasmín (Naomi Scott), quien se escabulló del palacio para explorar la ciudad, cansada de su vida sobre-protegida.
                        """),
                new Play(
                        "Toy Story",
                        "John Lasseter",
                        new String[]{"Tom Hanks", "Tim Allen"},
                        """
                        La historia sigue las aventuras de un grupo de juguetes vivientes, en particular del vaquero Woody y el guardián espacial Buzz Lightyear.
                        Si bien al principio rivalizan entre sí, conforme transcurre la trama se van volviendo amigos.
                        """),
        };

        // :::::: VARIABLES DE INSTANCIA ::::::
        Scanner scanner = new Scanner(System.in);
        MapProvider mapProvider = new MapProvider();
        BookingManager bookingManager = new BookingManager(mapProvider);

        // :::::: VARIABLES ::::::
        boolean sessionFinished = false;
        boolean reservationFinished = false;
        boolean toNextStep = false;

        // :::::: INICIO DEL PROGRAMA ::::::

        // El programa finalizará cuando "sessionFinished = true"
        while (!sessionFinished) {
            System.out.println(Constants.Messages.WELCOME);
            System.out.println(Constants.Messages.WEEKLY_SCHEDULE);

            for (int index = 0; index < plays.length; index++) {
                System.out.println((index + 1) + Constants.Utils.DOT + plays[index].title);
            }

            if (!scanner.hasNextInt()) {
                System.out.println(Constants.Error.INVALID_OPTION);
                scanner.next();
                continue;
            }

            int option = scanner.nextInt();

            if (option > plays.length || option < 1) {
                System.out.println(Constants.Error.INVALID_OPTION);
                scanner.nextInt();
                continue;
            }

            System.out.println(Constants.Messages.PLAY_DETAILS);
            System.out.println("TÍTULO ORIGINAL: " + plays[option - 1].title);
            System.out.println("DIRECTOR: " + plays[option - 1].directorName);
            System.out.println("REPARTO: " + Arrays.toString(plays[option - 1].cast));
            System.out.println("SINOPSIS: " + plays[option - 1].sinopsis);
            System.out.println("PRECIO: " + "$" + plays[option - 1].price);
            System.out.println(Constants.Utils.SPACE);

            System.out.println(Constants.Messages.PROCEED_QUESTION);

            for (int index = 0; index < Constants.Options.PROCEED_QUESTION_OPTIONS.length; index++) {
                System.out.println((index + 1) + Constants.Utils.DOT + Constants.Options.PROCEED_QUESTION_OPTIONS[index]);
            }

            if (!scanner.hasNextInt()) {
                System.out.println(Constants.Error.INVALID_OPTION);
                scanner.next();
                continue;
            }

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    break;
                case 2:
                    scanner.nextInt();
                    continue;
                default:
                    System.out.println(Constants.Error.INVALID_OPTION);
                    scanner.nextInt();
                    continue;
            }

            scanner.next();

            // :::::: COMPRA DE ENTRADAS::::::
            while (!reservationFinished) {
                boolean isValidSeat;
                String seat;

                System.out.println(Constants.Messages.BOOKING);

                mapProvider.drawMap();

                System.out.println(Constants.Utils.SPACE);
                for (int index = 0; index < Constants.Options.BOOKING_OPTIONS.length; index++) {
                    System.out.println((index + 1) + Constants.Utils.DOT + Constants.Options.BOOKING_OPTIONS[index]);
                }

                if (!scanner.hasNextInt()) {
                    System.out.println(Constants.Error.INVALID_OPTION);
                    scanner.next();
                    continue;
                }

                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        // :::::: ADICIONAR UN ASIENTO ::::::

                        System.out.println(Constants.Utils.SPACE);
                        System.out.println(Constants.Messages.SELECT_A_SEAT);

                        seat = scanner.nextLine().trim().toUpperCase();

                        isValidSeat = bookingManager.validateSeat(seat);

                        if (isValidSeat) {
                            bookingManager.bookSeat(seat);
                        }

                        continue;

                    case 2:
                        // :::::: ELIMINAR UNA RESERVA ::::::
                        System.out.println(Constants.Utils.SPACE);
                        System.out.println(Constants.Messages.SELECT_A_SEAT);

                        seat = scanner.nextLine().trim().toUpperCase();

                        isValidSeat = bookingManager.validateSeat(seat);

                        if (isValidSeat) {
                            bookingManager.modifySeat(seat);
                        }

                        continue;

                    case 3:
                        reservationFinished = true;
                        break;
                    default:
                        System.out.println(Constants.Error.INVALID_OPTION);
                        scanner.nextInt();
                        continue;
                }
            }

            sessionFinished = true;
        }
    }
}