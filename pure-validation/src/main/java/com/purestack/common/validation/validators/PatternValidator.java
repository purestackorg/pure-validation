package com.purestack.common.validation.validators;

import java.util.regex.Pattern;

import com.purestack.common.validation.IValidator;
import org.apache.commons.lang.StringUtils;

import com.purestack.common.validation.config.pojo.Rule;

/**
 * 正则匹配
 * @author Benson
 *
 */
public class PatternValidator implements IValidator {

	@SuppressWarnings("rawtypes")
	public boolean execute(Object context,Class type,Object value,Rule rule) {

		if(value == null) return false;
		String regex = rule.getParameter("regex");
		if(StringUtils.isBlank(regex)) return true;
		return Pattern.matches(regex, (String)value);
	}
}
