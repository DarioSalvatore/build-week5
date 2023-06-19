package app.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class Bill {
	@Id
	@GeneratedValue
	private UUID id;
	private Integer year;
	private Date date;
	private BigDecimal amount;
	private Integer number;
	@Enumerated(EnumType.STRING)
	private StatusBill statusBill;
	
	
	@ManyToOne
	private User user;

	public Bill(Integer year, Date date, BigDecimal amount, Integer number, StatusBill statusBill, User user) {
		super();
		this.year = year;
		this.date = date;
		this.amount = amount;
		this.number = number;
		this.statusBill = statusBill;
		this.user = user;
	}
	
	
}
