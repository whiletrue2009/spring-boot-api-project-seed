package cn.jssgx.esa.superclient.demo;

import com.alibaba.fastjson.JSONObject;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * @author along
 * @date 2023/6/18
 */
public class GetCameraPreviewURL {

    public static String getCameraPreviewURL(String cameraId) throws Exception {

        /**
         * STEP1：设置平台参数，根据实际情况,设置host appkey appsecret 三个参数.
         */

        ArtemisConfig artemisConfig = new ArtemisConfig("183.64.175.153:446", "29180881", "XO0wCAYGi4KV70ybjznx");

        //        ArtemisConfig.host = "127.0.0.1:443"; // 平台的ip端口
        //        ArtemisConfig.appKey = "29180881";  // 密钥appkey
        //        ArtemisConfig.appSecret = "XO0wCAYGi4KV70ybjznx";// 密钥appSecret

        /**
         * STEP2：设置OpenAPI接口的上下文
         */
        final String ARTEMIS_PATH = "/artemis";

        /**
         * STEP3：设置接口的URI地址
         */
        final String previewURLsApi = ARTEMIS_PATH + "/api/video/v1/cameras/previewURLs";
        Map<String, String> path = new HashMap<String, String>(2) {
            {
                put("https://", previewURLsApi);//根据现场环境部署确认是http还是https
            }
        };

        /**
         * STEP4：设置参数提交方式
         */
        String contentType = "application/json";

        /**
         * STEP5：组装请求参数
         */
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("cameraIndexCode", cameraId);
        jsonBody.put("streamType", 0);
        jsonBody.put("protocol", "rtsp");
        jsonBody.put("transmode", 1);
        jsonBody.put("expand", "streamform=ps");
        String body = jsonBody.toJSONString();
        /**
         * STEP6：调用接口
         */
        String result =
            ArtemisHttpUtil.doPostStringArtemis(artemisConfig, path, body, null, null, contentType, null);// post请求application/json类型参数
        return result;
    }

    public static void main(String[] args) throws Exception {
        String result = getCameraPreviewURL("50035700001310151858");
        System.out.println("result结果示例: " + result);
    }
}
