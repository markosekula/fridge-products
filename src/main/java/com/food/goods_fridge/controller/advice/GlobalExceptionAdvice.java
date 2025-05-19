package com.food.goods_fridge.controller.advice;

import com.food.goods_fridge.Consts;
import com.food.goods_fridge.model.GlobalResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

/**
 * @author sekulicmarko10@gmail.com on 5/9/2025
 * @project goods-fridge
 */

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<GlobalResponse> handleNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildResponse(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalResponse> handleValidationException(
            MethodArgumentNotValidException ex) {
        String message = "";

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            if (error.getField().equals(Consts.FIELD_FIRST_NAME)) {
                message = error.getDefaultMessage();
            } else if (error.getField().equals(Consts.FIELD_EMAIL)) {
                message = error.getDefaultMessage();
            } else if (error.getField().equals(Consts.FIELD_BEST_BEFORE_DATE)) {
                message = error.getDefaultMessage();
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildResponse(message));
    }

    // ConstraintViolationException thrown from db
    @ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(
            org.hibernate.exception.ConstraintViolationException ex) {

        String errorMessage = ex.getSQLException().getMessage();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildResponse(errorMessage));
    }

    private GlobalResponse buildResponse(String message) {
        return GlobalResponse.builder()
                .message(message)
                .build();
    }
}
