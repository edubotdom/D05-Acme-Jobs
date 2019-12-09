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
	<acme:form-textbox code="employer.job.form.label.referenceNumber" path="referenceNumber" placeholder="EEEE-JJJJ"/>
	<acme:form-textbox code="employer.job.form.label.title" path="title" />
	<acme:form-moment code="employer.job.form.label.deadline" path="deadline" />
	<acme:form-double code="employer.job.form.label.salary" path="salary" />
	<acme:form-textbox code="employer.job.form.label.moreInfo" path="moreInfo" />
	<acme:form-textbox code="employer.job.form.label.status" path="status" />

	<acme:form-submit test="${command== 'show'}" code="employer.job.form.button.update"
		action="/employer/job/update" />
		
	<acme:form-submit test="${command== 'show'}" code="employer.job.form.button.delete"
		action="/employer/job/delete" />
		
	<acme:form-submit test="${command== 'update'}" code="employer.job.form.button.update"
		action="/employer/job/update" />
		
	<acme:form-submit test="${command== 'delete'}" code="employer.job.form.button.delete"
		action="/employer/job/delete" />
		
	<acme:form-return code="employer.job.form.button.auditList" action="${auditList}"/>
	<acme:form-return code="employer.job.form.button.descriptorShow" action="${descriptorShow}"/>
		
	<acme:form-return code="employer.job.form.button.return" />
</acme:form>
