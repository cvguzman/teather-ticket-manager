import Models.Customer;
import Models.Discount;
import Models.Seat;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BookingManager {
    private final MapProvider mapProvider;
    private final PlayProvider playProvider;
    private final List<Seat> bookedSeats;
    private final List<Customer> customerList;
    private final Locale chileLocale;
    private final NumberFormat formatoCLP;

    private Discount discountAvailable;

    BookingManager(MapProvider mapProvider, PlayProvider playProvider) {
        this.mapProvider = mapProvider;
        this.playProvider = playProvider;
        bookedSeats = new ArrayList<Seat>();
        customerList = new ArrayList<Customer>();
        chileLocale = Locale.forLanguageTag("es-CL"); // Necesario para que el formateador de número sepa adaptarse a precios CLP.
        formatoCLP = NumberFormat.getCurrencyInstance(chileLocale);
     }

    public boolean validateSeat(String seatID) {
        // Validamos que la entrada empiece con una letra y luego números.
        // Se excluyen símbolos especiales.
        if (seatID.isEmpty() || !seatID.matches("^[A-Za-z][0-9]+$")) {
            System.out.println(Constants.Error.INVALID_SEAT);
            return false;
        }

        // Separamos asiento elegido en dos elementos (Letra asociada a una fila y número de columna)
        char row = seatID.charAt(0);
        int column = Integer.parseInt(seatID.substring(1));

        // Validamos que la fila no sobrepase los limites del mapa.
        if (row > 'D') {
            System.out.println(Constants.Error.INVALID_SEAT);
            return false;
        }

        // Validamos que la columna no sobrepase los limites del mapa.
        if (column > 4 || column < 1) {
            System.out.println(Constants.Error.INVALID_SEAT);
            return false;
        }

        return true;
    }

    public void bookSeat(String seatID) {
        Seat newSeat = createSeatObject(seatID);

        // Validamos que el asiento no se encuentre ocupado.
        // Si está disponible, lo marcamos como seleccionado hasta que el usuario finalice la compra.
        // Si el usuario finaliza la compra, los asientos seleccionados pasaran al estado no disponible.
        if (mapProvider.mapColumns[newSeat.positionX][newSeat.positionY].equals(Constants.Map.OCCUPIED_SEAT)) {
            System.out.println(Constants.Error.INVALID_SEAT);
            return;
        } else if (mapProvider.mapColumns[newSeat.positionX][newSeat.positionY].equals(Constants.Map.SELECTED_SEAT)) {
            System.out.println(Constants.Error.INVALID_SEAT);
            return;
        } else {
            mapProvider.mapColumns[newSeat.positionX][newSeat.positionY] = Constants.Map.SELECTED_SEAT;
            bookedSeats.add(
                    new Seat(
                            seatID,
                            newSeat.positionX,
                            newSeat.positionY
                    )
            );
        }
    }

    public void modifySeat(String seatID) {
        if (bookedSeats.isEmpty()) {
            System.out.println(Constants.Error.NO_RESERVATION);
            return;
        }

        boolean isValidSeat = false;

        for (Seat seat: bookedSeats) {
            if (seat.id.equals(seatID)) {
                mapProvider.mapColumns[seat.positionX][seat.positionY] = Constants.Map.AVAILABLE_SEAT;
                bookedSeats.remove(seat);
                isValidSeat = true;
                break;
            }
        }

        if (!isValidSeat) {
            System.out.println(Constants.Error.INVALID_SEAT);
        }
    }

    public List<Seat> getBookedSeats() {
        return bookedSeats;
    }

    public void makePurchase(String name, int age) {
        Customer newCustomer = new Customer(name, age);
        String totalAmount = getTotalAmount(newCustomer);
        List<String> seats = new ArrayList<String>();

        // Al validar los asientos en el recibo, los cambiamos de estado en el mapa por si existe otra compra.
        for (Seat seat: bookedSeats) {
            seats.add(seat.id);
            mapProvider.mapColumns[seat.positionX][seat.positionY] = Constants.Map.OCCUPIED_SEAT;
        }

        // ::::::::::::::: IMPRIMIR RECIBO :::::::::::::::
        // Mostramos un resumen de la transacción.

        System.out.println(":::::: BOLETA ::::::");
        System.out.println("Cliente: " + newCustomer.fullName);
        System.out.println("Obra: " + playProvider.getBookedPlay().title);
        System.out.println("Horario: " + playProvider.getBookedPlay().getCompleteReleaseInfo());
        System.out.println("Asientos reservados: " + seats.toString());
        System.out.println("Cantidad de entradas: " + seats.size());
        System.out.println("Precio final a pagar: "
                + totalAmount
                + Constants.Utils.SPACE + discountAvailable.getCompleteDiscountInfo()
        );
        System.out.println("::::::::::::::::::::::");
    }

    private Seat createSeatObject(String seatID) {
        // Separamos asiento elegido en dos elementos (Letra asociada a una fila y número de columna)
        char rowChar = seatID.charAt(0);
        int columnIndex = Integer.parseInt(seatID.substring(1));
        int rowIndex = 0;

        // Iteramos en el arreglo de hileras del mapa para conseguir el índice de la fila correspondiente.
        for (int index = 0; index < mapProvider.mapRows.length; index++) {
            if (mapProvider.mapRows[index].equals(String.valueOf(rowChar))) {
                rowIndex = index;
                break;
            }
        }

        return new Seat(
                seatID,
                rowIndex,
                (columnIndex - 1)
        );
    }

    private String getTotalAmount(Customer customer) {
        int totalDiscount;
        int totalAmount = 0;

        if (customer.age >= 60) {
            discountAvailable = Discount.ELDERLY;
        } else if (customer.age <= 25 && customer.age >= 12) {
            discountAvailable = Discount.STUDENT;
        } else {
            discountAvailable = Discount.NONE;
        }

        totalAmount += playProvider.getBookedPlay().price * bookedSeats.size();
        totalDiscount = (totalAmount * discountAvailable.getPercentage() / 100);
        totalAmount = totalAmount - totalDiscount;

        return formatoCLP.format(totalAmount);
    }
}
