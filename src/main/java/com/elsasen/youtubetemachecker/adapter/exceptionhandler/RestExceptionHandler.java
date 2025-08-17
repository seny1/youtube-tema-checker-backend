package com.elsasen.youtubetemachecker.adapter.exceptionhandler;

import com.elsasen.youtubetemachecker.app.api.exception.ChannelLinkNotUniqueException;
import com.elsasen.youtubetemachecker.app.api.exception.ChannelNotFoundException;
import com.elsasen.youtubetemachecker.app.api.exception.ClientByLoginException;
import com.elsasen.youtubetemachecker.app.api.exception.ClientChannelConflictException;
import com.elsasen.youtubetemachecker.app.api.exception.ClientNotFoundException;
import com.elsasen.youtubetemachecker.app.api.exception.NotUniqueClientLoginException;
import com.elsasen.youtubetemachecker.app.api.exception.ReportException;
import com.elsasen.youtubetemachecker.app.api.exception.VideoChannelConflictException;
import com.elsasen.youtubetemachecker.app.api.exception.VideoLinkNotUniqueException;
import com.elsasen.youtubetemachecker.app.api.exception.VideoNotFoundByChannelUuidException;
import com.elsasen.youtubetemachecker.app.api.exception.WrongPasswordException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "com.elsasen.youtubetemachecker.adapter.rest")
@RequiredArgsConstructor
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(value = {
            ClientNotFoundException.class,
            ChannelNotFoundException.class,
            VideoNotFoundByChannelUuidException.class,
            ClientByLoginException.class
    })
    protected ResponseEntity<String> handleNotFoundExceptions(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler({
            ChannelLinkNotUniqueException.class,
            VideoChannelConflictException.class,
            VideoLinkNotUniqueException.class,
            NotUniqueClientLoginException.class,
            IllegalArgumentException.class,
            IllegalStateException.class
    })
    protected ResponseEntity<String> handleConflictExceptions(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler({
            ClientChannelConflictException.class,
            WrongPasswordException.class
    })
    protected ResponseEntity<String> handleForbiddenExceptions(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    //@ExceptionHandler(ReportException.class)
    protected ResponseEntity<String> handleReportException(ReportException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
    }
}
