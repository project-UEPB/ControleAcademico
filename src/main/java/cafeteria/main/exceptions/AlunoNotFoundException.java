package cafeteria.main.exceptions;

import java.text.MessageFormat;

public class AlunoNotFoundException extends RuntimeException {
    public AlunoNotFoundException(String message, Throwable cause, Object... arguments) {
        super(MessageFormat.format(message, arguments), cause);
    }

    public AlunoNotFoundException(String message, Object... arguments) {
        super(MessageFormat.format(message, arguments));
    }
}
