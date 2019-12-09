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
	<acme:form-textbox code="employer.descriptor.form.label.description" path="description" />
	
	<acme:form-return code="employer.descriptor.form.button.duties" action="${direccion}"/>
	
	<acme:form-submit test="${command== 'show'}" code="employer.descriptor.form.button.update"
		action="/employer/descriptor/update" />
	
	<acme:form-submit test="${command== 'update'}" code="employer.descriptor.form.button.update"
		action="/employer/descriptor/update" />
		
		
	<acme:form-return code="employer.descriptor.form.button.return" />
</acme:form>
