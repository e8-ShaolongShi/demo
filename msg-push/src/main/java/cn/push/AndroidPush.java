package cn.push;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author shishaolong
 * @datatime 2020/8/7 16:57
 */
@Controller
public class AndroidPush {

//    @Autowired
//    private MqttPushClient mMqtt;
//
//    @RequestMapping("/")
//    private String  index() {
//        return "index";
//    }
//    @RequestMapping("api/android/push")
//    @ResponseBody
//    private ResponseBean<String> push(@RequestParam("pushStr")String pushStr){
//        mMqtt.publish(ConstString.ANDROID_PUSH_TOPIC, pushStr);
//        System.out.println(pushStr);
//        return ResponseUtils.success("",pushStr);
//    }
}
