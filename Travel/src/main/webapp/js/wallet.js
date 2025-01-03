var IMP = window.IMP;
IMP.init("imp03808101");

function requestPay() {
  var random_uid = Math.random().toString(36).substring(2) + (new Date()).getTime().toString(36);

  // 서버로부터 결제 금액을 받아옴
  $.ajax({
    url: "/getPaymentAmount",
    type: "GET",
    
    success: function (response) {
      var amount = response.amount;

      IMP.request_pay({
        pg: "kakaopay.TC0ONETIME",
        pay_method: "kakaopay",
        notice_url: "https://e765-119-193-42-2.ngrok-free.app/webendpoint", //웹훅수신 URL 설정 ngrok에서 부여받은 url을 이용해야 한다. 포트원 설정도 함께 해줘야 한다.
        merchant_uid: "IMP" + random_uid,
        name: "travel 구독",
        amount: amount,
        buyer_email: $("#mail").val(),
        buyer_name: $("#name").val(),
        buyer_tel: $("#phone").val(),
        buyer_addr: $("#addr").val(),
        buyer_postcode: $("#post").val(),
      }, function (res) {
        if (res.success) {
          var header = $("meta[name='_csrf_header']").attr('content');
          var token = $("meta[name='_csrf']").attr('content');
          $.ajax({
            url: "/verifyIamport/" + res.imp_uid,
            type: "POST",
            beforeSend: function (xhr) {
              xhr.setRequestHeader(header, token);
            },
            data: {
              "imp_uid": res.imp_uid,
              "merchant_uid": res.merchant_uid,
              "amount": res.paid_amount
            },
            success: function (data) {
              if (data.result === "success") {
                window.location.href = "/successPay";
              } else {
                alert("결제 실패: " + data.message);
              }
            },
            error: function (xhr, status, error) {
              alert("결제 검증 중 오류 발생: " + error);
            }
          });
        } else {
          alert("결제에 실패하였습니다. 에러 내용: " + res.error_msg);
        }
      });
    },
    error: function (xhr, status, error) {
      alert("결제 금액을 가져오는 중 오류 발생: " + error);
    }
  });
}
