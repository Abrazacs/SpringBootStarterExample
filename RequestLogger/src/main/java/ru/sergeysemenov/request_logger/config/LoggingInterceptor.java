package ru.sergeysemenov.request_logger.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

@Component
public class LoggingInterceptor implements HandlerInterceptor {
    private static final Logger log = Logger.getLogger(LoggingInterceptor.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        log.info("[preHandle] Request URL : "+ request.getRequestURL());
        log.info("[preHandle] Request Method : "+request.getMethod());
        request.getHeaderNames().asIterator()
                .forEachRemaining(header -> log.info("[preHandle] Request Header "+header+": "+request.getHeader(header)));
        log.info("[preHandle] Request Body : "+request.getReader().readLine());
        request.getParameterNames().asIterator()
                .forEachRemaining(param -> log.info("[preHandle] Request Parameter "+param+": "+request.getParameter(param)));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        log.info("[postHandle] Response Status : "+response.getStatus());
        for(String header : response.getHeaderNames()) {
            log.info("[postHandle] Response Header "+header+" : "+response.getHeader(header));
        }
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        log.info("[afterCompletion] Execution time (ms) : "+(System.currentTimeMillis() - (Long)request.getAttribute("startTime")));

    }

}