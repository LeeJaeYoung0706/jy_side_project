package com.msa.accounts.service.impl;

import com.msa.accounts.AccountsConstants;
import com.msa.accounts.dto.AccountsDto;
import com.msa.accounts.dto.CustomerDto;
import com.msa.accounts.entity.accounts.Accounts;
import com.msa.accounts.entity.accounts.AccountsMapper;
import com.msa.accounts.entity.customer.Customer;
import com.msa.accounts.entity.customer.CustomerMapper;
import com.msa.accounts.exceptions.CustomerAlreadyExistsException;
import com.msa.accounts.exceptions.ResourceNotFoundException;
import com.msa.accounts.repository.AccountsRepository;
import com.msa.accounts.repository.CustomerRepository;
import com.msa.accounts.service.IAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements IAccountService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    /**
     * account 생성
     *
     * @param customerDto
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto);
        Optional<Customer> byMobileNumber = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (byMobileNumber.isPresent()){
            throw new CustomerAlreadyExistsException("이미 존재하는 고객 전화번호입니다.");
        }

        Customer savedCustomer = customerRepository.save(customer);
        log.info("test11");
        Accounts newAccount = createNewAccount(savedCustomer);
        accountsRepository.save(newAccount);
    }

    /**
     * @param customer - Customer Object
     * @return 새로운 계정
     */
    private Accounts createNewAccount(Customer customer) {
        Long customerId = customer.getCustomerId();
        AccountsDto accountsDto = new AccountsDto();
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        accountsDto.setAccountNumber(randomAccNumber);
        accountsDto.setBranchAddress(AccountsConstants.ADDRESS);
        accountsDto.setAccountType(AccountsConstants.SAVINGS);
        Accounts accounts = AccountsMapper.mapToAccounts(accountsDto, customerId);
        return accounts;
    }

    /**
     * 핸드폰 번호 변경
     *
     * @param mobileNumber
     * @return
     */
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() ->
                new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() ->
                new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString())
        );
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        return customerDto;
    }

    /**
     * Account 정보 변경
     *
     * @param customerDto
     * @return
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto != null ){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);

            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    /**
     * 정보 삭제
     *
     * @param mobileNumber
     * @return
     */
    @Override
    @Transactional
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
//        Customer customer2 = customerRepository.findByMobileNumber("1234").orElseThrow(
//                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
//        );
//        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer2, new CustomerDto());
//        customerDto.setName("4321");
//        Accounts byCustomerId = accountsRepository.findByCustomerId(customer2.getCustomerId()).get();
//        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(byCustomerId, new AccountsDto()));
//        this.updateAccount(customerDto);
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }
}
