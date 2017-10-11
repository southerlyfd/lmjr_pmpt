/**
 * 
 */
package com.test.el;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author jitl
 *
 */
@Service
public class DemoService {

	@Value("其他类的属性")
	private String another;

	/**
	 * @return the another
	 */
	public String getAnother() {
		return another;
	}

	/**
	 * @param another the another to set
	 */
	public void setAnother(String another) {
		this.another = another;
	}
	
}
