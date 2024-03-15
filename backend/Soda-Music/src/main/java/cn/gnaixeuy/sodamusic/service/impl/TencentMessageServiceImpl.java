package cn.gnaixeuy.sodamusic.service.impl;

import cn.gnaixeuy.sodamusic.config.tencent.TencentMessageConfig;
import cn.gnaixeuy.sodamusic.dto.event.SendMessageTask;
import cn.gnaixeuy.sodamusic.service.MessageService;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/> <br/>
 * Soda-Music
 *
 * @author GnaixEuy
 * @version 1.0
 * @date 2024/3/15
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
@Slf4j
@Service(value = "tencent_message")
public class TencentMessageServiceImpl implements MessageService {

    private TencentMessageConfig tencentMessageConfig;

    @Override
    @SneakyThrows
    public Boolean sendPhoneCaptchaMessage(SendMessageTask sendMessageTask) {
        log.info("本次发送的验证码参数内容为:{}", sendMessageTask);

        String phoneNumber = sendMessageTask.getPhoneNumber();
        String captcha = sendMessageTask.getCaptcha();

        // 创建 COSCredentials 对象，传入 SecretId 和 SecretKey --- 密钥
        Credential cred = new Credential(this.tencentMessageConfig.getSecretId(), this.tencentMessageConfig.getSecretKey());
        HttpProfile httpProfile = new HttpProfile();
        // 设置腾讯云服务 API 的访问域名
        httpProfile.setEndpoint("sms.tencentcloudapi.com");
        ClientProfile clientProfile = new ClientProfile();
        // 设置 HTTP 的请求配置信息
        clientProfile.setHttpProfile(httpProfile);
        // 指定 SMS 所在的区域
        SmsClient client = new SmsClient(cred, "ap-guangzhou", clientProfile);

        SendSmsRequest req = this.getSendSmsRequest(phoneNumber, captcha);

        // 发送短信
        SendSmsResponse resp = client.SendSms(req);
        log.info("请求返回结果:{}", SendSmsResponse.toJsonString(resp));
        return Boolean.TRUE;
    }

    @NotNull
    private SendSmsRequest getSendSmsRequest(String phoneNumber, String captcha) {
        SendSmsRequest req = new SendSmsRequest();
        // 接收短信的手机号码，可以设置多个
        String[] phoneNumberSet1 = {phoneNumber};
        req.setPhoneNumberSet(phoneNumberSet1);

        // 设置正文模板 ID
        req.setTemplateId(this.tencentMessageConfig.getTemplateId());
        // 设置短信应用 ID
        req.setSmsSdkAppId(this.tencentMessageConfig.getSmsSdkAppId());
        // 设置签名内容
        req.setSignName(this.tencentMessageConfig.getSignName());

        // 短信模板中的参数，需与短信模板中的参数个数一致
        String[] templateParams = {captcha};
        req.setTemplateParamSet(templateParams);
        return req;
    }

    @Autowired
    public void setTencentMessageConfig(TencentMessageConfig tencentMessageConfig) {
        this.tencentMessageConfig = tencentMessageConfig;
    }
}