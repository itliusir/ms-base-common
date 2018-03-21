package com.itliusir.ms.handler;

import com.itliusir.ms.exception.BaseException;
import com.itliusir.ms.msg.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * 统一异常的处理
 *
 * @author liugang
 * @since 2018-03-07
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(BaseException.class)
    public BaseResponse baseExceptionHandler(HttpServletResponse response, BaseException ex) {
        logger.error(ex.getMessage(),ex);
        return new BaseResponse(ex.getStatus(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse otherExceptionHandler(HttpServletResponse response, Exception ex) {
        logger.error(ex.getMessage(),ex);
        return new BaseResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    /**
     * 参数校验产生的异常
     *
     * @author liugang 2018-01-01 16:28
     * */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse validExceptionHandler(HttpServletResponse response, MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuffer stringBuffer = new StringBuffer();
        if(bindingResult.hasErrors()){
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                //该格式仅仅作为response展示和log作用，前端应自己做校验
                stringBuffer.append(fieldError.getObjectName() + "--" + fieldError.getDefaultMessage() + " ");
            }
        }
        logger.error(stringBuffer.toString());
        return new BaseResponse(HttpStatus.BAD_REQUEST.value(), stringBuffer.toString());
    }
}
