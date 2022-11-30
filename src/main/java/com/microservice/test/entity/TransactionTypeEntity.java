package com.microservice.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.microservice.test.constant.GenericConstant;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@Entity(name = GenericConstant.ENTITY_TRANSACTIONTYPE)
public class TransactionTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type;

    @Type(type = GenericConstant.DEFAULT_NUMERIC_BOOLEAN_TYPE)
    private Boolean state;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date created_at;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updated_at;
}
