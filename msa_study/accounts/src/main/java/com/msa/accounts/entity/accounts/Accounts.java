package com.msa.accounts.entity.accounts;

import com.msa.accounts.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "accounts")
@Getter @ToString @NoArgsConstructor
public class Accounts extends BaseEntity {


    @Column(name = "customer_id")
    private Long customerId;

    @Id
    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;

    private Accounts(Long customerId, Long accountNumber, String accountType, String branchAddress) {
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.branchAddress = branchAddress;
    }

    public static Accounts createdAccounts(Long customerId, Long accountNumber, String accountType, String branchAddress){
        return new Accounts(customerId, accountNumber, accountType, branchAddress);
    }

    protected void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    protected void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    protected void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    protected void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }
}
