package api.com.jy.alipay.service;

import api.com.jy.alipay.util.UtilDate;
import api.com.jy.alipay.util.XmlConverUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import api.com.jy.alipay.util.AlipaySubmit;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 多级分润接口
 * Created by Saber on 2016/6/21.
 */
public class DistributeRoyaltyService {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(DistributeRoyaltyService.class.getCanonicalName());

    private static String input_charset="utf-8";
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
     *
     * @param partner       合作伙伴id
     * @param tradeNo       支付宝交易号
     * @param outTradeNo    商户网站唯一订单号（已此为准）
     * @param alipayAccount 分润账号
     * @param royaltyNum    分润金额
     * @return  result=0 正常  result=1 异常  result=2 分润出现错误
     * @throws IOException
     */
    public static JSONObject distributeRoyalty(String partner,String key,String tradeNo,String outTradeNo,String alipayAccount,Double royaltyNum){

        JSONObject json = new JSONObject();

        //检测条件
        if(StringUtils.isEmpty(tradeNo) || StringUtils.isEmpty(outTradeNo)){
            json.put("result",1);
            json.put("data",null);
            json.put("msg","参数异常：trade_no和out_trade_no 至少填写一项");
            return json;
        }

        //================业务参数====================
        //分润号
        String out_bill_no = UtilDate.getOrderNum();
        //分润参数明细 //todo 需要考虑信用卡
        String royalty_parameters = alipayAccount+"^"+royaltyNum+"^"+"充值分润";

        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", "distribute_royalty");
        sParaTemp.put("partner", partner);
        sParaTemp.put("_input_charset", "utf-8");
        sParaTemp.put("royalty_type", "10");

        //业务参数
        sParaTemp.put("out_bill_no",out_bill_no);
        sParaTemp.put("trade_no",tradeNo);
        sParaTemp.put("out_trade_no",outTradeNo);
        sParaTemp.put("royalty_parameters",royalty_parameters);

        try {
            //建立请求
            String sHtmlText = AlipaySubmit.buildRequest("", "", sParaTemp, key);
            //获得返回值
            JSONObject resultFromXML  = (JSONObject) JSON.parse(XmlConverUtil.xml2Json(sHtmlText));
            String is_success = resultFromXML.get("is_success").toString();
            if(is_success.equals("T") || is_success.equals("t")){
                json.put("result",0);
                json.put("data",resultFromXML);
                json.put("msg","ok");
                return json;
            }else {
                json.put("result",2);
                json.put("data",null);
                json.put("msg", resultFromXML.get("error").toString());
                logger.error("分润错误error：" + resultFromXML.get("error").toString());
                return json;
            }
        } catch (Exception e) {
            json.put("result",1);
            json.put("data",null);
            json.put("msg",e.getMessage());
            e.printStackTrace();
            return json;
        }
    }
    /**
     *
     * @param tradeNo       支付宝交易号
     * @param outTradeNo    商户网站唯一订单号（已此为准）
     * @param alipayAccount 分润账号
     * @param royaltyNum    分润金额
     * @return  result=0 正常  result=1 异常  result=2 分润出现错误
     * @throws IOException
     */
    public JSONObject distributeRoyalty(String tradeNo,String outTradeNo,String alipayAccount,Double royaltyNum){

        JSONObject json = new JSONObject();

        //检测条件
        if(StringUtils.isEmpty(tradeNo) || StringUtils.isEmpty(outTradeNo)){
            json.put("result",1);
            json.put("data",null);
            json.put("msg","参数异常：trade_no和out_trade_no 至少填写一项");
            return json;
        }

        //================业务参数====================
        //分润号
        String out_bill_no = UtilDate.getOrderNum();
        //分润参数明细 //todo 需要考虑信用卡
        String royalty_parameters = alipayAccount+"^"+royaltyNum+"^"+"充值分润";

        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", "distribute_royalty");
        sParaTemp.put("partner", partner);
        sParaTemp.put("_input_charset", "utf-8");
        sParaTemp.put("royalty_type", "10");

        //业务参数
        sParaTemp.put("out_bill_no",out_bill_no);
        sParaTemp.put("trade_no",tradeNo);
        sParaTemp.put("out_trade_no",outTradeNo);
        sParaTemp.put("royalty_parameters",royalty_parameters);

        try {
            //建立请求
            String sHtmlText = AlipaySubmit.buildRequest("", "", sParaTemp, key);
            //获得返回值
            JSONObject resultFromXML  = (JSONObject) JSON.parse(XmlConverUtil.xml2Json(sHtmlText));
            String is_success = resultFromXML.get("is_success").toString();
            if(is_success.equals("T") || is_success.equals("t")){
                json.put("result",0);
                json.put("data",resultFromXML);
                json.put("msg","ok");
                return json;
            }else {
                json.put("result",2);
                json.put("data",null);
                json.put("msg", resultFromXML.get("error").toString());
                logger.error("分润错误error：" + resultFromXML.get("error").toString());
                return json;
            }
        } catch (Exception e) {
            json.put("result",1);
            json.put("data",null);
            json.put("msg",e.getMessage());
            e.printStackTrace();
            return json;
        }
    }
}
