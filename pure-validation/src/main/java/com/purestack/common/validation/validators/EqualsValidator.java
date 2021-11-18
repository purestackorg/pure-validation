package com.purestack.common.validation.validators;

import com.purestack.common.validation.IValidator;
import com.purestack.common.validation.NumberUtil;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.purestack.common.validation.config.pojo.Rule;

import java.math.BigDecimal;

/**
 * 相等匹配
 * @author Benson
 *
 */
public class EqualsValidator implements IValidator {
	private static final Logger logger = Logger.getLogger(EqualsValidator.class);
	@SuppressWarnings("rawtypes")
	public boolean execute(Object context,Class type, Object value, Rule rule) {

		if(value == null) return false;
		String toName = rule.getParameter("target");
		if(!StringUtils.isBlank(toName)) {
//			logger.warn("Equals target parameter missed");
//			return false;


			Object toValue = null;
			try {
				toValue = PropertyUtils.getProperty(context, toName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.warn("Equals target value missed , "+toName);
			}
			return value.equals(toValue);
		}



		String val = rule.getParameter("value");
		if(!StringUtils.isBlank(val)) {

			if(StringUtils.isNumeric(val))
			{
				BigDecimal v1 = NumberUtil.toDecimal(value, 0);//new BigDecimal(value.toString());
				BigDecimal v2 = NumberUtil.toDecimal(val, 0);//new BigDecimal(val);
				int r =v1.compareTo(v2);
				return r==0;
			}
			else {

				return value.toString().equals(val);

			}


		}

		return false;
	}

}
