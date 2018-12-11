package com.alibaba.middleware.handler.buc;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

public class FilterManager {

	private static String appName;
	private static String appCode;
	private static String ssoServerUrl;
	private static String ssoTokenApi;
	private static String bucSsoTokenApi;
	private static String ssoLogoutUrl;
	private static String errorUrl;
	private static String ssoLoginUrl;
	private static String accessControlAllowOrigin;

	/**
	 * 返回当前应用在BUC SSO的注册名称
	 * 
	 * @return
	 */
	public static String getAppName() {
		return appName;
	}

	public static void setAppName(String name) {
		appName = name;
	}

	/**
	 * @return the appCode
	 */
	public static String getAppCode() {
		return appCode;
	}

	/**
	 * @param appCode
	 *            the appCode to set
	 */
	public static void setAppCode(String appCode) {
		FilterManager.appCode = appCode;
	}
	
	/**
     * 应用登录调用的URL
     * 
     * @return
     */
    public static String getSsoLoginUrl() {
        return ssoLoginUrl;
    }
    
    public static void setSsoLoginUrl(String loginUrl) {
        ssoLoginUrl = loginUrl;
    }

    /**
     * 应用退出调用的URL
     * 
     * @return
     */
    public static String getSsoLogoutUrl() {
        return ssoLogoutUrl;
    }
    
    public static void setSsoLogoutUrl(String logoutUrl) {
        ssoLogoutUrl = logoutUrl;
    }

    public static String getSsoTokenApi() {
        return ssoTokenApi;
    }
    
    public static void setSsoTokenApi(String tokenApi) {
        ssoTokenApi = tokenApi;
    }
    

    public static String getBucSsoTokenApi() {
        return bucSsoTokenApi;
    }


    public static void setBucSsoTokenApi(String bucSsoTokenApi) {
        FilterManager.bucSsoTokenApi = bucSsoTokenApi;
    }
    
    public static String getErrorUrl() {
        return errorUrl;
    }

	public static String getSsoServerUrl() {
		return ssoServerUrl;
	}

	public static void setSsoServerUrl(String ssoServerUrl) {
		FilterManager.ssoServerUrl = ssoServerUrl;
	}

	public static String getAccessControlAllowOrigin() {
		return accessControlAllowOrigin;
	}

	public static void setAccessControlAllowOrigin(String accessControlAllowOrigin) {
		FilterManager.accessControlAllowOrigin = accessControlAllowOrigin;
	}

	// TODO jiaye.wwh 异常里的稳定需要修改
    private static ServletException appConfNotEmpty(String paramName) {
        return new ServletException("The value of AppConfig named '" + paramName + "' should not be empty. see http://gitlab.alibaba-inc.com/buc/sso/wikis/buc-sso-client_0_8");
    }

    private static ServletException appConfIllegal(String paramName,String regex) {
        return new ServletException("The value of AppConfig named '" + paramName + "' should be match "+ regex + ".  see http://gitlab.alibaba-inc.com/buc/sso/wikis/buc-sso-client_0_8");
    }

	public static void initAppClientConfig(String ssoServerUrlStr, String appNameStr, String appCodeStr, String accessControlAllowOriginStr)
			throws ServletException, ClassNotFoundException, IllegalAccessException, InstantiationException {
		ssoServerUrl = ssoServerUrlStr;
		appName = appNameStr;
		appCode = appCodeStr;
		
		if (StringUtils.isBlank(ssoServerUrl)) {
			throw appConfNotEmpty(BucSSOConstants.SSO_SERVER_URL_NAME);
		}
		Pattern pattern = compile("^(https://|http://)[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\\.?$", 2);
		Matcher m = pattern.matcher(ssoServerUrl);
		if (m!=null && !m.matches()){
			throw appConfIllegal(BucSSOConstants.SSO_SERVER_URL_NAME,
					"domail name. eg : https://login.alibaba-inc.com or https://login-test.alibaba-inc.com");
		}

		ssoTokenApi = ssoServerUrl + "/rpc/sso/communicate.json";
		bucSsoTokenApi = ssoServerUrl + "/rpc/ssoToken/validateSsoToken.json";
		ssoLogoutUrl = ssoServerUrl + "/ssoLogout.htm";
		errorUrl = ssoServerUrl + "/error.htm?id=%1$s&msg=%2$s";
		ssoLoginUrl = ssoServerUrl + "/ssoLogin.htm";
		accessControlAllowOrigin = accessControlAllowOriginStr;
	}
}
