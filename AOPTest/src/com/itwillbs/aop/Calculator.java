package com.itwillbs.aop;

// 계산기 - 타겟
public class Calculator {

	public void add(int a,int b) {
		//System.out.println("[보조기능] Calculator-add(int a,int b) 실행! ");
		System.out.println("[주기능] 결과 : "+(a+b));		
	}
	
	public void sub(int a,int b) {
		System.out.println("결과 : "+(a-b));
	}
	public void mul(int a,int b) {
		System.out.println("결과 : "+(a*b));
	}
	public void div(int a,int b) {
		System.out.println("결과 : "+(a/b));
	}
	 
}
