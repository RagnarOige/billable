// src/main/java/ee/ta25/billable/project/ProjectRepository.java
package ee.ta25.billable.project;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

  long countByClientId(Long clientId);

  List<Project> findByClientIdOrderByName(Long clientId);
}
