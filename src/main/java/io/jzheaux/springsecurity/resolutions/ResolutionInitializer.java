package io.jzheaux.springsecurity.resolutions;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ResolutionInitializer implements SmartInitializingSingleton {
	private final ResolutionRepository resolutions;
	private final UserRepository users;
	private static final String GENERAL_PASSWORD = "{bcrypt}$2a$10$LDI1pbUjS.flx7jUMM3J..uq5woM5SYGIIlb7PEGFIbN.wPgcK89K";

	public ResolutionInitializer(ResolutionRepository resolutions, UserRepository users) {
		this.resolutions = resolutions;
		this.users = users;
	}

	@Override
	public void afterSingletonsInstantiated() {
		this.resolutions.save(new Resolution("Read War and Peace", "user"));
		this.resolutions.save(new Resolution("Free Solo the Eiffel Tower", "user"));
		this.resolutions.save(new Resolution("Hang Christmas Lights", "user"));

		User user = new User("user",GENERAL_PASSWORD);
		user.setFullName("User Userson");
		user.grantAuthority("resolution:read");
		user.grantAuthority("resolution:write");
		user.grantAuthority("user:read");
		this.users.save(user);

		User hasread = new User();
		hasread.setId();
		hasread.setUsername("hasread");
		hasread.setFullName("Has Read");
		hasread.grantAuthority("user:read");
		hasread.setPassword(GENERAL_PASSWORD);
		hasread.grantAuthority("resolution:read");
		this.users.save(hasread);

		User haswrite = new User();
		haswrite.setId();
		haswrite.setUsername("haswrite");
		haswrite.setFullName("Has Write");
		haswrite.grantAuthority("user:read");
		haswrite.setPassword(GENERAL_PASSWORD);
		haswrite.grantAuthority("resolution:write");
		this.users.save(haswrite);

		User admin = new User("admin",GENERAL_PASSWORD);
		admin.grantAuthority("ROLE_ADMIN");
		admin.setFullName("Admin Adminson");
		admin.grantAuthority("user:read");
		this.users.save(admin);

	}


}
