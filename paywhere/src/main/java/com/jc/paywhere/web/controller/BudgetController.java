package com.jc.paywhere.web.controller;

import com.jc.paywhere.commom.model.ServerResponse;
import com.jc.paywhere.dao.entity.BudgetEntity;
import com.jc.paywhere.dao.vo.BudgetVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FileName: BudgetController
 *
 * @author: admin
 * Date:     2021-12-21 14:42
 * Description:
 * Since: 1.0.0
 */

@RestController
@RequestMapping("budget")
public class BudgetController {


    @PostMapping
    public ServerResponse save(@RequestBody BudgetVO budgetVO) {

        BudgetEntity budgetEntity = new BudgetEntity();
        return ServerResponse.success();
    }


}


