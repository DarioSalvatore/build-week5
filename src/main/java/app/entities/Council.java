package app.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "councils")
@Setter
@Getter
@NoArgsConstructor
public class Council {
	@Id
	@GeneratedValue
	private UUID id;
	private String nome;
	@ManyToOne
	private District provincia;

	@OneToMany(mappedBy = "comune")
	private List<Address> indirizzi;

	public Council(String nome, District provincia, List<Address> indirizzi) {
		super();
		this.nome = nome;
		this.provincia = provincia;
		this.indirizzi = indirizzi;
	}

}
