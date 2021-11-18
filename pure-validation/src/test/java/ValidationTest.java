
import com.purestack.common.validation.BasicValidateService;
import com.purestack.common.validation.IValidateService;
import com.purestack.common.validation.ValidationResult;
import com.purestack.common.validation.config.BasicValidateConfig;
import com.purestack.common.validation.config.IValidateConfig;
import com.rjgf.rjcloud.validation.*;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import java.util.Calendar;

import org.apache.log4j.Logger;
/**
 * @author Benson
 * @create 2021/7/13 10:31
 */
public class ValidationTest {

    private static Logger logger = Logger.getLogger(ValidationTest.class);
    @Test
    public void testValid() {
        /**
         * Validation.FO的配置资源
         */
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

        user.setEmail("benson#rjgf.com");

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
