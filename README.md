# UnicoWebservice
This code is used to expose rest and soap webservice and put message in queue used wildfly server.

# Configuration for Queue on wildfly server
# Created a queue with the belwo configuration on server standalone.xml
<jms-destinations>
	<jms-queue name="gcdInputQueue">
		<entry name="queue/gcdInputQueue"/>
		<entry name="java:jboss/exported/jms/queue/gcdInputQueue"/>
	</jms-queue>
</jms-destinations>

This project is implement in Spring, RestWebservice and SOAP Webservice

# Rest Web service - 
# Exposed Rest web services using Spirng rest service (Web module)

    Class Name: com.nihilent.webservicecontroller.RestWebServiceController
    URL : http://{host}:{port}/UnicoWebservice/{push/list}/{parameter1}/{parameter2}

# Soap web services -
# Exposed via spring jws (Web module)

    Class Name: com.nihilent.webservicecontroller.SoapWebServiceController
    URL : http://{host}:{port}/UnicoWebservice/SoapWebServiceController?wsdl

