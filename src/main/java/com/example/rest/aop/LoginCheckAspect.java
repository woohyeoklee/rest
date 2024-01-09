package com.example.rest.aop;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
@Order
@Log4j2
public class LoginCheckAspect {

    @Around("@annotation(com.example.rest.aop.LoginCheck) && @annotation(LoginCheckType)")
    public Object adminLoginCheck(ProceedingJoinPoint proceedingJoinPoint, LoginCheck LoginCheckType) throws Throwable {
        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
        String id;

        LoginCheck.LoginCheckType userType = LoginCheckType.type();
        id = switch (userType) {
            case ADMIN -> {
                Object adminIdObject = session.getAttribute("adminId");
                yield (adminIdObject != null) ? adminIdObject.toString() : null;
            }
            case USER -> {
                Object memberIdObject = session.getAttribute("memberId");
                yield (memberIdObject != null) ? memberIdObject.toString() : null;
            }
        };

        if (id == null) {
            throw new HttpStatusCodeException(HttpStatus.UNAUTHORIZED, "로그인한 id 값을 확인해주세요.") {};
        }

        Object[] modifiedArgs = proceedingJoinPoint.getArgs();
        // 여기서 필요에 따라 수정된 인자 배열을 조작할 수 있습니다.

        return proceedingJoinPoint.proceed(modifiedArgs);
    }

}