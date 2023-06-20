package app.runners;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import app.services.CouncilService;

@Component
public class CouncilRunner implements CommandLineRunner {

	private final CouncilService councilService;

	public CouncilRunner(CouncilService councilService) {
		this.councilService = councilService;
	}

	@Override
	public void run(String... args) throws Exception {
		String filePath = "comuni-italiani.csv";
		councilService.importCouncilsFromCSV(filePath);

	}
}
