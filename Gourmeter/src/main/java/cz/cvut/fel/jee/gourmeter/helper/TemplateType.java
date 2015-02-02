package cz.cvut.fel.jee.gourmeter.helper;

public enum TemplateType {
	InvitationMail("invitationTemplate.txt", "/mailing/templates/","Invitation"), 
	TesterInformationMail("testerMailTemplate.txt", "/mailing/templates/","New catering facility added");

	private final String templateFileName;
	private final String path;
	private final String subject;

	TemplateType(String templateFileName,String path, String subject) 
	{
		this.templateFileName = templateFileName;
		this.path = path;
		this.subject = subject;
	}

	String getTemplateFileName() {
		return this.templateFileName;
	}
	String getPath()
	{
		return this.path;
	}
	String getSubject()
	{
		return this.subject;
	}
}
