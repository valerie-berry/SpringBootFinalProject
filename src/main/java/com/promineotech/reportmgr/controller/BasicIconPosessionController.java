package com.promineotech.reportmgr.controller;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.reportmgr.entity.IconCustomer;
import com.promineotech.reportmgr.service.IconPosessionUpdaterService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j

public class BasicIconPosessionController implements IconPosessionController {

  @Autowired
  private IconPosessionUpdaterService ipus;
  
  @Override
  public IconCustomer updatePosession(BigDecimal serialNumber, BigDecimal customerID, String date) {
    log.debug("IconPossession={} : {} : {}", serialNumber, customerID, date);
    return ipus.updatePosession(serialNumber, customerID, date);
  }

}
