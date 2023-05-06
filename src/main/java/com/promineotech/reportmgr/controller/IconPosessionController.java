package com.promineotech.reportmgr.controller;

import java.math.BigDecimal;
import javax.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.reportmgr.entity.IconCustomer;
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
@RequestMapping("/posession")
@OpenAPIDefinition(info = @Info(title = "ICON Posession Updater Service"),
    servers = {@Server(url = "http://localhost:8080", description = "Local server.")})


public interface IconPosessionController {

  // @formatter:off
  @Operation(
      summary = "Updates posession of an ICON",
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
            required = true,
            description = "The serial number of the ICON."
          ),
          @Parameter(name = "customerID",
          allowEmptyValue = false,
          required = true,
          description = "The ID of the customer."
        ),
          @Parameter(name = "date",
          allowEmptyValue = false,
          required = true,
          description = "The date the customer received the ICON."
        ),
      }
  )

  @PostMapping
  @ResponseStatus(code = HttpStatus.OK)
  IconCustomer updatePosession (
    //@Size(max = Constants.ICON_SERIALNUM_MAX_LENGTH)
    //@Pattern(regexp = "[\\w\\s]*")
      BigDecimal serialNumber, BigDecimal customerID, @Pattern(regexp = "[0-9]{4}-[0-1][0-9]-[0-9]{2}") String date);
}

// @formatter:on
