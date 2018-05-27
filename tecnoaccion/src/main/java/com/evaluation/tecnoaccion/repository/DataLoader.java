package com.evaluation.tecnoaccion.repository;

import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.evaluation.tecnoaccion.model.Bet;
import com.evaluation.tecnoaccion.model.User;

@Component
public class DataLoader implements ApplicationRunner {
	private UserRepository userRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private static Integer MAX_INITIAL_DATA = 1000;  
	@Autowired
	public DataLoader(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		LOGGER.info("Carga de datos inicial");
		Long maxOid = this.userRepository.getMaxId();
		
		if(maxOid != 0){
			return;
		}
		for (int idx1 = 1; idx1 < MAX_INITIAL_DATA ; idx1++) {	
			Optional<User> userFing = userRepository.findById((long) idx1);

			if (!userFing.isPresent()) {
				User user = new User("email_" + idx1, "firstName_" + idx1, "lastName_" + idx1);
				Bet bet1 = new Bet(new BigDecimal(20), 1, user);
				Bet bet2 = new Bet(new BigDecimal(20), 1, user);
				Bet bet3 = new Bet(new BigDecimal(20), 1, user);

				List<Bet> betList = new ArrayList<Bet>();
				betList.add(bet1);
				betList.add(bet2);
				betList.add(bet3);
				user.setBetList(betList);

				userRepository.save(user);
			}

		}

	}
}
