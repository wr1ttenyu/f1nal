package com.sun.corba.se.PortableActivationIDL;

/**
* com/sun/corba/se/PortableActivationIDL/NoSuchEndPointHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from c:/cygwin64/tmp/ojdkbuild/lookaside/java-1.8.0-openjdk/corba/src/share/classes/com/sun/corba/se/PortableActivationIDL/activation.idl
* Friday, January 25, 2019 2:33:12 PM PST
*/

public final class NoSuchEndPointHolder implements org.omg.CORBA.portable.Streamable
{
  public com.sun.corba.se.PortableActivationIDL.NoSuchEndPoint value = null;

  public NoSuchEndPointHolder ()
  {
  }

  public NoSuchEndPointHolder (com.sun.corba.se.PortableActivationIDL.NoSuchEndPoint initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = com.sun.corba.se.PortableActivationIDL.NoSuchEndPointHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    com.sun.corba.se.PortableActivationIDL.NoSuchEndPointHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return com.sun.corba.se.PortableActivationIDL.NoSuchEndPointHelper.type ();
  }

}
