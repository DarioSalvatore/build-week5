package app.payloads;

import java.math.BigDecimal;
import java.time.LocalDate;

import app.entities.StatusBill;
import app.entities.User;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class BillPayload {
	@NotNull(message = "ATTENZIONE!!! Il campo Year è obbligatorio")
	private Integer year;
	@NotNull(message = "ATTENZIONE!!! Il campo Date è obbligatorio")
	private LocalDate date;
	@NotNull(message = "ATTENZIONE!!! Il campo Amount è obbligatorio")
	private BigDecimal amount;
	@NotNull(message = "ATTENZIONE!!! Il campo Number Annuale è obbligatorio")
	private Integer number;
	@NotNull(message = "ATTENZIONE!!! Il campo Status Bill è obbligatorio")
	StatusBill statusBill;
//	@NotNull(message = "ATTENZIONE!!! Il campo User è obbligatorio")
	User user;
}
