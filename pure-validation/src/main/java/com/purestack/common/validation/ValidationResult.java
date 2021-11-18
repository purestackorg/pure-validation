package com.purestack.common.validation;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 验证结果
 * @author Benson
 *
 */
public class ValidationResult  {

	private Map<String,String> errors;

	/**
	 * 验证通过
	 */
	public Boolean isValid() {
		return errors == null || errors.size() == 0;
	}

	/**
	 * 验证错误
	 */
	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}


	public ValidationResult() {
		errors = new LinkedHashMap<String,String>();
	}

	
}
