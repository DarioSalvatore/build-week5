package app.entities;

import java.util.List;
import java.util.UUID;

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
	private List<Council> comuni;

}
