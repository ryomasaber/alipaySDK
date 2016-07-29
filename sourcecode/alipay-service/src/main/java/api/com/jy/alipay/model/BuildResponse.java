package api.com.jy.alipay.model;

/**
 * 构建支付请求返回结果
 * Created by Saber on 2016/7/7.
 */
public class BuildResponse {

    /**
     * 支付请求字符串
     */
    private String buildStr;
    /**
     * 订单号
     */
    private String outTradeNo;

    public String getBuildStr() {
        return buildStr;
    }
    public void setBuildStr(String buildStr) {
        this.buildStr = buildStr;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    @Override
    public String toString() {
        return "BuildResponse{" +
                "buildStr='" + buildStr + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                '}';
    }
}
