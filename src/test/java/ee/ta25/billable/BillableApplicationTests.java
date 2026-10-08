// src/test/java/ee/ta25/billable/BillableApplicationTests.java
package ee.ta25.billable;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
class BillableApplicationTests {

	@Test
	void contextLoads() {}
}