package com.promineotech.reportmgr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.reportmgr.dao.IconMakerDao;
import com.promineotech.reportmgr.entity.Icon;
import com.promineotech.reportmgr.entity.NewIcon;

@Service
public class DefaultIconMakerService implements IconMakerService {

  @Autowired
  private IconMakerDao iconMakerDao;
  
  @Override
  public Icon createIcon(NewIcon newIcon) {
    return iconMakerDao.saveIcon(newIcon);
  }

}
