package com.leverx.service.impl;

import com.leverx.dao.TagDao;
import com.leverx.entity.Tag;
import com.leverx.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;

    @Override
    @Transactional
    public List<Tag> getAllTags() {
        return tagDao.getAllTags();
    }
}
