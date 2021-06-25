package com.fehead.culturalrelicsdatabase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fehead.culturalrelicsdatabase.core.response.CommonReturnType;
import com.fehead.culturalrelicsdatabase.entity.Relic;
import com.fehead.culturalrelicsdatabase.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author Verge
 * @Date 2021/6/19 15:05
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/v1/doc")
public class DocController extends BaseController{
    @Autowired
    private DocService docService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    @Secured({"ROLE_admin","ROLE_user"})
    public void export(HttpServletResponse response, @RequestParam List<String> ids) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment;filename="+System.currentTimeMillis()+".json");

        List<Relic> list = docService.getDocsByIds(ids);

        OutputStream outputStream = response.getOutputStream();
        objectMapper.writeValue(outputStream,list);

        outputStream.close();
    }

    @PostMapping
    @Secured({"ROLE_admin"})
    public CommonReturnType import_(@RequestParam MultipartFile file) throws IOException {
        Relic[] relics = objectMapper.readValue(file.getInputStream(),Relic[].class);
        List<Relic> list = Arrays.asList(relics);
        docService.saveRelics(list);

        return CommonReturnType.create("ok");
    }


}