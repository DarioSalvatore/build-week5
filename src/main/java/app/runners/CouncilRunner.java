//package app.runners;
//
//import java.util.List;
//import java.util.Locale;
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import com.github.javafaker.Address;
//import com.github.javafaker.Faker;
//import com.github.javafaker.Name;
//
//import app.entities.Council;
//import app.repositories.CouncilRepository;
//
//@Component
//public class CouncilRunner implements CommandLineRunner {
//
//	@Autowired
//	private CouncilRepository councilRepo;
//
//	@Override
//	public void run(String... args) throws Exception {
//		Faker faker = new Faker(new Locale("it"));
//		
//		List<Council> councils = councilRepo.findAll();
//		
//		if(councils.size() == 0) {
//			for(int i = 0; i < 5; i++) {
//				try {
//					String nome = faker.address().cityName();
//					District provincia = new District();
//					int codiceStorico = faker.number();
//					
//					
//					
//					
//				}catch (Exception e) {
//                    System.out.println(e);
//			}
//		}
//	}
//}
//}
