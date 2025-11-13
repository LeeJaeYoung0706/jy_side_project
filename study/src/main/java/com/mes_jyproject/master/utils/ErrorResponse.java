package com.mes_jyproject.master.utils;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@ToString
public class ErrorResponse<T> implements Serializable {

    private static final long serialVersionUID = 362498820763181211L;
    private final String message;  // 결과 메시지
    private final String apiPath;  // api path
    private final HttpStatus status;
    private final LocalDateTime errorTime;

    /**
     * 생성자에서 필수적으로 넣어야하는 처리를 해야해서 커스텀
     * @param <T>
     */
    public static class ErrorResponseBuilder<T> {

        private String message;  // 결과 메시지
        private String apiPath;  // api path
        private HttpStatus status;
        private LocalDateTime errorTime;

        private ErrorResponseBuilder() {
        }

        public ErrorResponseBuilder<T> setMessage(String message) {
            this.message = message;
            return this;
        }

        public ErrorResponseBuilder<T> setApiPath(String apiPath) {
            this.apiPath = apiPath;
            return this;
        }

        public ErrorResponseBuilder<T> setStatus(HttpStatus status) {
            this.status = status;
            return this;
        }

        public ErrorResponseBuilder<T> setErrorTime(LocalDateTime errorTime) {
            this.errorTime = errorTime;
            return this;
        }

        public ErrorResponse<T> build() {
            return new ErrorResponse<>(this);
        }
    }

    public static <T> ErrorResponseBuilder<T> builder() {
        return new ErrorResponseBuilder<T>();
    }

    private ErrorResponse(ErrorResponseBuilder<T> responseBuilder){
        message = responseBuilder.message;
        status = responseBuilder.status;
        apiPath = responseBuilder.apiPath;
        errorTime = responseBuilder.errorTime;
    }

}