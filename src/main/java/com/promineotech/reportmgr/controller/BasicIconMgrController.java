package com.promineotech.reportmgr.controller;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.reportmgr.entity.Icon;
import com.promineotech.reportmgr.entity.IconCustomer;
import com.promineotech.reportmgr.entity.NewIcon;
import com.promineotech.reportmgr.service.IconMakerService;
import com.promineotech.reportmgr.service.IconMgrService;
import com.promineotech.reportmgr.service.IconPosessionUpdaterService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j

public class BasicIconMgrController implements IconMgrController {
  
  @Autowired
  private IconMakerService iconMakerService;
  
  @Autowired
  private IconMgrService iconMgrService;
  

  
  @Override
  public List<Icon> fetchIcons(BigDecimal serialNumber) {
    log.debug("serialumber={}");
    return iconMgrService.fetchIcons(serialNumber);
  }

  @Override
  public Icon createIcon(NewIcon newIcon) {
    log.debug("Icon={}", newIcon);
    return iconMakerService.createIcon(newIcon);
  }

}
