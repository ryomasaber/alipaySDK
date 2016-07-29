package api.com.jy.request;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * ALIPAY API:create_direct_pay_by_user
 * Created by Saber on 2016/7/1.
 */
public class CreateDirectPayByUserRequset{

    /**
     * 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
     */
    private String partner;
    /**
     * 商户网站唯一订单号 支付宝合作商户网站唯一订单号。 不可空
     */
    private String outTradeNo;    //String(64)
    /**
     * 异步通知地址
     */
    private String notifyUrl;
    /**
     *  同步通知地址
     */
    private String returnUrl;
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
     * 买家支付宝用户号，以2088开头的纯16位数字
     */
    private String buyerId;
    /**
     * 卖家支付宝账号
     */
    private String buyerEmail;     //String(100)
    /**
     * 商品单价  单位为：RMB Yuan。取值范围为[0.01，100000000.00]，精确到小数点后两位。此参数为单价
     规则：price、quantity能代替total_fee。即存在total_fee，就不能存在price和quantity；存在price、quantity，就不能存在total_fee。
     */
    private Double price;           //Number
    /**
     * 购买数量  price、quantity能代替total_fee。即存在total_fee，就不能存在price和quantity；存在price、quantity，就不能存在total_fee。
     */
    private Integer quantity;        //Number
    /**
     * 商品描述 对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
     */
    private String body;            //String(1000)  可空
    /**
     *  商品展示网址 收银台页面上，商品展示的超链接。
     */
    private String showUrl;        //String(400)
    /**
     *  默认支付方式  取值范围：creditPay（信用支付） directPay（余额支付） 如果不设置，默认识别为余额支付。
     */
    private String paymethod;
    /**
     *  支付渠道 用于控制收银台支付渠道显示，该值的取值范围请参见支付渠道。可支持多种支付渠道显示，以“^”分隔。
     */
    private String enablePaymethod;
    /**
     *  防钓鱼时间戳 通过时间戳查询接口获取的加密支付宝系统时间戳。如果已申请开通防钓鱼时间戳验证，则此字段必填。
     */
    private String antiPhishingKey;
    /**
     * 客户端IP  用户在创建交易时，该用户当前所使用机器的IP。如果商户申请后台开通防钓鱼IP地址检查选项，此字段必填，校验用。
     */
    private String exterInvokeIp;
    /**
     * 公用回传参数 如果用户请求时传递了该参数，则返回给商户时会回传该参数。
     */
    private String extraCommonParam;
    /**
     * 超时时间
     *          设置未付款交易的超时时间，一旦超时，该笔交易就会自动被关闭。
     取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。
     该参数数值不接受小数点，如1.5h，可转换为90m。
     */
    private String itBPay;
    /**
     * 快捷登录授权令牌  如果开通了快捷登录产品，则需要填写；如果没有开通，则为空。
     */
    private String token;
    /**
     * 扫码支付方式
     */
    private String qrPayMode;
    /**
     * qrcode_width 商户自定二维码宽度 商户自定义的二维码宽度。当qr_pay_mode=4时，该参数生效。
     */
    private String qrcodeWidth;
    /**
     * 是否需要买家实名认证  T表示需要买家实名认证；不传或者传其它值表示不需要买家实名认证。
     */
    private String needBuyerRealnamed;
    /**
     * 商户优惠活动参数 商户与支付宝约定的营销透传参数。
     */
    private String promoParam;
    /**
     * 花呗分期参数
     */
    private String hbFqParam;
    /**
     * 商品类型 1表示实物类商品 0表示虚拟类商品 如果不传，默认为实物类商品。
     */
    private Integer goodsType;
    /**
     * 分润参数 新文档里没写
     */
    private String royaltyParameters;
    /**
     * 分润方式 目前只能填 10
     */
    private Integer royaltyType=10;


    public String getPartner() {
        return partner;
    }
    public void setPartner(String partner) {
        this.partner = partner;
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

    public String getBuyerId() {
        return buyerId;
    }
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }
    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public String getShowUrl() {
        return showUrl;
    }
    public void setShowUrl(String showUrl) {
        this.showUrl = showUrl;
    }

