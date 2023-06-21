package app.entities;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
public class User implements UserDetails {
	@Id
	@GeneratedValue
	private UUID id;
	@Column(name = "ragione_sociale")
	private String ragioneSociale;
	@Column(name = "partita_iva")
	private String partitaIva;
	private String email;
	@Column(name = "data_inserimento")
	private LocalDate dataInserimento;
	@Column(name = "data_ultimo_contatto")
	private LocalDate dataUltimoContatto;
	@Column(name = "fatturato_annuale")
	private double fatturatoAnnuale;
	private String pec;
	private String telefono;
	@Column(name = "email_contatto")
	private String mailContatto;
	private String password;
	@Column(name = "nome_contatto")
	private String nomeContatto;
	@Column(name = "cognome_contatto")
	private String cognomeContatto;
	@Column(name = "telefono_contatto")
	private String telefonoContatto;
	@Enumerated(EnumType.STRING)
	private UserType tipo;
	@Enumerated(EnumType.STRING)
	private UserRole ruolo = UserRole.USER;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Address> indirizzi;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Bill> fatture;

	public User(String ragioneSociale, String partitaIva, String email, LocalDate dataInserimento,
			LocalDate dataUltimoContatto, double fatturatoAnnuale, String pec, String telefono, String mailContatto,
			String password, String nomeContatto, String cognomeContatto, String telefonoContatto, UserType tipo,
			List<Address> indirizzi, List<Bill> fatture) {
		this.ragioneSociale = ragioneSociale;
		this.partitaIva = partitaIva;
		this.email = email;
		this.dataInserimento = dataInserimento;
		this.dataUltimoContatto = dataUltimoContatto;
		this.fatturatoAnnuale = fatturatoAnnuale;
		this.pec = pec;
		this.telefono = telefono;
		this.mailContatto = mailContatto;
		this.password = password;
		this.nomeContatto = nomeContatto;
		this.cognomeContatto = cognomeContatto;
		this.telefonoContatto = telefonoContatto;
		this.tipo = tipo;
		this.indirizzi = indirizzi;
		this.fatture = fatture;
	}

	public User(String ragioneSociale, String partitaIva, String email, double fatturatoAnnuale, String pec,
			String telefono, String mailContatto, String password, String nomeContatto, String cognomeContatto,
			String telefonoContatto, UserType tipo, List<Address> indirizzi, List<Bill> fatture) {
		super();
		this.ragioneSociale = ragioneSociale;
		this.partitaIva = partitaIva;
		this.email = email;
		this.fatturatoAnnuale = fatturatoAnnuale;
		this.pec = pec;
		this.telefono = telefono;
		this.mailContatto = mailContatto;
		this.password = password;
		this.nomeContatto = nomeContatto;
		this.cognomeContatto = cognomeContatto;
		this.telefonoContatto = telefonoContatto;
		this.tipo = tipo;
		this.indirizzi = indirizzi;
		this.fatture = fatture;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(ruolo.name()));
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
