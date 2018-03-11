package com.booxware.test.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.ProblemHandling;
/*
I'm using problem-spring-web library for translatring exceptions into json and readable format
We need to translate AccountServiceException as a general exception in usermanagement module
 */
@ControllerAdvice
public class ExceptionTranslator implements ProblemHandling {
    @ExceptionHandler(AccountServiceException.class)
    public ResponseEntity<Problem> handleAIAServiceException(AccountServiceException ex, NativeWebRequest request) {
        Problem problem = Problem.builder()
                .withStatus(Status.INTERNAL_SERVER_ERROR)
                .with("message",ex.getMessage())
                .build();
        return create(ex, problem, request);
    }
}
