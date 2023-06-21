package app.runners;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import app.entities.Bill;
import app.entities.StatusBill;
import app.entities.User;
import app.repositories.UserRepository;
import app.services.BillService;

@Component
public class BillRunner implements CommandLineRunner {

	@Autowired
	BillService billService;

	@Autowired
	UserRepository userRepo;

//	@Override
//	public void run(String... args) throws Exception {
//		List<User> users = userRepo.findAll();
//		Faker faker = new Faker(new Locale("it"));
//		Random random = new Random();
//		for (int i = 0; i < 50; i++) {
//			try {
//				Integer year = faker.number().numberBetween(1900, 2023);
//				LocalDate date = faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//				BigDecimal amount = new BigDecimal(faker.number().randomDouble(2, 0, 1000)); // da 0 a 1000 con 2 cifre
//																								// decimali
//				Integer number = random.nextInt();
//				StatusBill statusBill = faker.options().nextElement(StatusBill.values());
//
//				User user = new User();
//
//				Bill bill = new Bill(year, date, amount, number, statusBill, user);
//				billService.create(bill);
//
//			} catch (Exception e) {
//				System.out.println(e);
//			}
//		}
//
//	}
	@Override
	public void run(String... args) throws Exception {
		List<User> users = userRepo.findAll();
		Faker faker = new Faker(new Locale("it"));
		Random random = new Random();

		if (billService.count() == 0) {
			for (User user : users) {
				try {
					int numberOfBills = random.nextInt(10) + 1; // Genera un numero casuale compreso tra 1 e 10

					for (int i = 0; i < numberOfBills; i++) {
						Integer year = faker.number().numberBetween(1900, 2023);
						LocalDate date = faker.date().birthday().toInstant().atZone(ZoneId.systemDefault())
								.toLocalDate();
						BigDecimal amount = new BigDecimal(faker.number().randomDouble(2, 0, 1000));
//						Integer number = random.nextInt();
						StatusBill statusBill = faker.options().nextElement(StatusBill.values());

						Bill bill = new Bill(year, date, amount, statusBill, user);
						billService.create(bill);
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		} else {
			System.out.println("Sono già presenti delle fatture nel database.");
		}
	}

}
