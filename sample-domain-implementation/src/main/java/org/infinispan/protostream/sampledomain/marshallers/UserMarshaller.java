package org.infinispan.protostream.sampledomain.marshallers;

import org.infinispan.protostream.MessageMarshaller;
import org.infinispan.protostream.sampledomain.Address;
import org.infinispan.protostream.sampledomain.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author anistor@redhat.com
 */
public class UserMarshaller implements MessageMarshaller<User> {

   @Override
   public String getTypeName() {
      return "sample_bank_account.User";
   }

   @Override
   public Class<? extends User> getJavaClass() {
      return User.class;
   }

   @Override
   public User readFrom(ProtoStreamReader reader) throws IOException {
      int id = reader.readInt("id");
      List<Integer> accountIds = reader.readCollection("accountIds", new ArrayList<Integer>(), Integer.class);

      String surname = reader.readString("surname");
      String name = reader.readString("name");

      List<Address> addresses = reader.readCollection("addresses", new ArrayList<Address>(), Address.class);

      Integer age = reader.readInt("age");
      User.Gender gender = reader.readObject("gender", User.Gender.class);

      User user = new User();
      user.setId(id);
      user.setAccountIds(accountIds);
      user.setName(name);
      user.setSurname(surname);
      user.setAge(age);
      user.setGender(gender);
      user.setAddresses(addresses);
      return user;
   }

   @Override
   public void writeTo(ProtoStreamWriter writer, User user) throws IOException {
      writer.writeInt("id", user.getId());
      writer.writeCollection("accountIds", user.getAccountIds(), Integer.class);
      writer.writeString("name", user.getName());
      writer.writeString("surname", user.getSurname());
      writer.writeCollection("addresses", user.getAddresses(), Address.class);
      writer.writeInt("age", user.getAge());
      writer.writeObject("gender", user.getGender(), User.Gender.class);
   }
}
