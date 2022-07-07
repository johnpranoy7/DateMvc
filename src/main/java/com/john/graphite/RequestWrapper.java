package com.john.graphite;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class RequestWrapper extends HttpServletRequestWrapper {
	private final String body;
	private ObjectMapper objectMapper = new ObjectMapper();

	public RequestWrapper(HttpServletRequest request) throws IOException {
		// So that other request method behave just like before
		super(request);

		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			InputStream inputStream = request.getInputStream();
			System.out.println(request.getParameterNames());

			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}

			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}
		// Store request body content in 'requestBody' variable
		String requestBody = stringBuilder.toString();
		JsonNode jsonNode = objectMapper.readTree(requestBody);
		// TODO -- Update your request body here
		// Sample
//        ((ObjectNode) jsonNode).remove("key");
		// Finally store updated request body content in 'body' variable
//		body = jsonNode.toString();
		String parameterNodeStr = getParameters(request);
		System.out.println("Printing body from bodyStrFromParameters: " + parameterNodeStr);
		body = parameterNodeStr;
	}

	private static final String YYYY_MM_DD_FORMAT = "((?:19|20)\\d\\d)[- \\/.](0[1-9]|1[012])[- \\/.](0[1-9]|[12][0-9]|3[01])";

	private String getParameters(HttpServletRequest request) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.createObjectNode();
		LinkedHashMap<String,String> myRes = new LinkedHashMap<>();
		Enumeration<?> e = request.getParameterNames();
		final Pattern pattern = Pattern.compile(YYYY_MM_DD_FORMAT, Pattern.MULTILINE);

		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			String value = request.getParameter(key);
			Matcher matcher = pattern.matcher(value);
			if (matcher.matches()) {
				value = "09/02/2022";
						//LocalDate.parse(matcher.group(0), DateTimeFormatter.ofPattern("yyyy-MM-dd")).format( DateTimeFormatter.ofPattern("MM/dd/yyyy"));
			}
			((ObjectNode) node).put(key, value);
			myRes.put(key, value);
		}
		
		return node.toString();
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
		ServletInputStream servletInputStream = new ServletInputStream() {
			public int read() throws IOException {
				return byteArrayInputStream.read();
			}

			@Override
			public boolean isFinished() {
				return false;
			}

			@Override
			public boolean isReady() {
				return false;
			}

			@Override
			public void setReadListener(ReadListener listener) {

			}
		};
		return servletInputStream;
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(this.getInputStream()));
	}
}