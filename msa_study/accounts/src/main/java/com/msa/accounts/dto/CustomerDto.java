package com.msa.accounts.dto;

import com.msa.accounts.entity.accounts.Accounts;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class CustomerDto {

    @NotEmpty(message = "이름을 입력해주세요.")
    @Size(min = 5, max = 30, message = "이름은 5 ~ 30자로 입력해주세요.")
    private String name;
    @NotEmpty(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{11})", message = "휴대폰 번호를 입력해주세요.")
    private String mobileNumber;

    private AccountsDto accountsDto;

}
