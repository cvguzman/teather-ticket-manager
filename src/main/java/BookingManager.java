import Models.Seat;

import java.util.ArrayList;
import java.util.List;

public class BookingManager {
    private final MapProvider mapProvider;
    private ArrayList<String> bookedSeats = new ArrayList<String>();

    BookingManager(MapProvider mapProvider) {
         this.mapProvider = mapProvider;
     }

    public boolean validateSeat(String seat) {
        if (seat.isEmpty() || !seat.matches("^[A-Za-z][0-9]+$")) {
            System.out.println(Constants.Error.INVALID_SEAT);
            return false;
        }

        // Separamos asiento elegido en dos elementos (Letra asociada a una fila y número de columna)
        char row = seat.charAt(0);
        int column = Integer.parseInt(seat.substring(1));

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

    public void bookSeat(String seat) {
        Seat mapCoordinates = getSeatCoordinates(seat);

        // Validamos que el asiento no se encuentre ocupado.
        // Si está disponible, lo marcamos como seleccionado hasta que el usuario finalice la compra.
        // Si el usuario finaliza la compra, los asientos seleccionados pasaran al estado no disponible.
        if (mapProvider.mapColumns[mapCoordinates.positionX][mapCoordinates.positionY].equals(Constants.Map.OCCUPIED_SEAT)) {
            System.out.println(Constants.Error.INVALID_SEAT);
            return;
        } else if (mapProvider.mapColumns[mapCoordinates.positionX][mapCoordinates.positionY].equals(Constants.Map.SELECTED_SEAT)) {
            System.out.println(Constants.Error.INVALID_SEAT);
            return;
        } else {
            mapProvider.mapColumns[mapCoordinates.positionX][mapCoordinates.positionY] = Constants.Map.SELECTED_SEAT;
            bookedSeats.add(seat);
        }
    }

    public void modifySeat(String seat) {
        if (bookedSeats.contains(seat)) {
            Seat seatCoordinates = getSeatCoordinates(seat);

            mapProvider.mapColumns[seatCoordinates.positionX][seatCoordinates.positionY] = Constants.Map.AVAILABLE_SEAT;
            bookedSeats.remove(seat);
        }
    }

    private Seat getSeatCoordinates(String seat) {
        // Separamos asiento elegido en dos elementos (Letra asociada a una fila y número de columna)
        char rowChar = seat.charAt(0);
        int columnIndex = Integer.parseInt(seat.substring(1));
        int rowIndex = 0;

        // Iteramos en el arreglo de hileras del mapa para conseguir el índice de la fila correspondiente.
        for (int index = 0; index < mapProvider.mapRows.length; index++) {
            if (mapProvider.mapRows[index].equals(String.valueOf(rowChar))) {
                rowIndex = index;
                break;
            }
        }

        return new Seat(rowIndex, (columnIndex - 1));
    }
}
