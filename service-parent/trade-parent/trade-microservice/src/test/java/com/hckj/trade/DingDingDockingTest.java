package com.hckj.trade;

import com.alibaba.fastjson.JSON;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiAttendanceListRequest;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiAttendanceListResponse;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.taobao.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * 对接顶顶类
 *
 * @author ：yuhui
 * @date ：Created in 2021/2/2 14:04
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = TradeServiceMain.class)
public class DingDingDockingTest {
    private static final Logger logger = LoggerFactory.getLogger(DingDingDockingTest.class);

    public static void main(String[] args) {
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
            OapiGettokenRequest request = new OapiGettokenRequest();
            request.setAppkey("dingw2vikfpsgjxu0pqs");
            request.setAppsecret("JGKogw3vJoAu9DOu_QukscMa___WcKWgmUla2fmR9hN1wHePo_Ks_UlUgWfV4txW");
            request.setHttpMethod("GET");
            OapiGettokenResponse response = client.execute(request);

            String accessToken = response.getAccessToken();
            fetchData(accessToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void fetchData(String accessToken) {
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/attendance/list");
            OapiAttendanceListRequest request = new OapiAttendanceListRequest();
            request.setWorkDateFrom("2020-12-17 00:00:00");
            request.setWorkDateTo("2020-12-21 23:00:00");
            request.setUserIdList(Arrays.asList("1594910640433186"));
            request.setOffset(0L);
            request.setLimit(10L);
            OapiAttendanceListResponse response = client.execute(request, accessToken);
            System.out.println(JSON.toJSONString(response.getRecordresult()));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


//    @Test
//    public void getData() {
//
//    }
}
