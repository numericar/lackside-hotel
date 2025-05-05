package com.dailycodework.lackside.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "rooms")
public class Room {
    private Long id;
}
