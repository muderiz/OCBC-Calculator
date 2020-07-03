package com.ocbc.calculator.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author cokkyturnip
 *
 */
@Component
public class AppProperties {

    @Value("${server.port}")
    String servicePort;

    @Value("${mail.username}")
    String mailUsername;

    @Value("${mail.password}")
    String mailPassword;

    @Value("${mail.smtp.auth}")
    String mailStmpAuth;

    @Value("${mail.smtp.starttls.enable}")
    String mailSmtpTls;

    @Value("${mail.smtp.host}")
    String mailSmtpHost;

    @Value("${mail.smtp.port}")
    String mailSmtpPort;

    @Value("${sdk.connectTimeout}")
    String sdkConnectTimeout;

    @Value("${sdk.readTimeout}")
    String sdkReadTimeout;

    @Value("${sdk.username}")
    String sdkDolphinUsername;

    @Value("${sdk.password}")
    String sdkDolphinPassword;

    @Value("${sdk.dolphin.base.url}")
    String sdkDolphinBaseUrl;

    @Value("${sdk.dolphin.graph.auth}")
    String sdkDolphinGraphAuth;

    @Value("${sdk.dolphin.graph.contacts}")
    String sdkDolphinGraphContacts;

    @Value("${sdk.dolphin.graph.contacts.update}")
    String sdkDolphinGraphContactsUpdate;

    @Value("${ocbc.pictures.quotes}")
    String ocbc_pictures_quotes;

    public String getServicePort() {
        return servicePort;
    }

    public void setServicePort(String servicePort) {
        this.servicePort = servicePort;
    }

    public String getMailUsername() {
        return mailUsername;
    }

    public void setMailUsername(String mailUsername) {
        this.mailUsername = mailUsername;
    }

    public String getMailPassword() {
        return mailPassword;
    }

    public void setMailPassword(String mailPassword) {
        this.mailPassword = mailPassword;
    }

    public String getMailStmpAuth() {
        return mailStmpAuth;
    }

    public void setMailStmpAuth(String mailStmpAuth) {
        this.mailStmpAuth = mailStmpAuth;
    }

    public String getMailSmtpTls() {
        return mailSmtpTls;
    }

    public void setMailSmtpTls(String mailSmtpTls) {
        this.mailSmtpTls = mailSmtpTls;
    }

    public String getMailSmtpHost() {
        return mailSmtpHost;
    }

    public void setMailSmtpHost(String mailSmtpHost) {
        this.mailSmtpHost = mailSmtpHost;
    }

    public String getMailSmtpPort() {
        return mailSmtpPort;
    }

    public void setMailSmtpPort(String mailSmtpPort) {
        this.mailSmtpPort = mailSmtpPort;
    }

    /**
     * @return the sdkConnectTimeout
     */
    public String getSdkConnectTimeout() {
        return sdkConnectTimeout;
    }

    /**
     * @param sdkConnectTimeout the sdkConnectTimeout to set
     */
    public void setSdkConnectTimeout(String sdkConnectTimeout) {
        this.sdkConnectTimeout = sdkConnectTimeout;
    }

    /**
     * @return the sdkReadTimeout
     */
    public String getSdkReadTimeout() {
        return sdkReadTimeout;
    }

    /**
     * @param sdkReadTimeout the sdkReadTimeout to set
     */
    public void setSdkReadTimeout(String sdkReadTimeout) {
        this.sdkReadTimeout = sdkReadTimeout;
    }

    /**
     * @return the sdkDolphinUsername
     */
    public String getSdkDolphinUsername() {
        return sdkDolphinUsername;
    }

    /**
     * @param sdkDolphinUsername the sdkDolphinUsername to set
     */
    public void setSdkDolphinUsername(String sdkDolphinUsername) {
        this.sdkDolphinUsername = sdkDolphinUsername;
    }

    /**
     * @return the sdkDolphinPassword
     */
    public String getSdkDolphinPassword() {
        return sdkDolphinPassword;
    }

    /**
     * @param sdkDolphinPassword the sdkDolphinPassword to set
     */
    public void setSdkDolphinPassword(String sdkDolphinPassword) {
        this.sdkDolphinPassword = sdkDolphinPassword;
    }

    /**
     * @return the sdkDolphinBaseUrl
     */
    public String getSdkDolphinBaseUrl() {
        return sdkDolphinBaseUrl;
    }

    /**
     * @param sdkDolphinBaseUrl the sdkDolphinBaseUrl to set
     */
    public void setSdkDolphinBaseUrl(String sdkDolphinBaseUrl) {
        this.sdkDolphinBaseUrl = sdkDolphinBaseUrl;
    }

