package com.bilalkose.springrediscache.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Product") //Redis Data Store'una Product ismiyle Hashleyip kaydedecek
public class Product implements Serializable {
    @Id
    private Long id;
    private String name;
    private int qty;
    private long price;
}
