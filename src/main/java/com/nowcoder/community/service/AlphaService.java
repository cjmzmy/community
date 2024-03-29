package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Spring的Bean默认是单例的，
 * 若想不使用单例，则加注解：@Scope("prototype") 但是基本不使用 一般使用默认的单例
 */
@Service
//@Scope("prototype")
public class AlphaService {
    @Autowired
    private AlphaDao alphaDao;

    public AlphaService(){
        //System.out.println("实例化AlphaService");
    }

    @PostConstruct
    public void init(){
        //System.out.println("初始化AlphaService");
    }

    @PreDestroy
    public void destory(){
        //System.out.println("销毁AlphaService");
    }

    public String find(){
        return alphaDao.select();
    }
}
