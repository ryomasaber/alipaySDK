package api.com.jy.response;

import java.io.Serializable;

/**
 * wap支付异步响应体
 * Created by Saber on 2016/7/4.
 */
public class AlipayWapCreateDirectPayByUser_Notify_Response implements Serializable{
    private static final long serialVersionUID = 4704710391998448973L;

    //------------------------------   基本参数       ----------------------------------//
    /**
     *  通知时间    通知时间（支付宝时间）。格式为yyyy-MM-dd HH:mm:ss。
     */
    private String notifyTime;
    /**
     *  通知类型
     */
    private String notifyType;
    /**
     *  通知校验ID  支付宝通知校验ID，商户可以用这个流水号询问支付宝该条通知的合法性。
     */
    private String notifyId;
    /**
     *  签名方式 DSA、RSA、MD5三个值可选，必须大写。
     */
    private String signType;
    /**
     *  签名
     */
    private String sign;                //String(32)

    //------------------------------   业务参数       ----------------------------------//
    /**
     *  商户网站唯一订单号   对应商户网站的订单系统中的唯一订单号，非支付宝交易号。
     */
    private String outTradeNo;              //String(64)
    /**
     *  商品的标题/交易标题/订单标题/订单关键字等。
     */
    private String subject;                 //String(256)
    /**
     *  对应请求时的payment_type参数，原样返回。
     */
    private String paymentType;
    /**
     *  支付宝交易号  该交易在支付宝系统中的交易流水号。最长64位。
     */
    private String tradeNo;                 //String(64)
    /**
     *  交易状态    成功状态的值只有两个：
     TRADE_FINISHED（普通即时到账的交易成功状态）
     TRADE_SUCCESS（开通了高级即时到账或机票分销产品后的交易成功状态）
     */
    private String tradeStatus;
    /**
     *  交易创建时间   格式为yyyy-MM-dd HH:mm:ss
     */
    private String gmtCreate;
    /**
     *  交易付款时间   格式为yyyy-MM-dd HH:mm:ss
     */
    private String gmtPayment;
    /**
     *  交易关闭时间   格式为yyyy-MM-dd HH:mm:ss
     */
    private String gmtClose;
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
     * 商品单价  如果请求时使用的是total_fee，那么price等于total_fee；如果请求时传了price，那么对应请求时的price参数，原样通知回来。
     */
    private String price;           //Number
    /**
     *  交易金额  请求时对应的参数，原样通知回来。
     */
    private String totalFee;       //Number
    /**
     *  购买数量 如果请求时使用的是total_fee，那么quantity等于1；如果请求时有传quantity，那么对应请求时的quantity参数，原样通知回来。
     */
    private String quantity;        //Number
    /**
     *  对一笔交易的具体描述信息。请求参数原样返回。
     */
    private String body;            //String(400)
    /**
     * 折扣   支付宝系统会把discount的值加到交易金额上，如果需要折扣，本参数为负数。
     */
    private String discount;        //Number
    /**
     * 是否调整总价   该交易是否调整过价格。 ( Y/N)
     */
    private String isTotalFeeAdjust;
    /**
     * 是否使用红包买家  是否在交易过程中使用了红包。(Y/N)
     */
    private String useCoupon;
    /**
     * 退款状态
     */
    private String refundStatus;
    /**
     * 退款时间 格式为yyyy-MM-dd HH:mm:ss
     */
    private String gmtRefund;

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

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
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

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtPayment() {
        return gmtPayment;
    }

    public void setGmtPayment(String gmtPayment) {
        this.gmtPayment = gmtPayment;
    }

    public String getGmtClose() {
        return gmtClose;
    }

    public void setGmtClose(String gmtClose) {
        this.gmtClose = gmtClose;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getIsTotalFeeAdjust() {
        return isTotalFeeAdjust;
    }

    public void setIsTotalFeeAdjust(String isTotalFeeAdjust) {
        this.isTotalFeeAdjust = isTotalFeeAdjust;
    }

    public String getUseCoupon() {
        return useCoupon;
    }

    public void setUseCoupon(String useCoupon) {
        this.useCoupon = useCoupon;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getGmtRefund() {
        return gmtRefund;
    }

    public void setGmtRefund(String gmtRefund) {
        this.gmtRefund = gmtRefund;
    }

    @Override
    public String toString() {
        return "AlipayWapCreateDirectPayByUser_Notify_Response{" +
                "notifyTime='" + notifyTime + '\'' +
                ", notifyType='" + notifyType + '\'' +
                ", notifyId='" + notifyId + '\'' +
                ", signType='" + signType + '\'' +
                ", sign='" + sign + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", subject='" + subject + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", tradeNo='" + tradeNo + '\'' +
                ", tradeStatus='" + tradeStatus + '\'' +
                ", gmtCreate='" + gmtCreate + '\'' +
                ", gmtPayment='" + gmtPayment + '\'' +
                ", gmtClose='" + gmtClose + '\'' +
                ", sellerEmail='" + sellerEmail + '\'' +
                ", buyerEmail='" + buyerEmail + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", buyerId='" + buyerId + '\'' +
                ", price='" + price + '\'' +
                ", totalFee='" + totalFee + '\'' +
                ", quantity='" + quantity + '\'' +
                ", body='" + body + '\'' +
                ", discount='" + discount + '\'' +
                ", isTotalFeeAdjust='" + isTotalFeeAdjust + '\'' +
                ", useCoupon='" + useCoupon + '\'' +
                ", refundStatus='" + refundStatus + '\'' +
                ", gmtRefund='" + gmtRefund + '\'' +
                '}';
    }
}
