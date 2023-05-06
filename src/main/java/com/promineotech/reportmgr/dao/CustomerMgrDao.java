package com.promineotech.reportmgr.dao;

import java.math.BigDecimal;
import java.util.List;
import com.promineotech.reportmgr.entity.Customer;

public interface CustomerMgrDao {
  List<Customer> fetchCustomers(BigDecimal customerID);
}
