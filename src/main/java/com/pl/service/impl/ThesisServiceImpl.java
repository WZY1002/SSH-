package com.pl.service.impl;

import com.pl.dao.ThesisDao;
import com.pl.model.TeacherEntity;
import com.pl.model.ThesisEntity;
import com.pl.service.ThesisService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ThesisServiceImpl implements ThesisService {

    @Resource
    private ThesisDao thesisDao;

    public List<ThesisEntity> list(int start,int end, String title) {
        List<ThesisEntity> list=thesisDao.list(start,end,title);
        int index=0;
        if(list!=null){
            for (ThesisEntity thesisEntity:list) {
                index=index+1;
                thesisEntity.setIndex(index);
            }
        }
        return list;
    }

    public int listCount(String title) {
        return thesisDao.ListCount(title);
    }

    public void delete(String id) {
        thesisDao.Delete(id);
    }
}
