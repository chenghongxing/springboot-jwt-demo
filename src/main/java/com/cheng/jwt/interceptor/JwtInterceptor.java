package com.cheng.jwt.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.cheng.jwt.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenghx on 2020/12/21 14:03
 */
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从请求头中拿到token
        String token = request.getHeader("token");
        Map<String,Object> resultMap = new HashMap<>();
        try {
            JwtUtil.verify(token);
            return true;
        } catch (SignatureVerificationException e) {
            resultMap.put("msg","无效签名！");
            e.printStackTrace();
        }catch (TokenExpiredException e){
            e.printStackTrace();
            resultMap.put("msg","token已过期");
        }catch (AlgorithmMismatchException e){
            e.printStackTrace();
            resultMap.put("msg","token算法不一致");
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("msg","token无效");
        }
        resultMap.put("state",false);
        //响应到前台
        String json = new ObjectMapper().writeValueAsString(resultMap);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
