package com.example.paywhere.service.impl;

import com.example.paywhere.converter.ConsumeConverter;
import com.example.paywhere.dao.entity.ConsumeRecord;
import com.example.paywhere.dao.respository.ConsumeRepository;
import com.example.paywhere.dao.vo.ConsumeVO;
import com.example.paywhere.service.ConsumeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * FileName: ConsumeServiceImpl
 *
 * @author: admin
 * Date:     2021-09-17 14:06
 * Description:
 * Since: 1.0.0
 */
@Service
public class ConsumeServiceImpl implements ConsumeService {

    @Resource
    private ConsumeRepository consumeRepository;

    @Override
    public void save(ConsumeVO consumeVO) {
        ConsumeConverter converter = ConsumeConverter.create();
        ConsumeRecord consumeRecord = converter.convertFrom(consumeVO);
        consumeRepository.save(consumeRecord);
    }

    //TODO
}
