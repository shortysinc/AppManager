FUNCTION Restart-Service
{
PARAM([STRING]$SERVERNAME=$env:COMPUTERNAME,[STRING]$SERVICENAME) #MSSQLSERVER FOR DEFAULT INSTANCE FOR NAMED INSTANCE MSSQL`$KAT

$SERVICE = GET-SERVICE -COMPUTERNAME $SERVERNAME -NAME $SERVICENAME -ERRORACTION SILENTLYCONTINUE

IF( $SERVICE.STATUS -EQ "RUNNING" )
{
	$DEPSERVICES = GET-SERVICE -COMPUTERNAME $SERVERNAME -Name $SERVICE.SERVICENAME -DEPENDENTSERVICES | WHERE-OBJECT {$_.STATUS -EQ "RUNNING"}
	IF( $DEPSERVICES -NE $NULL )
	{
		FOREACH($DEPSERVICE IN $DEPSERVICES)
		{
		Stop-Service -InputObject (Get-Service -ComputerName $SERVERNAME -Name $DEPSERVICES.ServiceName)
		}
	}
  Stop-Service -InputObject (Get-Service -ComputerName $SERVERNAME -Name $SERVICE.SERVICENAME) -Force
   if($?)
   {
   Start-Service -InputObject (Get-Service -ComputerName $SERVERNAME -Name $SERVICE.SERVICENAME)
	$DEPSERVICES = GET-SERVICE -COMPUTERNAME $SERVERNAME -NAME $SERVICE.SERVICENAME -DEPENDENTSERVICES | WHERE-OBJECT {$_.STATUS -EQ "STOPPED"}
	IF( $DEPSERVICES -NE $NULL )
	{
		FOREACH($DEPSERVICE IN $DEPSERVICES)
		{
			Start-Service -InputObject (Get-Service -ComputerName $SERVERNAME -Name $DEPSERVICE.SERVICENAME)
		}
	}
    }
 }
ELSEIF ( $SERVICE.STATUS -EQ "STOPPED" )
{
	Start-Service -InputObject (Get-Service -ComputerName $SERVERNAME -Name $SERVICE.SERVICENAME)
	$DEPSERVICES = GET-SERVICE -COMPUTERNAME $SERVERNAME -NAME $SERVICE.SERVICENAME -DEPENDENTSERVICES | WHERE-OBJECT {$_.STATUS -EQ "STOPPED"}
	IF( $DEPSERVICES -NE $NULL )
	{
		FOREACH($DEPSERVICE IN $DEPSERVICES)
		{
			Start-Service -InputObject (Get-Service -ComputerName $SERVERNAME -Name $DEPSERVICE.SERVICENAME)
		}
	}
}
ELSE
{

	"THE SPECIFIED SERVICE DOES NOT EXIST"
}

}


