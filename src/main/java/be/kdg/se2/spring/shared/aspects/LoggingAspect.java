package be.kdg.se2.spring.shared.aspects;

public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * Log all service method calls
     */
    @Around("execution(* be.kdg.se2.templates.application.services..*(..))")
    public Object logServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        logger.info("Calling: {}", methodName);

        long startTime = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - startTime;
            logger.info("Completed: {} in {}ms", methodName, duration);
            return result;
        } catch (Exception e) {
            logger.error("Error in: {}", methodName, e);
            throw e;
        }
    }
}

// ----------------------------------------------------------------------------
