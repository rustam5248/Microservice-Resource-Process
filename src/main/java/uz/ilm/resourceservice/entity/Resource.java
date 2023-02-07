package uz.ilm.resourceservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="resource")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Resource {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name="file_name")
  private String fileName;

 /* public Resource(String fileName) {

  }*/
}
