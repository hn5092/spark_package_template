package org.springframework.boot.autoconfigure.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration(
    proxyBeanMethods = false
)
@ConditionalOnClass({Gson.class})
@EnableConfigurationProperties({GsonProperties.class})
public class GsonAutoConfiguration {
    public GsonAutoConfiguration() {
    }

    @Bean
    @ConditionalOnMissingBean
    public GsonBuilder gsonBuilder(List<GsonBuilderCustomizer> customizers) {
        GsonBuilder builder = new GsonBuilder();
        customizers.forEach((c) -> {
            c.customize(builder);
        });
        return builder;
    }

    @Bean
    @ConditionalOnMissingBean
    public Gson gson(GsonBuilder gsonBuilder) {
        return gsonBuilder.create();
    }

    @Bean
    public GsonAutoConfiguration.StandardGsonBuilderCustomizer standardGsonBuilderCustomizer(GsonProperties gsonProperties) {
        return new GsonAutoConfiguration.StandardGsonBuilderCustomizer(gsonProperties);
    }

    static final class StandardGsonBuilderCustomizer implements GsonBuilderCustomizer, Ordered {
        private final GsonProperties properties;

        StandardGsonBuilderCustomizer(GsonProperties properties) {
            this.properties = properties;
        }

        public int getOrder() {
            return 0;
        }

        public void customize(GsonBuilder builder) {
            GsonProperties properties = this.properties;
            PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();
            properties.getClass();
            map.from(properties::getGenerateNonExecutableJson).toCall(builder::generateNonExecutableJson);
            properties.getClass();
            map.from(properties::getExcludeFieldsWithoutExposeAnnotation).toCall(builder::excludeFieldsWithoutExposeAnnotation);
            properties.getClass();
            map.from(properties::getSerializeNulls).whenTrue().toCall(builder::serializeNulls);
            properties.getClass();
            map.from(properties::getEnableComplexMapKeySerialization).toCall(builder::enableComplexMapKeySerialization);
            properties.getClass();
            map.from(properties::getDisableInnerClassSerialization).toCall(builder::disableInnerClassSerialization);
            properties.getClass();
            map.from(properties::getLongSerializationPolicy).to(builder::setLongSerializationPolicy);
            properties.getClass();
            map.from(properties::getFieldNamingPolicy).to(builder::setFieldNamingPolicy);
            properties.getClass();
            map.from(properties::getPrettyPrinting).toCall(builder::setPrettyPrinting);
            properties.getClass();
            map.from(properties::getDisableHtmlEscaping).toCall(builder::disableHtmlEscaping);
            properties.getClass();
            map.from(properties::getDateFormat).to(builder::setDateFormat);
        }
    }
}
