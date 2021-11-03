package by.itacademy.javaenterprise.knyazev.entities;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
	private int id;
	private String name;
	private String description;
}