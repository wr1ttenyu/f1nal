package com.sun.corba.se.PortableActivationIDL;


/**
* com/sun/corba/se/PortableActivationIDL/ServerAlreadyInstalled.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from c:/cygwin64/tmp/ojdkbuild/lookaside/java-1.8.0-openjdk/corba/src/share/classes/com/sun/corba/se/PortableActivationIDL/activation.idl
* Friday, January 25, 2019 2:33:12 PM PST
*/

public final class ServerAlreadyInstalled extends org.omg.CORBA.UserException
{
  public String serverId = null;

  public ServerAlreadyInstalled ()
  {
    super(ServerAlreadyInstalledHelper.id());
  } // ctor

  public ServerAlreadyInstalled (String _serverId)
  {
    super(ServerAlreadyInstalledHelper.id());
    serverId = _serverId;
  } // ctor


  public ServerAlreadyInstalled (String $reason, String _serverId)
  {
    super(ServerAlreadyInstalledHelper.id() + "  " + $reason);
    serverId = _serverId;
  } // ctor

} // class ServerAlreadyInstalled
