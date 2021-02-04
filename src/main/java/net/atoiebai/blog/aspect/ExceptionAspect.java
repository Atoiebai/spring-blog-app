package net.atoiebai.blog.aspect;

import net.atoiebai.blog.exception.site.exception.NoSuchUserException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionAspect {
    // TODO: 2/4/2021 more flexible handling with logging  
    private final Logger logger = LoggerFactory.getLogger(ExceptionAspect.class);

    @Pointcut("within(net.atoiebai.blog..*) && !within(net.atoiebai.blog.api.controller..*)")
    public void exceptionPoint() {

    }

     @AfterThrowing(value = "exceptionPoint()" , throwing = "ex")
    public void substitutionToCustomException(JoinPoint jp , Exception ex) {
        logger.warn("Exception substituted to " + jp.getThis() + " or " + ex);
        throw new NoSuchUserException("No user with such id");
    }
}
