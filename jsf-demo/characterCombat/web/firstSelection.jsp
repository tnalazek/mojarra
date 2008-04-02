<%@ taglib uri="http://java.sun.com/jsf/core"  prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html"  prefix="h" %>

<f:view>
<html>
<head>
  <title>
  </title>
</head>

<body>

  <h:form>

    <h:panelGrid columns="1">

      <h:outputText value="First Pick!"/>

      <h:selectOneRadio 
        layout="pageDirection" 
        value="#{modelBean.firstSelection}">
        <f:selectItems 
          value="#{modelBean.charactersToSelect}" />
      </h:selectOneRadio>

    </h:panelGrid>

    <h:commandButton
      action="success"
      value="Submit First Character Selection"/>

  </h:form>

</body>
</html>
</f:view>
