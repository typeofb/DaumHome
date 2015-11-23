package com.components.codevalue;

import java.util.HashMap;
import java.util.Map;

public class CodeValueCache {

	protected static Map<String, CodeValue> codeValueMap = null;
	
	public static Map<String, CodeValue> get() {
		return codeValueMap;
	}
	
	public static CodeValue get(String codeGroupId) {
		if (codeValueMap != null) {
			return (codeValueMap.containsKey(codeGroupId)) ? codeValueMap.get(codeGroupId) : codeValueMap.get("NULL");
		}
		// codeValueMap이 null인 경우는 아직 초기화 되기 전임
		return new CodeValue();
	}
	
	public static void addOrModify(String key, String value, String codeGroupId) {
		if (codeValueMap == null) {
			codeValueMap = new HashMap<String, CodeValue>();
			codeValueMap.put("NULL", new CodeValue());
		}
		CodeValue codeValue = null;
		if (codeValueMap.containsKey(codeGroupId)) {
			codeValue = codeValueMap.get(codeGroupId);
		} else {
			codeValue = new CodeValue();
			codeValueMap.put(codeGroupId, codeValue);
		}
		if (value == null) {
			value = "";
		}
		codeValue.put(key, value);
	}
	
	public static void remove(String key, String codeGroupId) {
		if (codeValueMap == null) {
			return;
		}
		CodeValue codeValue = null;
		if (codeValueMap.containsKey(codeGroupId)) {
			codeValue = codeValueMap.get(codeGroupId);
			codeValue.remove(key);
		}
	}
}
