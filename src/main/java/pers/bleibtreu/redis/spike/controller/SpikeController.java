package pers.bleibtreu.redis.spike.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pers.bleibtreu.redis.spike.entity.Phone;
import pers.bleibtreu.redis.spike.service.PhoneService;
import pers.bleibtreu.redis.spike.vo.ResponseMessage;

import java.util.List;

/**
 * @author bleibtreu
 * @date 2020/12/28 16:58
 */
@Slf4j
@Api(value = "秒杀活动API")
@RestController("spike")
public class SpikeController {

    @Autowired
    private PhoneService phoneService;

    @ApiOperation(value = "手机库存初始化", notes = "")
    @RequestMapping(value = "initNum", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage InitNum() {
        ResponseMessage responseMessage = new ResponseMessage();
        phoneService.initNum();
        responseMessage.setStatus(1);
        responseMessage.setDesc("初始化成功");
        return responseMessage;
    }

    @ApiOperation("获取全部手机库存")
    @RequestMapping(value = "getNum", method = RequestMethod.GET)
    public ResponseMessage getNum() {
        ResponseMessage responseMessage = new ResponseMessage();
        List<Phone> phones = phoneService.getAllInfo();
        responseMessage.setStatus(1);
        responseMessage.setDesc("获取成功");
        responseMessage.setBody(phones);
        return responseMessage;
    }

    @ApiOperation("订单生成")
    @RequestMapping(value = "order", method = RequestMethod.POST)
    public ResponseMessage orderHandle(String phone, int orderNum) {
        ResponseMessage responseMessage = new ResponseMessage();
        phoneService.handleOrder(phone, orderNum);
        responseMessage.setStatus(1);
        responseMessage.setDesc("下单成功");
        return responseMessage;
    }

    @ApiOperation("取消订单")
    @RequestMapping(value = "cancelOrder", method = RequestMethod.POST)
    public ResponseMessage cancelOrder(String phone, int orderNum) {
        ResponseMessage responseMessage = new ResponseMessage();
        phoneService.cannelOrder(phone, orderNum);
        responseMessage.setStatus(1);
        responseMessage.setDesc("取消订单成功");
        return responseMessage;
    }
}
