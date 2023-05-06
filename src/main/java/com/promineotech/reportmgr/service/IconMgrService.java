package com.promineotech.reportmgr.service;

import java.math.BigDecimal;
import java.util.List;
import com.promineotech.reportmgr.entity.Icon;

public interface IconMgrService {

  List<Icon> fetchIcons(BigDecimal serialNumber);
}
