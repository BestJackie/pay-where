package com.jc.paywhere.service.impl;

import com.jc.paywhere.converter.ConsumeConverter;
import com.jc.paywhere.dao.entity.PayEntity;
import com.jc.paywhere.dao.respository.ConsumeRepository;
import com.jc.paywhere.dao.vo.ConsumeVO;
import com.jc.paywhere.service.ConsumeService;
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
        PayEntity payEntity = converter.convertFrom(consumeVO);
        consumeRepository.save(payEntity);
    }

    //TODO
}
