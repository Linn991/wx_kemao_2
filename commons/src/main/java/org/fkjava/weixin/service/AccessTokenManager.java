package org.fkjava.weixin.service;

public interface AccessTokenManager {
	/*
	 * 此方法始终都要返回一个合法的令牌，如果没有获得令牌应该抛出异常。
	 * account微信公众号的微信号，要根据微信号找到对应的开发者ID，要通过开发者ID和密码才能得到正确的令牌。
	 * @throws RuntimeException没有获得令牌则抛出异常
	 * */
	String getToken(String account) throws RuntimeException;
}
