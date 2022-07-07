package com.john.graphite;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class MyFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("outside the Filter");
		RequestWrapper wrappedRequest = null;
		if (((HttpServletRequest) request).getMethod().equals(HttpMethod.POST.toString())) {
			System.out.println("Inside the Filter");
			String characterEncoding = request.getCharacterEncoding();
		      Charset charset = Charset.forName(characterEncoding);
		      String bodyInStringFormat = readInputStreamInStringFormat(request.getInputStream(), charset);
			wrappedRequest = new RequestWrapper((HttpServletRequest) request);
//		      wrappedRequest = new CustomHttpRequestWrapper(((HttpServletRequest) request));
		}
		chain.doFilter(wrappedRequest != null ? wrappedRequest : request, response);
	}

	@Override
	public void destroy() {

	}
	
	private String readInputStreamInStringFormat(InputStream stream, Charset charset) throws IOException {
	    final int MAX_BODY_SIZE = 1024;
	    final StringBuilder bodyStringBuilder = new StringBuilder();
	    if (!stream.markSupported()) {
	      stream = new BufferedInputStream(stream);
	    }

	    stream.mark(MAX_BODY_SIZE + 1);
	    final byte[] entity = new byte[MAX_BODY_SIZE + 1];
	    final int bytesRead = stream.read(entity);

	    if (bytesRead != -1) {
	      bodyStringBuilder.append(new String(entity, 0, Math.min(bytesRead, MAX_BODY_SIZE), charset));
	      if (bytesRead > MAX_BODY_SIZE) {
	        bodyStringBuilder.append("...");
	      }
	    }
	    stream.reset();

	    return bodyStringBuilder.toString();
	  }

}
