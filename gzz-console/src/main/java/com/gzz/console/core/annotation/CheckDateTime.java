package com.gzz.console.core.annotation;

import com.gzz.console.core.validator.DateTimeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Target({ElementType.FIELD, ElementType.PARAMETER}) // 约束注解应用的目标元素类型
@Retention(RetentionPolicy.RUNTIME) // 约束注解应用的时机
@Constraint(validatedBy = DateTimeValidator.class) // 与约束注解关联的验证器
public @interface CheckDateTime {
    String message() default "格式错误";

    String format() default "yyyy-MM-dd";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
