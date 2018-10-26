package com.example.demo.Service;

import com.example.demo.Base.Person;
import com.example.demo.Book.OrderBook;
import com.example.demo.Enm.OrderState;
import com.example.demo.Model.OrderInfo;
import com.example.demo.Model.ResponseInfo;
import com.example.demo.Tools.HttpTool;
import com.example.demo.Tools.JsonTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Component
public class OrderService extends Person {

    @Autowired
    private OrderBook orderBook;

    public ResponseInfo CreateOrder(int productId, int accountId, String remark, Integer count) {
        try {
            ///CallInterface, 计算订单总金额。
            String countPaymentUrl = "http://localhost:8089/CountPayment";
            String countPaymentParma = String.format("productId=%s&count=%d", productId, count);
            String countPaymentResultStr = HttpTool.sendGet(countPaymentUrl, countPaymentParma);
            ResponseInfo countPaymentResponseInfo = JsonTool.DeserializeObject(countPaymentResultStr);
            ///订单总金额。
            BigDecimal payment = new BigDecimal(countPaymentResponseInfo.getData().toString());

            if (countPaymentResponseInfo.getCode() == 0) {/// 插入订单。
                ///订单编号 = 商品ID + 时间戳 + 用户ID。
                String timeStamp = String.valueOf(productId) + new Date().getTime() + String.valueOf(accountId);
                OrderInfo orderInfo = new OrderInfo(timeStamp, productId, accountId, OrderState.WaitPay, new Timestamp(new Date().getTime()),
                        new Timestamp(new Date().getTime()), payment, remark, count);

                this.orderBook.Insert(orderInfo);

                return ProvideResult(0, JsonTool.SerializeInfo(orderInfo));
            } else {
                return ProvideResult(-1, countPaymentResponseInfo.getMessage());
            }
        } catch (Exception ex) {
            ///写日志。
            return ProvideResult(-1, ex.getMessage());
        } finally {
            /// 释放资源等。
        }
    }

    public ResponseInfo ModifyOrder(String orderId, int state) {

        try {
            int modifyResult = orderBook.ModifyState(orderId, Integer.valueOf(state));
            return ProvideResult(0, "Sucess!!");
        } catch (Exception ex) {
            ///TODO
            return ProvideResult(-1, ex.getMessage());
        } finally {
            ///TODO
        }


    }
}
