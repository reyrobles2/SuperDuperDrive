package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RequestMapping("/result")
public class FileExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxSizeException(MaxUploadSizeExceededException exc) {
        Boolean isOk = false;
        String message = "File is too large. Upload file request rejected because its size exceeds the Configured Maximum Limit.";
        return "redirect:/result?isOk=" + isOk+"&message="+message;
    }

    @ExceptionHandler(Exception.class)
    public String exception(Exception exc) {
        Boolean isOk = false;
        String message = "File Upload Error";
        return "redirect:/result?isOk=" + isOk+"&message="+message;
    }
}