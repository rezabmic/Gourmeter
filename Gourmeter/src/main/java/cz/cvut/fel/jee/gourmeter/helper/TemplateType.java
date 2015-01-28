package cz.cvut.fel.jee.gourmeter.helper;

public enum TemplateType {
	InvitationMail("invitationTemplate.xml"), TesterInformationMail(
			"testerMailTemplate.xml");

	private final String templateFileName;

	TemplateType(String templateFileName) {
		this.templateFileName = templateFileName;
	}

	String getTemplateFileName() {
		return this.templateFileName;
	}
}
