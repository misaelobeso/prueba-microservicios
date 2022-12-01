package com.microservice.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.microservice.test.constant.GenericConstant;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity(name = GenericConstant.ENTITY_TRANSACTION)
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "id", nullable = false, insertable = false, updatable = false)
    private TransactionTypeEntity transactionTypeEntity;

    private Integer idTransactionType;

    private Integer idAccount;

    private Integer value;

    private Integer currentBalance;

    @Type(type = GenericConstant.DEFAULT_NUMERIC_BOOLEAN_TYPE)
    private Boolean state;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name="created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name="updated_at")
    private Date updatedAt;
}
