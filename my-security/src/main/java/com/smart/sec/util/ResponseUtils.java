package com.smart.sec.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: cloud-alibaba-example
 * @author: hzx
 * @since: JDK 1.8
 * @create: 2020-12-12 11:14
 **/
public class ResponseUtils {
    public static final String JSON_CONTENT_TYPE = "application/json;charset=UTF-8";

    public static void responseToJson(HttpServletResponse response, String result) throws IOException {
        response.setContentType(JSON_CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.write(result);
        out.flush();
    }

    public static void responseToJson(HttpServletResponse response, ObjectMapper objectMapper, BaseResult<Object> result) throws IOException {
        response.setContentType(JSON_CONTENT_TYPE);
        response.getWriter().print(objectMapper.writeValueAsString(result));
    }
}
