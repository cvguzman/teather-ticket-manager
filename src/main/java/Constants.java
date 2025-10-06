public class Constants {
    public static class Messages {
        public static final String WELCOME = """
                Bienvenid@ al Teatro Moro
                ¡Estamos encantados de tenerte aquí!
                """;
        public static final String WEEKLY_SCHEDULE = """
                ::::::: Cartelera Semanal ::::::
                Consulta nuestra cartelera semanal de obras de teatro:
                """;
        public static final String PLAY_DETAILS = ":::::: Detalles de la obra ::::::";
        public static final String BOOKING = """
                ::::::: Compra de Entradas ::::::
                Leyenda: [_] Disponible, [X] No disponible, [S] Seleccionado para reservar
                """;
        public static final String PROCEED_QUESTION = "Seleccione una opción para proceder:";
        public static final String SELECT_A_SEAT = "Seleccione un asiento del mapa ingresando la letra de la fila y el número de la columna (ej: B1):";
    }

    public static class Options {
        public static final String[] PROCEED_QUESTION_OPTIONS = {"Continuar", "Volver"};
        public static final String[] BOOKING_OPTIONS = {"Reservar un asiento", "Eliminar una reserva", "Volver"};
    }

    public static class Map {
        public static final String AVAILABLE_SEAT = "[_]";
        public static final String OCCUPIED_SEAT = "[X]";
        public static final String SELECTED_SEAT = "[S]";
        public static final String[] AVAILABLE_SEAT_GROUP = {
                AVAILABLE_SEAT,
                AVAILABLE_SEAT,
                AVAILABLE_SEAT,
                AVAILABLE_SEAT
        };
    }

    public static class Utils {
        public static final String SPACE = " ";
        public static final String DOUBLE_SPACE = "  ";
        public static final String DOT = ".";
    }

    public static class Error {
        public static final String INVALID_OPTION = "::: ERROR ::: Por favor, ingrese una opción válida.";
        public static final String INVALID_SEAT = "::: ERROR ::: Por favor, ingrese una fila y una columna válidas y disponibles.";
        public static final String NO_RESERVATION = "::: ERROR ::: Debes tener al menos una reserva para continuar.";
    }
}