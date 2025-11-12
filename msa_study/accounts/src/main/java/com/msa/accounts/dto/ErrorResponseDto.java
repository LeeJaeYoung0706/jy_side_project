package com.msa.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
public class ErrorResponseDto {

    // 호출 경로
    private String apiPath;
    // 호출 실패 이유
    private HttpStatus errorCode;
    private String errorMessage;
    private LocalDateTime errorTime;
}
