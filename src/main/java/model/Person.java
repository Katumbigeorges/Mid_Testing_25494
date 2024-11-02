package model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import enums.EGender;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@MappedSuperclass
@Getter
@Setter
public abstract class Person {
	@Id
	@GeneratedValue
	public UUID person_id = UUID.randomUUID();
	@Column(name = "first_name")
	public String first_name;
	@Column(name = "last_name")
	public String last_name;
	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	public EGender gender;
	@Column(name = "phone_number")
	public String phone_number;
}
