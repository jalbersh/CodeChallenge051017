package com.dish.ofm.service.jwa.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "/findSummer", description = "Code Challenge 051017")
public class CodeChallenge051017Controller {

    private static Logger LOGGER = LoggerFactory.getLogger(CodeChallenge051017Controller.class);

    @RequestMapping(value = "/findSummer", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "fundSummer", httpMethod = "GET",
        response = HttpStatus.class,
        produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Groovy", response = HttpStatus.class),
        @ApiResponse(code = 500, message = "Internal server error")})
    public ResponseEntity fundSummer() {
        LOGGER.info("Received /findSummer");

        ResponseEntity<Void> responseEntity = ResponseEntity.ok().build();

        LOGGER.info("Finished /findSummer with  responseEntity = {}", responseEntity);
        return responseEntity;
    }
}
