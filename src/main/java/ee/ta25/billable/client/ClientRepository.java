// src/main/java/ee/ta25/billable/client/ClientRepository.java
package ee.ta25.billable.client;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

  List<Client> findByNameContainingIgnoreCaseOrderByName(String text);

  Optional<Client> findByEmail(String email);
}
