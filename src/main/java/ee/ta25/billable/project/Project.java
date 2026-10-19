package ee.ta25.billable.project;

import ee.ta25.billable.client.Client;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "projects")
public class Project {

  private static final Set<Integer> ALLOWED_ROUNDING = Set.of(1, 6, 15);

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "client_id", nullable = false)
  private Client client;

  @Column(nullable = false, length = 120)
  private String name;

  @Enumerated(EnumType.STRING)
  @Column(name = "billing_type", nullable = false, length = 20)
  private BillingType billingType;

  @Column(name = "hourly_rate_cents")
  private Long hourlyRateCents;

  @Column(name = "fixed_price_cents")
  private Long fixedPriceCents;

  @Column(name = "budget_cap_cents")
  private Long budgetCapCents;

  @Column(name = "rounding_minutes", nullable = false)
  private short roundingMinutes = 1;

  @Column(name = "archived_at")
  private LocalDateTime archivedAt;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  protected Project() {}

  public Project(Client client, String name, BillingType billingType, int roundingMinutes) {
    this.client = client;
    this.name = name;
    this.billingType = billingType;
    setRoundingMinutes(roundingMinutes);
  }

  public Long getId() {
    return id;
  }

  /** Lazy: only the id is available without a query. */
  public Client getClient() {
    return client;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BillingType getBillingType() {
    return billingType;
  }

  public void setBillingType(BillingType billingType) {
    this.billingType = billingType;
  }

  public Long getHourlyRateCents() {
    return hourlyRateCents;
  }

  public void setHourlyRateCents(Long hourlyRateCents) {
    this.hourlyRateCents = hourlyRateCents;
  }

  public Long getFixedPriceCents() {
    return fixedPriceCents;
  }

  public void setFixedPriceCents(Long fixedPriceCents) {
    this.fixedPriceCents = fixedPriceCents;
  }

  public Long getBudgetCapCents() {
    return budgetCapCents;
  }

  public void setBudgetCapCents(Long budgetCapCents) {
    this.budgetCapCents = budgetCapCents;
  }

  public int getRoundingMinutes() {
    return roundingMinutes;
  }

  public void setRoundingMinutes(int roundingMinutes) {
    if (!ALLOWED_ROUNDING.contains(roundingMinutes)) {
      throw new IllegalArgumentException("Rounding must be 1, 6 or 15 minutes: " + roundingMinutes);
    }
    this.roundingMinutes = (short) roundingMinutes;
  }

  public LocalDateTime getArchivedAt() {
    return archivedAt;
  }

  public boolean isArchived() {
    return archivedAt != null;
  }

  public void archive(LocalDateTime when) {
    this.archivedAt = when;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }
}
