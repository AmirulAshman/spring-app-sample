package com.ashman.sample.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashman.sample.api.switchgames.SwitchGamesClient;
import com.ashman.sample.api.switchgames.Response;
import com.ashman.sample.utility.ObjectMapperUtil;
import com.ashman.sample.utility.PaginationUtil;

@RestController
@RequestMapping(path = "/sample/api")
public class GamesController {

    @Autowired
    SwitchGamesClient switchGamesClient;

    Logger logger = LoggerFactory.getLogger(GamesController.class);

    @GetMapping("/games")
    public ResponseEntity<Page<Response>> getAllGames(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        logger.info("Request: " + ObjectMapperUtil.toJson(null));
        List<Response> response = switchGamesClient.callGetSwitchGamesList().getBody();

        PaginationUtil<Response> paginationUtil = new PaginationUtil<>();
        Page<Response> pagedResponse = paginationUtil.listToPage(response, page, size);

        logger.info("Response: " + response);
        return ResponseEntity.status(HttpStatus.OK).body(pagedResponse);
    }

}
