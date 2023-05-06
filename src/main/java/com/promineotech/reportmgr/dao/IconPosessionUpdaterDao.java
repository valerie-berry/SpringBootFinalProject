package com.promineotech.reportmgr.dao;

import java.math.BigDecimal;
import com.promineotech.reportmgr.entity.IconCustomer;

public interface IconPosessionUpdaterDao {

  IconCustomer updatePosession(BigDecimal serialNumber, BigDecimal customerID, String date);
}
