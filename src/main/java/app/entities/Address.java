package app.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "addresses")
public class Address {
	@Id
	@GeneratedValue
	private UUID id;
	private String via;
	private String civico;
	private String località;
	private int cap;
	@Enumerated(EnumType.STRING)
	private AddressType tipo;
	@ManyToOne
	@JsonBackReference
	private User user;
	@ManyToOne
	@JsonBackReference
	private Council comune;

	public Address(String via, String civico, String località, int cap, AddressType tipo, User user, Council comune) {
		super();
		this.via = via;
		this.civico = civico;
		this.località = località;
		this.cap = cap;
		this.tipo = tipo;
		this.user = user;
		this.comune = comune;
	}

}
