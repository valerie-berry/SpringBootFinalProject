package com.promineotech.reportmgr.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.reportmgr.dao.IconMgrDao;
import com.promineotech.reportmgr.entity.Icon;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

public class DefaultIconMgrService implements IconMgrService {

  @Autowired
  private IconMgrDao iconMgrDao;
  
  @Override
  public List<Icon> fetchIcons(BigDecimal serialNumber) {
    log.info("The fetchIcons method was called with model={} and trim = {}");

    List<Icon> icons = iconMgrDao.fetchIcons(serialNumber);

    if (icons.isEmpty()) {
      String msg = String.format("No icons found with serialNumber=%s", serialNumber.toString());
      throw new NoSuchElementException(msg);
    }

    Collections.sort(icons);
    return icons;

  }

}
