package com.msa.accounts.entity.customer;

import com.msa.accounts.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "customer")
@Getter @ToString @NoArgsConstructor
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private Long customerId;

    private String name;
    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;

    private Customer(String name, String email, String mobileNumber) {
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public static Customer createdCustomer(String name, String email, String mobileNumber){
        return new Customer(name, email, mobileNumber);
    }

    protected void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    protected void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
