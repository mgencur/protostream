package org.infinispan.protostream;

/**
 * @author anistor@redhat.com
 */
public interface BaseMarshaller<T> {

   Class<? extends T> getJavaClass();

   /**
    * Returns the full name of the message or enum type as declared in the protobuf file.
    *
    * @return the full name of the message or enum type declared in the protobuf file.
    */
   String getTypeName();
}
