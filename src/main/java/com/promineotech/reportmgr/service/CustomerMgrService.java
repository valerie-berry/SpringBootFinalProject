package com.promineotech.reportmgr.service;

import java.math.BigDecimal;
import java.util.List;
import com.promineotech.reportmgr.entity.Customer;

public interface CustomerMgrService {

  List<Customer> fetchCustomers(BigDecimal customerID);
}

