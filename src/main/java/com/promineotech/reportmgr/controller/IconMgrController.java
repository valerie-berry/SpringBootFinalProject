package com.promineotech.reportmgr.controller;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.reportmgr.entity.Icon;
import com.promineotech.reportmgr.entity.IconCustomer;
import com.promineotech.reportmgr.entity.NewIcon;
import com.promineotech.reportmgr.entity.Report;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/icons")
@OpenAPIDefinition(info = @Info(title = "ICON Manager Service"),
    servers = {@Server(url = "http://localhost:8080", description = "Local server.")})


public interface IconMgrController {

  // @formatter:off
    @Operation(
        summary = "Returns a list of ICONs",
        description = "Returns a list of ICONs given an optional serialNumber",
        responses = {
            @ApiResponse(responseCode = "200",
              description = "A list of ICONs is returned",
              content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = Report.class))
            ),
            @ApiResponse(responseCode = "400",
              description = "The request parameters are invalid",
              content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(responseCode = "404",
              description = "No ICONs were found with the input criteria",
              content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(responseCode = "500",
              description = "An unplanned error occured",
              content = @Content(mediaType = "application/json")
            )
        },
        parameters = {
            @Parameter(name = "serialNumber",
              allowEmptyValue = false,
              required = false,
              description = "The serial number of the ICON."
            )
        }
    )

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    List<Icon> fetchIcons(
      //@Size(max = Constants.ICON_SERIALNUM_MAX_LENGTH)
      //@Pattern(regexp = "[\\w\\s]*")
      @RequestParam(required = false)
        BigDecimal serialNumber);
    
    @PostMapping
    @ResponseStatus(code = HttpStatus.OK)
    Icon createIcon(
      //@Size(max = Constants.ICON_SERIALNUM_MAX_LENGTH)
      //@Pattern(regexp = "[\\w\\s]*")
        NewIcon newIcon);
    
    /*@PostMapping
    @ResponseStatus(code = HttpStatus.OK)
    IconCustomer updatePosession (
      //@Size(max = Constants.ICON_SERIALNUM_MAX_LENGTH)
      //@Pattern(regexp = "[\\w\\s]*")
        BigDecimal serialNumber, BigDecimal customerID, @Pattern(regexp = "[0-9]{4}-[0-1][0-9]-[0-9]{2}") String date);
*/
}

  // @formatter:on
