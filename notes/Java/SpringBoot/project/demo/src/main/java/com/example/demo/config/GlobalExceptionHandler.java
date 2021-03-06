package com.example.demo.config;

import com.example.demo.bean.Response;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;


@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = BindException.class)
    public Object BindingExceptionMethod(BindException ex) {
        Response<String> response = new Response<>();
        response.setCode(100);
        response.setData("");
        List<ObjectError> errors = ex.getAllErrors();

        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < errors.size(); i++) {
            strBuilder.append(errors.get(i).getDefaultMessage());
            if (i != errors.size() - 1) {
                strBuilder.append(",");
            }
        }
        response.setMessage(strBuilder.toString());
        return response;
    }
}
