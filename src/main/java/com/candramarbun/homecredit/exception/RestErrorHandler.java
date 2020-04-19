package com.candramarbun.homecredit.exception;

import com.candramarbun.homecredit.dto.GeneralResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class RestErrorHandler {

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GeneralResponse processValidationError(DataNotFoundException ex) {
        return GeneralResponse.dialog(404,ex.getMessage());
    }
}