    public String getPaymethod() {
        return paymethod;
    }
    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }

    public String getEnablePaymethod() {
        return enablePaymethod;
    }
    public void setEnablePaymethod(String enablePaymethod) {
        this.enablePaymethod = enablePaymethod;
    }

    public String getAntiPhishingKey() {
        return antiPhishingKey;
    }
    public void setAntiPhishingKey(String antiPhishingKey) {
        this.antiPhishingKey = antiPhishingKey;
    }

    public String getExterInvokeIp() {
        return exterInvokeIp;
    }
    public void setExterInvokeIp(String exterInvokeIp) {
        this.exterInvokeIp = exterInvokeIp;
    }

    public String getExtraCommonParam() {
        return extraCommonParam;
    }
    public void setExtraCommonParam(String extraCommonParam) {
        this.extraCommonParam = extraCommonParam;
    }

    public String getItBPay() {
        return itBPay;
    }
    public void setItBPay(String itBPay) {
        this.itBPay = itBPay;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public String getQrPayMode() {
        return qrPayMode;
    }
    public void setQrPayMode(String qrPayMode) {
        this.qrPayMode = qrPayMode;
    }


    public String getQrcodeWidth() {
        return qrcodeWidth;
    }
    public void setQrcodeWidth(String qrcodeWidth) {
        this.qrcodeWidth = qrcodeWidth;
    }

    public String getNeedBuyerRealnamed() {
        return needBuyerRealnamed;
    }
    public void setNeedBuyerRealnamed(String needBuyerRealnamed) {
        this.needBuyerRealnamed = needBuyerRealnamed;
    }

    public String getPromoParam() {
        return promoParam;
    }
    public void setPromoParam(String promoParam) {
        this.promoParam = promoParam;
    }

    public String getHbFqParam() {
        return hbFqParam;
    }
    public void setHbFqParam(String hbFqParam) {
        this.hbFqParam = hbFqParam;
    }

    public Integer getGoodsType() {
        return goodsType;
    }
    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public String getRoyaltyParameters() {
        return royaltyParameters;
    }
    public void setRoyaltyParameters(String royaltyParameters) {
        this.royaltyParameters = royaltyParameters;
    }

    //-------------------------------------


    public String getApiMethodName() {
        return "create_direct_pay_by_user";
    }

    public Map<String, String> getTextParams() {
        Map<String,String> txtParams = new HashMap<>();
        txtParams.put("service","create_direct_pay_by_user");
        txtParams.put("partner",this.partner);
        txtParams.put("_input_charset","utf-8");
        txtParams.put("notify_url",notifyUrl);
        txtParams.put("return_url",returnUrl);
        txtParams.put("out_trade_no", this.outTradeNo);
        txtParams.put("subject", this.subject);
        txtParams.put("payment_type", "1");
        txtParams.put("total_fee", this.totalFee.toString());
        txtParams.put("seller_id", this.sellerId);
        txtParams.put("seller_email", this.partner);
        txtParams.put("buyer_id", this.buyerId);
        txtParams.put("buyer_email", this.buyerEmail);
        txtParams.put("price", this.price.toString());
        txtParams.put("quantity", this.quantity.toString());
        txtParams.put("body", this.body);
        txtParams.put("show_url", this.showUrl);
        txtParams.put("paymethod", this.paymethod);
        txtParams.put("enable_paymethod", this.enablePaymethod);
        txtParams.put("anti_phishing_key", this.antiPhishingKey);
        txtParams.put("exter_invoke_ip", this.antiPhishingKey);
        txtParams.put("extra_common_param", this.extraCommonParam);
        txtParams.put("it_b_pay", this.itBPay);
        txtParams.put("token", this.token);
        txtParams.put("qr_pay_mode", this.qrPayMode);
        txtParams.put("qrcode_width", this.qrcodeWidth);
        txtParams.put("need_buyer_realnamed", this.needBuyerRealnamed);
        txtParams.put("promo_param", this.promoParam);
        txtParams.put("hb_fq_param", this.hbFqParam);
        txtParams.put("goods_type", this.goodsType.toString());
        //新版文档里未写 旧版可用
        txtParams.put("royalty_parameters", this.royaltyParameters);
        txtParams.put("royalty_type", this.royaltyType.toString());

        Iterator<Map.Entry<String, String>> it = txtParams.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, String> entry=it.next();
            String value=entry.getValue();
            if(StringUtils.isEmpty(value)){
                it.remove();        //移除空值
            }
        }
        return txtParams;
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
}
