package com.example.demo.Controller;


import com.example.demo.Model.ResponseInfo;
import com.example.demo.Service.OrderService;
import com.example.demo.Tools.JsonTool;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CreateOrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/CreateOrder")
    public String Index(int productId, int accountId, String remark, Integer count){

        ///生成订单。 ///这里还需要再次进行订单数量验证，如何把 query 和 set 包装成原子操作呢？ query 和 set 的过程中，
        // 保证商品数量不变呢，利用悲观锁，把所有扣库存的操作锁起来。（但是如果不是在一个系统中呢）
        ResponseInfo result=orderService.CreateOrder(productId,accountId,remark,count);
        ///响应。
        return JsonTool.SerializeObject(result);
    }
}
