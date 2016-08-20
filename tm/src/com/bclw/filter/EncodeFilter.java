package com.bclw.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodeFilter implements Filter {
	private String encode = null;
	private boolean ignore = false;// 过滤器开关

	public void destroy() {
		encode = null;
		ignore = false;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
	
		if (!ignore) {
			System.out.println("编码过滤器启动");
			
				request.setCharacterEncoding(encode);
		
			
				response.setContentType("text/html");
		

				response.setCharacterEncoding("UTF-8");
			
		}
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String encode = filterConfig.getInitParameter("encode");
		String ignore = filterConfig.getInitParameter("ignore");
		if (this.encode == null)
			this.encode = encode;
		if ("1".equals(ignore) || "yes".equals(ignore)) {
			this.ignore = true;
		}
	}

}