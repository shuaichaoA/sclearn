package man.controller;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConditionalOnProperty(value = "test.conditional", havingValue = "abc")
public class TestConfiguration {

    /**
     * 默认值
     */
    private String field = "default";

}