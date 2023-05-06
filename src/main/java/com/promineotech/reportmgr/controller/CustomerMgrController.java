package com.promineotech.reportmgr.controller;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.reportmgr.entity.Customer;
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
@RequestMapping("/customers")
@OpenAPIDefinition(info = @Info(title = "Customer Manager Service"),
    servers = {@Server(url = "http://localhost:8080", description = "Local server.")})

public interface CustomerMgrController {

  // @formatter:off
  @Operation(
      summary = "Returns a list of customers",
      description = "Returns a list of customers given an optional serialNumber",
      responses = {
          @ApiResponse(responseCode = "200",
            description = "A list of customers is returned",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Report.class))
          ),
          @ApiResponse(responseCode = "400",
            description = "The request parameters are invalid",
            content = @Content(mediaType = "application/json")
          ),
          @ApiResponse(responseCode = "404",
            description = "No customers were found with the input criteria",
            content = @Content(mediaType = "application/json")
          ),
          @ApiResponse(responseCode = "500",
            description = "An unplanned error occured",
            content = @Content(mediaType = "application/json")
          )
      },
      parameters = {
          @Parameter(name = "customerID",
            allowEmptyValue = false,
            required = false,
            description = "The ID of the customer."
          )
      }
  )

  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  List<Customer> fetchCustomers(
    //@Size(max = Constants.customer_SERIALNUM_MAX_LENGTH)
    //@Pattern(regexp = "[\\w\\s]*")
    @RequestParam(required = false)
      BigDecimal customerID);
}

// @formatter:on

