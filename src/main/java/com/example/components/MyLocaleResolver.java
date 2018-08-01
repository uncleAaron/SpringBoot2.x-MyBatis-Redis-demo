package com.example.components;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {

//        Locale locale = Locale.getDefault();    // 默认语言Locale
        Locale locale;
        String l = request.getParameter("l");   // 获取请求头l参数（语言格式）
        if (StringUtils.isEmpty(l)) {    //若没有在参数中说明语言格式，则使用默认的LocaleResolver解析器
            // 这个localeResolver会按照请求头的Accept-Language进行解析请求头语言返回一个Locale
            locale = new AcceptHeaderLocaleResolver().resolveLocale(request);
            return locale;
        } else {
            // 若地址栏的自定义语言参数l不为空，则检查自定义语言
            // 接下来是自定义LocaleResolver部分，语言从地址栏参数中获取（适用于点击链接设置语言的场景）
            String[] messages = l.split("_");
            locale = new Locale(messages[0], messages[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
