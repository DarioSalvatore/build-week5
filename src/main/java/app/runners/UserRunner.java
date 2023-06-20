//package app.runners;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
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
//import app.entities.Bill;
//import app.entities.User;
//import app.entities.UserRole;
//import app.entities.UserType;
//import app.repositories.UserRepository;
//import jakarta.persistence.Column;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.OneToMany;
//
//@Component
////@Order(1)
//public class UserRunner implements CommandLineRunner {
//	@Autowired
//	UserRepository userRepo;
//
//	@Override
//	public void run(String... args) throws Exception {
//		Faker faker = new Faker(new Locale("it"));
//		Random random = new Random();
//
//		List<User> users = userRepo.findAll();
//
//		if (users.size() == 0) {
//			for (int i = 0; i < 10; i++) {
//				try {
//					
//					String ragioneSociale = faker.company().name();
//					
//					Long partitaIva;
//					String email = faker.internet().emailAddress();
//					
//
//					
//					double fatturatoAnnuale = random.nextDouble();
//					String pec;
//					String telefono;
//					
//					String mailContatto;
//					String password;
//					
//					String nomeContatto;
//					
//					String cognomeContatto;
//					
//					String telefonoContatto;
//					@Enumerated(EnumType.STRING)
//					UserType tipo;
//					@Enumerated(EnumType.STRING)
//					UserRole ruolo = UserRole.USER;
//					
//					private List<Address> indirizzi;
//					
//					private List<Bill> fatture;
//					
//					String ragioneSociale = faker.company().name();
//					String
//					String cognome = faker.name().lastName();
//					String email = faker.internet().emailAddress();
//					String username = faker.name().username();
//					String password = faker.internet().password();
//					String creditCard = faker.finance().creditCard();
//					List<Bill> dispositivi = new ArrayList<>();
//
//					User user = new User(nome, cognome, email, username, password, creditCard, dispositivi, ruolo);
//					userRepo.save(user);
//				} catch (Exception e) {
//					System.out.println(e);
//				}
//			}
//		}
//
//	}
//
//	public static String generateRandomPecAddress(String email) {
//		// Estrai il dominio dall'indirizzo email fornito
//		int atIndex = email.indexOf('@');
//		String domain = email.substring(atIndex + 1);
//
//		// Genera l'indirizzo PEC casuale
//		String pecAddress = faker.name().username() + "@" + domain;
//		return pecAddress;
//	}
//
//}
