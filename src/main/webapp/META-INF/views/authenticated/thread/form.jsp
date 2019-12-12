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
	<jstl:if test="${command == 'create' }">
		<acme:form-textbox code="authenticated.thread.form.label.title" path="title" readonly="false" />
		<acme:form-submit code="authenticated.thread.form.button.create" action="/authenticated/thread/create" />
	</jstl:if>

	<jstl:if test="${command != 'create' }">
		<acme:form-textbox code="authenticated.thread.form.label.title" path="title" />
		<acme:form-moment code="authenticated.thread.form.label.creationDate" path="creationDate" />
		<acme:form-return code="authenticated.thread.form.button.messageList" action="${direccion}" />
		<acme:form-return code="authenticated.thread.form.button.message.create" action="${threadCreateMessage}" />
		<acme:form-return code="authenticated.thread.form.button.message.addUser" action="${direccionAnadirUsuario}" />
		<%--<acme:form-hidden path="direccionThreadUpdate"/>--%>
		<%--<acme:form-return code="authenticated.thread.form.button.update" action="${direccionThreadUpdate}" />--%>
		<acme:form-return code="authenticated.thread.form.button.update" action="/authenticated/authenticated/list" />
	</jstl:if>



	<acme:form-return code="authenticated.thread.form.button.return" />
</acme:form>
