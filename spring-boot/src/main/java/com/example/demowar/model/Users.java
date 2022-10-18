package com.example.demowar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.transaction.Transactional;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString
@Table(name="USERS")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator ="USERS")
	@TableGenerator( name="USERS",  allocationSize=1 )
	@Column(name = "ID")
	private Long id;

	private String name;

	private String teamName;

	private Integer salary;

}
