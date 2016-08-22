package api.com.jy.alipay.service;

import api.com.jy.alipay.model.ResultJSON;
import api.com.jy.alipay.util.AlipayNotify;
import api.com.jy.alipay.util.MapUtils;
import api.com.jy.alipay.util.RequestUtil;
import api.com.jy.response.AlipayWapCreateDirectPayByUser_Notify_Response;
import api.com.jy.response.AlipayWapCreateDirectPayByUser_Return_Response;
import api.com.jy.response.CreateDirectPayByUser_Notify_Response;
import api.com.jy.response.CreateDirectPayByUser_Return_Response;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 同/异步 通知
 * Created by Saber on 2016/6/29.
 */
@Service
public class NotifySDKService {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(NotifySDKService.class.getCanonicalName());

    /**
     * PC即时到账异步通知
     * @param request
     * @param partner       合作身份者ID，以2088开头由16位纯数字组成的字符串
     * @param key           商户的私钥
     * @return
     */
    public static ResultJSON webNotifyUrl(HttpServletRequest request,String partner,String key){

        ResultJSON json = new ResultJSON();

        Map<String,String> params = RequestUtil.getRequestParams(request);
        logger.debug("异步通知返回参数 params: {}", params);
        try {
            CreateDirectPayByUser_Notify_Response response= MapUtils.toObject(CreateDirectPayByUser_Notify_Response.class, params, true);
            json.setObj(response);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        try {
            //校验参数
            if (!AlipayNotify.verify(params, partner, key)){
                json.setSuccess(false);
                json.setMsg("支付宝web异步通知参数验证不通过");
                return json;
            }else {
                logger.debug("支付宝web异步通知参数验证>>OK");
            }
            String tradeStatus = params.get("TRADE_STATUS");
            if("TRADE_SUCCESS".equals(tradeStatus)){          //支付成功
                logger.debug("支付成功");
            }else if ("TRADE_FINISHED".equals(tradeStatus)){  //交易完成
                logger.debug("交易完成，不可再执行其他操作");
            }

            json.setSuccess(true);
            json.setMsg(tradeStatus);

            return json;
        }catch (Exception e){
            json.setSuccess(false);
            json.setMsg(e.getMessage());
            logger.error("支付宝异步通知验证出错",e.getStackTrace().toString());
            e.printStackTrace();
        }
        return json;
    }

    /**
     *  PC即时到账异步通知（带分润）
     * @param request
     * @param partner       合作身份者ID，以2088开头由16位纯数字组成的字符串
     * @param key           商户的私钥
     * @param alipayAccount     支付宝账号
     * @param royaltyNum        分润金额
     * @return
     */
    public static ResultJSON webNotifyUrl(HttpServletRequest request,String partner,String key,String alipayAccount,Double royaltyNum){

        ResultJSON json = new ResultJSON();

        Map<String,String> params = RequestUtil.getRequestParams(request);

        logger.debug("异步web通知返回参数 params: {}", params);
        try {
            CreateDirectPayByUser_Notify_Response response= MapUtils.toObject(CreateDirectPayByUser_Notify_Response.class, params, true);
            logger.debug("支付渠道"+response.getOutChannelType());
            json.setObj(response);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        try {
            //校验参数
            if (!AlipayNotify.verify(params, partner, key)){
                json.setSuccess(false);
                json.setMsg("支付宝web异步通知参数验证不通过");
                return json;
            }else {
                logger.debug("支付宝web异步通知参数验证>>OK");
            }
            String tradeStatus = params.get("trade_status");
            if("TRADE_SUCCESS".equals(tradeStatus)){          //支付成功
                logger.debug("支付成功");
                //分润
                JSONObject result = DistributeRoyaltyService.distributeRoyalty(partner,key,params.get("trade_no"),params.get("out_trade_no"),alipayAccount,royaltyNum);
                logger.debug("分润结果：" + result.toString());
                json.setMsg("分润结果：" + result.getString("msg"));
            }else if ("TRADE_FINISHED".equals(tradeStatus)){  //交易完成
                logger.debug("交易完成，不可再执行其他操作");
            }

            json.setSuccess(true);
            return json;
        }catch (Exception e){
            json.setSuccess(false);
            json.setMsg(e.getMessage());
            logger.error("支付宝web异步通知验证出错",e.getStackTrace().toString());
            e.printStackTrace();
        }
        return json;
    }

    /**
     * wap手机网站异步通知
     * @param partner       合作身份者ID，以2088开头由16位纯数字组成的字符串
     * @param key           商户的私钥
     * @param request
     * @return
     */
    public static ResultJSON wapNotifyUrl(HttpServletRequest request,String partner,String key){

        ResultJSON json = new ResultJSON();

        Map<String,String> params = RequestUtil.getRequestParams(request);
        logger.info("异步通知返回参数 params: {}", params);
        try {
            AlipayWapCreateDirectPayByUser_Notify_Response response= MapUtils.toObject(AlipayWapCreateDirectPayByUser_Notify_Response.class, params, true);
            json.setObj(response);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        try {
            //校验参数
            if (!AlipayNotify.verify(params, partner, key)){
                json.setSuccess(false);
                json.setMsg("支付宝wap异步通知参数验证不通过");
                return json;
            }else {
                logger.debug("支付宝wap异步通知参数验证>>OK");
            }
            String tradeStatus = params.get("TRADE_STATUS");
            if("TRADE_SUCCESS".equals(tradeStatus)){          //支付成功
                logger.debug("支付成功");
            }else if ("TRADE_FINISHED".equals(tradeStatus)){  //交易完成
                logger.debug("交易完成，不可再执行其他操作");
            }

            json.setSuccess(true);
            json.setMsg(tradeStatus);

            return json;
        }catch (Exception e){
            json.setSuccess(false);
            json.setMsg(e.getMessage());
            logger.error("支付宝wap异步通知验证出错",e.getStackTrace().toString());
            e.printStackTrace();
        }
        return json;
    }

    /**
     *  wap异步通知（带分润）
     * @param request
     * @param partner       合作身份者ID，以2088开头由16位纯数字组成的字符串
     * @param key           商户的私钥
     * @param alipayAccount     支付宝账号
     * @param royaltyNum        分润金额
     * @return
     */
    public static ResultJSON wapNotifyUrl(HttpServletRequest request,String partner,String key,String alipayAccount,Double royaltyNum){

        ResultJSON json = new ResultJSON();

        Map<String,String> params = RequestUtil.getRequestParams(request);

        logger.debug("异步wap通知返回参数 params: {}", params);
        try {
            AlipayWapCreateDirectPayByUser_Notify_Response response= MapUtils.toObject(AlipayWapCreateDirectPayByUser_Notify_Response.class, params, true);
            json.setObj(response);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        try {
            //校验参数
            if (!AlipayNotify.verify(params, partner, key)){
                json.setSuccess(false);
                json.setMsg("支付宝wap异步通知参数验证不通过");
                return json;
            }else {
                logger.debug("支付宝wap异步通知参数验证>>OK");
            }
            String tradeStatus = params.get("trade_status");
            if("TRADE_SUCCESS".equals(tradeStatus)){          //支付成功
                logger.debug("支付成功");
                //分润
                JSONObject result = DistributeRoyaltyService.distributeRoyalty(partner,key,params.get("trade_no"),params.get("out_trade_no"),alipayAccount,royaltyNum);
                logger.debug("分润结果：" + result.toString());
                json.setMsg("分润结果：" + result.getString("msg"));
            }else if ("TRADE_FINISHED".equals(tradeStatus)){  //交易完成
                logger.debug("交易完成，不可再执行其他操作");
            }

            json.setSuccess(true);
            return json;
        }catch (Exception e){
            json.setSuccess(false);
            json.setMsg(e.getMessage());
            logger.error("支付宝wap异步通知验证出错",e.getStackTrace().toString());
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 支付宝web前端跳转通知
     * @param request ]
     * @param partner       合作身份者ID，以2088开头由16位纯数字组成的字符串
     * @param key           商户的私钥
     */
    public static ResultJSON webReturnUrl(HttpServletRequest request,String partner,String key){

        ResultJSON json = new ResultJSON();
        //获取支付宝GET过来的信息
        Map<String, String> params = RequestUtil.getRequestParams(request);
        //Map<String, String> params = RequestUtil.getGetRequestParams(request);
        logger.info("web支付同步通知参数 params: {}", params);

        try {
            //移除值为null的元素
            MapUtils.removeNullValue(params);
            CreateDirectPayByUser_Return_Response response= MapUtils.toObject(CreateDirectPayByUser_Return_Response.class, params, true);
            json.setObj(response);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //校验参数
        if(!AlipayNotify.verify(params, partner, key)){
            json.setSuccess(false);
            json.setMsg("web同步通知参数验证不通过");

            logger.error("web同步通知参数验证不通过");
            return json;
        }else {
            logger.error("web同步通知参数验证>>OK");
        }

        String tradeStatus = params.get("trade_status");
        if("TRADE_SUCCESS".equals(tradeStatus)){          //支付成功
            logger.debug("支付成功");
        }else if ("TRADE_FINISHED".equals(tradeStatus)){  //交易完成
            logger.debug("交易完成，不可再执行其他操作");
        }

        json.setSuccess(true);
        json.setMsg(tradeStatus);

        return json;
    }

    /**
     * 支付宝wap前端跳转通知
     * @param request
     * @param partner       合作身份者ID，以2088开头由16位纯数字组成的字符串
     * @param key           商户的私钥
     */
    public static ResultJSON wapReturnUrl(HttpServletRequest request,String partner,String key){

        ResultJSON json = new ResultJSON();

        Map<String, String> params = RequestUtil.getRequestParams(request);

        try {
            //移除值为null的元素
            MapUtils.removeNullValue(params);
            AlipayWapCreateDirectPayByUser_Return_Response response= MapUtils.toObject(AlipayWapCreateDirectPayByUser_Return_Response.class, params, true);
            json.setObj(response);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //校验参数
        if(!AlipayNotify.verify(params, partner, key)){
            json.setSuccess(false);
            json.setMsg("同步通知参数验证不通过");

            logger.error("wap同步通知参数验证不通过");
            return json;
        }

        String tradeStatus = params.get("trade_status");
        if("TRADE_SUCCESS".equals(tradeStatus)){          //支付成功
            logger.debug("支付成功");
        }else if ("TRADE_FINISHED".equals(tradeStatus)){  //交易完成
            logger.debug("交易完成，不可再执行其他操作");
        }

        json.setSuccess(true);
        json.setMsg(tradeStatus);

        logger.info("wap支付同步通知参数 params: {}", params);

        return json;
    }

}
