package project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.common.ClientResponse;

import java.util.*;

/**
 * @author shishaolong
 * @datatime 2020/4/8 10:11
 */
@RestController
@RequestMapping("/api")
public class ProjectController {

    @GetMapping("/data")
    public ClientResponse data() {
        Map<String,Object> resultMap = new LinkedHashMap();
        // = 公司列表
        List<Map<String, Object>> companyList = new ArrayList<>();
        Map<String,Object> companyMap1 = new HashMap();
        Map<String,Object> companyMap2 = new HashMap();
        companyMap1.put("companyName", "华重");
        companyMap1.put("companyId", "4028816c6759b6cb01675aacc98a00f6");
        companyMap2.put("companyName", "华石");
        companyMap2.put("companyId", "4028816c6759b6cb01675aacc98a0085");
        companyList.add(companyMap1);
        companyList.add(companyMap2);
        resultMap.put("companyList", companyList);

        // = 发货合同
        Map<String,Object> contractMap = new HashMap();
        // 合同申请
        contractMap.put("applyCount", 5);
        // 合同审批
        contractMap.put("auditingCount", 2);
        // 审批完成
        contractMap.put("auditedCount", 3);
        resultMap.put("contract", contractMap);

        // == 额度申请
        Map<String,Object> amountApplyMap = new HashMap();
        // 申请次数
        amountApplyMap.put("applyCount", 5);
        // 额度审批
        amountApplyMap.put("auditingCount", 2);
        // 审批完成
        amountApplyMap.put("auditedCount", 3);
        resultMap.put("amountApply", amountApplyMap);

        // == 发货申请
        Map<String,Object> deliveryApplyMap = new HashMap();
        // 发货申请
        deliveryApplyMap.put("applyCount", 4);
        resultMap.put("deliveryApply", deliveryApplyMap);

        // == 生产排班
        Map<String,Object> produceScheduleMap = new HashMap();
        // 待排班
        produceScheduleMap.put("preScheduleCount", 5);
        // 同意生产
        produceScheduleMap.put("agreedProduceCount", 2);
        // 拒绝生产
        produceScheduleMap.put("disagreedProduceCount", 3);
        resultMap.put("produceSchedule", produceScheduleMap);

        // == 调度排班
        Map<String,Object> dispatchScheduleMap = new HashMap();
        // 待排班
        dispatchScheduleMap.put("preScheduleCount", 3);
        // 排班完成
        dispatchScheduleMap.put("scheduledCount", 4);
        resultMap.put("dispatchSchedule", dispatchScheduleMap);

        // == 司机派送
        Map<String,Object> driverDeliveryMap = new HashMap();
        // 配送中
        driverDeliveryMap.put("deliveringCount", 625);
        // 已送达
        driverDeliveryMap.put("deliveredCount", 226);
        resultMap.put("driverDelivery", driverDeliveryMap);

        // == 客户确认
        Map<String,Object> customerConfirmMap = new HashMap();
        // 待确认
        customerConfirmMap.put("preConfirmCount", 625);
        // 已确认
        customerConfirmMap.put("confirmedCount", 226);
        resultMap.put("customerConfirm", customerConfirmMap);

        ClientResponse response = ClientResponse.createOk("查询成功");
        response.addData(resultMap);
        return response;
    }

}
