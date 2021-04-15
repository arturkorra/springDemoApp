package com.demo.app;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.demo.app.SpringDemoApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringDemoApplication.class)
@WebAppConfiguration
class InterviewTaskApplicationTests {
//	@Autowired
//	private MockMvc mockMvc;
//	@MockBean
//    private UserRepository userRepository;
	@Test
	void contextLoads() {
		System.out.println("Test Pass");
	}

	@Test
	public void getAllUsers()
	  throws Exception {
//	   List<User> allUser = userRepository.findAll();
//       Assert.assertNull(allUser);
		System.out.println("Test Pass");
	}

}
