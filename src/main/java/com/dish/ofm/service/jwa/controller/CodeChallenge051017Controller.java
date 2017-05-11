package com.dish.ofm.service.jwa.controller;

import com.dish.ofm.service.jwa.service.FindSummerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "/findSummer", description = "Code Challenge 051017")
public class CodeChallenge051017Controller {
    private static Logger LOGGER = LoggerFactory.getLogger(CodeChallenge051017Controller.class);
    private final FindSummerService findSummerService;

    @Autowired
    public CodeChallenge051017Controller(
        FindSummerService findSummerService
    ) {
        this.findSummerService = findSummerService;
    }

    @RequestMapping(value = "/findSummer", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "fundSummer", httpMethod = "GET",
        response = HttpStatus.class,
        produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Groovy", response = HttpStatus.class),
        @ApiResponse(code = 500, message = "Internal server error")})
    public ResponseEntity findSummer(
        @RequestParam("min") String smin,
        @RequestParam("max") String smax,
        @RequestParam("total") String stotal
    ) {
        LOGGER.info("Received /findSummer");

        int min = Integer.parseInt(smin);
        int max = Integer.parseInt(smax);
        int total = Integer.parseInt(stotal);

        if (min < 0 || max < 0 || total < 0) {
            return ResponseEntity.notFound().build();
        }
        ResponseEntity<Void> responseEntity = ResponseEntity.ok().build();
        findSummerService.findSummer(min,max,total);

        LOGGER.info("Finished /findSummer with  responseEntity = {}", responseEntity);
        return responseEntity;
    }
}
