package com.promineotech.reportmgr.service;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.reportmgr.dao.IconPosessionUpdaterDao;
import com.promineotech.reportmgr.entity.IconCustomer;

@Service

public class DefaultIconPosessionUpdaterService implements IconPosessionUpdaterService {

  @Autowired
  private IconPosessionUpdaterDao ipud;
  
  @Override
  public IconCustomer updatePosession(BigDecimal serialNumber, BigDecimal customerID, String date) {
    return ipud.updatePosession(serialNumber, customerID, date);
  }

}
