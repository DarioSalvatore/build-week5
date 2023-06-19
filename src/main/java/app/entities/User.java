package app.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue
	private UUID id;
	private String ragioneSociale;
	private Long partitaIva;
	private String email;
	private LocalDate dataInserimento;
	private LocalDate dataUltimoContatto;
	private double fatturatoAnnuale;
	private String pec;
	private String telefono;
	private String mailContatto;
	private String nomeContatto;
	private String cognomeContatto;
	private String telefonoContatto;
	@Enumerated(EnumType.STRING)
	private UserType tipo;
	@Enumerated(EnumType.STRING)
	private UserRole ruolo = UserRole.USER;
	@OneToMany(mappedBy = "user")
	private List<Address> indirizzi;
	@OneToMany(mappedBy = "user")
	private List<Bill> fatture;

	public User(String ragioneSociale, Long partitaIva, String email, LocalDate dataInserimento,
			LocalDate dataUltimoContatto, double fatturatoAnnuale, String pec, String telefono, String mailContatto,
			String nomeContatto, String cognomeContatto, String telefonoContatto, UserType tipo,
			List<Address> indirizzi, List<Bill> fatture) {
		super();
		this.ragioneSociale = ragioneSociale;
		this.partitaIva = partitaIva;
		this.email = email;
		this.dataInserimento = dataInserimento;
		this.dataUltimoContatto = dataUltimoContatto;
		this.fatturatoAnnuale = fatturatoAnnuale;
		this.pec = pec;
		this.telefono = telefono;
		this.mailContatto = mailContatto;
		this.nomeContatto = nomeContatto;
		this.cognomeContatto = cognomeContatto;
		this.telefonoContatto = telefonoContatto;
		this.tipo = tipo;
		this.indirizzi = indirizzi;
		this.fatture = fatture;
	}

}
