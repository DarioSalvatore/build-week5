//package app.runners;
//
//import java.util.List;
//import java.util.Locale;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import com.github.javafaker.Faker;
//
//import app.entities.Address;
//import app.entities.AddressType;
//import app.entities.Council;
//import app.entities.User;
//import app.entities.UserType;
//import app.repositories.AddressesRepository;
//import app.services.AddressesService;
//
//@Component
//public class AddressRunner implements CommandLineRunner {
//
//	@Autowired
//	AddressesService addressesService;
//
//	@Autowired
//	AddressesRepository addressesRepo;
//
//	Faker faker;
//
//	@Override
//	public void run(String... args) throws Exception {
//		Faker faker = new Faker(new Locale("it"));
//		List<Address> list = addressesRepo.findAll();
//
//		if (list.size() == 0) {
//			for (int i = 0; i < 10; i++) {
//				try {
//					String via = faker.address().streetName();
//					String civico = faker.address().buildingNumber();
//					String localita = faker.address().city();
//					int cap = faker.number().numberBetween(10000, 99999);
//					AddressType tipo = AddressType.SEDE_LEGALE;
//
////					User user = new User("ADA SRL", "03455411871", "ada@ada.it", 100000, "ada@pec.it", "+39098411111",
////							"ajeje@ada.it", "1234", "Ajeje", "Brazorv", "+393333333333", UserType.SRL, null, null);
////					Council comune = new Council("Sassari", null, null);
//
//					Address address = new Address(via, civico, localita, cap, tipo, user, comune);
//					addressesRepo.save(address);
//				} catch (Exception e) {
//					System.out.println(e);
//				}
//			}
//		}
//	}
//}