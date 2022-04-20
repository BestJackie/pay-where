package com.jc.paywhere.web.controller;

import com.jc.paywhere.commom.model.ServerResponse;
import com.jc.paywhere.dao.vo.ConsumeVO;
import com.jc.paywhere.service.ConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FileName: ConsumeController
 *
 * @author: admin
 * Date:     2021-09-17 14:05
 * Description:
 * Since: 1.0.0
 */

@RestController
@RequestMapping("consume")
public class ConsumeController {
    @Autowired
    private ConsumeService consumeService;

    @PostMapping
    public ServerResponse save(@RequestBody ConsumeVO consumeVO) {
        consumeService.save(consumeVO);
        return ServerResponse.success();
    }


}
