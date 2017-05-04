package api.com.jy.alipay.service;

import api.com.jy.alipay.util.AlipaySubmit;
import api.com.jy.alipay.util.UUID16;
import api.com.jy.alipay.util.XmlConverUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 多级分润接口
 * Created by Saber on 2016/6/21.
 */
public class DistributeRoyaltyService {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(DistributeRoyaltyService.class.getCanonicalName());

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

        //分润号
        String outBillNo = UUID16.create().toString();
        return buildDistributeRoyalty(partner,key,tradeNo,outTradeNo,outBillNo,alipayAccount,royaltyNum);
    }

    /**
     *
     * @param partner       合作伙伴id
     * @param tradeNo       支付宝交易号
     * @param outTradeNo    商户网站唯一订单号（已此为准）
     * @param outBillNo     分润号(16位唯一值)
     * @param alipayAccount 分润账号
     * @param royaltyNum    分润金额
     * @return  result=0 正常  result=1 异常  result=2 分润出现错误
     * @throws IOException
     */
    public static JSONObject distributeRoyalty(String partner,String key,String tradeNo,String outTradeNo,String outBillNo,String alipayAccount,Double royaltyNum){

        return buildDistributeRoyalty(partner,key,tradeNo,outTradeNo,outBillNo,alipayAccount,royaltyNum);
    }

    /**
     *
     * @param partner       合作伙伴id
     * @param tradeNo       支付宝交易号
     * @param outTradeNo    商户网站唯一订单号（已此为准）
     * @param outBillNo     分润号(16位唯一值)
     * @param royaltyParameters	分润参数
     * @return  result=0 正常  result=1 异常  result=2 分润出现错误
     * @throws IOException
     */
    public static JSONObject distributeRoyalty(String partner, String key, String tradeNo, String outTradeNo, String outBillNo, Map<String, Double> royaltyParameters) {
        return buildDistributeRoyalty(partner, key ,tradeNo, outTradeNo, outBillNo, royaltyParameters);
    }

    /**
     * 构建分润请求
     * @param partner       合作伙伴id
     * @param tradeNo       支付宝交易号
     * @param outTradeNo    商户网站唯一订单号（已此为准）
     * @param outBillNo     分润号(16位唯一值)
     * @param alipayAccount 分润账号
     * @param royaltyNum    分润金额（需要考虑信用卡）
     * @return  result=0 正常  result=1 异常  result=2 分润出现错误
     * @throws IOException
     */
    private static JSONObject buildDistributeRoyalty(String partner,String key,String tradeNo,String outTradeNo,String outBillNo,String alipayAccount,Double royaltyNum){

        JSONObject json = new JSONObject();

        //检测条件
        if(StringUtils.isEmpty(tradeNo) || StringUtils.isEmpty(outTradeNo)){
            json.put("result",1);
            json.put("data",null);
            json.put("msg","参数异常：trade_no和out_trade_no 至少填写一项");
            return json;
        }
        if(StringUtils.isEmpty(outBillNo)){
            json.put("result",1);
            json.put("data",null);
            json.put("msg","参数异常：分润号（outBillNo）不能为空");
            return json;
        }

        //================业务参数====================
        //分润号
        String out_bill_no = outBillNo;
        //分润参数明细
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
                logger.error("商户订单号：{}，分润错误：{}" ,outTradeNo, resultFromXML.get("error").toString());
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
     * 构建分润请求
     * @param partner       合作伙伴id
     * @param tradeNo       支付宝交易号
     * @param outTradeNo    商户网站唯一订单号（已此为准）
     * @param outBillNo     分润号(16位唯一值)
     * @param royaltyParameters 分润参数
     * @return  result=0 正常  result=1 异常  result=2 分润出现错误
     * @throws IOException
     */
    private static JSONObject buildDistributeRoyalty(String partner, String key, String tradeNo, String outTradeNo, String outBillNo, Map<String, Double> royaltyParameters){

        JSONObject json = new JSONObject();

        //检测条件
        if(StringUtils.isEmpty(tradeNo) || StringUtils.isEmpty(outTradeNo)){
            json.put("result",1);
            json.put("data",null);
            json.put("msg","参数异常：trade_no和out_trade_no 至少填写一项");
            return json;
        }
        if(StringUtils.isEmpty(outBillNo)){
            json.put("result",1);
            json.put("data",null);
            json.put("msg","参数异常：分润号（outBillNo）不能为空");
            return json;
        }

        //================业务参数====================
        //分润号
        String out_bill_no = outBillNo;
        //分润参数明细

        StringBuffer royalty_parameters = new StringBuffer();
        for (String alipayAccount : royaltyParameters.keySet()) {
            Double royaltyNum = royaltyParameters.get(alipayAccount);
            royalty_parameters.append(alipayAccount).append("^").append(royaltyNum).append("^充值分润|");
        }

        String royalty_parameters_str = royalty_parameters.toString().substring(0, royalty_parameters.length() - 1);

        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", "distribute_royalty");
        sParaTemp.put("partner", partner);
        sParaTemp.put("_input_charset", "utf-8");
        sParaTemp.put("royalty_type", "10");

        //业务参数
        sParaTemp.put("out_bill_no",out_bill_no);
        sParaTemp.put("trade_no",tradeNo);
        sParaTemp.put("out_trade_no",outTradeNo);
        sParaTemp.put("royalty_parameters",royalty_parameters_str);

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
                logger.error("商户订单号：{}，分润错误：{}" ,outTradeNo, resultFromXML.get("error").toString());
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
