package app.runners;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import app.entities.Address;
import app.entities.Bill;
import app.entities.User;
import app.entities.UserType;
import app.repositories.UserRepository;

@Component
public class UserRunner implements CommandLineRunner {
	@Value("${adminPW}")
	private String adminPW;
	private String adminEmail = "ajeje@google.com";
	@Autowired
	UserRepository userRepo;
	@Autowired
	PasswordEncoder bcrypt;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));
		Random random = new Random();
		int randomNumber = random.nextInt(60);
		List<User> users = userRepo.findAll();

		User existingAdmin = userRepo.findByEmail(adminEmail).orElse(null);
		if (existingAdmin == null) {
			String encryptedAdminPW = bcrypt.encode(adminPW);
			User admin = new User(adminEmail, encryptedAdminPW, "Ajeje", "Brazorv");
			userRepo.save(admin);
		}

		if (users.size() == 0) {
			for (int i = 0; i < 10; i++) {
				try {
					String ragioneSociale = faker.company().name();
					String partitaIva = faker.number().digits(11);
					String email = faker.internet().emailAddress();
					LocalDate dataInserimento = LocalDate.now();
					LocalDate dataUltimoContatto = dataInserimento.plusDays(i + randomNumber);
					int scalingFactor = 100;
					double fatturatoAnnuale = scalingFactor * random.nextDouble() + 100;
					String pec = faker.internet().emailAddress();
					String telefono = faker.phoneNumber().phoneNumber();
					String mailContatto = faker.internet().emailAddress();
					String password = faker.internet().password();
					String nomeContatto = faker.name().firstName();
					String cognomeContatto = faker.name().lastName();
					String telefonoContatto = faker.phoneNumber().phoneNumber();
					UserType tipo = getRandomEnumValue(UserType.class);

					List<Address> indirizzi = new ArrayList<>();
					List<Bill> fatture = new ArrayList<>();
					String encryptedPassword = bcrypt.encode(password);

					User user = new User(ragioneSociale, partitaIva, email, dataInserimento, dataUltimoContatto,
							fatturatoAnnuale, pec, telefono, mailContatto, encryptedPassword, nomeContatto,
							cognomeContatto, telefonoContatto, tipo, indirizzi, fatture);
					userRepo.save(user);
				} catch (Exception e) {
					System.out.println(e);
				}
			}

		}
	}

	public static <T extends Enum<?>> T getRandomEnumValue(Class<T> enumClass) {
		T[] values = enumClass.getEnumConstants();
		Random random = new Random();
		int index = random.nextInt(values.length);
		return values[index];
	}

}
