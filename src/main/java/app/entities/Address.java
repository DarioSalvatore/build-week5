package app.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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
	@NotNull
	private String via;
	@NotNull
	private String civico;
	private String località;
	@NotNull
	private int cap;
	@NotNull
	private String comune;
	@Enumerated(EnumType.STRING)
	@NotNull
	private AddressType tipo;
	@ManyToOne
	private Client client;

	public Address(String via, String civico, String località, int cap, String comune, AddressType tipo) {
		super();
		this.via = via;
		this.civico = civico;
		this.località = località;
		this.cap = cap;
		this.comune = comune;
		this.tipo = tipo;
	}

}
