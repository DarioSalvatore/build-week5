package app.payloads;

import app.entities.AddressType;
import app.entities.Council;
import app.entities.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AddressPayload {
	@NotNull(message = "La via è obbligatoria")
	private String via;
	@NotNull(message = "Il civico è obbligatorio")
	private String civico;
	private String località;
	@NotNull(message = "Il CAP è obbligatorio")
	private int cap;
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Il tipo è obbligatorio")
	private AddressType tipo;
	@NotNull(message = "L'utente è obbligatorio")
	private User user;
	@NotNull(message = "Il Comune è obbligatorio")
	private Council comune;

}
