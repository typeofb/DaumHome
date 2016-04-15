<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>
<style type="text/css">
.file_input_textbox { float: left; width: 220px; height: 19px; line-height: 19px; border: 1px solid #adafb5; resize: none; }
.file_input_div { position: relative; width: 90px; height: 25px; overflow: hidden; }
.file_input_button { position: absolute; width: 80px; height: 23px; line-height: 18px; top: 0px; background-color: #f8f8f8; color: #555555; border: 1px solid #cccccc; margin-left: 5px; }
.file_input_hidden { position: absolute; height: 23px; font-size: 12px; top: 0px; right: 5px; opacity: 0; filter: alpha(opacity = 0); -ms-filter: "alpha(opacity=0)"; -khtml-opacity: 0; -moz-opacity: 0; }
.file_add_button { width: 70px; height: 23px; line-height: 18px; top: 0px; background-color: #f8f8f8; color: #555555; border: 1px solid #cccccc; margin-left: 5px; }
.file_upload_button { width: 50px; height: 20px; line-height: 15px; top: 0px; background-color: #f8f8f8; color: #555555; border: 1px solid #cccccc; }
.file_list_select { width: 820px; height: 100px; margin-top: 7px; }
</style>
<!-- jquery plugin -->
<script src="js/jquery-popup.js"></script>
<script type="text/javascript">
	function callback(data) {
		if (data != undefined)
			alert(data.id + " : " + data.name);
	}
	
	var fileNum = 1;
	var totalFileCnt = 0;
	var maxFileCnt = 20;
	
	function addFile() {
		var fileName = $("#fileName").val();
		var originalFileName = fileName;
		
		var lastIndex = fileName.lastIndexOf("\\");
		fileName = fileName.substring(lastIndex + 1, fileName.length);
		var fileExt = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length);
		fileExt = fileExt.toLowerCase();
		
		var duplicated = false;
		$("#fileList").find("option").each(function() {
			if (fileName == $(this).val()) {
				duplicated = true;
			}
		});
		
		if (duplicated == true) {
			alert("해당 파일은 이미 추가되었습니다.");
			return;
		} else if (fileName == null || fileName == "" || fileName == undefined) {
			alert("추가할 파일이 없습니다.");
			return;
		} else if (fileExt == "exe" || fileExt == "jsp" || fileExt == "asp"
				|| fileExt == "php" || fileExt == "cgi" || fileExt == "php3"
				|| fileExt == "inc" || fileExt == "pl" || fileExt == "html"
				|| fileExt == "htm" || fileExt == "js") {
			alert("EXE, JSP, ASP, PHP, CGI, PHP3, INC, PL, HTML, HTM, JS 파일은 업로드 할 수 없습니다.");
			return;
		} else {
			if (totalFileCnt == maxFileCnt) {
				alert("파일은 "+ maxFileCnt + "개까지 추가할 수 있습니다.");
				return;
			} else {
				totalFileCnt++;
				var opt = '<option name="' + fileNum + '" value="' + fileName + '" fakepath="' + originalFileName + '">' + fileName + '</option><br/>';
				$("#fileList").append(opt);
				
				fileNum++;
				var attachSection = '<input type="file" id="attachFiles_' + fileNum + '" name="attachFiles_' + fileNum + '" onchange="javascript:document.getElementById(\'fileName\').value=this.value" class="file_input_hidden" />';
				$(".file_input_div").append(attachSection);
			}
		}
	}
	
	function delFile() {
		if ($("#fileList").find("option:selected").val() != undefined) {
			totalFileCnt--;
			var selectedFile = $("#fileList").find("option:selected").attr("name");
			$("#attachFiles_" + selectedFile).remove();
			
			$("#fileList").find("option:selected").remove();
		}
	}
	
	function upload() {
		$("#attachFiles_" + fileNum).remove();
		document.mForm.action = "<c:url value='daumeditor/pages/trex/upload.do' />";
		document.mForm.submit();
	}
	
	function cancel() {
	}
</script>
<iframe name="my_iframe" style="display: none;"></iframe>
<div class="container">
	<div><a href="javascript:;" onclick="EP.showPopup('MULTI_DEPT', callback, '418', '');">jquery plugin modal test</a></div>
	<form name="mForm" method="post" enctype="multipart/form-data" target="my_iframe">
		<table>
			<tr>
				<td>
					<input type="text" id="fileName" readonly="readonly" class="file_input_textbox" />
					<div class="file_input_div">
						<input type="button" value="찾아보기..." class="file_input_button" />
						<input type="file" id="attachFiles_1" name="attachFiles_1" onchange="javascript:document.getElementById('fileName').value=this.value" class="file_input_hidden" />
					</div>
				</td>
				<td>
					<input type="button" value="추가" onclick="javascript:addFile();" class="file_add_button" />
					<input type="button" value="삭제" onclick="javascript:delFile();" class="file_add_button" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<select id="fileList" name="fileList" multiple="multiple" size="10" class="file_list_select"></select>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="업로드" onclick="javascript:upload()" class="file_upload_button" />
					<input type="button" value="취소" onclick="javascript:cancel();" class="file_upload_button" />
				</td>
			</tr>
		</table>
	</form>
	<div><h:upload name="ojtFiles" /></div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	<h:init />
});
</script>
<%@ include file="layout/footer.jsp"%>
</body>
</html>
