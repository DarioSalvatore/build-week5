package app.entities;

import java.util.UUID;

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
	private String comune;
	@Enumerated(EnumType.STRING)
	private AddressType tipo;
	@ManyToOne
	private User user;

	public Address(String via, String civico, String località, int cap, String comune, AddressType tipo, User user) {
		super();
		this.via = via;
		this.civico = civico;
		this.località = località;
		this.cap = cap;
		this.comune = comune;
		this.tipo = tipo;
		this.user = user;
	}

}
