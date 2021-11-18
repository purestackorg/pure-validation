
**pure-validation**一款可配置、无侵入、易拓展、热加载、轻量级的动态参数验证框架，可替代hibernate-validation，代码总数不足1000行，简单易学，也可以自行拓展实现更多验证器validator，有需要star一下。

github： [https://github.com/purestackorg/pure-validation](https://github.com/purestackorg/pure-validation)

默认包含多个通用验证实现

```xml
<?xml version="1.0" encoding="UTF-8"?>
<validators>
	<validator name="required" class="com.purestack.common.validation.validators.RequiredValidator"/>
	<validator name="pattern" class="com.purestack.common.validation.validators.PatternValidator"/>
	<validator name="size" class="com.purestack.common.validation.validators.SizeValidator"/>
	<validator name="min" class="com.purestack.common.validation.validators.MinValidator"/>
	<validator name="max" class="com.purestack.common.validation.validators.MaxValidator"/>
	<validator name="equals" class="com.purestack.common.validation.validators.EqualsValidator"/>
	<validator name="timestampMax" class="com.purestack.common.validation.validators.TimestampMaxValidator"/>
	<validator name="timestampMin" class="com.purestack.common.validation.validators.TimestampMinValidator"/>
	<validator name="decimalMax"  class="com.purestack.common.validation.validators.DecimalMaxValidator"/>
	<validator name="decimalMin"  class="com.purestack.common.validation.validators.DecimalMinValidator"/>
	<validator name="spring"  useSpring="true" class="com.purestack.common.validation.validators.SpringValidator"/>
</validators>
```

1. 定义全局规则配置，引入各个实体验证规则
```xml
<?xml version="1.0" encoding="UTF-8"?>
<validation>
	<!-- include 标签导入其他配置 -->
	<include file="validation/user.rule.xml"/>
	<include file="validation/article.rule.xml"/>
</validation>
```

2. 定义user和article实体验证规则

user.rule.xml规则
```xml
<?xml version="1.0" encoding="UTF-8"?>
<validation>
	<!-- 验证组ID，全局唯一 -->
	<group name="user.rule">
		<!-- 验证字段 -->
		<field name="email">
			<rule name="required" message="邮件必须填写"/>
			<rule name="size" message="邮件长度应该3-100之间">
				<param name="min" value="3"/>
				<param name="max" value="100"/>
			</rule>
			<rule name="pattern" message="邮件格式不正确">
				<param name="regex" value="^[A-Za-z]+[\.\-_A-Za-z0-9]*@[A-Za-z0-9]+[\.\-_A-Za-z0-9]*$"/>
			</rule>
		</field>
		<field name="password">
			<rule name="required" message="密码必须填写" />
			<rule name="min" message="密码至少8个字符">
				<param name="value" value="9" />
			</rule>
			<rule name="max" message="密码最多20个字符">
				<param name="value" value="20" />
			</rule>
			<rule name="equals" message="两次密码输入不正确">
				<param name="target" value="passwordOne" />
			</rule>
		</field>
		<field name="starttime">
			<rule name="timestampMax" message="开始时间不能大于结束时间">
				<param name="target" value="endtime" />
			</rule>
		</field>
		<field name="endtime">
			<rule name="timestampMin" message="结束时间不能小于开始时间">
				<param name="target" value="starttime" />
			</rule>
		</field>
		<field name="age">
			<rule name="decimalMin" message="年龄要大于30岁">
				<param name="value" value="30" />
			</rule>
			<rule name="decimalMax" message="年龄要小于或等于50岁">
				<param name="inclusive" value="true" />
				<param name="value" value="50" />
			</rule>
		</field>
		<field name="address">
			<rule name="required" message="地址必须填写" />
		</field>
		<field name="postcode">
			<rule name="equals" message="邮编必须为440000">
				<param name="value" value="440000" />
			</rule>
		</field>
		<field name="stars">
			<rule name="decimalMin" message="星星数量要大于5">
				<param name="value" value="5" />
			</rule>
		</field>
	</group>
</validation>

article.rule.xml规则
```xml
<?xml version="1.0" encoding="UTF-8"?>
<validation>
	<!-- 验证组ID，全局唯一 -->
	<group name="article.rule">
		<!-- 验证字段 -->
		<field name="text">
			<!-- 规则列表 -->
			<rule name="required" message="内容必須填寫" />
			<rule name="between" message="长度应该100-10000之间">
				<param name="min" value="100" />
				<param name="max" value="10000" />
			</rule>
		</field>
	</group>
</validation>
```

3. 定义测试实体类
```java

import java.sql.Timestamp;

/**
 * 用户对象
 *
 */
public class User {
    // 用户名
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // 密码
    private String password;
    // 确认密码
    private String passwordOne;

    // 邮箱
    private String email;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Double getStars() {
        return stars;
    }

    public void setStars(Double stars) {
        this.stars = stars;
    }

    private String address;
    private String postcode;
    private Double stars;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private Integer age;

    private Timestamp starttime;
    private Timestamp endtime;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordOne() {
        return passwordOne;
    }

    public void setPasswordOne(String passwordOne) {
        this.passwordOne = passwordOne;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getStarttime() {
        return starttime;
    }

    public void setStarttime(Timestamp starttime) {
        this.starttime = starttime;
    }

    public Timestamp getEndtime() {
        return endtime;
    }

    public void setEndtime(Timestamp endtime) {
        this.endtime = endtime;
    }
}
```

4. 定义单元测试方法，查看效果

```java

import com.purestack.common.validation.BasicValidateService;
import com.purestack.common.validation.IValidateService;
import com.purestack.common.validation.ValidationResult;
import com.purestack.common.validation.config.BasicValidateConfig;
import com.purestack.common.validation.config.IValidateConfig;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import java.util.Calendar;

import org.apache.log4j.Logger;

public class ValidationTest {

    private static Logger logger = Logger.getLogger(ValidationTest.class);
    @Test
    public void testValid() {

        // 验证器配置，系统默认配置
        String validatorsXML = "validation/validators.xml";

        // 规则配置
        String rulesXML = "validation/rules.xml";

        /**
         * 实例化配置对象
         */
        IValidateConfig config =new BasicValidateConfig(validatorsXML, rulesXML);
        /**
         * 实例化验证服务层
         */
        IValidateService validateService = new BasicValidateService(config);

        // 实例化用户
        User user = createUser();

        /**
         * 执行验证
         */
        ValidationResult result = validateService.validate(user, "user.rule");
        // 输出结果
        if(result.isValid()) {

            logger.info("验证成功.");
        } else {
            logger.info("验证失败，结果如下");
            logger.info(result.getErrors());

        }
    }

    public User createUser(){
        User user = new User();

        user.setEmail("test#purestack.cc");

        user.setPassword("123");
        user.setPasswordOne("abc");
        user.setAge(70);
        user.setPostcode("4422222");

        Calendar date = Calendar.getInstance();

        user.setStarttime(new Timestamp(date.getTimeInMillis()));
        user.setEndtime(new Timestamp(date.getTimeInMillis()-1000));
        return user;

    }
}

```


简单的4个步骤即可完成动态配置验证规则，简单方便，你值得拥有！
