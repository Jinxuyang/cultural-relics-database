package com.fehead.culturalrelicsdatabase.controller;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fehead.culturalrelicsdatabase.core.error.BusinessException;
import com.fehead.culturalrelicsdatabase.core.error.EmBusinessError;
import com.fehead.culturalrelicsdatabase.core.response.CommonReturnType;
import com.fehead.culturalrelicsdatabase.dto.Doc;
import com.fehead.culturalrelicsdatabase.entity.Relic;
import com.fehead.culturalrelicsdatabase.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Value("${verify.md5.key}")
    private String key;

    @GetMapping
    @Secured({"ROLE_admin","ROLE_user"})
    public void export(HttpServletResponse response, @RequestParam List<String> ids) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment;filename="+System.currentTimeMillis()+".relics");

        List<Relic> list = docService.getDocsByIds(ids);

        String data = symmetricCrypto.encryptBase64(objectMapper.writeValueAsString(list));
        String md5 = SecureUtil.md5(data+key);
        Doc doc = new Doc(data,md5);

        OutputStream outputStream = response.getOutputStream();
        objectMapper.writeValue(outputStream, doc);

        outputStream.close();
    }

    @PostMapping
    @Secured({"ROLE_admin"})
    public CommonReturnType import_(@RequestParam MultipartFile file) throws Exception {
        Doc doc = objectMapper.readValue(file.getInputStream(),Doc.class);

        if (!SecureUtil.md5(doc.getData()+key).equals(doc.getMd5())){
            throw new BusinessException(EmBusinessError.PRIMARY_ERROR,"文件不合法,请勿修改文件");
        }

        Relic[] relics = objectMapper.readValue(symmetricCrypto.decrypt(doc.getData()),Relic[].class);
        List<Relic> list = Arrays.asList(relics);
        docService.saveRelics(list);

        return CommonReturnType.create("ok");
    }


}