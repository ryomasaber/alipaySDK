package api.com.jy.response;


/**
 * ALIPAY API:create_direct_pay_by_user
 * Created by Saber on 2016/7/1.
 */
public class CreateDirectPayByUser_Notify_Response extends CreateDirectPayByUser_Base_Response{

    private static final long serialVersionUID = 2745464581330765604L;

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
     * 退款状态
     */
    private String refundStatus;
    /**
     * 退款时间 格式为yyyy-MM-dd HH:mm:ss
     */
    private String gmtRefund;
    /**
     * 商品单价  如果请求时使用的是total_fee，那么price等于total_fee；如果请求时传了price，那么对应请求时的price参数，原样通知回来。
     */
    private String price;           //Number
    /**
     *  购买数量 如果请求时使用的是total_fee，那么quantity等于1；如果请求时有传quantity，那么对应请求时的quantity参数，原样通知回来。
     */
    private String quantity;        //Number
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
     *  是否扫码支付    回传给商户此标识为qrpay时，表示对应交易为扫码支付。
     目前只有qrpay一种回传值。
     非扫码支付方式下，目前不会返回该参数。
     */
    private String businessScene;
    /**
     * 支付渠道组合信息  该笔交易所使用的支付渠道
     */
    private String outChannelType;
    /**
     * 支付金额组合信息 该笔交易通过使用各支付渠道所支付的金额。 格式为：金额1+金额2... 如果有多个支付渠道，各渠道所支付金额用“|”隔开
     */
    private String outChannelAmount;


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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
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

    public String getBusinessScene() {
        return businessScene;
    }

    public void setBusinessScene(String businessScene) {
        this.businessScene = businessScene;
    }

    public String getOutChannelType() {
        return outChannelType;
    }

    public void setOutChannelType(String outChannelType) {
        this.outChannelType = outChannelType;
    }

    public String getOutChannelAmount() {
        return outChannelAmount;
    }

    public void setOutChannelAmount(String outChannelAmount) {
        this.outChannelAmount = outChannelAmount;
    }

    @Override
    public String toString() {
        return "CreateDirectPayByUser_Notify_Response{" +
                "gmtCreate='" + gmtCreate + '\'' +
                ", gmtPayment='" + gmtPayment + '\'' +
                ", gmtClose='" + gmtClose + '\'' +
                ", refundStatus='" + refundStatus + '\'' +
                ", gmtRefund='" + gmtRefund + '\'' +
                ", price='" + price + '\'' +
                ", quantity='" + quantity + '\'' +
                ", discount='" + discount + '\'' +
                ", isTotalFeeAdjust='" + isTotalFeeAdjust + '\'' +
                ", useCoupon='" + useCoupon + '\'' +
                ", businessScene='" + businessScene + '\'' +
                ", outChannelType='" + outChannelType + '\'' +
                ", outChannelAmount='" + outChannelAmount + '\'' +
                "} " + super.toString();
    }
}
