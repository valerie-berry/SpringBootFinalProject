package com.promineotech.reportmgr.entity;

import java.math.BigDecimal;
import java.util.Comparator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Customer implements Comparable<Customer> {
  private BigDecimal customerID;
  private String customerName;
  private String address;
  private BigDecimal contactNumber;
  private String notes;

  @JsonIgnore
  public BigDecimal getCustomerID() {
    return customerID;
  }

  @Override
  public int compareTo(Customer that) {
    // formatter:off
    return Comparator
        .comparing(Customer::getCustomerID)
        .compare(this, that);

    // formatter:off
  }
}
