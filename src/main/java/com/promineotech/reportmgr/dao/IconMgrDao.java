package com.promineotech.reportmgr.dao;

import java.math.BigDecimal;
import java.util.List;
import com.promineotech.reportmgr.entity.Icon;

public interface IconMgrDao {
  List<Icon> fetchIcons(BigDecimal serialNumber);
}
