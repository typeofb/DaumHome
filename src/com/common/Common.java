package com.common;

public class Common {

	// int -> 2byte
	public static byte[] intToTwoByteArray(int integer) {
		byte[] byteArray = new byte[2];
		byteArray[0] |= (byte) ((integer & 0xFF00) >> 8);
		byteArray[1] |= (byte) (integer & 0xFF);
		return byteArray;
	}

	// 2byte -> int
	public static int twoByteArrayToInt(byte[] byteArray, int index) {
		int s1 = byteArray[index] & 0xFF;
		int s2 = byteArray[index + 1] & 0xFF;
		return ((s1 << 8) + (s2 << 0));
	}

	public static String NVL(String str, String NVLString) {
		if (str == null || str.trim().equals("") || str.trim().equals("null"))
			return NVLString;
		else
			return str;
    }

	public static String paging(int totalRowSize, int rowSize, int targetPage, int pageGroupSize) {
		StringBuffer returnValue = new StringBuffer();

		String preImg = "<img src='/DaumHome/images/Icon_BoardPage_Prev.gif' alt='이전' border='0' />";
		String postImg = "<img src='/DaumHome/images/Icon_BoardPage_Next.gif' alt='다음' border='0' />";

		int totalPageSize = (totalRowSize / rowSize);
		if ((totalRowSize % rowSize) > 0)
			totalPageSize++;

		int startPage = ((targetPage - 1) / pageGroupSize) * pageGroupSize + 1;
		int endPage = startPage + pageGroupSize - 1;
		if (endPage > totalPageSize)
			endPage = totalPageSize;

		if (totalPageSize == 0)
			targetPage = 0;

		if (targetPage > pageGroupSize) {
			int prePage = ((targetPage / pageGroupSize) - 1) * pageGroupSize + 1;
			returnValue.append("<a href=\"javascript:goPage('" + prePage + "')\">" + preImg + "</a>");
		} else {
			returnValue.append(preImg);
		}

		for (int i = startPage; i <= endPage; i++) {
			if (i == targetPage) {
				returnValue.append("<a><strong>" + i + "</strong></a>");
			} else {
				returnValue.append("<a href=\"javascript:goPage('" + i + "')\"><span>" + i + "</span></a>");
			}
		}

		if (endPage < totalPageSize) {
			int nextPage = endPage + 1;
			returnValue.append("<a href=\"javascript:goPage('" + nextPage + "')\">" + postImg + "</a>");
		} else {
			returnValue.append(postImg);
		}

		return returnValue.toString();
	}
}
