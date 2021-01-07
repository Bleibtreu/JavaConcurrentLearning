package pers.bleibtreu.redis.spike.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.bleibtreu.redis.spike.vo.ResponseMessage;

import javax.servlet.http.HttpServletResponse;

/**
 * @author bleibtreu
 * @date 2021/1/6 11:45
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public ResponseMessage globalException(HttpServletResponse response, RuntimeException ex){
        log.info("GlobalExceptionHandler...");
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus(500);
        responseMessage.setDesc(ex.getMessage());
        return responseMessage;
    }
}
