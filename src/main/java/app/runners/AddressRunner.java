package app.runners;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import app.entities.Address;
import app.entities.AddressType;
import app.entities.Council;
import app.entities.User;
import app.repositories.AddressesRepository;
import app.repositories.CouncilRepository;
import app.repositories.UserRepository;
import app.services.AddressesService;

@Component
public class AddressRunner implements CommandLineRunner {

	@Autowired
	AddressesService addressesService;

	@Autowired
	AddressesRepository addressesRepo;

	@Autowired
	CouncilRepository councilRepo;

	@Autowired
	UserRepository userRepo;

	Faker faker;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));
		List<Address> list = addressesRepo.findAll();
		List<Council> councilList = councilRepo.findAll();
		List<User> userList = userRepo.findAll();
		Random random = new Random();

		if (list.size() == 0) {

			for (int i = 0; i < 10; i++) {
				try {
					String via = faker.address().streetName();
					String civico = faker.address().buildingNumber();
					String localita = faker.address().city();
					int cap = faker.number().numberBetween(10000, 99999);
					AddressType tipo = AddressType.SEDE_LEGALE;
					User randomUser = userList.get(random.nextInt(userList.size()));
					Council randomCouncil = councilList.get(random.nextInt(councilList.size()));
					Address address = new Address(via, civico, localita, cap, tipo, randomUser, randomCouncil);
					addressesRepo.save(address);
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
	}
}