package org.samtonyclarke.test;

import java.lang.management.ManagementFactory;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.web.bind.annotation.RestController
public class RestController
{
    public static final Logger log = LoggerFactory.getLogger(RestController.class);

    @Value("${spring.application.name}")
    String appName;

    @RequestMapping(value = "/app-name")
    public String appName()
    {
        log.info("Get App Name was called.");
        return this.appName;
    }

    @RequestMapping(value = "/return-auth")
    public String returnAuth(HttpServletRequest request)
    {
        log.info("Return Auth was called.");
        return request.getHeader(HttpHeaders.AUTHORIZATION);
    }

    @RequestMapping(value = "/get-headers")
    public Map<String, String> getHeaders(HttpServletRequest request)
    {
        log.info("Get headers was called.");
        Map<String, String> headers = new HashMap<String, String>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            headers.put(name, request.getHeader(name));
        }
        return headers;
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<?,?> post(@RequestBody Map<?,?> body)
    {
        log.info("Post was called with body: " + body);
        return body;
    }

    @RequestMapping(value = "/health")
    public String health()
    {
        return String.valueOf(ManagementFactory.getRuntimeMXBean().getUptime());
    }

}
