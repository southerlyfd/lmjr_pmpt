package com.pmpt.controller;

import com.pmpt.common.Response;
import com.pmpt.common.ResponseStatusCode;
import com.pmpt.interfaces.Service.VerificationCodeService;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SessionAop {

	@Autowired
	private VerificationCodeService verificationCodeService;

	// 使用环绕方法需要配置切入点表达式
	@Pointcut("execution(* com.pmpt.controller.PublishController.*(..)) || execution(* com.pmpt.controller.PromotionController.*(..)) || execution(* com.pmpt.controller.HomeController.*(..))")
	private void sessionIsAvailable() {
	}

	/**
	 * 验证SessionId是否存在
	 *
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("sessionIsAvailable()")
	public Object aroundExecut(ProceedingJoinPoint joinPoint) throws Throwable {

		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod(); // 获取被拦截的方法
		String methodName = method.getName(); // 获取被拦截的方法名

		// 获取参数列表,请将sessionId至于参数列表第一位
		Object[] args = joinPoint.getArgs();
		String sessionId = (String) args[0];
		if (verificationCodeService.getRedisStr(sessionId) != null) {
			return joinPoint.proceed();
		}
		return new Response(ResponseStatusCode.PARAMETER_ERROR.getCode(), ResponseStatusCode.PARAMETER_ERROR.getDesc(),
				null, null);
	}
}
