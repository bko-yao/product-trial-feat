package com.alten.producttrialfeat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Version;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/*
 * Pour generaliser les attributs qui sont communs à toutes les entités.
 */
@MappedSuperclass
public class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @CreatedBy
    @Column(name = "audit_create_by", nullable = false, updatable = false)
    private String auditCreateBy;

    @JsonIgnore
    @LastModifiedBy
    @Column(name = "audit_update_by")
    private String auditUpdateBy;

    @JsonIgnore
    @CreatedDate
    @Column(name = "audit_create_at", nullable = false, updatable = false)
    private LocalDateTime auditCreateAt;

    @JsonIgnore
    @LastModifiedDate
    @Column(name = "audit_update_at")
    private LocalDateTime auditUpdateAt;

    /**
     * Version permet de définir le nombre de fois où l'occurrence a été modifiée.
     * La valeur s'incrémente à chaque modification.
     */
    @Version
    @Column(name = "version")
    private int version;

    /**
     * Execute avant chaque modification de l'entité
     */
    @PreUpdate
    public void onUpdate() {

        auditUpdateBy = "bkoffi";
        auditUpdateAt = LocalDateTime.now();
    }

    /**
     * Execute avant un nouvel enregistrement.
     */
    @PrePersist
    public void onSave() {
        auditCreateBy = "bkoffi";
        auditCreateAt = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public String getAuditCreateBy() {
        return auditCreateBy;
    }

    public String getAuditUpdateBy() {
        return auditUpdateBy;
    }

    public LocalDateTime getAuditCreateAt() {
        return auditCreateAt;
    }

    public LocalDateTime getAuditUpdateAt() {
        return auditUpdateAt;
    }

    public int getVersion() {
        return version;
    }
}
