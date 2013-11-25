package com.common;

public class Common {

	// int -> 2byte
	public static byte[] intToFourByteArray(int integer) {
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
}
