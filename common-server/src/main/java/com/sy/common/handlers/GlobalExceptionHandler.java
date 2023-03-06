package com.sy.common.handlers;

import com.sy.common.execeptions.MyRuntimeException;
import com.sy.common.rest.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义运行异常处理器
     * @param e
     * @return
     */
    @ExceptionHandler(MyRuntimeException.class)
    @ResponseBody
    public R myRuntimeException(Exception e){
        return R.error(e.getMessage());
    }

    /**
     * Validated参数校验异常
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public R handle(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        StringBuffer sb=new StringBuffer();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            sb.append(constraintViolation.getMessage());
            break;
        }
        return R.error(sb.toString());
    }
}
