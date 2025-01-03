package net.daum.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

@Controller
public class ImportApiController {

    private IamportClient api;

    public ImportApiController(@Value("${iamport.api.key}") String apiKey, @Value("${iamport.api.secret}") String apiSecret) {
   
       this.api = new IamportClient(apiKey, apiSecret);
    //System.out.println("API Key: " + apiKey);
    //System.out.println("API Secret: " + apiSecret);
    }

    @PostMapping("/webendpoint")
    @ResponseBody
    public String handleWebhook(@RequestBody MyPayment payload) {
        // 웹훅 수신 처리-가능한지만
    // ngrok를 통해 통신한다.
    // 포트원이 보내는 형식 {"imp_uid":"imp_614505216799","merchant_uid":"IMPtz60b24qh0elxd3320p","status":"paid"}
   
   
    System.out.println(payload.getimp_uid());
    System.out.println(payload.getmerchant_uid());
    System.out.println(payload.getStatus());
   
        System.out.println("웹훅 수신이 정상적으로 완료됨");
        return null;
    }
 
   
    // 결제 금액을 계산하여 반환하는 API
    @GetMapping("/getPaymentAmount")
    @ResponseBody
    public ResponseEntity<Map<String, Integer>> getPaymentAmount() {
        Map<String, Integer> response = new HashMap<>();
        response.put("amount", 9900); // 결제 금액을 직접 계산하여 반환
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verifyIamport/{imp_uid}")
    @ResponseBody
    public ResponseEntity<Map<String, String>> paymentByImpUid(
            @PathVariable(value = "imp_uid") String imp_uid,
            @RequestParam("merchant_uid") String merchant_uid,
            @RequestParam("amount") int amount) throws IamportResponseException, IOException {

        //System.out.println("Received amount from client: " + amount);

        IamportResponse<Payment> response = api.paymentByImpUid(imp_uid);
       
        Payment payment = response.getResponse();
        if (payment == null) {
            Map<String, String> errorResult = new HashMap<>();
            errorResult.put("result", "fail");
            errorResult.put("message", "결제 정보를 찾을 수 없습니다.");
            return ResponseEntity.badRequest().body(errorResult);
        }

        //System.out.println("Payment amount from Iamport: " + payment.getAmount().intValue());
        //System.out.println("Payment merchant_uid from Iamport: " + payment.getMerchantUid());

        Map<String, String> result = new HashMap<>();
        int expectedAmount = 9900; // 서버에서 직접 계산한 결제 금액
        if (payment.getAmount().intValue() == expectedAmount && payment.getMerchantUid().equals(merchant_uid)) {
            result.put("result", "success");
            return ResponseEntity.ok(result);
        } else {
            // 결제 금액 또는 merchant_uid가 일치하지 않는 경우 결제 취소
            CancelData cancelData = new CancelData(imp_uid, true);
            api.cancelPaymentByImpUid(cancelData);

            result.put("result", "fail");
            result.put("message", "결제 금액 불일치 또는 상점 주문번호 불일치. 결제가 취소되었습니다.");
            return ResponseEntity.badRequest().body(result);
        }
    }
}