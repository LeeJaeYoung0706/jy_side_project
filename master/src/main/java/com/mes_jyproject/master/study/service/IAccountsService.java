package com.mes_jyproject.master.study.service;

import com.mes_jyproject.master.study.dto.CustomerDto;

public interface IAccountsService {
    /**
     *
     * @param customerDto - Object CustomerDto
     */
    void createAccount(CustomerDto customerDto);


    CustomerDto fetchAccount(String mobileNumber);
}
