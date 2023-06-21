package app.runners;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import app.entities.Address;
import app.entities.Bill;
import app.entities.User;
import app.entities.UserType;
import app.repositories.UserRepository;

@Component
//@Order(1)
public class UserRunner implements CommandLineRunner {
	@Autowired
	UserRepository userRepo;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));
		Random random = new Random();

		List<User> users = userRepo.findAll();

		if (users.size() == 0) {
			for (int i = 0; i < 10; i++) {
				try {
					String ragioneSociale = faker.company().name();
					String partitaIva = faker.number().digits(11);
					String email = faker.internet().emailAddress();
					LocalDate dataInserimento = faker.date().birthday().toInstant().atZone(ZoneId.systemDefault())
							.toLocalDate();
					LocalDate dataUltimoContatto = faker.date().birthday().toInstant().atZone(ZoneId.systemDefault())
							.toLocalDate();
					double scalingFactor = 100.0;
					double fatturatoAnnuale = scalingFactor*random.nextDouble()+100;
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

					User user = new User(ragioneSociale, partitaIva, email, dataInserimento, dataUltimoContatto,
							fatturatoAnnuale, pec, telefono, mailContatto, password, nomeContatto, cognomeContatto,
							telefonoContatto, tipo, indirizzi, fatture);
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
