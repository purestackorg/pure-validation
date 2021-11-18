package com.purestack.common.validation.validators;

import java.sql.Timestamp;

import com.purestack.common.validation.IValidator;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.purestack.common.validation.config.pojo.Rule;

/**
 * 当前时间需要大于等于
 * @author Benson
 *
 */
public class TimestampMinValidator implements IValidator {
	private static final Logger logger = Logger.getLogger(TimestampMinValidator.class);
	@SuppressWarnings("rawtypes")
	public boolean execute(Object context,Class type, Object value, Rule rule) {

		if(value == null || !(value instanceof Timestamp)) return false;
		String toName = rule.getParameter("target");
		String inclusive = rule.getParameter("inclusive");
		Boolean inclusiveVal = "true".equals(inclusive) ;
		if(StringUtils.isBlank(toName)) {
			logger.warn("Greater And Equal target parameter missed");
			return false;
		}
		
		Object toValue = null;
		try {
			toValue = PropertyUtils.getProperty(context, toName);
			if(!(toValue instanceof Timestamp)) return false;
		} catch (Exception e) {
			logger.warn("Greater And Equal target value missed , "+toName);
		}
		Timestamp timestamp = (Timestamp) value;
		Timestamp toTimestamp = (Timestamp) toValue;
		if(inclusiveVal){
			return timestamp.getTime() >= toTimestamp.getTime();
		}
		else{
			return timestamp.getTime() > toTimestamp.getTime();

		}
	}

}
