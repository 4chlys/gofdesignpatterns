package be.kdg.se2.spring.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class CustomCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // Example: enabled only when property "feature.conditionalService=true"
        String enabled = context.getEnvironment().getProperty("feature.conditionalService");
        return "true".equalsIgnoreCase(enabled);
    }
}
