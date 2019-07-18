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

    @Value("${ocbc.nisp.api.base.url}")
    public String BASE_URL;

    @Value("${ocbc.nisp.api.post.futurevalue}")
    public String POST_FUTUREVALUE;

    @Value("${ocbc.nisp.api.post.targetvalue}")
    public String POST_TARGETVALUE;

    @Value("${ocbc.nisp.api.post.listproduct}")
    public String POST_LISTPRODUCT;

    @Value("${model.request.all.Channel_ID}")
    public String Channel_ID;

    @Value("${ocbc.nisp.id.livechat}")
    public String IdLiveChat;
    
    @Value("${ocbc.nisp.error.response.8}")
    public String response8;
    
    @Value("${ocbc.nisp.error.response.10}")
    public String response10;

}
