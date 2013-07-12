#ашвЊзЂВсJNDI

<?xml version="1.0" encoding="UTF-8"?>
<Context docBase="C:\Users\1410267\workspace\iJms\target\iJms-1.0.0" path="iJms" reloadable="true">

	<Resource
        name="CONNECTON_FACTORY"
        auth="Container"
        type="org.apache.activemq.ActiveMQConnectionFactory"
        description="JMS Connection Factory"
        factory="org.apache.activemq.jndi.JNDIReferenceFactory"
        brokerURL="tcp://localhost:61616"
        brokerName="LocalActiveMQBroker"
        useEmbeddedBroker="false"/>

    <Resource name="RECEIVE"
        auth="Container"
        type="org.apache.activemq.command.ActiveMQQueue"
        factory="org.apache.activemq.jndi.JNDIReferenceFactory"
        physicalName="RECEIVE"/>
		
    <Resource name="REPLY"
        auth="Container"
        type="org.apache.activemq.command.ActiveMQQueue"
        factory="org.apache.activemq.jndi.JNDIReferenceFactory"
        physicalName="REPLY"/>

</Context>
