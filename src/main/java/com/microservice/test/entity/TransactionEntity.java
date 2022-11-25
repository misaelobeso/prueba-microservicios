package com.microservice.test.entity;

import com.microservice.test.constant.GenericConstant;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity(name = GenericConstant.ENTITY_TRANSACTION)
@Data
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer idTransactionType;

    private Integer idAccount;

    private Integer value;

    private Integer currentBalance;

    @Type(type = GenericConstant.DEFAULT_NUMERIC_BOOLEAN_TYPE)
    private Boolean state;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name="create_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name="updated_at")
    private Date updatedAt;
}
