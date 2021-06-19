package com.fehead.culturalrelicsdatabase.service;

import com.fehead.culturalrelicsdatabase.entity.Relic;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Verge
 * @Date 2021/6/19 15:10
 * @Version 1.0
 */
public interface DocService {
    List<Relic> getDocsByIds(List<String> ids);

    boolean saveRelics(List<Relic> list);
}