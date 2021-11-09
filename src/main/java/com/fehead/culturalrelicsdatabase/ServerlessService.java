package com.fehead.culturalrelicsdatabase;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.Method;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.util.HashMap;

/**
 * @Author Verge
 * @Date 2021/11/9 16:38
 * @Version 1.0
 */
public class ServerlessService implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private static boolean isStart = false;

    static {
        if (!isStart) {
            CulturalRelicsDatabaseApplication.main(new String[]{});
            isStart = true;
        }
    }


    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {

        String path = event.getPath();
        String httpMethod = event.getHttpMethod();
        HttpRequest httpRequest = new HttpRequest("http://localhost:8889"+path);
        HttpResponse httpResponse = httpRequest
                .setMethod(Method.valueOf(httpMethod))
                .execute();


        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setIsBase64Encoded(false);
        response.setStatusCode(200);
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "text/html");
        response.setHeaders(headers);
        response.setBody(httpResponse.body());
        // log execution details
        return response;
    }
}
