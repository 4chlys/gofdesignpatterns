package be.kdg.se2.spring.config.conditions;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Custom condition for conditional bean creation.
 * <p>
 * Implement matches() to return true when bean should be created.
 */
public class AdvancedNotificationsCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String enabled = context.getEnvironment()
                .getProperty("feature.advanced-notifications");
        return "true".equalsIgnoreCase(enabled);
    }
}
