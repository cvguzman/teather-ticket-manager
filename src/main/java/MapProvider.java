public class MapProvider {
    // :::::: PROPIEDADES ::::::

    private final String[] mapRows = {"A", "B", "C", "D"};
    public String[][] mapColumns = {
            Constants.Map.AVAILABLE_SEAT_GROUP.clone(),
            Constants.Map.AVAILABLE_SEAT_GROUP.clone(),
            Constants.Map.AVAILABLE_SEAT_GROUP.clone(),
            Constants.Map.AVAILABLE_SEAT_GROUP.clone()
    };

    // :::::: FUNCIONES PUBLICAS ::::::

    public void drawMap() {
        drawColumnNumbers();
        drawRows();
    }

    // :::::: FUNCIONES PRIVADAS ::::::

    private void drawColumnNumbers() {
        // Iteramos por cada elemento de "mapRows".
        // Por cada elemento, conseguimos el índice y lo aprovechamos para enumerar cada columna.
        // Importante: Los espacios fueron definidos para una mejor visualización en la terminal.
        for (int index = 0; index <= mapRows.length; index++) {
            switch (index) {
                case 0:
                    System.out.print(Constants.Utils.DOUBLE_SPACE);
                    break;
                case 1, 3:
                    System.out.print(Constants.Utils.SPACE + index + Constants.Utils.SPACE);
                    break;
                case 2:
                    System.out.print(Constants.Utils.DOUBLE_SPACE + index + Constants.Utils.DOUBLE_SPACE);
                    break;
                case 4:
                    System.out.print(Constants.Utils.DOUBLE_SPACE + index + Constants.Utils.SPACE);
                    break;
            }
        }
        System.out.println(Constants.Utils.SPACE);
    }

    private void drawRows() {
        // Iteramos por cada letra de "mapRows".
        // Por cada letra, concatenamos contenido hasta armar una hilera completa.
        for (int letterIndex = 0; letterIndex < mapRows.length; letterIndex++) {
            // Contenido base para luego concatenar los asientos (ejemplo:  A + " ").
            String rowContent = mapRows[letterIndex] + Constants.Utils.SPACE;

            for (String seat: mapColumns[letterIndex]) {
                // Concatenamos todos los asientos al contenido base (ejemplo: [_], [X] ó [S]).
                // Para conocer el significado de cada símbolo de asiento, revisar clase "Constants.Map".
                rowContent += (seat + Constants.Utils.SPACE);
            }

            System.out.println(rowContent);
        }
    }
}