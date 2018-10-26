package com.example.demo.Controller;


import com.example.demo.Model.ResponseInfo;
import com.example.demo.Service.OrderService;
import com.example.demo.Tools.JsonTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModifyOrderStateController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/ModifyOrderState")
    public String Index(String orderId,int state){

     ResponseInfo modifyResultInfo= orderService.ModifyOrder(orderId,state);

        if(modifyResultInfo.getCode()==0){

            return JsonTool.SerializeObject(modifyResultInfo);
        } else {
            return JsonTool.SerializeObject(new ResponseInfo(-1,modifyResultInfo.getMessage()));
        }
    }
}
