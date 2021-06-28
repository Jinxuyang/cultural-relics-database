package com.fehead.culturalrelicsdatabase.controller;

import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fehead.culturalrelicsdatabase.core.response.CommonReturnType;
import com.fehead.culturalrelicsdatabase.entity.Relic;
import com.fehead.culturalrelicsdatabase.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
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

    @Autowired
    private SymmetricCrypto symmetricCrypto;

    @GetMapping
    @Secured({"ROLE_admin","ROLE_user"})
    public void export(HttpServletResponse response, @RequestParam List<String> ids) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment;filename="+System.currentTimeMillis()+".relics");

        List<Relic> list = docService.getDocsByIds(ids);

        String doc = objectMapper.writeValueAsString(list);

        OutputStream outputStream = response.getOutputStream();
        objectMapper.writeValue(outputStream, symmetricCrypto.encryptBase64(doc));

        outputStream.close();
    }

    @PostMapping
    @Secured({"ROLE_admin"})
    public CommonReturnType import_(@RequestParam MultipartFile file) throws IOException {
        String doc = objectMapper.readValue(file.getInputStream(),String.class);
        System.out.println();

        Relic[] relics = objectMapper.readValue(symmetricCrypto.decrypt(doc),Relic[].class);
        List<Relic> list = Arrays.asList(relics);
        docService.saveRelics(list);

        return CommonReturnType.create("ok");
    }


}