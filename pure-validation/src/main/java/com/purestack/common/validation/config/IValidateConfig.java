package com.purestack.common.validation.config;

import com.purestack.common.validation.config.pojo.Configuration;

/**
 * 验证框架的配置器
 * @author Benson
 *
 */
public interface IValidateConfig {
	/**
	 * 读取配置
	 * @return 配置对象
	 */
	public Configuration readConfiguration();
}
