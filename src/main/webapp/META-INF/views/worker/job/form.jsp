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
	<acme:form-textbox code="worker.job.form.label.referenceNumber" path="referenceNumber" placeholder="EEEE-JJJJ" />
	<acme:form-textbox code="worker.job.form.label.title" path="title" />
	<acme:form-moment code="worker.job.form.label.deadline" path="deadline" />
	<acme:form-double code="worker.job.form.label.salary" path="salary" />
	<acme:form-textbox code="worker.job.form.label.moreInfo" path="moreInfo" />
	<acme:form-textbox code="worker.job.form.label.description" path="description" />
	<acme:form-textbox code="worker.job.form.label.status" path="status" />


	<acme:form-return code="worker.job.form.button.auditList" action="${auditList}" />
	<acme:form-return code="worker.job.form.button.duties" action="${duties}" />
	<acme:form-return code="worker.job.form.button.application.create" action="${jobCreateApplication}" />

	<acme:form-return code="worker.job.form.button.return" />
</acme:form>
