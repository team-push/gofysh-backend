package com.gofysh.gofyshbackend.user.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by myounghoshin on 4/29/17.
 */
@Entity
@Table(name = "privilege")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Privilege implements Serializable {

	private static final long serialVersionUID = 3132510652498386426L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String privilege;

	@ManyToMany(mappedBy = "privileges")
	private List<Role> roles;

}
