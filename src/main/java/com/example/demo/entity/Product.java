package com.example.demo.entity;

import lombok.Data;
import org.seasar.doma.Entity;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Table;

@Entity(metamodel = @Metamodel)
@Data
@Table(name = "tbl_employee")
public class Product {

}
