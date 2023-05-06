package com.promineotech.reportmgr.service;

import java.math.BigDecimal;
import com.promineotech.reportmgr.entity.IconCustomer;

public interface IconPosessionUpdaterService {
  IconCustomer updatePosession(BigDecimal serialNumber, BigDecimal customerID, String date);
}
