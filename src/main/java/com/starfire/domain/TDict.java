package com.starfire.domain;
/**
 *字典表实体类 
 */
public class TDict {
	private String dictType;
	private String dictKey;
	private String dictValue;
	public String getDictType() {
		return dictType;
	}
	public void setDictType(String dictType) {
		this.dictType = dictType;
	}
	public String getDictKey() {
		return dictKey;
	}
	public void setDictKey(String dictKey) {
		this.dictKey = dictKey;
	}
	public String getDictValue() {
		return dictValue;
	}
	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}
	public TDict(String dictType, String dictKey, String dictValue) {
		super();
		this.dictType = dictType;
		this.dictKey = dictKey;
		this.dictValue = dictValue;
	}
	public TDict() {
		super();
	}
	
	
	
	
}
