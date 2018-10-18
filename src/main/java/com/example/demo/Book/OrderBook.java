package com.example.demo.Book;


import com.example.demo.Model.OrderInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface OrderBook {
    @Insert("INSERT INTO OrderTable(orderId,productId,accountId,state,createDate,updateDate,payment,remark,count) " +
            "VALUES(#{orderId}, #{productId},#{accountId},#{state},#{createDate},#{updateDate},#{payment},#{remark},#{count})")
    int Insert(OrderInfo orderInfo);
}
