package com.mes_jyproject.master.study.service;

import com.mes_jyproject.master.study.contains.AccountsConstants;
import com.mes_jyproject.master.study.dto.AccountsDto;
import com.mes_jyproject.master.study.dto.CustomerDto;
import com.mes_jyproject.master.study.entity.Accounts;
import com.mes_jyproject.master.study.entity.Customer;
import com.mes_jyproject.master.study.exceptions.CustomerAlreadyExistsException;
import com.mes_jyproject.master.study.exceptions.ResourceNotFoundException;
import com.mes_jyproject.master.study.mapper.AccountsMapper;
import com.mes_jyproject.master.study.mapper.CustomerMapper;
import com.mes_jyproject.master.study.repository.AccountsRepository;
import com.mes_jyproject.master.study.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountsServiceImpl implements IAccountsService {


    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    /**
     *
     * @param customerDto - Object CustomerDto
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());

        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("already exists customer mobile number" + customer.getMobileNumber());
        }

        Customer savedCustomer = customerRepository.save(customer);
        Accounts savedAccounts = accountsRepository.save(createNewAccount(savedCustomer));
    }

    /**
     * 
     * @param customer -  Object CustomerDto
     * @return - Object new Account 
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
        return customerDto;
    }


}
