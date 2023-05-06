package com.promineotech.reportmgr.controller;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.reportmgr.entity.Customer;
import com.promineotech.reportmgr.service.CustomerMgrService;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j


public class BasicCustomerMgrController implements CustomerMgrController {
  
  @Autowired
  private CustomerMgrService customerMgrService;
  
  @Override
  public List<Customer> fetchCustomers(BigDecimal customerID) {
    log.debug("customerID={}");
    return customerMgrService.fetchCustomers(customerID);
  }

}