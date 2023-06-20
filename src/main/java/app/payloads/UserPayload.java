package app.payloads;

import app.entities.UserType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPayload {
	@NotNull(message = "ATTENZIONE!!! Il campo Ragione Sociale è obbligatorio")
	private String ragioneSociale;
	@NotNull(message = "ATTENZIONE!!! Il campo partita Iva è obbligatorio")
	private String partitaIva;
	@NotNull(message = "ATTENZIONE!!! Il campo Email è obbligatorio")
	private String email;
	@NotNull(message = "ATTENZIONE!!! Il campo Fatturato Annuale è obbligatorio")
	private double fatturatoAnnuale;
	@NotNull(message = "ATTENZIONE!!! Il campo Pec è obbligatorio")
	private String pec;
	@NotNull(message = "ATTENZIONE!!! Il campo Telefono è obbligatorio")
	private String telefono;
	@NotNull(message = "ATTENZIONE!!! Il campo Email Contatto è obbligatorio")
	private String mailContatto;
	@NotNull(message = "ATTENZIONE!!! Il campo Password è obbligatorio")
	@Size(min = 3, max = 20, message = "ATTENZIONE!!! la password deve essere minimo di 8 caratteri e massimo di 20")
	private String password;
	@NotNull(message = "ATTENZIONE!!! Il campo Nome Contatto è obbligatorio")
	private String nomeContatto;
	@NotNull(message = "ATTENZIONE!!! Il campo Cognome Contatto è obbligatorio")
	private String cognomeContatto;
	@NotNull(message = "ATTENZIONE!!! Il campo Telefono Contatto è obbligatorio")
	private String telefonoContatto;
	@NotNull(message = "ATTENZIONE!!! Il campo Tipo è obbligatorio")
	private UserType tipo;

}
