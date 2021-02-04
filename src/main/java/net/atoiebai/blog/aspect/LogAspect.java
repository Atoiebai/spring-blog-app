package net.atoiebai.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    // TODO: 2/4/2021 log to diff files by priority lvl
    private final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("within(net.atoiebai.blog..*)")
    public void callAtMyServiceAnnotation() {
    }

    @Before("callAtMyServiceAnnotation()")
    public void beforeServiceMethodInvocation(JoinPoint jp) {
        logger.info("Invocation method: " + jp.getSignature());
    }

}

