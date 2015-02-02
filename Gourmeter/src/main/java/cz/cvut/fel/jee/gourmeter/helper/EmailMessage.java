package cz.cvut.fel.jee.gourmeter.helper;

import java.io.Serializable;
import java.util.Map;

public class EmailMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	private String receiverEmail;
	private String templateName;
	private Map<String, Object> params;

	public EmailMessage() {
	}

	public EmailMessage(String receiverEmail, String templateName,
			Map<String, Object> params) {
		this.receiverEmail = receiverEmail;
		this.templateName = templateName;
		this.params = params;
	}

	public String getReceiverEmail() {
		return receiverEmail;
	}

	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "EmailMessage [receiverEmail=" + receiverEmail
				+ ", templateName=" + templateName + ", params=" + params + "]";
	}
	
	

}
