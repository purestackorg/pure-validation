package com.purestack.common.validation.validators;

import com.purestack.common.validation.IValidator;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.purestack.common.validation.config.pojo.Rule;

/**
 * 最小匹配
 * @author Benson
 *
 */
public class MinValidator implements IValidator {
	private static final Logger logger = Logger.getLogger(MinValidator.class);
	@SuppressWarnings("rawtypes")
	public boolean execute(Object context,Class type, Object value, Rule rule) {

		if(value == null) return false;
		int length = 0;
		if(ClassUtils.isAssignable(type, Object[].class)) {
			length = ((Object[])value).length;
		} else if(type == String.class) {
			length = ((String)value).trim().length();
		}
		String minString = rule.getParameter("value");
		int min = StringUtils.isNumeric(minString)?Integer.parseInt(minString):-1;
		if(min == -1) {
			logger.warn("Invalid Parameter for minimun or maximun.");
			return false;
		}
		return length >= min;
	}

}
