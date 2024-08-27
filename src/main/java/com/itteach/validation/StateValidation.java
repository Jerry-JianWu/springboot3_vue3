package com.itteach.validation;

import com.itteach.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator</*给哪个注解提供规则*/State, String/*校验的数据类型*/> {
    /**
     *
     * @param value 将来要检验的数据
     * @param context context in which the constraint is evaluated
     * @return false not pass, true pass
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        // 提供校验规则
        if(value == null){
            return false;
        }
        if(value.equals("已发布")|| value.equals("草稿")){
            return true;
        }
        return false;
    }
}
