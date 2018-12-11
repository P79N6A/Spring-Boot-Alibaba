//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.alibaba.middleware.handler.buc;

public class BucSSOConstants {
    public static final String SSO_TOKEN = "SSO_TOKEN";
    public static final String LANGUAGE = "LANG";
    public static final String EMPID_HASH = "EMPID_HASH";
    public static final String SSO_CERT_SWITCH_TOKEN = "SSO_CERT_SWITCH_TOKEN";
    public static final String SSO_CERT_SWITCH_KEY = "SSO_CERT_SWITCH";
    public static final String SSO_AD_SWITCH_KEY = "SSO_AD_SWITCH";
    public static final String BUC_SSO_LOGOUT_CMD = "BUC_SSO_LOGOUT_CMD";
    public static final String SEND_BUC_SSO_TOKEN = "sendBucSSOToken";
    public static final String HAVANA_PROXY_TOKEN = "havanaProxyToken";
    public static final String DO_SEND_BUC_SSO_TOKEN = "sendBucSSOToken.do";
    public static final String BUC_SSO_LOGOUT = "bucSSOLogout";
    public static final String DO_BUC_SSO_LOGOUT = "bucSSOLogout.do";
    public static final String BUC_SSO_REFRESH_DATA = "bucSSORefresh";
    public static final String BACK_URL = "BACK_URL";
    public static final String CONTEXT_PATH = "CONTEXT_PATH";
    public static final String RQ_SUFFIX = "RQ_SUFFIX";
    public static final String LAST_HEART_BEAT_TIME = "LAST_HEART_BEAT_TIME";
    public static final String SSO_TICKET = "SSO_TICKET";
    public static final String APP_CODE = "APP_CODE";
    public static final String SSO_TICKET_USER = "SSO_TICKET_USER";
    public static int TOKEN_MAX_AGE = -1;
    public static int HEART_BEAT_TIME = 300000;
    public static int HEART_BEAT_TIME_CLIENT_SET;
    public static final int MIN_HEART_BEAT_TIME = 300;
    public static final String QUESTION = "?";
    public static final String EQUALS = "=";
    public static final String SLASH = "/";
    public static final String AMPERSAND = "&";
    public static final String COMMA = ",";
    public static final String PIPE = "|";
    public static final String ENCODING_UTF8 = "UTF-8";
    public static final String SSO_REQUEST_VAR = "var";
    public static final String SSO_REQUEST_VALUE = "value";
    public static final String APP_NAME = "APP_NAME";
    public static final String BUC_REQUEST_SUFFIX = "BUC_REQUEST_SUFFIX";
    public static final String BUC_PATH_PREFIX = "BUC_PATH_PREFIX";
    public static final String SSO_CALLBACK_CLASS = "SSO_CALLBACK_CLASS";
    public static final String SSO_SERVER_URL_NAME = "SSO_SERVER_URL";
    public static final String SSO_INNER_URL_NAME = "SSO_INNER_URL";
    public static final String CUSTOM_LOGIN_URL_NAME = "CUSTOM_LOGIN_URL";
    public static final String REQ_CODE = "REQ_CODE";
    public static final String SSO_LOGIN_URL_NAME = "SSO_LOGIN_URL";
    public static final String SSO_LOGOUT_URL_NAME = "SSO_LOGOUT_URL";
    public static final String SSO_TOKEN_API = "SSO_TOKEN_API";
    public static final String EXCLUSIONS = "EXCLUSIONS";
    public static final String SSO_TOKEN_MAX_TIME = "SSO_TOKEN_MAX_TIME";
    public static final String HEART_BEAT_TIME_PERIOD = "HEART_BEAT_TIME_PERIOD";
    public static final String BUC_REQUEST_METHOD = "REQUEST_METHOD";
    public static final String SSO_CHECK_ENABLE = "SSO_CHECK_ENABLE";
    public static final String SSO_CHECK_TIME_PERIOD = "SSO_CHECK_TIME_PERIOD";
    public static final String LOCAL_LOGIN_URL = "LOCAL_LOGIN_URL";
    public static final String SSO_CHECK_URL = "SSO_CHECK_URL";
    public static final String SSO_CHECK_VALUE = "SSO_CHECK_VALUE";
    public static final String SSO_COOKIE_DOMAIN = "SSO_COOKIE_DOMAIN";
    public static final String SSO_GROUP_NAME = "SSO_GROUP_NAME";
    public static final String APP_REQUEST_SCHEMA = "APP_REQUEST_SCHEMA";
    public static final String RETURN_USER_EXTEND_INFO = "RETURN_USER_EXTEND_INFO";
    public static final String CLIENT_VERSION = "CLIENT_VERSION";
    public static final String CLIENT_IP = "CLIENT_IP";
    public static final String RETURN_USER = "RETURN_USER";
    public static final String CLIENT_KEY = "CLIENT_KEY";
    public static final String LOGIN_TYPE = "LOGIN_TYPE";
    public static final String APP_SERVER = "APP_SERVER";
    public static final String APP_PORT = "APP_PORT";
    public static final String ENCODING = "ENCODING";
    public static final String HTTP_302_JSON_RESPONSE = "HTTP_302_JSON_RESPONSE";
    public static final String HTTP_302_JSONP_RESPONSE = "HTTP_302_JSONP_RESPONSE";
    public static final String HTTP_302_JSON_RESPONSE_VALUE = "{\"content\":false,\"errors\":[{\"code\":\"302\",\"field\":\"SSOException\",\"msg\":\"JSON request has been redirect \"}],\"hasError\":true}";
    public static final String HTTP_302_JSONP_RESPONSE_VALUE = "onJSONPCallback({\"content\":false,\"errors\":[{\"code\":\"302\",\"field\":\"SSOException\",\"msg\":\"JSONP request has been redirect \"}],\"hasError\":true});";
    public static final String LOGIN_ENV = "LOGIN_ENV";
    public static final String SIGN_KEY = "SIGN_KEY";
    public static final String PRIVATE_KEY = "PRIVATE_KEY";
    public static final int ERROR_1401 = 1401;
    public static final int ERROR_1402 = 1402;
    public static final String ACCOUNT_SECURITY_INFO = "ACCOUNT_SECURITY_INFO";
    public static final String LOGIN_NAME = "LOGIN_NAME";
    public static final String HAS_ERROR_VALUE = "hasError";
    public static final String CONTENT_VALUE = "content";
    public static final String ERROR_MESSAGE_VALUE = "errorMessage";
    public static final String ERROR_CODE_VALUE = "errorCode";
    public static final String SSO_EXCEPTION_001_VALUE = "SSOException001";
    public static final String TOKEN_VALUE = "token";
    public static final String APP_NAME_VALUE = "appName";
    public static final String UMID_VALUE = "umid";
    public static final String RET_EMPID_HASH = "empIdHash";
    public static final String MD5 = "MD5";
    public static final String SSO_CLIENT_ARTIFACTID = "buc.sso.client";
    public static final int PULL_CLIENT_CONF_PERIOD = 300;
    public static final String CLIENT_CONF_HEART_BEAT_TIME = "CLIENT_CONF_HEART_BEAT_TIME";
    public static final String CLIENT_CONF_APPLOG = "CLIENT_CONF_APPLOG";
    public static final String CLIENT_CONF_ISONLINE = "CLIENT_CONF_ISONLINE";
    public static final String CLIENT_CONF_APP = "CLIENT_CONF_APP";
    public static final String CORP_CHECK_ENABLE = "CORP_CHECK_ENABLE";
    public static final String RECOVER_USER_INTERVAL = "RECOVER_USER_INTERVAL";
    public static final String LOCAL_CONFIG_UPDATE_RESULT = "LOCAL_CONFIG_UPDATE_RESULT";
    public static final String RESULT_SUCCESS = "success";
    public static final String RESULT_MESSAGE = "message";
    public static final String CLIENT_CONF_URI = "/rpc/client/conf/v2/getConf.json";
    public static final String SSO_CLIENT_THREAD_NAME_PREFIX = "sso-client_";
    public static final String LOGIN_ENV_DAILY = "daily";
    public static final String LOGIN_ENV_ONLINE = "online";
    public static final String SUPPORT_DINGTALK = "SUPPORT_DINGTALK";
    public static final boolean COOKIE_LANG_HTTP_ONLY = false;
    public static final String DING_CORP_ID = "corpid";
    public static final String DING_CODE = "code";
    public static final String DING_DT_CODE = "dtcode";
    public static final String DING_CODY_TYPE = "code_type";
    public static final String DING_CODY_TYPE_COOKIE = "cookie";
    public static final String DING_CODY_TYPE_JSAPI = "jsapi";
    public static final String DING_CODY_TYPE_ADMIN = "admin";
    public static final String DING_ADD_COOKIE = "dd_addcookie";
    public static final String DING_UT_COOKIE_NAME = "ding_ut";
    public static final String APP_PROFILE = "APP_PROFILE";
    public static final String APP_PROFILE_GROUP = "group";
    public static final String APP_PROFILE_CLOUD = "cloud";
    public static final String APP_PROFILE_CLOUD_HB2 = "cloud-hb2";
    public static final String APP_PROFILE_CLOUD_IB = "cloud-ib";
    public static final String METHOD_OPTIONS = "OPTIONS";

    public BucSSOConstants() {
    }

    static {
        HEART_BEAT_TIME_CLIENT_SET = HEART_BEAT_TIME;
    }
}
