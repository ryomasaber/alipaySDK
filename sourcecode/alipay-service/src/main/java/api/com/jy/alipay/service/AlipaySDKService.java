package api.com.jy.alipay.service;

import api.com.jy.alipay.model.BuildResponse;
import api.com.jy.alipay.util.AlipaySubmit;
import api.com.jy.alipay.util.UtilDate;
import api.com.jy.request.WapPayRequest;
import api.com.jy.request.WebPayRequest;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * 支付宝相关接口
 * Created by Saber on 2016/4/1.
 */
@Service
public class AlipaySDKService {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(AlipaySDKService.class.getCanonicalName());

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
     * PC即时到账接口
     *      partner         合作伙伴id
     *      key             秘钥
     *      notify_url      异步通知地址
     *      webReturn_url   PC即时到账同步通知
     *   适用于4个参数均在环境变量中配置过的情况
     * @param totalFee     付款金额
     * @param orderName     订单名称
     * @param body          商品描述
     * @return  支付宝表单请求
     */
    public BuildResponse webPay(Double totalFee,String orderName ,String body){

        return _webPay(partner, key, totalFee, orderName,body, webNotifyUrl, webReturnUrl);
    }

    /**
     *  PC即时到账接口    传参
     *      notify_url      异步通知地址
     *      webReturn_url   PC即时到账同步通知
     *    适用于2个参数固定且已配置的情况
     * @param partner       合作伙伴id
     * @param key           秘钥
     * @param totalFee      付款金额
     * @param orderName     订单名称
     * @param body          商品描述
     * @return  支付宝表单请求
     */
    public BuildResponse webPay(String partner,String key,Double totalFee,String orderName,String body){

        return _webPay(partner, key, totalFee, orderName, body, webNotifyUrl, webReturnUrl);
    }

    /**
     *  PC即时到账接口     传参
     * @param partner       合作伙伴id
     * @param key           秘钥
     * @param totalFee      付款金额
     * @param orderName     订单名称
     * @param body          商品描述
     * @param webReturnUrl  同步通知地址
     * @return  支付宝表单请求
     */
    public BuildResponse webPay(String partner,String key,Double totalFee,String orderName,String body ,String webReturnUrl){

        return _webPay(partner, key, totalFee, orderName,body, webNotifyUrl, webReturnUrl);
    }
    /**
     *  PC即时到账接口    传参
     * @param partner       合作伙伴id
     * @param key           秘钥
     * @param totalFee      付款金额
     * @param orderName     订单名称
     * @param body          商品描述
     * @param webNotifyUrl  异步通知地址
     * @param webReturnUrl  同步通知地址
     * @return  支付宝表单请求
     */
    public static BuildResponse webPay(String partner,String key,Double totalFee,String orderName,String body,String webNotifyUrl,String webReturnUrl){

        return _webPay(partner, key, totalFee, orderName,body, webNotifyUrl, webReturnUrl);
    }

    /**
     *  PC即时到账接口
     * @param webPayRequest   全部参数
     * @param key             秘钥
     * @return
     */
    public static String webPay(WebPayRequest webPayRequest,String key){

        return AlipaySubmit.buildWebPayRequest(webPayRequest, key);
    }

//---------------------------------------------------------------------------------------------------------------------------------
    /**
     * wap手机网站支付接口
     *      partner         合作伙伴id
     *      key             秘钥
     *      notify_url      异步通知地址
     *      wapReturnUrl    wap手机网站同步通知
     *   适用于4个参数均在环境变量中配置过的情况
     * @param totalFee     付款金额
     * @param orderName     订单名称
     * @param body          商品描述
     * @return  支付宝表单请求
     */
    public BuildResponse wapPay(Double totalFee,String orderName,String body){

        return _wapPay( partner,key,totalFee,orderName,body,wapNotifyUrl,wapReturnUrl);
    }

