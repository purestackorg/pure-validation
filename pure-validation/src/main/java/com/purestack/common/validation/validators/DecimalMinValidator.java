package com.purestack.common.validation.validators;

import com.purestack.common.validation.IValidator;
import com.purestack.common.validation.NumberUtil;
import com.purestack.common.validation.config.pojo.Rule;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.math.BigDecimal;

/**
 * 数字需要大于最小值
 * @author Benson
 *
 */
public class DecimalMinValidator implements IValidator {
	private static final Logger logger = Logger.getLogger(DecimalMinValidator.class);
	@SuppressWarnings("rawtypes")
	public boolean execute(Object context,Class type, Object value, Rule rule) {

		if(value == null || !(StringUtils.isNumeric(value.toString()))) return false;
		String toName = rule.getParameter("target");
		String inclusive = rule.getParameter("inclusive");
		Boolean inclusiveVal = "true".equals(inclusive) ;
		if(!StringUtils.isBlank(toName)) {
//			logger.warn("Greater Than target parameter missed");
//			return false;
			Object toValue = null;
			try {
				toValue = PropertyUtils.getProperty(context, toName);
				if(toValue == null || !(StringUtils.isNumeric(toValue.toString()))) return false;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.warn("Greater Than target value missed , "+toName);
			}
			BigDecimal v1 = NumberUtil.toDecimal(value, 0);// new BigDecimal(value.toString());
			BigDecimal v2 = NumberUtil.toDecimal(toValue, 0);// new BigDecimal(toValue.toString());

			int r =v1.compareTo(v2); //和0，duZero比较
//			if(r==0) //等于
//				if(r==1) //大于
//					if(r==-1) //小于
//					if(r>-1) //大于等于
//					if(r<1) //小于等于

			if (inclusiveVal){
				return r>-1 ;
			}
			else {
				return r == 1 ;
			}

		}

		String val = rule.getParameter("value");
		if(!StringUtils.isBlank(val) && StringUtils.isNumeric(val)) {

			BigDecimal v1 = NumberUtil.toDecimal(value, 0);//new BigDecimal(value.toString());
			BigDecimal v2 = NumberUtil.toDecimal(val, 0);//new BigDecimal(val);
			int r =v1.compareTo(v2);

			if (inclusiveVal){
				return r>-1 ;
			}
			else {
				return r == 1 ;
			}

		}

		return false;
	}

}
