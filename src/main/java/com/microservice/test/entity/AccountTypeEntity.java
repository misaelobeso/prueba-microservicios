package com.microservice.test.entity;

import com.microservice.test.constant.GenericConstant;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity(name = GenericConstant.ENTITY_ACCOUNTTYPE)
@Data
public class AccountTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Type(type = GenericConstant.DEFAULT_NUMERIC_BOOLEAN_TYPE)
    private Boolean state;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date created_at;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updated_at;
}
