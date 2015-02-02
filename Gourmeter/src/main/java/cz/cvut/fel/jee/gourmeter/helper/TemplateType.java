package cz.cvut.fel.jee.gourmeter.helper;

public enum TemplateType {
	InvitationMail("invitationTemplate.txt", "/mailing/templates/"), 
	TesterInformationMail("testerMailTemplate.txt", "/mailing/templates/");

	private final String templateFileName;
	private final String path;

	TemplateType(String templateFileName,String path) {
		this.templateFileName = templateFileName;
		this.path = path;
	}

	String getTemplateFileName() {
		return this.templateFileName;
	}
	String getPath()
	{
		return this.path;
	}
}
