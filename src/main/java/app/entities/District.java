package app.entities;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "districts")
@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties({ "comuni" })
public class District {
	@Id
	@GeneratedValue
	private UUID id;
	@Column(name = "Provincia")
	private String provincia;
	@Column(name = "Sigla")
	private String sigla;
	@Column(name = "Regione")
	private String regione;
	@OneToMany(mappedBy = "provincia")
//	@JsonManagedReference
	private List<Council> comuni;

	public District(String provincia, String sigla, String regione, List<Council> comuni) {
		super();
		this.provincia = provincia;
		this.sigla = sigla;
		this.regione = regione;
		this.comuni = comuni;
	}

	public District(String sigla, String provincia, String regione) {
		this.provincia = provincia;
		this.sigla = sigla;
		this.regione = regione;
	}

	public District(String provincia) {
		this.provincia = provincia;
	}

	@Override
	public String toString() {
		return "District [id=" + id + ", provincia=" + provincia + ", sigla=" + sigla + ", regione=" + regione
				+ ", comuni=" + comuni + "]";
	}

}
