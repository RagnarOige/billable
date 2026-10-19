// src/main/java/ee/ta25/billable/project/ClientProjectCount.java
package ee.ta25.billable.project;

/**
 * One row of {@link ProjectRepository#countByClientIds}: a client id and its number of projects.
 */
public interface ClientProjectCount {

  Long getClientId();

  long getProjectCount();
}
