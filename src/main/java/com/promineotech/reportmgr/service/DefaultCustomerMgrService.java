package com.promineotech.reportmgr.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.reportmgr.dao.CustomerMgrDao;
import com.promineotech.reportmgr.entity.Customer;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service


public class DefaultCustomerMgrService implements CustomerMgrService {

    @Autowired
    private CustomerMgrDao customerMgrDao;
    
    @Override
    public List<Customer> fetchCustomers(BigDecimal customerID) {
      log.info("The fetchCustomers method was called with customerID = {}");

      List<Customer> customers = customerMgrDao.fetchCustomers(customerID);

      if (customers.isEmpty()) {
        String msg = String.format("No icons found with serialNumber=%s", customerID.toString());
        throw new NoSuchElementException(msg);
      }

      Collections.sort(customers);
      return customers;

    }

  }
