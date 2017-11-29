package com.muyi.mpdemo.wechat;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.muyi.mpdemo.config.properties.WechatPayProperties;
import com.muyi.mpdemo.constants.WechatConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: muyi-corp
 * @Date: Created in 14:48 2017/11/29
 * @Description:
 */

@Slf4j
public class WechatPayTemplate {

    @Autowired
    private WechatPayProperties wechatPayProperties;
    @Autowired
    private WXPay wxPayService;

    /**
     * 统一下单 api
     * ◆ 除被扫支付场景以外，商户系统先调用该接口在微信支付服务后台生成预支付交易单，
     *   返回正确的预支付交易回话标识后再按扫码、JSAPI、APP等不同场景生成交易串调起支付
     *
     * 证书 ：no
     *
     * device_info : 自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
     * body        : 商品简单描述，该字段请按照规范传递
     * detail      : 商品详细描述，对于使用单品优惠的商户，改字段必须按照规范上传
     * attach      : 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用
     * out_trade_no: 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一
     * fee_type    : 符合ISO 4217标准的三位字母代码，默认人民币：CNY
     * total_fee   : 订单总金额，单位为分
     * spbill_create_ip : APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
     * goods_tag   : 订单优惠标记，使用代金券或立减优惠功能时需要的参数
     * notify_url  : 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
     * trade_type  : JSAPI，NATIVE，APP等
     * product_id  : trade_type=NATIVE时（即扫码支付），此参数必传。此参数为二维码中包含的商品ID，商户自行定义
     * openid      : trade_type=JSAPI时（即公众号支付），此参数必传，此参数为微信用户在商户对应appid下的唯一标识
     * scene_info  : 该字段用于上报场景信息，目前支持上报实际门店信息。
     *
     */
    public void unifiedOrder(){
        Map<String, String> data = new HashMap<String, String>();
        data.put("body", "腾讯充值中心-QQ会员充值");
        data.put("out_trade_no", "2016090910595900000012");
        data.put("device_info", "");
        data.put("fee_type", "CNY");
        data.put("total_fee", "1");
        data.put("spbill_create_ip", "123.12.12.123");
        data.put("notify_url", wechatPayProperties.getNotifyUrl());
        data.put("trade_type", WechatConstants.TRADE_JSAPI);
        data.put("product_id", "12");

        try {
            Map<String, String> resp = this.wxPayService.unifiedOrder(data);
            log.info(resp.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 查询订单 api
     * 该接口提供所有微信支付订单的查询，商户可以通过查询订单接口主动查询订单状态，完成下一步的业务逻辑。
     * ◆ 当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知；
     * ◆ 调用支付接口后，返回系统错误或未知交易状态情况；
     * ◆ 调用刷卡支付API，返回USERPAYING的状态；
     * ◆ 调用关单或撤销接口API之前，需确认支付状态；
     *
     * 证书 ：no
     *
     * 以下二者 二选一：
     * transaction_id : 微信的订单号，建议优先使用
     * out_trade_no: 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一
     */
    public void orderQuery(){
        Map<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", "2016090910595900000012");

        try {
            Map<String, String> resp = this.wxPayService.orderQuery(data);
            log.info(resp.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 关闭订单 api
     * ◆ 商户订单支付失败需要生成新单号重新发起支付，要对原订单号调用关单，避免重复支付；
     * ◆ 系统下单后，用户支付超时，系统退出不再受理，避免用户继续，请调用关单接口
     * 注意：订单生成后不能马上调用关单接口，最短调用时间间隔为5分钟。
     *
     * 证书 ：no
     *
     * out_trade_no: 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一
     *
     */
    public void closeOrder(){

        Map<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", "2016090910595900000012");

        try {
            Map<String, String> resp = this.wxPayService.closeOrder(data);
            log.info(resp.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 申请退款 api
     * 当交易发生之后一段时间内，由于买家或者卖家的原因需要退款时，卖家可以通过退款接口将支付款退还给买家，
     * 微信支付将在收到退款请求并且验证成功之后，按照退款规则将支付款按原路退到买家帐号上。
     *
     * 注意：
     * 1、交易时间超过一年的订单无法提交退款
     * 2、微信支付退款支持单笔交易分多次退款，多次退款需要提交原支付订单的商户订单号和设置不同的退款单号。申请退款总金额不能超过订单金额。 一笔退款失败后重新提交，请不要更换退款单号，请使用原商户退款单号
     * 3、请求频率限制：150qps，即每秒钟正常的申请退款请求次数不超过150次
     *    错误或无效请求频率限制：6qps，即每秒钟异常或错误的退款申请请求不超过6次
     * 4、每个支付订单的部分退款次数不能超过50次
     *
     * 证书 ：请求需要双向证书
     *
     * 以下二者 二选一：
     * transaction_id : 微信的订单号，建议优先使用
     * out_trade_no: 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一
     * out_refund_no：商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     * total_fee   : 订单总金额，单位为分，只能为整数
     * refund_fee  : 退款总金额，订单总金额，单位为分，只能为整数
     * refund_fee_type: 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY
     * refund_desc : 商户传入，会在下发给用户的退款消息中体现退款原因
     *
     */
    public void refund(){

        Map<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", "2016090910595900000012");

        try {
            Map<String, String> resp = this.wxPayService.refund(data);
            log.info(resp.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 查询退款 api
     * 提交退款申请后，通过调用该接口查询退款状态。
     * 退款有一定延时，用零钱支付的退款20分钟内到账，银行卡支付的退款3个工作日后重新查询退款状态。
     *
     * 分页查询
     * 当一个订单部分退款超过10笔后，商户用微信订单号或商户订单号调退款查询API查询退款时，默认返回前10笔和total_refund_count（订单总退款次数）。
     * 商户需要查询同一订单下超过10笔的退款单时，可传入订单号及offset来查询，微信支付会返回offset及后面的10笔，以此类推。
     * 当商户传入的offset超过total_refund_count，则系统会返回报错PARAM_ERROR。
     *
     * 证书 ：no
     *
     * 以下四者 4选一   refund_id > out_refund_no > transaction_id > out_trade_no
     * transaction_id : 微信的订单号，建议优先使用
     * out_trade_no   : 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一
     * out_refund_no  : 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     * refund_id      : 微信生成的退款单号，在申请退款接口有返回
     * offset         : 偏移量，当部分退款次数超过10次时可使用，表示返回的查询结果从这个偏移量开始取记录
     *
     */
    public void refundQuery(){

        Map<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", "2016090910595900000012");

        try {
            Map<String, String> resp = this.wxPayService.refundQuery(data);
            log.info(resp.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载对账单 api
     * 商户可以通过该接口下载历史交易清单。比如掉单、系统错误等导致商户侧和微信侧数据不一致，通过对账单核对后可校正支付状态。
     * 注意：
     * 1、微信侧未成功下单的交易不会出现在对账单中。支付成功后撤销的交易会出现在对账单中，跟原支付单订单号一致；
     * 2、微信在次日9点启动生成前一天的对账单，建议商户10点后再获取；
     * 3、对账单中涉及金额的字段单位为“元”。
     * 4、对账单接口只能下载三个月以内的账单。
     *
     * 证书 : no
     *
     * bill_date       : 下载对账单的日期，格式：20140603
     * bill_type       : ALL，返回当日所有订单信息，默认值
     *                   SUCCESS，返回当日成功支付的订单
     *                   REFUND，返回当日退款订单
     *                   RECHARGE_REFUND，返回当日充值退款订单（相比其他对账单多一栏“返还手续费”）
     * tar_type        : 非必传参数，固定值：GZIP，返回格式为.gzip的压缩包账单。不传则默认为数据流形式。
     *
     */
    public void downloadBill(){

        Map<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", "2016090910595900000012");

        try {
            Map<String, String> resp = this.wxPayService.downloadBill(data);
            log.info(resp.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 微信支付异步通知
     *
     * 支付完成后，微信会把相关支付结果和用户信息发送给商户，商户需要接收处理，并返回应答。
     * 对后台通知交互时，如果微信收到商户的应答不是成功或超时，微信认为通知失败，微信会通过一定的策略定期重新发起通知，尽可能提高通知的成功率，但微信不保证通知最终能成功。
     * （通知频率为15/15/30/180/1800/1800/1800/1800/3600，单位：秒）
     * 注意：同样的通知可能会多次发送给商户系统。商户系统必须能够正确处理重复的通知。
     *
     * 推荐的做法是，当收到通知进行处理时，首先检查对应业务数据的状态，判断该通知是否已经处理过，如果没有处理过再进行处理，如果处理过直接返回结果成功。
     * 在对业务数据进行状态检查和处理之前，要采用数据锁进行并发控制，以避免函数重入造成的数据混乱。
     *
     * FIXME 商户系统对于支付结果通知的内容一定要做签名验证,并校验返回的订单金额是否与商户侧的订单金额一致
     *
     */
    public void checkPayResult(String notifyData) throws Exception{
        //String notifyData = "...."; // 支付结果通知的xml格式数据

        Map<String, String> notifyMap = WXPayUtil.xmlToMap(notifyData);  // 转换成map
        log.info("【微信支付异步通知】{}",notifyMap);

        if (this.wxPayService.isPayResultNotifySignatureValid(notifyMap)) {
            // 签名正确
            // 进行处理。
            // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功
        }
        else {
            // 签名错误，如果数据里没有sign字段，也认为是签名错误
        }
    }
























}
