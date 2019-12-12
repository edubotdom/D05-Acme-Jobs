<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="employer.application.form.label.reference" path="reference" placeholder="EEEE-JJJJ:WWWW" readonly="true" />
	<acme:form-textbox code="employer.application.form.label.referenceJob" path="referenceJob" readonly="true" />
	<acme:form-textbox code="employer.application.form.label.JobEmployer" path="JobEmployer" readonly="true" />
	<acme:form-moment code="employer.application.form.label.moment" path="moment" readonly="true" />

	<acme:form-textbox code="employer.application.form.label.status" path="status" placeholder="pending, accepted, rejected" readonly="false" />

	<acme:form-textbox code="employer.application.form.label.justification" path="justification" readonly="false" />

	<acme:form-textarea code="employer.application.form.label.statement" path="statement" readonly="true" />
	<acme:form-textarea code="employer.application.form.label.skills" path="skills" readonly="true" />
	<acme:form-textarea code="employer.application.form.label.qualifications" path="qualifications" readonly="true" />

	<acme:form-submit test="${command == 'show' || command == 'update'}" code="employer.application.form.button.update"
		action="/employer/application/update" />
	<acme:form-return code="employer.application.form.button.return" />

</acme:form>
