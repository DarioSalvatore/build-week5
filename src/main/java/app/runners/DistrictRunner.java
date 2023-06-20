package app.runners;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import app.services.DistrictService;

@Component
public class DistrictRunner implements CommandLineRunner {

	private final DistrictService districtService;

	public DistrictRunner(DistrictService districtService) {
		this.districtService = districtService;
	}

	@Override
	public void run(String... args) throws Exception {
		String filePath = "province-italiane.csv";
		districtService.importDistrictsFromCSV(filePath);

	}
}
