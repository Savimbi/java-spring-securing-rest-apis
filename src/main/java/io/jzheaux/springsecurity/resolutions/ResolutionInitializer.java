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

		User hasRead = new User();
		hasRead.setId();
		hasRead.setUsername("hasread");
		hasRead.setFullName("Has Read");
		hasRead.grantAuthority("user:read");
		hasRead.setPassword(GENERAL_PASSWORD);
		hasRead.grantAuthority("resolution:read");
		this.users.save(hasRead);

		User hasWrite = new User();
		hasWrite.setId();
		hasWrite.setUsername("haswrite");
		hasWrite.setFullName("Has Write");
		hasWrite.grantAuthority("user:read");
		hasWrite.setPassword(GENERAL_PASSWORD);
		hasWrite.grantAuthority("resolution:write");
		hasWrite.addFriend(hasRead);
		hasWrite.setSubscription("premium");
		this.users.save(hasWrite);

		User admin = new User("admin",GENERAL_PASSWORD);
		admin.grantAuthority("ROLE_ADMIN");
		admin.setFullName("Admin Adminson");
		admin.grantAuthority("user:read");
		this.users.save(admin);

	}


}
