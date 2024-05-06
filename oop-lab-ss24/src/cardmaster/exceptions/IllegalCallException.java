package cardmaster.exceptions;

import cardmaster.Game;

public class IllegalCallException extends RuntimeException{

    public IllegalCallException(String methodName, Game.Mode currentMode, Game.Mode expectedMode) {
        super("Die Methode " + methodName + " darf im aktuellen Modus " + currentMode + " nicht aufgerufen werden. Erwarteter Modus: " + expectedMode);
    }
}
