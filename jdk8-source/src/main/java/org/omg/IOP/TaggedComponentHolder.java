package org.omg.IOP;

/**
* org/omg/IOP/TaggedComponentHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from c:/cygwin64/tmp/ojdkbuild/lookaside/java-1.8.0-openjdk/corba/src/share/classes/org/omg/PortableInterceptor/IOP.idl
* Friday, January 25, 2019 2:33:21 PM PST
*/

public final class TaggedComponentHolder implements org.omg.CORBA.portable.Streamable
{
  public org.omg.IOP.TaggedComponent value = null;

  public TaggedComponentHolder ()
  {
  }

  public TaggedComponentHolder (org.omg.IOP.TaggedComponent initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = org.omg.IOP.TaggedComponentHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    org.omg.IOP.TaggedComponentHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return org.omg.IOP.TaggedComponentHelper.type ();
  }

}