package ru.knastnt.tryrestconfig.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *  Эти исключения являются частью REST API и, как таковые, должны использоваться только на соответствующих уровнях,
 *  соответствующих REST; если, например, слой DAO / DAL существует, он не должен использовать исключения напрямую.
 *  Также обратите внимание, что это не проверяемые исключения, а исключения времени выполнения - в соответствии с
 *  практикой и идиомами Spring.
 */

/**
 * Выброс исключений из любого уровня веб-уровня гарантирует,
 * что Spring отобразит соответствующий (@ResponseStatus) код состояния в ответе HTTP
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyResourceNotFoundException extends RuntimeException {
    public MyResourceNotFoundException() {
        super();
    }

    public MyResourceNotFoundException(String message) {
        super(message);
    }

    public MyResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public MyResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
