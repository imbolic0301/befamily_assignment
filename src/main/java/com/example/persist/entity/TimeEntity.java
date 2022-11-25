package com.example.persist.entity;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@ToString
public class TimeEntity {

    @CreatedDate
    @Column(name = "created_at", nullable = true, updatable = false)
    private Long createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = true)
    private Long updatedAt;

}