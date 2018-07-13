package com.zhengtou.cf.handler;

import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.vo.ErrorFessionVO;
import com.zhengtou.cf.common.pojo.vo.NetVO;
import com.zhengtou.cf.common.pojo.vo.SuccFessionVO;
import com.zhengtou.cf.common.utils.StringUtils;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.List;


/**
 * 统一异常处理
 */
@RestControllerAdvice
public class UniformExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(UniformExceptionHandler.class);

    /**
     * 业务类异常
     */
    @ExceptionHandler({BaseException.class})
    public NetVO processCfException(BaseException exception) {
        exception.printStackTrace();
        logger.info("自定义异常处理-BaseException:" + exception.getMessage());
        return new SuccFessionVO(exception.getErrorCode(), exception.getErrorMsg());
    }

    /**
     * 参数验证异常
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public Object process(ConstraintViolationException exception) {
        logger.info("自定义异常处理-参数验证Exception:" + exception.getStackTrace());
        if (!exception.getConstraintViolations().isEmpty()) {
            for (ConstraintViolation constraintViolation : exception.getConstraintViolations()) {
                return new SuccFessionVO(RtnResultEnum.E030000.name(), constraintViolation.getMessage());
            }
        }
        return new SuccFessionVO(RtnResultEnum.U000000.name(), "未知异常");
    }

    /**
     * 对象构建参数验证异常
     */
    //不满足认证条件
    @ExceptionHandler({ValidationException.class})
    public Object process(ValidationException exception) {
        logger.info("对象构建参数验证异常-不满足认证条件ValidationException:" + exception.getStackTrace());
        exception.printStackTrace();
        return new SuccFessionVO(RtnResultEnum.E030000);
    }
    //满足认证条件
    @ExceptionHandler({BindException.class})
    public Object process(BindException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> errors = bindingResult.getFieldErrors();
        if (!errors.isEmpty()) {
            StringBuffer errorMsg = new StringBuffer("");
            for (FieldError fieldError : errors) {
                errorMsg.append(fieldError.getDefaultMessage() + "|");
            }
            return new SuccFessionVO(RtnResultEnum.E030000.name(), StringUtils.isNotBlank(errorMsg.toString()) ? errorMsg.substring(0, errorMsg
                    .length() - 1) : RtnResultEnum.E030000.getMsg());
        }
        logger.info("对象构建参数验证异常-满足认证条件BindException:" + exception.getStackTrace());
        return new SuccFessionVO(RtnResultEnum.U000000.name(), "未知异常");
    }

    /**
     * 数据库唯一键重复异常
     */
    @ExceptionHandler({DataIntegrityViolationException.class})
    public Object process(DataIntegrityViolationException exception) {
        logger.info("自定义异常处理-数据库Exception:" + exception.getStackTrace());
        PSQLException psqlException = (PSQLException) exception.getCause().getCause();
        return new SuccFessionVO(RtnResultEnum.E050001.name(), psqlException.getMessage());
    }

    @ExceptionHandler({RuntimeException.class})
    public NetVO processException(RuntimeException exception) {
        exception.printStackTrace();
        logger.info("RuntimeException:" + exception.getStackTrace());
        return new ErrorFessionVO(exception.getClass().getName(), exception.getStackTrace()[0].toString());
    }

    @ExceptionHandler({Exception.class})
    public NetVO process(Exception exception) {
        exception.printStackTrace();
        logger.info("Exception:" + exception.getStackTrace());
        return new ErrorFessionVO(exception.getClass().getName(), exception.getStackTrace()[0].toString());
    }
}
