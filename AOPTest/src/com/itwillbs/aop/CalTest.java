package com.itwillbs.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CalTest {

	public static void main(String[] args) {
		
		// 계산기 기능
		Calculator c = new Calculator();
		c.add(100, 200);
		
		System.out.println("-----------------------------------------");
		
		// AOP를 활용한 계산기 기능 사용
		
		ApplicationContext CTX
              = new ClassPathXmlApplicationContext("AOPtest.xml");
		
		// 계산기 객체 생성
		Calculator c2 
		      = (Calculator)CTX.getBean("proxyCal");
		
		c2.add(500, 400);
		
		c2.mul(40, 2);
		
		
		
		
		
		
		
		
	}

}
