package api.com.jy.alipay.controller;

import api.com.jy.alipay.model.BuildResponse;
import api.com.jy.alipay.model.ResultJSON;
import api.com.jy.alipay.service.AlipaySDKService;
import api.com.jy.alipay.service.NotifySDKService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 支付宝专用
 * Created by Saber on 2015/11/11.
 */
@Controller
@RequestMapping("/alipay")
public class PayController {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(PayController.class.getCanonicalName());

    @Autowired
    private AlipaySDKService alipayService;

    @Autowired
    private NotifySDKService notifyService;

    /**
     *  合作身份者ID，以2088开头由16位纯数字组成的字符串
     */
    @Value("${partner}")
    private String partner;
    /**
     *  商户的私钥
     */
    @Value("${key}")
    private String key;

    @Value("${private_key}")
    private String privateKey;

    /**
     *  PC即时到账异步通知地址
     */
    @Value("${web_notify_url}")
    private String webNotifyUrl;
    /**
     *  wap手机网站异步通知地址
     */
    @Value("${wap_notify_url}")
    private String wapNotifyUrl;
    /**
     *  PC即时到账同步通知
     */
    @Value("${webReturn_url}")
    private String webReturnUrl;
    /**
     *  wap手机网站同步通知
     */
    @Value("${wapReturn_url}")
    private String wapReturnUrl;

    /**
     * web即时到账
     *
     * @param response
     * @param total_fee 金额
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/webPay.do", method = RequestMethod.GET)
    public void webPay(HttpServletResponse response,Double total_fee){

        String orderName="webPay测试订单";
        //构建支付请求
        BuildResponse buildResponse = alipayService.webPay(total_fee, orderName, null);
        try {
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(buildResponse.getBuildStr());
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * wap手机网站
     *
     * @param response
     * @param total_fee 金额
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/wapPay.do", method = RequestMethod.GET)
    public void wapPay(HttpServletResponse response,Double total_fee){

        String orderName="wapPay测试订单";
        //构建支付请求
        BuildResponse buildResponse = alipayService.wapPay(total_fee, orderName, null);
        logger.debug("buildResponse:"+buildResponse.getBuildStr());
        try {
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(buildResponse.getBuildStr());
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/appPay.do",method = RequestMethod.GET)
    @ResponseBody
    public Object appPay(HttpServletResponse response,Double total_fee){
        String orderName="钉钉appPay测试订单";
        String body = "钉钉支付";
        BuildResponse buildResponse = alipayService.appPay(partner, key, total_fee, orderName, body, wapNotifyUrl, wapReturnUrl);
        logger.debug("buildResponse:" + buildResponse.getBuildStr());
        return buildResponse.getBuildStr();
    }

    @RequestMapping(value = "/rsaWapPay.do",method = RequestMethod.GET)
    public void rsaWapPay(HttpServletResponse response,Double total_fee){
        String orderName="钉钉wapPay测试订单";
        String body = "钉钉支付";
        BuildResponse buildResponse = alipayService.wapPayRequest(partner, key, total_fee, orderName, body, wapNotifyUrl, wapReturnUrl, "RSA");
        logger.debug("buildResponse:" + buildResponse.getBuildStr());
        try {
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(buildResponse.getBuildStr());
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * web 同步通知
     * @param request
     * @return
     * @author Saber
     */
    @RequestMapping(value="/web_return_url.do",method = RequestMethod.GET)
    public String web_return_url(HttpServletRequest request){

        ResultJSON json = notifyService.webReturnUrl(request,partner,key);
        logger.debug("web同步通知："+json.toString());
        if(json.isSuccess()){
            return "alipay/web_success";
        }else {
            return "alipay/fail";
        }
    }
    /**
     * wap 同步通知
     * @param request
     * @return
     * @author Saber
     */
    @RequestMapping(value="/wap_return_url.do",method = RequestMethod.GET)
    public String wap_return_url(HttpServletRequest request){

        ResultJSON json = notifyService.webReturnUrl(request,partner,key);
        logger.debug("wap同步通知："+json.toString());
        if(json.isSuccess()){
            return "alipay/web_success";
        }else {
            return "alipay/fail";
        }
    }
    /**
     * 支付宝异步通知
     *
     * @param request
     * @return
     * @author Saber
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/web_notify_url.do", method = RequestMethod.POST)
    public void web_notify_url(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

        ResultJSON json = notifyService.webNotifyUrl(request, partner,key ,"15700113209", 0.01);
        logger.debug("异步通知："+json.toString());

        String result = "fail";
        if(json.isSuccess()){
            result="SUCCESS";
        }
        try {
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(result);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/wap_notify_url.do", method = RequestMethod.POST)
    public void wap_notify_url(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

        ResultJSON json = notifyService.wapNotifyUrl(request, partner,key,"15700113209", 0.01);
        logger.debug("异步通知："+json.toString());

        String result = "fail";
        if(json.isSuccess()){
            result="SUCCESS";
        }
        try {
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(result);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //TODO 退款_异步通知[未完成]
   /* @RequestMapping(value = "/refund_notify.do", method = RequestMethod.POST,produces = "application/json")
    public String refund_notify_url(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception {

        //服务器异步通知页面路径
        String notify_url = "http://商户网关地址/refund_fastpay_by_platform_pwd-JAVA-UTF-8/notify_url.jsp";
        //需http://格式的完整路径，不允许加?id=123这类自定义参数

        //卖家支付宝帐户
        String seller_email = new String(request.getParameter("WIDseller_email").getBytes("ISO-8859-1"),"UTF-8");
        //必填

        //退款当天日期
        String refund_date = new String(request.getParameter("WIDrefund_date").getBytes("ISO-8859-1"),"UTF-8");
        //必填，格式：年[4位]-月[2位]-日[2位] 小时[2位 24小时制]:分[2位]:秒[2位]，如：2007-10-01 13:13:13

        //批次号
        String batch_no = new String(request.getParameter("WIDbatch_no").getBytes("ISO-8859-1"),"UTF-8");
        //必填，格式：当天日期[8位]+序列号[3至24位]，如：201008010000001

        //退款笔数
        String batch_num = new String(request.getParameter("WIDbatch_num").getBytes("ISO-8859-1"),"UTF-8");
        //必填，参数detail_data的值中，“#”字符出现的数量加1，最大支持1000笔（即“#”字符出现的数量999个）

        //退款详细数据
        String detail_data = new String(request.getParameter("WIDdetail_data").getBytes("ISO-8859-1"),"UTF-8");
        //必填，具体格式请参见接口技术文档


        //////////////////////////////////////////////////////////////////////////////////

        //把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", "refund_fastpay_by_platform_pwd");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("notify_url", notify_url);
        sParaTemp.put("seller_email", seller_email);
        sParaTemp.put("refund_date", refund_date);
        sParaTemp.put("batch_no", batch_no);
        sParaTemp.put("batch_num", batch_num);
        sParaTemp.put("detail_data", detail_data);

        //建立请求
        String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
       // out.println(sHtmlText);
       logger.debug(sHtmlText);

        return null;
    }
*/



}
