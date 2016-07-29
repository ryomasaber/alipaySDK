package api.com.jy.response;

import java.io.Serializable;

/**
 * 手机网站支付响应体
 * Created by Saber on 2016/7/4.
 */
public class AlipayWapCreateDirectPayByUser_Return_Response implements Serializable{
    private static final long serialVersionUID = -1913879460186980484L;

    //------------------------------   基本参数       ----------------------------------//
    /**
     *  成功标识 表示接口调用是否成功，并不表明业务处理结果。( T/F )
     */
    private String isSuccess;
    /**
     *  签名方式 DSA、RSA、MD5三个值可选，必须大写。
     */
    private String signType;
    /**
     *  签名
     */
    private String sign;                //String(32)
    /**
     *  接口名称
     */
    private String service;
    /**
     *  通知校验ID  支付宝通知校验ID，商户可以用这个流水号询问支付宝该条通知的合法性。
     */
    private String notifyId;
    /**
     *  通知时间    通知时间（支付宝时间）。格式为yyyy-MM-dd HH:mm:ss。
     */
    private String notify_time;
    /**
     *  通知类型
     */
    private String notify_type;
    //------------------------------   业务参数参数       ----------------------------------//
    /**
     *  商户网站唯一订单号   对应商户网站的订单系统中的唯一订单号，非支付宝交易号。
     */
    private String outTradeNo;              //String(64)
    /**
     *  支付宝交易号  该交易在支付宝系统中的交易流水号。最长64位。
     */
    private String tradeNo;                 //String(64)
    /**
     *  商品的标题/交易标题/订单标题/订单关键字等。
     */
    private String subject;                 //String(256)
    /**
     *  对应请求时的payment_type参数，原样返回。
     */
    private String paymentType;
    /**
     *  交易状态    成功状态的值只有两个：
                        TRADE_FINISHED（普通即时到账的交易成功状态）
                        TRADE_SUCCESS（开通了高级即时到账或机票分销产品后的交易成功状态）
     */
    private String tradeStatus;
    /**
     *  卖家支付宝账户号
     */
    private String sellerid;            //String(30)
    /**
     *  交易金额    该笔订单的资金总额，单位为RMB-Yuan。取值范围为[0.01，100000000.00]，精确到小数点后两位。
     */
    private String totalfee;            //Number
    /**
     *  商品描述    对一笔交易的具体描述信息。请求参数原样返回。
     */
    private String body;                //String(400)


    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
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

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    public String getNotify_time() {
        return notify_time;
    }

    public void setNotify_time(String notify_time) {
        this.notify_time = notify_time;
    }

    public String getNotify_type() {
        return notify_type;
    }

    public void setNotify_type(String notify_type) {
        this.notify_type = notify_type;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
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

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    public String getTotalfee() {
        return totalfee;
    }

    public void setTotalfee(String totalfee) {
        this.totalfee = totalfee;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "AlipayWapCreateDirectPayByUser_Return_Response{" +
                "isSuccess='" + isSuccess + '\'' +
                ", signType='" + signType + '\'' +
                ", sign='" + sign + '\'' +
                ", service='" + service + '\'' +
                ", notifyId='" + notifyId + '\'' +
                ", notify_time='" + notify_time + '\'' +
                ", notify_type='" + notify_type + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", tradeNo='" + tradeNo + '\'' +
                ", subject='" + subject + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", tradeStatus='" + tradeStatus + '\'' +
                ", sellerid='" + sellerid + '\'' +
                ", totalfee='" + totalfee + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
