package api.com.jy.request;

/**
 * APP手机移动支付
 * Created by Saber on 2016/8/31.
 */
public class AppPayRequest extends AlipayRquest{

    private static final long serialVersionUID = -3008579444611483878L;

    public AppPayRequest(String partner, Double totalFee, String outTradeNo, String subject, String body) {
        super(partner, totalFee, outTradeNo, subject, body);
        this.setService("mobile.securitypay.pay");
    }

    /**
     * 客户端号     标识客户端。
     */
    private String app_id;
    /**
     * 客户端来源   标识客户端来源。参数值内容约定如下：appenv=”system=客户端平台名^version=业务系统版本”
     */
    private String appenv;
    /**
     * 商品类型
     *          具体区分本地交易的商品类型。
     1：实物交易；
     0：虚拟交易。
     默认为1（实物交易）。
     */
    private String goods_type;
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
     *  是否发起实名校验 T：发起实名校验；F：不发起实名校验。
     */
    private String rn_check;
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
     *  商户优惠活动参数 商户与支付宝约定的营销参数，为Key:Value键值对，如需使用，请联系支付宝技术人员。 String(128)
     */
    private String promo_params;


    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getAppenv() {
        return appenv;
    }

    public void setAppenv(String appenv) {
        this.appenv = appenv;
    }

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }

    public String getHb_fq_param() {
        return hb_fq_param;
    }

    public void setHb_fq_param(String hb_fq_param) {
        this.hb_fq_param = hb_fq_param;
    }

    public String getRn_check() {
        return rn_check;
    }

    public void setRn_check(String rn_check) {
        this.rn_check = rn_check;
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

    public String getPromo_params() {
        return promo_params;
    }

    public void setPromo_params(String promo_params) {
        this.promo_params = promo_params;
    }

    @Override
    public String toString() {
        return "AppPayRequest{" +
                "app_id='" + app_id + '\'' +
                ", appenv='" + appenv + '\'' +
                ", goods_type='" + goods_type + '\'' +
                ", hb_fq_param='" + hb_fq_param + '\'' +
                ", rn_check='" + rn_check + '\'' +
                ", it_b_pay='" + it_b_pay + '\'' +
                ", extern_token='" + extern_token + '\'' +
                ", promo_params='" + promo_params + '\'' +
                "} " + super.toString();
    }
}
