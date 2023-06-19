package app.entities;

import java.util.List;
import java.util.UUID;

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
	private String nome;
	private String sigla;
	private String Regione;
	@OneToMany(mappedBy = "provincia")
	private List<Council> comuni;

}
