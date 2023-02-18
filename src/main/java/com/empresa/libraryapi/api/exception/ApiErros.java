package com.empresa.libraryapi.api.exception;
import com.empresa.libraryapi.exception.BusinessException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApiErros {
    private List<String> errors;
    public ApiErros(BindingResult bindingResult) {
        this.errors = new ArrayList<>();
        bindingResult.getAllErrors().forEach(erro -> this.errors.add(erro.getDefaultMessage()));
    }

    public ApiErros(BusinessException ex) {
        this.errors = Arrays.asList(ex.getMessage());
    }

    public ApiErros(ResponseStatusException ex) {
        this.errors = Arrays.asList(ex.getReason());
    }

    public List<String>getErros() {
        return errors;
    }
}
