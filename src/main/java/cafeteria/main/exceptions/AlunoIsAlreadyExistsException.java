package cafeteria.main.exceptions;

import java.text.MessageFormat;

public class AlunoIsAlreadyExistsException extends RuntimeException {
    public AlunoIsAlreadyExistsException(String message, Throwable cause, Object... arguments) {
        super(MessageFormat.format(message, arguments), cause);
    }

    public AlunoIsAlreadyExistsException(String message, Object... arguments) {
        super(MessageFormat.format(message, arguments));
    }
}
