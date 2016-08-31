package api.com.jy.request;

/**
 * wap手机网站请求体
 * Created by Saber on 2016/7/4.
 */
public class WapPayRequest extends AlipayRquest{

    private static final long serialVersionUID = -7618759133075942475L;

    public WapPayRequest(String partner, Double totalFee, String outTradeNo, String subject) {
        super(partner, totalFee, outTradeNo, subject);
        this.setService("alipay.wap.create.direct.pay.by.user");
    }

    public WapPayRequest(String partner, Double totalFee, String outTradeNo, String subject ,String body) {
        super(partner, totalFee, outTradeNo, subject,body);
        this.setService("alipay.wap.create.direct.pay.by.user");
    }

    public WapPayRequest(String partner, Double totalFee, String outTradeNo, String subject ,String body,String signType) {
        super(partner, totalFee, outTradeNo, subject, body, signType);
        this.setService("alipay.wap.create.direct.pay.by.user");
    }

    /**
     *  商品展示网址 收银台页面上，商品展示的超链接。
     */
    private String show_url;        //String(400)
    /**
     * 超时时间
     *          设置未付款交易的超时时间，一旦超时，该笔交易就会自动被关闭。
     取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。
     该参数数值不接受小数点，如1.5h，可转换为90m。
     */
    private String it_b_pay="2h";
    /**
     * 钱包token
     *
     接入极简版wap收银台时支持。
     当商户请求是来自支付宝钱包，在支付宝钱包登录后，有生成登录信息token时，使用该参数传入token将可以实现信任登录收银台，不需要再次登录。
     注意：
     登录后用户还是有入口可以切换账户，不能使用该参数锁定用户。
     */
    private String extern_token;
    /**
     *  航旅订单其它费用
     */
    private String otherfee;    //Number
    /**
     * 航旅订单金额 航旅订单金额描述，由四项或两项构成，各项之间由“|”分隔，每项包含金额与描述，金额与描述间用“^”分隔，票面价之外的价格之和必须与otherfee相等。
     */
    private String airticket;   //String(1,64)
    /**
     *  是否发起实名校验 T：发起实名校验；F：不发起实名校验。
     */
    private String rn_check;
    /**
     * 买家证件号码 （需要与支付宝实名认证时所填写的证件号码一致）。
     说明：
     当scene=ZJCZTJF的情况下，才会校验buyer_cert_no字段。
     */
    private String buyer_cert_no;
    /**
     * 买家真实姓名   当scene=ZJCZTJF的情况下，才会校验buyer_real_name字段。
     */
    private String buyer_real_name;
    /**
     * 收单场景。如需使用该字段，需向支付宝申请开通，否则传入无效。
     */
    private String scene;
    /**
     * 花呗分期参数
     *          Json格式。
     hb_fq_num：花呗分期数，比如分3期支付；
     hb_fq_seller_percent：卖家承担收费比例，比如100代表卖家承担100%。
     两个参数必须一起传入。

     具体花呗分期期数和卖家承担收费比例可传入的数值请咨询支付宝。
     */
    private String hb_fq_param;
    /**
     * 商品类型 1表示实物类商品 0表示虚拟类商品 如果不传，默认为实物类商品。
     */
    private String goods_type;
    /**
     * 是否使用支付宝客户端支付 app_pay=Y：尝试唤起支付宝客户端进行支付，若用户未安装支付宝，则继续使用wap收银台进行支付。商户若为APP，则需在APP的webview中增加alipays协议处理逻辑。
     */
    private String app_pay = "Y";



    public String getShow_url() {
        return show_url;
    }

    public void setShow_url(String show_url) {
        this.show_url = show_url;
    }

    public String getIt_b_pay() {
        return it_b_pay;
    }

    public void setIt_b_pay(String it_b_pay) {
        this.it_b_pay = it_b_pay;
    }

    public String getExtern_token() {
        return extern_token;
    }

    public void setExtern_token(String extern_token) {
        this.extern_token = extern_token;
    }

    public String getOtherfee() {
        return otherfee;
    }

    public void setOtherfee(String otherfee) {
        this.otherfee = otherfee;
    }

    public String getAirticket() {
        return airticket;
    }

    public void setAirticket(String airticket) {
        this.airticket = airticket;
    }

    public String getRn_check() {
        return rn_check;
    }

    public void setRn_check(String rn_check) {
        this.rn_check = rn_check;
    }

    public String getBuyer_cert_no() {
        return buyer_cert_no;
    }

    public void setBuyer_cert_no(String buyer_cert_no) {
        this.buyer_cert_no = buyer_cert_no;
    }

    public String getBuyer_real_name() {
        return buyer_real_name;
    }

    public void setBuyer_real_name(String buyer_real_name) {
        this.buyer_real_name = buyer_real_name;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getHb_fq_param() {
        return hb_fq_param;
    }

    public void setHb_fq_param(String hb_fq_param) {
        this.hb_fq_param = hb_fq_param;
    }

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }

    public String getApp_pay() {
        return app_pay;
    }

    public void setApp_pay(String app_pay) {
        this.app_pay = app_pay;
    }

    @Override
    public String toString() {
        return "WapPayRequest{" +
                "show_url='" + show_url + '\'' +
                ", it_b_pay='" + it_b_pay + '\'' +
                ", extern_token='" + extern_token + '\'' +
                ", otherfee='" + otherfee + '\'' +
                ", airticket='" + airticket + '\'' +
                ", rn_check='" + rn_check + '\'' +
                ", buyer_cert_no='" + buyer_cert_no + '\'' +
                ", buyer_real_name='" + buyer_real_name + '\'' +
                ", scene='" + scene + '\'' +
                ", hb_fq_param='" + hb_fq_param + '\'' +
                ", goods_type='" + goods_type + '\'' +
                ", app_pay='" + app_pay + '\'' +
                "} " + super.toString();
    }
}
