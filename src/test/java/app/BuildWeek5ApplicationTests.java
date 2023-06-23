package app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class BuildWeek5ApplicationTests {

	String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhamVqZUBnb29nbGUuY29tIiwiaWF0IjoxNjg3NTI1MzM1LCJleHAiOjE2ODgxMzAxMzV9.WDjyAM75doxqgSFTfHlSy5aEzU4bIRUEtTWqdJJoIdg";
	String billId = "ede3edcb-99f8-4578-b8a4-dc60be7b4d8f";
	String userId = "5df7afad-93b9-4a6a-85c2-2c27aa510dc1";
	String deletedBillId = "3b88928c-abf3-4da1-b2fb-f8d06d244e22";

	@Autowired
	private MockMvc mockMvc;

	// GET SU USERS
	@Test
	void testGetUsers() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.get("/users").header("Authorization", "Bearer " + token))
				.andExpect(status().isOk());
	}

	// GET SU SINGOLO UTENTE BY ID
	@Test
	void testGetUserById() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.get("/users/5df7afad-93b9-4a6a-85c2-2c27aa510dc1")
				.header("Authorization", "Bearer " + token)).andExpect(status().isOk());
	}

	// GET SU FATTURE USANDO LA PAGINAZIONE
	@Test
	void testGetBillsWithPagination() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:3001/bills?page=0&size=20")
				.header("Authorization", "Bearer " + token)).andExpect(status().isOk());
	}

	// GET SU FATTURE DEL SINGOLO UTENTE
	@Test
	void testGetUserBills() throws Exception {

		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/users/{userId}/bills", userId).header("Authorization",
						"Bearer " + token))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	// PUT SU FATTURA
	@Test
	void testUpdateBill() throws Exception {
		String requestBody = "{\"amount\": 100.0}";

		this.mockMvc
				.perform(
						MockMvcRequestBuilders.put("/bills/{billId}", billId).header("Authorization", "Bearer " + token)
								.contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	// DELETE SU FATTURA
	@Test
	void testDeleteBillById() throws Exception {
		this.mockMvc.perform(delete("/bills/{billId}", deletedBillId).header("Authorization", "Bearer " + token))
				.andExpect(status().isNoContent());
	}

}
