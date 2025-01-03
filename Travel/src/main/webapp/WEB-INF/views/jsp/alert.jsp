<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../css/alert_css.css">
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>

<%--아작스로 토큰을 보내기 위해서 해놓는 초기 설정 > 이후에 아작스에서 값을 받아 보내줘야 한다. --%>
<meta name="_csrf_header" content="${_csrf.headerName}">
<meta name="_csrf" content="${_csrf.token}">
</head>
<body>
<jsp:include page="../include/header.jsp" />
<div class="wrap">
	<div class="button_box">
		<a href="/Wallet" class="btn_1">지갑</a> 
		<a href="/Alert" class="btn_2">정보 수정</a>
		<a href="/chat" class="btn_3">고객 상담</a>
	</div>
	
<!-- 내 정보를 홈페이지 중앙에 띄우는 jsp -->
<div id="Join_wrap">
		<h2 class="Join_title">내 정보</h2> 
		<div id="error-container" style="color:red; font-size:15px; position:fixed; right:35%;"></div>
		
		<form name="m" method="post" action="Alert_All"  onsubmit="return join_check();">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<table id="Join_t">
				<tr>
					<th>회원이름</th>
					<td><input name="member_name" id="member_name" size="10" value="${m.member_name}" ></td>
				</tr>
				<tr>
					<th>주민번호</th>
					<td><input name="resident_id" id="resident_id" value="${m.resident_id}" size="8" maxlength="6">- 
					    <input type="password" name="resident_id2" id="resident_id2" value="${m.resident_id2}" size="8" maxlength="7">
					</td>
				</tr>
				<tr>
					<th>아이디</th>
					<td><input name="member_id" id="member_id" size="14" value="${m.member_id}" readonly>
					<br>
					</td>		
				</tr>

				<tr>
					<th>폰번호</th>
					<td><input name="member_phone01" id="member_phone01" size="3"maxlength="3" value="${m.member_phone01}"> -
						<input name="member_phone02" id="member_phone02" size="4" maxlength="4" value="${m.member_phone02}"> -
						<input name="member_phone03" id="member_phone03" size="4" maxlength="4" value="${m.member_phone03}">
					</td>
				</tr>

				<tr>
					<th>이메일</th>
					<td><input name="mail_id" id="mail_id" size="14" class="input_box" value="${m.mail_id}">@ 
						<input name="mail_domain" id="mail_domain" size="18" value="${m.mail_domain}" class="input_box" readonly > 
						<select name="mail_list" onchange="domain_list();">
							<c:forEach var="mail" items="${email}">
								<option value="${mail}">${mail}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td><input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"></td>
				</tr>
				<tr>
					<th>우편 번호</th>
					<td><input type="text" name="sample6_postcode" id="sample6_postcode" value="${m.sample6_postcode}"></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input type="text" name="sample6_address" id="sample6_address" size="30" value="${m.sample6_address}"></td>
				</tr>
				<tr>
					<th>상세주소</th>
					<td><input type="text" name="sample6_detailAddress" id="sample6_detailAddress" size="30" value="${m.sample6_detailAddress}"></td>
				</tr>
				<tr>
					<th>참고항목</th>
					<td><input type="text" name="sample6_extraAddress" id="sample6_extraAddress" value="${m.sample6_extraAddress}"></td>
				</tr>
			</table>
			<div id="Join_menu">
				<input type="submit" value="정보 수정" name="alert_A">&nbsp;&nbsp;&nbsp; 
				<input type="submit" value="비밀번호 수정" name="alert_A">&nbsp;&nbsp;&nbsp; 
				<input type="submit" value="회원 탈퇴" name="alert_A">
			</div>
		</form>
	</div>		
	
	
	
	
	
	
	</div>	
<jsp:include page="../include/footer.jsp" />
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="../../js/Join_Zip.js"></script>
<script src="../../js/alert.js"></script>	
</body>
</html>
