package com.alibaba.middleware.handler.buc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 用户登录过滤器
 */
@WebFilter(filterName = "bucLoginFilter", urlPatterns = "/*")
@Order(0)
@Component
public class BucLoginFilter implements Filter {
    /**
     * 不需要过滤的URL配置分割符";"
     */
    private static final String SEPARATOR_SEMICOLON = ",";

    @Value("${spring.buc.sso-server-url}")
    private String bucSsoServerUrl;

    @Value("${spring.buc.app-name}")
    private String appName;
    
    @Value("${spring.buc.app-code}")
    private String appCode;

    @Value("${spring.buc.excludeUrl}")
    private String excludeUrlStr;

    @Value("${spring.buc.accessControlAllowOrigin}")
    private String accessControlAllowOrigin;

    @Value("${spring.buc.enable-buc}")
    private Boolean enableBuc;
    
    private String BucLogOutApi = "/ssoLogout.htm";

    private static Logger logger = LoggerFactory.getLogger(BucLoginFilter.class);
    
    /**
     * 不进行Token认证的url
     */
    private String[] excludeUrls = new String[] {};
    
    @Override
    public void destroy() {
        if (logger.isWarnEnabled()) {logger.warn("destroy filter");}
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        
        if (enableBuc != true) {
            // go on the filter chain
        	arg2.doFilter(arg0, arg1);
        	return;
        }
        
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String fullUrl = getFullUrl(request);

        String requestUri = request.getRequestURI();
        //获取get请求的params
        //Map<String, String> params = HTTPUtil.splitQueryString(request.getQueryString());
        //判断拿到已经有登录过的token,进行刷新验证
        /*if(StringUtils.isNotBlank(params.get(BucSSOConstants.SSO_TOKEN))){
            String bucSsoToken = params.get(BucSSOConstants.SSO_TOKEN);
            boolean result = BucSSOClientUtil.handleSSOLogin(bucSsoToken,request,response);
            if (response.isCommitted()){return;}
            if(result){
            	BucSSOClientUtil.sendRedirect(arg1, fullUrl);
                return;
            }
        }*/
        
        // 用户登录成功后的URI，支持任意后缀
        if (requestUri.contains(BucSSOConstants.SEND_BUC_SSO_TOKEN)) {
            /*if (StringUtils.isNotBlank(request.getQueryString())) {
                String ssoToken = params.get(BucSSOConstants.SSO_TOKEN);
                String backUrl = params.get(BucSSOConstants.BACK_URL);
                if (StringUtils.isNotBlank(ssoToken) && StringUtils.isNotBlank(backUrl)) {
                    backUrl = URLDecoder.decode(backUrl, BucSSOConstants.ENCODING_UTF8);
                    //如果不相同，说明有钓鱼可能
                    if (!HTTPUtil.getHost(fullUrl).equals(HTTPUtil.getHost(backUrl))) {
                    	BucSSOClientUtil.sendRedirect(arg1, String.format(FilterManager.getErrorUrl(), BucSSOConstants.ERROR_1401, URLEncoder.encode(fullUrl, BucSSOConstants.ENCODING_UTF8)));
                        return;
                    }
					BucSSOClientUtil.handleSSOLogin(ssoToken, request, response);
                    if (response.isCommitted()) {return;}
                    // 登录成功后，重定向回用户访问的初始页面
                    BucSSOClientUtil.sendRedirect(arg1, backUrl);
                }
            }*/
            return;
        }
        
        // 用户登出
        else if (requestUri.contains(BucSSOConstants.DO_BUC_SSO_LOGOUT)) {
            BucSSOClientUtil.handleSSOLogout(request, response);
            return;
        }
        
        if (request.getSession().getAttribute(BucSSOClientUtil.SESSION_LOGIN_KEY) != null) {
            // go on the filter chain
        	arg2.doFilter(arg0, arg1);
        	return;
        } 

        // 匹配的URI则不需要登录即可访问
        if (!isNeedFilter(request)) {
            // go on the filter chain
            arg2.doFilter(arg0, arg1);
            return;
        }
        
        // 最后所有情况都不满足，则跳转到用户登录页面
        StringBuilder sb = new StringBuilder();
        sb.append(bucSsoServerUrl).append(BucSSOConstants.QUESTION).append(BucSSOConstants.APP_NAME).append(BucSSOConstants.EQUALS).append(FilterManager.getAppName()).append(BucSSOConstants.AMPERSAND).append(BucSSOConstants.BACK_URL).append(BucSSOConstants.EQUALS).append(URLEncoder.encode(fullUrl, BucSSOConstants.ENCODING_UTF8));
        BucSSOClientUtil.sendRedirect(response, sb.toString());
    }

    /**
     * 用户未登录时，构建作为BACK_URL参数发送到BUC SSO 登录页面，登录成功后，BUC SSO 会重定向此BACK_URL
     * @param request Http请求对象
     * @return 登录后回调的url
     */
    protected String  getFullUrl(HttpServletRequest request) {
    	String fullUrl = request.getParameter("BACK_URL");
    	if (fullUrl == null) {
    		fullUrl = request.getHeader("Referer");
    	}	
    	if (fullUrl == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(request.getScheme()).append("://").append(request.getServerName()).append(request.getRequestURI());
            if (request.getQueryString() != null){
                sb.append(BucSSOConstants.QUESTION).append(request.getQueryString());
            }
    		fullUrl = sb.toString();
    	}
        return fullUrl;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    	try {
			FilterManager.initAppClientConfig(bucSsoServerUrl, appName, appCode, accessControlAllowOrigin);
		} catch (Exception e) {
            logger.error(e.getMessage(), e);
		}
        this.excludeUrls = excludeUrlStr.split(SEPARATOR_SEMICOLON);
    }

    /**
     * 是否需要进行登录认证
     * @param request request请求
     * @return 是否需要进行过滤
     */
    private boolean isNeedFilter(HttpServletRequest request) {
    	
        //如果是excludeUrls里边的则直接放行，登陆接口不校验token，直接放行
        for (String url : excludeUrls) {
            if (request.getRequestURI().endsWith(url)) {
                return false;
            }
        }
        return true;
    }
}