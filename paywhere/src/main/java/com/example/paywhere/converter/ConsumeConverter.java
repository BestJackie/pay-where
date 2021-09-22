package com.example.paywhere.converter;

import com.example.paywhere.dao.entity.ConsumeRecord;
import com.example.paywhere.dao.vo.ConsumeVO;
import com.example.paywhere.web.security.SecurityUtils;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

/**
 * FileName: ConsumeConver
 *
 * @author: admin
 * Date:     2021-09-17 15:25
 * Description:
 * Since: 1.0.0
 */

public class ConsumeConverter extends AbstractConverter<ConsumeRecord, ConsumeVO> {


    public static ConsumeConverter create() {
        return new ConsumeConverter();
    }

    @Override
    public ConsumeRecord convertFrom(ConsumeVO consumeVO) {
        if (Objects.isNull(consumeVO))
            return null;
        ConsumeRecord consumeRecord = new ConsumeRecord();
        consumeRecord.setId(consumeVO.getId());
        if (!convertKeyOnly()) {
            BeanUtils.copyProperties(consumeVO, consumeRecord);
        }
        consumeRecord.setConsumer(SecurityUtils.getCurrentUser());
        return consumeRecord;
    }

    @Override
    public ConsumeVO convertTo(ConsumeRecord consumeRecord) {
        if (Objects.isNull(consumeRecord))
            return null;
        return new ConsumeVO();
    }

    //TODO
}
