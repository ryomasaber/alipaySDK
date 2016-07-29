package api.com.jy.request;

import java.io.Serializable;

/**
 * PC即时到账和wap手机网站公共请求体
 * Created by Saber on 2016/7/4.
 */
public abstract class AlipayRquest implements Serializable{
    private static final long serialVersionUID = -5940332131464014570L;

    /**
     * 接口名称
     */
    private String service;
    /**
     * 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
     */
    private String partner;
    /**
     *  异步通知地址
     */
    private String notifyUrl;
    /**
     * 同步通知地址
      */
    private String returnUrl;
    /**
     * 商户网站唯一订单号 支付宝合作商户网站唯一订单号。 不可空
     */
    private String outTradeNo;    //String(64)
    /**
     * 商品名称 商品的标题/交易标题/订单标题/订单关键字等。该参数最长为128个汉字。
     */
    private String subject;         //String(256)
    /**
     * 交易金额.不可空
     */
    private Double totalFee;       //Number
    /**
     * 卖家支付宝用户号，以2088开头的纯16位数字 不可空
     */
    private String sellerId;       //String(16)
    /**
     *  商品描述 该笔订单的备注、描述、明细等。对应请求时的body参数，原样通知回来。
     */
    private String body;           //String(400)
    public String _input_charset="utf-8";
    public String payment_type="1";

    public AlipayRquest(String partner, Double totalFee, String outTradeNo, String subject) {
        this.partner = partner;
        this.totalFee = totalFee;
        this.outTradeNo = outTradeNo;
        this.subject = subject;
    }

    public AlipayRquest(String partner, Double totalFee, String outTradeNo, String subject ,String body) {
        this.partner = partner;
        this.totalFee = totalFee;
        this.outTradeNo = outTradeNo;
        this.subject = subject;
        this.body = body;
    }

    public String getService() {
        return service;
    }
    public void setService(String service) {
        this.service = service;
    }

    public String getPartner() {
        return partner;
    }
    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }
    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Double getTotalFee() {
        return totalFee;
    }
    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    public String getSellerId() {
        return sellerId;
    }
    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
