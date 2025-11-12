package com.msa.accounts.service;

import com.msa.accounts.dto.CustomerDto;

// I로 시작하면 인터페이스 vs Impl
public interface IAccountService {
    /**
     * account 생성
     * @param customerDto
     */
    void createAccount(CustomerDto customerDto);

    /**
     * 핸드폰 번호 변경
     * @param mobileNumber
     * @return
     */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     * Account 정보 변경
     * @param customerDto
     * @return
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     * 정보 삭제
     * @param mobileNumber
     * @return
     */
    boolean deleteAccount(String mobileNumber);
}
