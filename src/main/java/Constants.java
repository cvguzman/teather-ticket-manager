public class Constants {
    public static class Messages {
        public static final String WELCOME = """
                Bienvenid@ al Teatro
                ¡Estamos encantados de tenerte aquí!
                """;
        public static final String WEEKLY_SCHEDULE = """
                ::::::: Cartelera Semanal ::::::
                Consulta nuestra cartelera semanal de obras de teatro:
                """;
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
    }
}