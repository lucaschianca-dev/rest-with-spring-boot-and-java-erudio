package br.com.lucasdev.rest_with_spring_boot_and_java_erudio.data.dto.v2;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@JacksonXmlRootElement(localName = "Person")
@JsonPropertyOrder({"id", "firstName", "lastName", "address", "gender"})
public class PersonDTOV2 extends RepresentationModel<PersonDTOV2> {

    @JsonProperty("id")
    private long personId;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;

    public PersonDTOV2() {
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTOV2 personDTOV2 = (PersonDTOV2) o;
        return personId == personDTOV2.personId && Objects.equals(firstName, personDTOV2.firstName) && Objects.equals(lastName, personDTOV2.lastName) && Objects.equals(address, personDTOV2.address) && Objects.equals(gender, personDTOV2.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, firstName, lastName, address, gender);
    }
}
