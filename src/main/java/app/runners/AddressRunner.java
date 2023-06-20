package app.runners;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import app.entities.Address;
import app.entities.AddressType;
import app.entities.Council;
import app.entities.User;
import app.entities.UserType;
import app.repositories.AddressesRepository;
import app.services.AddressesService;

//@Component
//public class AddressRunner implements CommandLineRunner {
//	@Autowired
//	AddressesService addressesService;
//	@Autowired
//	Faker faker;
//
//	@Override
//	public void run(String... args) throws Exception {
//
//	}
//}
