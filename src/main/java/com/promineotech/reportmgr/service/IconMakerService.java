package com.promineotech.reportmgr.service;

import com.promineotech.reportmgr.entity.Icon;
import com.promineotech.reportmgr.entity.NewIcon;

public interface IconMakerService {
  Icon createIcon(NewIcon newIcon);
  }
