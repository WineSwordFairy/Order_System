package com.example.demo.Book;


import com.example.demo.Model.OrderInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
@Mapper
public interface OrderBook {
    @Insert("INSERT INTO OrderTable(orderId,productId,accountId,state,createDate,updateDate,payment,remark,count) " +
            "VALUES(#{orderId}, #{productId},#{accountId},#{state},#{createDate},#{updateDate},#{payment},#{remark},#{count})")
    int Insert(OrderInfo orderInfo);



    @Update("UPDATE OrderTable SET State = #{state} WHERE orderId=#{orderId}")
    int ModifyState(@Param("orderId") String orderId,@Param("state") Integer state);
}
