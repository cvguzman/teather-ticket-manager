import Models.Play;

import java.util.Arrays;
import java.util.Scanner;

public class TheaterTicketManager {
    public static void main(String[] args) {
        // :::::: VARIABLES DE INSTANCIA ::::::
        Scanner scanner = new Scanner(System.in);
        MapProvider mapProvider = new MapProvider();
        PlayProvider playProvider = new PlayProvider();
        BookingManager bookingManager = new BookingManager(mapProvider, playProvider);

        // :::::: VARIABLES ::::::
        int selectedOption;
        int selectedPlayIndex;
        boolean sessionFinished = false;
        boolean reservationFinished = false;
        boolean reservationCancelled = false;

        // :::::: INICIO DEL PROGRAMA ::::::

        // El programa finalizará cuando "sessionFinished = true"
        while (!sessionFinished) {
            System.out.println(Constants.Messages.WELCOME);
            System.out.println(Constants.Messages.WEEKLY_SCHEDULE);

            for (int index = 0; index < playProvider.getPlays().length; index++) {
                System.out.println((index + 1) + Constants.Utils.DOT + playProvider.getPlays()[index].title);
            }

            if (!scanner.hasNextInt()) {
                System.out.println(Constants.Error.INVALID_OPTION);
                scanner.next();
                continue;
            }

            selectedOption = scanner.nextInt();

            if (selectedOption > playProvider.getPlays().length || selectedOption < 1) {
                System.out.println(Constants.Error.INVALID_OPTION);
                scanner.nextInt();
                continue;
            }

            selectedPlayIndex = selectedOption - 1;

            System.out.println(Constants.Messages.PLAY_DETAILS);
            System.out.println("Título Original: " + playProvider.getPlays()[selectedPlayIndex].title);
            System.out.println("Director: " + playProvider.getPlays()[selectedPlayIndex].directorName);
            System.out.println("Reparto: " + Arrays.toString(playProvider.getPlays()[selectedPlayIndex].cast));
            System.out.println("Sinopsis: " + playProvider.getPlays()[selectedPlayIndex].sinopsis);
            System.out.println("Precio: " + "$" + playProvider.getPlays()[selectedPlayIndex].price);
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

            selectedOption = scanner.nextInt();
            scanner.nextLine();

            switch (selectedOption) {
                case 1:
                    playProvider.setPlay(playProvider.getPlays()[selectedPlayIndex]);
                    break;
                case 2:
                    continue;
                default:
                    System.out.println(Constants.Error.INVALID_OPTION);
                    continue;
            }

            // :::::: PRE-COMPRA DE ENTRADAS ::::::
            while (!reservationFinished && !reservationCancelled) {
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

                selectedOption = scanner.nextInt();
                scanner.nextLine();

                switch (selectedOption) {
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
                        if (bookingManager.getBookedSeats().isEmpty()) {
                            System.out.println(Constants.Error.NO_RESERVATION);
                            continue;
                        } else {
                            reservationFinished = true;
                            break;
                        }
                    case 4:
                        reservationCancelled = true;
                        break;
                    default:
                        System.out.println(Constants.Error.INVALID_OPTION);
                        continue;
                }
            }

            if (reservationCancelled) {
                continue;
            }

            // :::::: COMPRA DE ENTRADAS ::::::

            String customerName;
            int customerAge;

            System.out.println(Constants.Utils.SPACE);
            System.out.println(Constants.Messages.CUSTOMER_INFO);

            System.out.println(Constants.Messages.NAME_QUESTION);

             customerName = scanner.nextLine().trim().toUpperCase();

            System.out.println(Constants.Messages.AGE_QUESTION);

            customerAge = scanner.nextInt();

            scanner.nextLine();

            bookingManager.validateCustomer(customerName, customerAge);

            System.out.println(Constants.Utils.SPACE);
            System.out.println(Constants.Messages.SESSION_QUESTION);
            for (int index = 0; index < Constants.Options.SESSION_OPTIONS.length; index++) {
                System.out.println((index + 1) + Constants.Utils.DOT + Constants.Options.SESSION_OPTIONS[index]);
            }

            if (!scanner.hasNextInt()) {
                System.out.println(Constants.Error.INVALID_OPTION);
                scanner.next();
                continue;
            }

            selectedOption = scanner.nextInt();

            if (selectedOption > Constants.Options.SESSION_OPTIONS.length || selectedOption < 1) {
                System.out.println(Constants.Error.INVALID_OPTION);
                scanner.nextInt();
                continue;
            }

            if (selectedOption == 0) {
                sessionFinished = false;
                reservationFinished = false;
                reservationCancelled = false;
                continue;
            }

            if (selectedOption == 1) {
                sessionFinished = true;
                scanner.close();
            }
        }
    }
}