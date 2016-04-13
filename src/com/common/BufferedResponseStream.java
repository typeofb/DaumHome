package com.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * ServletOutputStream을 상속받아 내부 버퍼에 데이터를 담아 반환
 * HttpServletResponseWrapper에서 사용
 */
public class BufferedResponseStream extends ServletOutputStream {

	protected OutputStream bufferedOutput = null;
	
	public BufferedResponseStream(HttpServletResponse response) throws IOException {
		super();
		bufferedOutput = new ByteArrayOutputStream();
	}
	
	public void close() throws IOException {
	}
	
	public void write(int b) throws IOException {
		bufferedOutput.write((byte) b);
	}
	
	public void write(byte[] b) throws IOException {
		write(b, 0, b.length);
	}
	
	public void write(byte[] b, int off, int len) throws IOException {
		bufferedOutput.write(b, off, len);
	}
	
	public String toString() {
		return toString("UTF-8");
	}
	
	public String toString(String encoding) {
		String outputContents = "";
		try {
			ByteArrayOutputStream baos = (ByteArrayOutputStream) bufferedOutput;
			byte[] bytes = baos.toByteArray();
			outputContents = new String(bytes, encoding);
		} catch (UnsupportedEncodingException e) {
		} finally {
			try {
				bufferedOutput.close();
			} catch (IOException e) {
			}
		}
		return outputContents;
	}
}
