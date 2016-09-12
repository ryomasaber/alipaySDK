package api.com.jy.response;

/**
 * ALIPAY API:create_direct_pay_by_user
 * pc即时到账 通知
 * Created by Saber on 2016/7/1.
 */
public class CreateDirectPayByUser_Base_Response {

    private static final long serialVersionUID = 6622790910711358587L;

    /**
     * 签名方式 DSA、RSA、MD5三个值可选，必须大写。
     */
    private String signType;       //String(32)
    /**
     * 签名
     */
    private String sign;
    /**
     *  商户网站唯一订单号   对应商户网站的订单系统中的唯一订单号，非支付宝交易号。需保证在商户网站中的唯一性。是请求时对应的参数，原样返回。
     */
    private String outTradeNo;
    /**
     *  商品名称
     */
    private String subject;
    /**
     * 支付类型
     */
    private String paymentType;
    /**
     *  支付宝交易号  该交易在支付宝系统中的交易流水号。最长64位。
     */
    private String tradeNo;        //String(64)
    /**
     *  交易状态
     *          成功状态的值只有两个：
     TRADE_FINISHED（普通即时到账的交易成功状态）；
     TRADE_SUCCESS（开通了高级即时到账或机票分销产品后的交易成功状态）
     */
    private String tradeStatus;
    /**
     * 通知校验ID  支付宝通知校验ID，商户可以用这个流水号询问支付宝该条通知的合法性。
     */
    private String notifyId;
    /**
     * 通知时间 （支付宝时间）。格式为yyyy-MM-dd HH:mm:ss。
     */
    private String notifyTime;
    /**
     * 通知类型
     */
    private String notifyType;
    /**
     * 卖家支付宝账号  可以是Email或手机号码。
     */
    private String sellerEmail;        //String(100)
    /**
     * 买家支付宝账号  可以是Email或手机号码。
     */
    private String buyerEmail;         //String(100)
    /**
     *  卖家支付宝账户号
     */
    private String sellerId;           //String(30)
    /**
     *  买家支付宝账户号
     */
    private String buyerId;            //String(30)
    /**
     *  交易金额  请求时对应的参数，原样通知回来。
     */
    private String totalFee;       //Number
    /**
     *  对一笔交易的具体描述信息。请求参数原样返回。
     */
    private String body;            //String(400)
    /**
     *  公用回传参数  用于商户回传参数，该值不能包含“=”、“&”等特殊字符。如果用户请求时传递了该参数，则返回给商户时会回传该参数。
     */
    private String extraCommonParam;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
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

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    public String getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(String notifyTime) {
        this.notifyTime = notifyTime;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getExtraCommonParam() {
        return extraCommonParam;
    }

    public void setExtraCommonParam(String extraCommonParam) {
        this.extraCommonParam = extraCommonParam;
    }

    @Override
    public String toString() {
        return "CreateDirectPayByUser_Base_Response{" +
                "signType='" + signType + '\'' +
                ", sign='" + sign + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", subject='" + subject + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", tradeNo='" + tradeNo + '\'' +
                ", tradeStatus='" + tradeStatus + '\'' +
                ", notifyId='" + notifyId + '\'' +
                ", notifyTime='" + notifyTime + '\'' +
                ", notifyType='" + notifyType + '\'' +
                ", sellerEmail='" + sellerEmail + '\'' +
                ", buyerEmail='" + buyerEmail + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", buyerId='" + buyerId + '\'' +
                ", totalFee='" + totalFee + '\'' +
                ", body='" + body + '\'' +
                ", extraCommonParam='" + extraCommonParam + '\'' +
                '}';
    }
}
