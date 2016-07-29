package api.com.jy.alipay.util;

import api.com.jy.alipay.sign.MD5;
import api.com.jy.alipay.util.httpClient.HttpProtocolHandler;
import api.com.jy.alipay.util.httpClient.HttpResponse;
import api.com.jy.alipay.util.httpClient.HttpResultType;
import api.com.jy.request.AlipayRquest;
import api.com.jy.request.WapPayRequest;
import api.com.jy.request.WebPayRequest;
import api.com.jy.alipay.util.httpClient.HttpRequest;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/* *
 *类名：AlipaySubmit
 *功能：支付宝各接口请求提交类
 *详细：构造支付宝各接口表单HTML文本，获取远程HTTP数据
 *版本：3.3
 *日期：2012-08-13
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipaySubmit {

    /**
     * 支付宝提供给商户的服务接入网关URL(新)
     */
    private static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";
    private static final String input_charset ="utf-8";

    /**
     * 生成签名结果
     * @param sPara 要签名的数组
     * @return 签名结果字符串
     */
	public static String buildRequestMysign(Map<String, String> sPara,String key) {
    	String prestr = AlipayCore.createLinkString(sPara); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String mysign = "";
        mysign = MD5.sign(prestr, key, input_charset);
        return mysign;
    }

    /**
     * 生成要请求给支付宝的参数数组
     * @param sParaTemp 请求前的参数数组
     * @return 要请求的参数数组
     */
    private static Map<String, String> buildRequestPara(Map<String, String> sParaTemp,String key) {
        //除去数组中的空值和签名参数
        Map<String, String> sPara = AlipayCore.paraFilter(sParaTemp);
        //生成签名结果
        String mysign = buildRequestMysign(sPara, key);

        //签名结果与签名方式加入请求提交参数组中
        sPara.put("sign", mysign);
        sPara.put("sign_type", "MD5");

        return sPara;
    }

    /**
     * 建立请求，以表单HTML形式构造（默认）
     * @param sParaTemp 请求参数数组
     * @param strMethod 提交方式。两个值可选：post、get
     * @param strButtonName 确认按钮显示文字
     * @return 提交表单HTML文本
     */
    public static String buildRequest(Map<String, String> sParaTemp, String strMethod, String strButtonName,String key) {
        //待请求参数数组
        Map<String, String> sPara = buildRequestPara(sParaTemp, key);
        List<String> keys = new ArrayList<String>(sPara.keySet());

        StringBuffer sbHtml = new StringBuffer();

        sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"" + ALIPAY_GATEWAY_NEW
                      + "_input_charset=" + input_charset + "\" method=\"" + strMethod
                      + "\">");

        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) sPara.get(name);

            sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
        }

        //submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
        sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script>");

        return sbHtml.toString();
    }

    public static String buildRequest(Map<String, String> sParaTemp,String key) {
        //待请求参数数组
        Map<String, String> sPara = buildRequestPara(sParaTemp,key);
        List<String> keys = new ArrayList<String>(sPara.keySet());

        StringBuffer sbHtml = new StringBuffer();

        sbHtml.append(ALIPAY_GATEWAY_NEW+"_input_charset=" + input_charset);

        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) sPara.get(name);

            sbHtml.append("&" + name + "=" + value );
        }

        return sbHtml.toString();
    }

    /**
     * 建立请求，以表单HTML形式构造，带文件上传功能
     * @param sParaTemp 请求参数数组
     * @param strMethod 提交方式。两个值可选：post、get
     * @param strButtonName 确认按钮显示文字
     * @param strParaFileName 文件上传的参数名
     * @return 提交表单HTML文本
     */
    public static String buildRequest(Map<String, String> sParaTemp, String strMethod, String strButtonName, String strParaFileName,String key) {
        //待请求参数数组
        Map<String, String> sPara = buildRequestPara(sParaTemp,key);
        List<String> keys = new ArrayList<String>(sPara.keySet());

        StringBuffer sbHtml = new StringBuffer();

        sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\"  enctype=\"multipart/form-data\" action=\"" + ALIPAY_GATEWAY_NEW
                      + "_input_charset=" + input_charset+ "\" method=\"" + strMethod
                      + "\">");

        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) sPara.get(name);

            sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
        }

        sbHtml.append("<input type=\"file\" name=\"" + strParaFileName + "\" />");

        //submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");

        return sbHtml.toString();
    }

    /**
     * 建立请求，以模拟远程HTTP的POST请求方式构造并获取支付宝的处理结果
     * 如果接口中没有上传文件参数，那么strParaFileName与strFilePath设置为空值
     * 如：buildRequest("", "",sParaTemp)
     * @param strParaFileName 文件类型的参数名
     * @param strFilePath 文件路径
     * @param sParaTemp 请求参数数组
     * @param key 请求参数数组
     * @return 支付宝处理结果
     * @throws Exception
     */
    public static String buildRequest(String strParaFileName, String strFilePath,Map<String, String> sParaTemp,String key) throws Exception {
        //待请求参数数组
        Map<String, String> sPara = buildRequestPara(sParaTemp,key);

        HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();

        HttpRequest request = new HttpRequest(HttpResultType.BYTES);
        //设置编码集
        request.setCharset(input_charset);

        request.setParameters(generatNameValuePair(sPara));
        request.setUrl(ALIPAY_GATEWAY_NEW + "_input_charset=" + input_charset);

        HttpResponse response = httpProtocolHandler.execute(request,strParaFileName,strFilePath);
        if (response == null) {
            return null;
        }

        String strResult = response.getStringResult();

        return strResult;
    }

    /**
     * MAP类型数组转换成NameValuePair类型
     * @param properties  MAP类型数组
     * @return NameValuePair类型数组
     */
    private static NameValuePair[] generatNameValuePair(Map<String, String> properties) {
        NameValuePair[] nameValuePair = new NameValuePair[properties.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            nameValuePair[i++] = new NameValuePair(entry.getKey(), entry.getValue());
        }

        return nameValuePair;
    }

    /**
     * 用于防钓鱼，调用接口query_timestamp来获取时间戳的处理函数
     * 注意：远程解析XML出错，与服务器是否支持SSL等配置有关
     * @return 时间戳字符串
     * @throws IOException
     * @throws DocumentException
     * @throws MalformedURLException
     */
	public static String query_timestamp(String partner) throws MalformedURLException, DocumentException, IOException {

        //构造访问query_timestamp接口的URL串
        String strUrl = ALIPAY_GATEWAY_NEW + "service=query_timestamp&partner=" + partner + "&_input_charset" + input_charset;
        StringBuffer result = new StringBuffer();

        SAXReader reader = new SAXReader();
        Document doc = reader.read(new URL(strUrl).openStream());

        List<Node> nodeList = doc.selectNodes("//alipay/*");

        for (Node node : nodeList) {
            // 截取部分不需要解析的信息
            if (node.getName().equals("is_success") && node.getText().equals("T")) {
                // 判断是否有成功标示
                List<Node> nodeList1 = doc.selectNodes("//response/timestamp/*");
                for (Node node1 : nodeList1) {
                    result.append(node1.getText());
                }
            }
        }

        return result.toString();
    }

    /**
     *  PC即时到账支付
     * @param params
     * @param key
     * @return
     */
    public static String buildWebPayRequest(WebPayRequest params,String key){
        try {
            //对象转为map 并将key由驼峰转为下划线
            Map<String, String> sParaTemp =MapUtils.toMapForFlat(params);;
            //构建基础参数
            buildPayRequest(params,sParaTemp);

            //移除map中值为空的元素
            removeNullValue(sParaTemp);
            return buildRequest(sParaTemp, "post", "确认", key);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String buildWapPayRequest(WapPayRequest params ,String key){
        try {
            //对象转为map 并将key由驼峰转为下划线
            Map<String, String> sParaTemp =MapUtils.toMapForFlat(params);;
            //构建基础参数
            buildPayRequest(params,sParaTemp);

            //移除map中值为空的元素
            removeNullValue(sParaTemp);
            return buildRequest(sParaTemp, "post", "确认", key);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 构建公共请求
     * @param params
     * @return
     */
    public static void buildPayRequest(AlipayRquest params,Map<String, String> sParaTemp){
        sParaTemp.put("service", params.getService());//接口服务
        sParaTemp.put("partner", params.getPartner());//支付宝PID
        sParaTemp.put("seller_id", params.getPartner());//支付宝PID
        sParaTemp.put("_input_charset", params._input_charset);//统一编码
        sParaTemp.put("payment_type", params.payment_type);//支付类型
        sParaTemp.put("notify_url", params.getNotifyUrl());//异步通知页面
        sParaTemp.put("return_url", params.getReturnUrl());//页面跳转同步通知页面

        String noEmptys[] = {"service","partner","notify_url","return_url"};

        try {
            for(String key:noEmptys){
                if(StringUtils.isEmpty(key))
                    throw new Exception(key+" 参数值不能为空!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 移除Map中值为空的元素
     * @param sParaTemp
     */
    public static void removeNullValue( Map<String, String> sParaTemp){
        Iterator<Map.Entry<String, String>> it = sParaTemp.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, String> entry=it.next();
            String value=entry.getValue();
            if(StringUtils.isEmpty(value) || "class".equals(entry.getKey())){
                it.remove();//移除空元素
            }
        }
    }
}
