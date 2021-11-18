package com.purestack.common.validation.validators;

import com.purestack.common.validation.IValidator;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.purestack.common.validation.config.pojo.Rule;

/**
 * 字符长度区间验证器
 * @author Benson
 *
 */
public class SizeValidator implements IValidator {
	private static final Logger logger = Logger.getLogger(SizeValidator.class);
	@SuppressWarnings("rawtypes")
	public boolean execute(Object context,Class type, Object value, Rule rule) {

		if(value == null) return false;
		int length = 0;
		if(ClassUtils.isAssignable(type, Object[].class)) {
			length = ((Object[])value).length;
		} else if(type == String.class) {
			length = ((String)value).trim().length();
		}
		String minString = rule.getParameter("min");
		int min = StringUtils.isNumeric(minString)?Integer.parseInt(minString):-1;
		String maxString = rule.getParameter("max");
		int max = StringUtils.isNumeric(maxString)?Integer.parseInt(maxString):-1;
		if(min == -1 || max == -1) {
			logger.warn("Invalid Parameter for minimun or maximun.");
			return false;
		}
		return length >= min && length <= max;
	}

}
