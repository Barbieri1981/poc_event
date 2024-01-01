package com.poc.security.constants;

public final class Constants {

	private Constants() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	// URLs and HTTP methods
	public static final String POST = "POST";
	public static final String LOGIN_URL = "/login";
	public static final String USER = "/user";

	// JWT Token Constants
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";
	public static final String ISSUER_INFO = "security";
	public static final long TOKEN_EXPIRATION_TIME = 900; // 15 minutes
	public static final String AUTHORITIES_KEY = "auth";
	public static final String ROLE = "ROLE_";

	public static final String SECRET_KEY ="a2Fma2EgcG9jIGV2ZW50b3NkczBIdE5pT05ocnlIYlNnbDRfdTNyWmdHd0lmS2IzMXpnMFNEdXJySXlVeTdGd2hEdHpTU1RNa1Z5blpCSkk=";
}
