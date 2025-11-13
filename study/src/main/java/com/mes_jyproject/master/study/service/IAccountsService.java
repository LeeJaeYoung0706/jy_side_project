package com.mes_jyproject.master.study.service;

import com.mes_jyproject.master.study.dto.CustomerDto;

public interface IAccountsService {
    /**
     *
     * @param customerDto - Object CustomerDto
     */
    void createAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber
     * @return
     */
    CustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of Account details is successful or not
     */
    boolean deleteAccount(String mobileNumber);

}
