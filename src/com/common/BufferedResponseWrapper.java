package com.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class BufferedResponseWrapper extends HttpServletResponseWrapper {

	private StringWriter sw;
	private BufferedResponseStream bufferedResponse;
	private boolean useOutputStream;
	private boolean useFlushBuffer;
	
	private Map data;
	private Exception exception;
	
	public BufferedResponseWrapper(HttpServletResponse response) {
		super(response);
		this.useFlushBuffer = true;
		this.sw = new StringWriter();
		try {
			this.bufferedResponse = new BufferedResponseStream(response);
			this.useOutputStream = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public PrintWriter getWriter() throws IOException {
		useOutputStream = false;
		return new PrintWriter(sw);
	}
	
	public ServletOutputStream getOutputStream() throws IOException {
		useOutputStream = true;
		return bufferedResponse;
	}
	
	public String toString() {
		if (useOutputStream) {
			return bufferedResponse.toString();
		} else {
			return sw.toString();
		}
	}
	
	public boolean isUseOutputStream() {
		return useOutputStream;
	}
	
	public Exception getException() {
		return exception;
	}
	
	public void setException(Exception exception) {
		this.exception = exception;
	}
	
	public Map getData() {
		return data;
	}
	
	public void setData(Map data) {
		this.data = data;
	}
	
	public void addData(String key, Object value) {
		if (data == null)
			data = new HashMap();
		data.put(key, value);
	}
	
	public void flushBuffer() throws IOException {
		if (useFlushBuffer) {
			super.flushBuffer();
		}
	}
	
	public void setUseFlushBuffer(boolean useFlushBuffer) {
		this.useFlushBuffer = useFlushBuffer;
	}
}
