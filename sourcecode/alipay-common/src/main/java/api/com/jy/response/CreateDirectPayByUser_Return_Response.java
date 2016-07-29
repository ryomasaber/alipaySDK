package api.com.jy.response;


/**
 * ALIPAY API:create_direct_pay_by_user
 * Created by Saber on 2016/7/1.
 */
public class CreateDirectPayByUser_Return_Response extends CreateDirectPayByUser_Base_Response{

    private static final long serialVersionUID = 1217590268502439517L;

    /**
     * 成功标识 表示接口调用是否成功，并不表明业务处理结果。(T / F)
     */
    private String isSuccess;
    /**
     *  接口名称
     */
    private String exterface;

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getExterface() {
        return exterface;
    }

    public void setExterface(String exterface) {
        this.exterface = exterface;
    }
}
