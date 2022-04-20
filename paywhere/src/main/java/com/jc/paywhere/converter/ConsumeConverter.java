package com.jc.paywhere.converter;

import com.jc.paywhere.dao.entity.PayEntity;
import com.jc.paywhere.dao.vo.ConsumeVO;
import com.jc.paywhere.web.security.SecurityUtils;
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

public class ConsumeConverter extends AbstractConverter<PayEntity, ConsumeVO> {


    public static ConsumeConverter create() {
        return new ConsumeConverter();
    }

    @Override
    public PayEntity convertFrom(ConsumeVO consumeVO) {
        if (Objects.isNull(consumeVO))
            return null;
        PayEntity consumeRecord = new PayEntity();
        consumeRecord.setId(consumeVO.getId());
        if (!convertKeyOnly()) {
            BeanUtils.copyProperties(consumeVO, consumeRecord);
        }
        consumeRecord.setConsumer(SecurityUtils.getCurrentUser());
        return consumeRecord;
    }

    @Override
    public ConsumeVO convertTo(PayEntity payEntity) {
        if (Objects.isNull(payEntity))
            return null;
        return new ConsumeVO();
    }

    //TODO
}
