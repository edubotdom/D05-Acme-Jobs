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

<acme:form readonly="false">
	<acme:form-textbox code="worker.application.form.label.reference" path="reference" placeholder="EEEE-JJJJ:WWWW" />

	<jstl:if test="${command != 'create' }">--%>
		<acme:form-textbox code="worker.application.form.label.referenceJob" path="referenceJob" readonly="true" />
		<acme:form-textbox code="worker.application.form.label.JobEmployer" path="JobEmployer" readonly="true" />
		<acme:form-moment code="worker.application.form.label.moment" path="moment" readonly="true" />
	</jstl:if>

	<acme:form-hidden path="status" />
	<acme:form-hidden path="moment" />
	<%--<acme:form-hidden path="job" />
	<acme:form-hidden path="worker" />--%>

	<acme:form-textarea code="worker.application.form.label.statement" path="statement" />
	<acme:form-textarea code="worker.application.form.label.skills" path="skills" />
	<acme:form-textarea code="worker.application.form.label.qualifications" path="qualifications" />

	<acme:form-submit test="${command=='create'}" code="worker.application.form.button.create" action="/worker/application/create" />

	<acme:form-return code="worker.application.form.button.return" />

</acme:form>
