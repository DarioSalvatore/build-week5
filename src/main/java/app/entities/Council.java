package app.entities;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	private String codiceStorico;
	private String progressivoComune;
	private String denominazione;
	@ManyToOne
	@JsonBackReference
	private District provincia;

	@OneToMany(mappedBy = "comune")
	@JsonManagedReference
	private List<Address> indirizzi;

	public Council(String codiceStorico, String progressivoComune, String denominazione, District provincia,
			List<Address> indirizzi) {
		super();
		this.codiceStorico = codiceStorico;
		this.progressivoComune = progressivoComune;
		this.denominazione = denominazione;
		this.provincia = provincia;
		this.indirizzi = indirizzi;
	}

	public Council(String codiceStorico, String progressivoComune, String denominazione, District provincia) {
		super();
		this.codiceStorico = codiceStorico;
		this.progressivoComune = progressivoComune;
		this.denominazione = denominazione;
		this.provincia = provincia;
	}

	public Council(String denominazione) {
		this.denominazione = denominazione;
	}
}
