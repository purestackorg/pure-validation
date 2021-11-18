package com.purestack.common.validation;

/**
 * 验证处理管理器
 * @author Benson
 *
 */
public interface IValidateService {
	/**
	 * 初始化
	 */
	public void init();
	/**
	 * 对某个对象执行按组验证操作
	 * @param object 执行对象
	 * @param groupName 验证组
	 * @return 返回验证结果
	 */
	public ValidationResult validate(Object object,String groupName);
}
