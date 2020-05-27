package ru.knastnt.tryrestconfig.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Аннотация @ ControllerAdvice позволяет нам объединить несколько разбросанных ранее @ExceptionHandlers в один
 * глобальный компонент обработки ошибок. Фактический механизм чрезвычайно прост, но также и очень гибок. Это дает нам:
 * Полный контроль над телом ответа, а также кодом состояния. Отображение нескольких исключений в один и тот же метод,
 * которые будут обрабатываться вместе, и эффективно использует более новый ответ RESTful ResposeEntity. Здесь нужно
 * помнить одну вещь: сопоставить исключения, объявленные с @ExceptionHandler, с исключением, используемым в качестве
 * аргумента метода. Если они не совпадают, компилятор не будет жаловаться - без причины, и Spring тоже не будет жаловаться.
 * Однако, когда исключение фактически выдается во время выполнения, механизм разрешения исключения завершится с:
 *
 * java.lang.IllegalStateException: No suitable resolver for argument [0] [type=...] HandlerMethod details: ...
 *
 *
 * Работает либо этот перехватчик, либо тот, что в контроллере - @ExceptionHandler. Вместе - неа
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { /*IllegalArgumentException.class, IllegalStateException.class, Exception.class, */MyResourceNotFoundException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponce = "Что-то пошло не так";
        return handleExceptionInternal(ex, bodyOfResponce, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
