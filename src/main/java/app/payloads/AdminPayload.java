package app.payloads;

import app.entities.UserType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminPayload {
	@NotNull(message = "ATTENZIONE!!! Il campo Email è obbligatorio")
	private String email;
	@NotNull(message = "ATTENZIONE!!! Il campo Password è obbligatorio")
	@Size(min = 3, max = 20, message = "ATTENZIONE!!! la password deve essere minimo di 8 caratteri e massimo di 20")
	private String password;
	@NotNull(message = "ATTENZIONE!!! Il campo Nome Contatto è obbligatorio")
	private String nomeContatto;
	@NotNull(message = "ATTENZIONE!!! Il campo Cognome Contatto è obbligatorio")
	private String cognomeContatto;
	@NotNull(message = "ATTENZIONE!!! Il campo Tipo è obbligatorio")
	private UserType tipo;
}
