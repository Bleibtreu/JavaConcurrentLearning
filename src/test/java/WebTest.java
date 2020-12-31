import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.List;
import java.util.Map;

/**
 * @author bleibtreu
 * @date 2020/12/25 19:55
 */
public class WebTest {
    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //setConnectTimeout：设置连接主机超时（单位：毫秒）
            connection.setConnectTimeout(2000);//2秒超时
            //setReadTimeout：设置从主机读取数据超时（单位：毫秒）
            //connection.setReadTimeout(2000);

            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("localhost", 8888));
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection(proxy);;
            // 设置通用的请求属性
            System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
//            conn.setRequestProperty("Host", "dev.coc.10086.cn");
            conn.setRequestProperty("Cache-Control", "no-cache");
            conn.setRequestProperty("Postman-Token", "aaab3a19-4847-4c44-b05f-24a8b4c578dd");
//            conn.setRequestProperty("referer", "https://dev.coc.10086.cn/coc/web/coc2020/package2/?pageId=1258951852608311296&channelId=P00000006174");
//            conn.setRequestProperty("Origin", "https://dev.coc.10086.cn");
            conn.setRequestProperty("Content-Type", "application/json");
//            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            conn.setRequestProperty("Cookies", "Cookie: udata_s_300011958696=1608878931328613652; udata_lt_300011958696=1608878931330131566; udata_s_%40id%40=1608879889304221858; udata_lt_%40id%40=1608879889305339924; mobile=1059-29731-5103-38189; WT_FPC=id=28bbf4501473f0a9bed1608606023781:lv=1608893020720:ss=1608893002629");
            conn.setRequestMethod("POST");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String result = sendPost("https://dev.coc.10086.cn/coc/pkg/order/bookProduct", "{\"activityId\":\"\",\"channelSeqId\":\"\",\"mobile\":\"13503092313\",\"pageId\":\"22001\",\"productCode\":\"100000001271\",\"sellerId\":\"\",\"sellerMobile\":\"\",\"smsCode\":\"540263\",\"touchCode\":\"null\",\"uid\":\"\",\"userId\":\"\",\"verifyType\":0}");
//        String result = sendPost("https://dev.coc.10086.cn/coc/pkg/order/bookProduct", "{\"activityId\": \"\",\"channelSeqId\": \"\",\"mobile\": \"13503092313\",\"pageId\": \"null\",\"productCode\": \"100000001493\",\"sellerId\": \"\",\"sellerMobile\": \"\",\"smsCode\": \"783936\",\"touchCode\": \"null\",\"uid\": \"\",\"userId\": \"1299201369435869184\",\"verifyType\": \"0\"}");
//        String result = sendPost("https://dev.coc.10086.cn/coc/pkg/order/bookProduct", "{\"activityId\":\"\",\"channelSeqId\":\"\",\"mobile\":\"13503092313\",\"pageId\":\"1258951852608311296\",\"productCode\":\"100000001271\",\"sellerId\":\"\",\"sellerMobile\":\"\",\"smsCode\":\"718987\",\"touchCode\":\"\",\"uid\":\"\",\"userId\":\"\",\"verifyType\":0}");
        System.out.println(result);
    }
}