    /**
     *  wap手机网站支付接口     传参
     *      notify_url      异步通知地址
     *      webReturn_url   PC即时到账同步通知
     *    适用于2个参数固定且已配置的情况
     * @param partner       合作伙伴id
     * @param key           秘钥
     * @param totalFee      付款金额
     * @param orderName     订单名称
     * @param body          商品描述
     * @return  支付宝表单请求
     */
    public BuildResponse wapPay(String partner,String key,Double totalFee,String orderName,String body){

        return _wapPay( partner,key,totalFee,orderName,body,wapNotifyUrl,wapReturnUrl);
    }

    /**
     *  wap手机网站支付接口     传参
     * @param partner       合作伙伴id
     * @param key           秘钥
     * @param totalFee      付款金额
     * @param orderName     订单名称
     * @param body          商品描述
     * @param wapReturnUrl  同步通知地址
     * @return  支付宝表单请求
     */
    public BuildResponse wapPay(String partner,String key,Double totalFee,String orderName,String body,String wapReturnUrl){

        return _wapPay( partner,key,totalFee,orderName,body,wapNotifyUrl,wapReturnUrl);
    }

    /**
     *  wap手机网站支付接口    传参
     * @param partner       合作伙伴id
     * @param key           秘钥
     * @param totalFee      付款金额
     * @param orderName     订单名称
     * @param body          商品描述
     * @param wapNotifyUrl  异步通知地址
     * @param wapReturnUrl  同步通知地址
     * @return  支付宝表单请求
     */
    public static BuildResponse wapPay(String partner,String key,Double totalFee,String orderName,String body,String wapNotifyUrl,String wapReturnUrl){

        return _wapPay( partner,key,totalFee,orderName,body,wapNotifyUrl,wapReturnUrl);
    }

    /**
     *  wap手机支付接口
     * @param wapPayRequest     全部参数
     * @param key               秘钥
     * @return url
     */
    public static String wapPay(WapPayRequest wapPayRequest,String key){

        return AlipaySubmit.buildWapPayRequest(wapPayRequest,key);
    }

//---------------------------------------------------------------------------------------------------------------------------------
    /**
     *  PC即时到账 公共部分
     * @param partner       合作伙伴id
     * @param key           秘钥
     * @param totalFee      付款金额
     * @param orderName     订单名称
     * @param body          商品描述
     * @param webNotifyUrl  异步通知地址
     * @param webReturnUrl  同步通知地址
     * @return
     */
    private static BuildResponse _webPay(String partner,String key,Double totalFee,String orderName,String body,String webNotifyUrl,String webReturnUrl){
        BuildResponse response = new BuildResponse();
        // 商户订单号.
        String outTradeNo = UtilDate.getOrderNum();
        WebPayRequest request = new WebPayRequest(partner,totalFee,outTradeNo,orderName,body);
        request.setNotifyUrl(webNotifyUrl);
        request.setReturnUrl(webReturnUrl);

        response.setOutTradeNo(outTradeNo);
        response.setBuildStr(AlipaySubmit.buildWebPayRequest(request, key));
        return response;
    }

    /**
     *  wap手机网站支付 公共部分
     * @param partner       合作伙伴id
     * @param key           秘钥
     * @param totalFee      付款金额
     * @param orderName     订单名称
     * @param body          商品描述
     * @param wapNotifyUrl  异步通知地址
     * @param wapReturnUrl  同步通知地址
     * @return
     */
    private static BuildResponse _wapPay(String partner,String key,Double totalFee,String orderName,String body,String wapNotifyUrl,String wapReturnUrl){
        BuildResponse response = new BuildResponse();
        // 商户订单号.
        String outTradeNo = UtilDate.getOrderNum();
        WapPayRequest request = new WapPayRequest(partner,totalFee,outTradeNo,orderName,body);
        request.setNotifyUrl(wapNotifyUrl);
        request.setReturnUrl(wapReturnUrl);

        response.setOutTradeNo(outTradeNo);
        response.setBuildStr(AlipaySubmit.buildWapPayRequest(request,key));
        return response;
    }

}
