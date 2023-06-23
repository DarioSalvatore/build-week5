package app.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bills")
@Setter
@Getter
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
	@JsonBackReference
	private User user;

	public Bill(Integer year, LocalDate date, BigDecimal amount, StatusBill statusBill, User user) {
		super();
		this.year = year;
		this.date = date;
		this.amount = amount;
		this.number = generateNextNumber();
		this.statusBill = statusBill;
		this.user = user;
	}

	public Bill(Integer year, LocalDate date, BigDecimal amount, StatusBill statusBill) {
		super();
		this.year = year;
		this.date = date;
		this.amount = amount;
		this.number = generateNextNumber();
		this.statusBill = statusBill;
	}

	private Integer generateNextNumber() {
		lastGeneratedNumber++;
		return lastGeneratedNumber;
	}
}
