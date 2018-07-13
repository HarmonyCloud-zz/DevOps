package com.zhengtou.cf.enums;

public enum ClerkType {
	/**
	 * 经纪人
	 */
	A("经纪人"),
	/**
	 * 下户
	 */
	S("下户"),
	/**
	 * 公证
	 */
	N("公证"),
	/**
	 * 抵押
	 */
	M("抵押"),
	/**
	 * 风控
	 */
	C("风控");
	
	private String clerkType;

	public String getClerkType() {
		return clerkType;
	}

	private ClerkType(String clerkType) {
		this.clerkType = clerkType;
	}
	
}
