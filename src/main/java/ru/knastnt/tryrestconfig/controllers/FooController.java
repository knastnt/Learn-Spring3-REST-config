package ru.knastnt.tryrestconfig.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.knastnt.tryrestconfig.dto.FooDto;
import ru.knastnt.tryrestconfig.entities.Foo;
import ru.knastnt.tryrestconfig.exceptions.MyBadRequestException;
import ru.knastnt.tryrestconfig.exceptions.MyResourceNotFoundException;
import ru.knastnt.tryrestconfig.services.IFooService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @RestController является центральным артефактом всего веб-уровня API RESTful. Для этого поста контроллер
 * моделирует простой ресурс REST - Foo
 */
@RestController
@RequestMapping(value = "/api/foos")
public class FooController {
    /**
     * Реализация контроллера не является общедоступной - это потому, что не должно быть. (НУ НЕТ, ДЛЯ ТЕСТОВ НАДО)
     * Обычно контроллер является последним в цепочке зависимостей.
     * Он получает HTTP-запросы от фронт-контроллера Spring (DispatcherServlet) и просто передает их на
     * сервисный уровень. Если нет никакого варианта использования, когда контроллер должен вводиться или
     * манипулировать через прямую ссылку, тогда я предпочитаю не объявлять его как открытый.
     *
     *
     * Отображения запроса просты. Как и в любом контроллере, фактическое значение отображения, а также метод HTTP
     * определяют целевой метод для запроса.
     * @RequestBody будет привязывать параметры метода к телу HTTP-запроса,
     * тогда как @ResponseBody делает то же самое для ответа и типа возврата.
     * @RestController - это сокращение, включающее в наш класс аннотации @ResponseBody и @Controller.
     * Они также гарантируют, что ресурс будет распакован и распакован с использованием правильного HTTP-конвертера.
     * Будет проведено согласование контента, чтобы выбрать, какой из активных конвертеров будет использоваться,
     * основанный главным образом на заголовке Accept, хотя другие заголовки HTTP также могут использоваться для
     * определения представления.
     */


    private IFooService fooService;

    @Autowired
    public FooController(IFooService fooService) {
        this.fooService = fooService;
    }


//    @CrossOrigin(origins = "http://localhost:8089")
    @GetMapping(value = "/{id}")
    public FooDto findOne(@PathVariable Long id){
        Foo entity = fooService.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
                .orElseThrow(() -> new MyResourceNotFoundException());
        return convertToDto(entity);
    }


    @GetMapping
    public Collection<FooDto> findAll(){
        Iterable<Foo> foos = fooService.findAll();
        List<FooDto> fooDtos = new ArrayList<>();
        foos.forEach(foo -> fooDtos.add(convertToDto(foo)));
        return fooDtos;
    }




    protected FooDto convertToDto(Foo foo){
        FooDto fooDto = new FooDto(foo.getId(), foo.getName());
        return fooDto;
    }


    @PatchMapping(value = "/{id}")//, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable( "id" ) Long id, @RequestBody FooDto resource) {
        if (resource == null || id == null || !id.equals(resource.getId())) throw new MyBadRequestException();
        Optional<Foo> foo = fooService.findById(id);
        if(!foo.isPresent()) throw new MyResourceNotFoundException();

        foo.get().setName(resource.getName());

        fooService.save(foo.get());
    }

    /**
     * Перехватывает исключения указанных классов только в пределах данного контроллера
     */
    @ExceptionHandler({MyResourceNotFoundException.class, MyBadRequestException.class})
    public void handleException(Exception e) throws Exception {
        throw e;
    }


//
//
//    @GetMapping
//    public List<Foo> findAll() {
//        return fooService.findAll();
//    }
//
//    @GetMapping(value = "/{id}")
//    public Foo findById(@PathVariable("id") Long id) {
//        return RestPreconditions.checkFound(fooService.findById(id));
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Long create(@RequestBody Foo resource) {
//        Preconditions.checkNotNull(resource);
//        return fooService.create(resource);
//    }
//
//    @PutMapping(value = "/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public void update(@PathVariable( "id" ) Long id, @RequestBody Foo resource) {
//        Preconditions.checkNotNull(resource);
//        RestPreconditions.checkNotNull(fooService.getById(resource.getId()));
//        fooService.update(resource);
//    }
//
//    @DeleteMapping(value = "/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public void delete(@PathVariable("id") Long id) {
//        fooService.deleteById(id);
//    }

}
