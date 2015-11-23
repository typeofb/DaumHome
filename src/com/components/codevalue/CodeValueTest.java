package com.components.codevalue;

public class CodeValueTest {

	public static void main(String[] args) throws InterruptedException {
		CodeValueSynchronizer cvs = new CodeValueSynchronizer();
		cvs.start();
		while (CodeValueCache.get() == null) {
			System.out.println("while");
		}
		System.out.println("End while");
		Thread.sleep(1000);
		
		CodeValue cv = CodeValueFactory.getInstance("APP_CATEGORY");
		System.out.println(cv);
		if (cv.containsKey("CTG1")) {
			System.out.println(cv.get("CTG1"));
		}
		
//		CodeValue cv2 = CodeValueFactory.getInstance("mail.write.massfileDrm");
//		if (cv2.hasRoleByKeyword("USER_ID", requestData.getUser().getUserId())) {
//			System.out.println(true);
//		}
		
		cvs.removeInstance();
	}
}
