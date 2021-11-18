package com.purestack.common.validation;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import com.purestack.common.validation.config.IValidateConfig;
import com.purestack.common.validation.config.pojo.Configuration;
import com.purestack.common.validation.config.pojo.Field;
import com.purestack.common.validation.config.pojo.Group;
import com.purestack.common.validation.config.pojo.Rule;

/**
 * 基础验证服务
 * @author Benson
 *
 */
public class BasicValidateService implements IValidateService {
	private static final Logger logger = Logger.getLogger(BasicValidateService.class);
	public void init() {

		//读取配置
		this.configuration = config.readConfiguration();
		
		//设定已经被初始化
		this.isInited = true;
	}
	@SuppressWarnings("rawtypes")
	public ValidationResult validate(Object object, String groupName) {

		
		//判断是否被初始化
		if(!this.isInited) this.init();
		ValidationResult result  = new ValidationResult();
		
		Map<String,String> results = result.getErrors();// new LinkedHashMap<String,String>();
		if(object == null) {return result;}
		
		Group group = this.configuration.getGroup(groupName);
		if(group == null) {return result;}
		
		Map<String,IValidator> validators = this.configuration.getValidators();
		if(validators == null || validators.isEmpty()) return result;
		
		List<Field> fields = group.getFields();
		if(fields == null || fields.isEmpty()) return result;
		Iterator<Field> fs = fields.iterator();
		while(fs.hasNext()){
			Field field = fs.next();
			String fname = field.getName();
			Object value = null;
			Class type = null;
			List<Rule> rules = field.getRules();
			if(rules == null || rules.isEmpty()) continue;
			try {
				value = PropertyUtils.getProperty(object, fname);
				type = PropertyUtils.getPropertyType(object, fname);
			} catch (Exception e) {

				logger.warn(e.getMessage());
			} finally {
				Iterator<Rule> rs = rules.iterator();
				while(rs.hasNext()){
					Rule rule = rs.next();
					String rname = rule.getName();
					IValidator validator = validators.get(rname);
					if(validator == null) continue;
					boolean r = validator.execute(object,type,value,rule);
					if(!r) {
						results.put(fname, rule.getMessage());
						break;
					}
				}
			}
		}
		return result;
	}
	
	private Configuration configuration;
	private IValidateConfig config;
	private boolean isInited;
	public BasicValidateService() {
	}
	public BasicValidateService(IValidateConfig config) {
		this.setConfig(config);
	}
	public Configuration getConfiguration() {
		return configuration;
	}
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	public IValidateConfig getConfig() {
		return config;
	}
	public void setConfig(IValidateConfig config) {
		this.config = config;
	}
	
}
