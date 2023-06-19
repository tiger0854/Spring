package com.itwillbs.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

// 로그출력 - 어드바이스
public class LoggingAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation inv) throws Throwable {

		System.out.println("[보조기능] : "+inv.getMethod()+" 실행 - 시작");	
		// 주기능 실행
		Object obj = inv.proceed();
		
		System.out.println("[보조기능] : "+inv.getMethod()+" 실행 - 끝");
		
		return obj;
	}
	
}
