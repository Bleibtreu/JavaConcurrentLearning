package pers.bleibtreu.redis.spike.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pers.bleibtreu.redis.spike.entity.Phone;
import pers.bleibtreu.redis.spike.service.PhoneService;
import pers.bleibtreu.redis.spike.vo.ResponseMessage;

import java.util.List;

/**
 * @author bleibtreu
 * @date 2020/12/28 16:58
 */
@RestController("spike")
public class SpikeController {

    @Autowired
    private PhoneService phoneService;

    @RequestMapping(value = "getNum", method = RequestMethod.GET)
    public ResponseMessage InitNum() {
        ResponseMessage responseMessage = new ResponseMessage();
        phoneService.initNum();
        responseMessage.setStatus(1);
        responseMessage.setDesc("初始化成功");
        return responseMessage;
    }

    @RequestMapping(value = "getNum", method = RequestMethod.GET)
    public ResponseMessage getNum() {
        ResponseMessage responseMessage = new ResponseMessage();
        List<Phone> phones = phoneService.getAllInfo();
        responseMessage.setStatus(1);
        responseMessage.setDesc("获取成功");
        responseMessage.setBody(phones);
        return responseMessage;
    }

    @RequestMapping(value = "order", method = RequestMethod.POST)
    public ResponseMessage orderHandle() {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus(1);
        responseMessage.setDesc("下单成功");
        return responseMessage;
    }

    @RequestMapping(value = "cancelOrder", method = RequestMethod.POST)
    public ResponseMessage cancelOrder() {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus(1);
        responseMessage.setDesc("取消订单成功");
        return responseMessage;
    }
}
