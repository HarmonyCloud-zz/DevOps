package com.harmony.devops.context.handler;

import com.harmony.devops.common.enums.RtnResultEnum;
import com.harmony.devops.common.exception.BaseException;
import com.harmony.devops.common.vo.ErrorFessionVO;
import com.harmony.devops.common.vo.NetVO;
import com.harmony.devops.common.vo.SuccFessionVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;


/**
 * 统一异常处理
 */
@RestControllerAdvice
public class UniformExceptionHandler{

    private static final Logger logger = LoggerFactory.getLogger(UniformExceptionHandler.class);

    /**
     * 业务类异常
     */
    @ExceptionHandler({BaseException.class})
    public NetVO processCfException(BaseException exception) {
        exception.printStackTrace();
        logger.info("自定义异常处理-BaseException" + exception.getMessage());
        return new SuccFessionVO(exception.getErrorCode(), exception.getErrorMsg());
    }

    /**
     * 参数验证异常
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public Object process(ConstraintViolationException exception) {
        logger.info("自定义异常处理-Exception" + exception.getStackTrace());
        if(!exception.getConstraintViolations().isEmpty()){
            for(ConstraintViolation constraintViolation:exception.getConstraintViolations()){
                return new SuccFessionVO(RtnResultEnum.E030000.name(),constraintViolation.getMessage());
            }
        }
        return new SuccFessionVO(RtnResultEnum.U000000.name(), "未知异常");
    }

    @ExceptionHandler({RuntimeException.class})
    public NetVO processException(RuntimeException exception) {
        exception.printStackTrace();
        logger.info("自定义异常处理-RuntimeException:" + exception.getStackTrace());
        return new ErrorFessionVO(exception.getClass().getName(), exception.getStackTrace()[0].toString());
    }

    @ExceptionHandler({Exception.class})
    public NetVO process(Exception exception) {
        exception.printStackTrace();
        logger.info("自定义异常处理-Exception:" + exception.getStackTrace());
        return new ErrorFessionVO(exception.getClass().getName(), exception.getStackTrace()[0].toString());
    }
}
