package com.components.codevalue;

public class CodeValueFactory {

	public static CodeValue getInstance() {
		return getInstance("DEFAULT");
	}
	
	public static CodeValue getInstance(String codeGroupId) {
		return CodeValueCache.get(codeGroupId);
	}
}
