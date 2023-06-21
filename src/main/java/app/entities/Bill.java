package app.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bills")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
	@Id
	@GeneratedValue
	private UUID id;
	private Integer year;
	private LocalDate date;
	private BigDecimal amount;
	private Integer number;
	@Enumerated(EnumType.STRING)
	private StatusBill statusBill;
	private static Integer lastGeneratedNumber = 0;

	@ManyToOne
	private User user;

	public Bill(Integer year, LocalDate date, BigDecimal amount, StatusBill statusBill, User user) {
		super();
		this.year = year;
		this.date = date;
		this.amount = amount;
		this.statusBill = statusBill;
		this.user = user;
		this.number = generateNextNumber();
	}

	private Integer generateNextNumber() {
		lastGeneratedNumber++; // Incrementa l'ultimo numero generato
		return lastGeneratedNumber;
	}
}
