package com.zhengtou.cf.handler;

import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.vo.ErrorFessionVO;
import com.zhengtou.cf.common.pojo.vo.SuccFessionVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;


/**
 * 统一异常处理
 */
@RestControllerAdvice
public class UniformExceptionHandler {

    private static final Log logger = LogFactory.getLog(UniformExceptionHandler.class);

    /**
     * 业务类异常
     */
    @ExceptionHandler({BaseException.class})
    public Object processException(BaseException exception) {
        logger.info("自定义异常处理-BaseException" + exception.getMessage());
        return new ErrorFessionVO(exception.getErrorCode(), exception.getErrorMsg());
    }

    /**
     * 参数验证异常
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public Object process(ConstraintViolationException exception) {
        logger.info("自定义异常处理-ConstraintViolationException" + exception.getStackTrace());
        if(!exception.getConstraintViolations().isEmpty()){
            for(ConstraintViolation constraintViolation:exception.getConstraintViolations()){
                return new SuccFessionVO(RtnResultEnum.E030000.name(),constraintViolation.getMessage());
            }
        }
        return new SuccFessionVO(RtnResultEnum.U000000.name(), "未知异常");
    }

    @ExceptionHandler({Exception.class})
    public Object process(Exception exception) {
        exception.printStackTrace();
        logger.info("自定义异常处理-Exception" + exception.getStackTrace());
        return new ErrorFessionVO(exception.getMessage(), exception.getLocalizedMessage());
    }

}
