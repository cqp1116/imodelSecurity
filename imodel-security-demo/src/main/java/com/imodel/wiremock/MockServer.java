package com.imodel.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;

/**
 * 模拟restful服务请求
 */
public class MockServer {


    /**
     * 模拟restful服务
     * @param args
     */

    public static void main(String[] args) {
        WireMock.configureFor(9999);//指定wiremock的端口
        WireMock.removeAllMappings();

        //编写相应

        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo("/wire")).willReturn(WireMock.aResponse().withBody("{\"id\":1,\"name\":\"Jack\"}").withStatus(200)));
    }
}
