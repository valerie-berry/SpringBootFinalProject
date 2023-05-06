package com.promineotech.reportmgr.dao;

import com.promineotech.reportmgr.entity.Icon;
import com.promineotech.reportmgr.entity.NewIcon;

public interface IconMakerDao {

  Icon saveIcon(NewIcon newIcon);
}