    /**
     * @return the sdkDolphinGraphAuth
     */
    public String getSdkDolphinGraphAuth() {
        return sdkDolphinGraphAuth;
    }

    /**
     * @param sdkDolphinGraphAuth the sdkDolphinGraphAuth to set
     */
    public void setSdkDolphinGraphAuth(String sdkDolphinGraphAuth) {
        this.sdkDolphinGraphAuth = sdkDolphinGraphAuth;
    }

    /**
     * @return the sdkDolphinGraphContacts
     */
    public String getSdkDolphinGraphContacts() {
        return sdkDolphinGraphContacts;
    }

    /**
     * @param sdkDolphinGraphContacts the sdkDolphinGraphContacts to set
     */
    public void setSdkDolphinGraphContacts(String sdkDolphinGraphContacts) {
        this.sdkDolphinGraphContacts = sdkDolphinGraphContacts;
    }

    /**
     * @return the sdkDolphinGraphContactsUpdate
     */
    public String getSdkDolphinGraphContactsUpdate() {
        return sdkDolphinGraphContactsUpdate;
    }

    /**
     * @param sdkDolphinGraphContactsUpdate the sdkDolphinGraphContactsUpdate to
     * set
     */
    public void setSdkDolphinGraphContactsUpdate(String sdkDolphinGraphContactsUpdate) {
        this.sdkDolphinGraphContactsUpdate = sdkDolphinGraphContactsUpdate;
    }

    @Value("${ocbc.nisp.api.base.url}")
    public String BASE_URL;

    @Value("${ocbc.nisp.api.post.futurevalue}")
    public String POST_FUTUREVALUE;

    @Value("${ocbc.nisp.api.post.targetvalue}")
    public String POST_TARGETVALUE;

    @Value("${ocbc.nisp.api.post.listproduct}")
    public String POST_LISTPRODUCT;

    @Value("${ocbc.nisp.api.post.presentvalue}")
    public String POST_PRESENTVALUE;

    @Value("${model.request.all.Channel_ID}")
    public String Channel_ID;

    @Value("${ocbc.nisp.id.livechat}")
    public String IdLiveChat;

    @Value("${ocbc.nisp.error.response.8}")
    public String response8;

    @Value("${ocbc.nisp.error.response.10}")
    public String response10;

    public String getOcbc_pictures_quotes() {
        return ocbc_pictures_quotes;
    }

    public void setOcbc_pictures_quotes(String ocbc_pictures_quotes) {
        this.ocbc_pictures_quotes = ocbc_pictures_quotes;
    }

    public String getBASE_URL() {
        return BASE_URL;
    }

    public void setBASE_URL(String BASE_URL) {
        this.BASE_URL = BASE_URL;
    }

    public String getPOST_FUTUREVALUE() {
        return POST_FUTUREVALUE;
    }

    public void setPOST_FUTUREVALUE(String POST_FUTUREVALUE) {
        this.POST_FUTUREVALUE = POST_FUTUREVALUE;
    }

    public String getPOST_TARGETVALUE() {
        return POST_TARGETVALUE;
    }

    public void setPOST_TARGETVALUE(String POST_TARGETVALUE) {
        this.POST_TARGETVALUE = POST_TARGETVALUE;
    }

    public String getPOST_LISTPRODUCT() {
        return POST_LISTPRODUCT;
    }

    public void setPOST_LISTPRODUCT(String POST_LISTPRODUCT) {
        this.POST_LISTPRODUCT = POST_LISTPRODUCT;
    }

    public String getPOST_PRESENTVALUE() {
        return POST_PRESENTVALUE;
    }

    public void setPOST_PRESENTVALUE(String POST_PRESENTVALUE) {
        this.POST_PRESENTVALUE = POST_PRESENTVALUE;
    }

    public String getChannel_ID() {
        return Channel_ID;
    }

    public void setChannel_ID(String Channel_ID) {
        this.Channel_ID = Channel_ID;
    }

    public String getIdLiveChat() {
        return IdLiveChat;
    }

    public void setIdLiveChat(String IdLiveChat) {
        this.IdLiveChat = IdLiveChat;
    }

    public String getResponse8() {
        return response8;
    }

    public void setResponse8(String response8) {
        this.response8 = response8;
    }

    public String getResponse10() {
        return response10;
    }

    public void setResponse10(String response10) {
        this.response10 = response10;
    }

}
