package com.why.transportsecuritybackend.common.filter;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.why.transportsecuritybackend.common.config.AuthWhiteUriConfig;
import com.why.transportsecuritybackend.common.constants.RedisConstants;
import com.why.transportsecuritybackend.common.constants.ResponseConstants;
import com.why.transportsecuritybackend.common.constants.UserConstants;
import com.why.transportsecuritybackend.common.utils.JwtUtils;
import com.why.transportsecuritybackend.common.utils.RedisUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * 权限验证filterBean
 *
 * @author why
 * @date 2023/04/29 15:39
 **/
@Component
public class AuthFilterBean implements Filter {
    private final RedisUtils redisUtils;
    private final AuthWhiteUriConfig authWhiteUriConfig;

    @Autowired
    public AuthFilterBean(RedisUtils redisUtils, AuthWhiteUriConfig authWhiteUriConfig) {
        this.redisUtils = redisUtils;
        this.authWhiteUriConfig = authWhiteUriConfig;
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain
    ) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestUri = request.getRequestURI();
        // 白名单放行
        List<String> whiteUris = authWhiteUriConfig.getWhiteUris();

        if (!whiteUris.contains(requestUri)) {
            String token = request.getHeader("token");
            if (StrUtil.isBlank(token)) {
                responseJson(response);
                return;
            }
            Claims claims = JwtUtils.parseJWT(UserConstants.JWT_SECURITY_KEY, token);
            if (claims == null) {
                responseJson(response);
                return;
            }
            Object account = claims.get("account");
            Object tokenRedis = redisUtils.get(RedisConstants.REDIS_USER_PREFIX + account.toString());
            if (ObjectUtil.isEmpty(tokenRedis) || !token.equals(tokenRedis)) {
                responseJson(response);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void responseJson(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("code", ResponseConstants.AUTH_ERROR);
        map.put("msg", ResponseConstants.MSG_AUTH_ERROR);
        map.put("data", null);
        writer.write(JSONObject.toJSONString(map));
    }
}
