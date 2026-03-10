package users.infra.entity;


import jakarta.persistence.*;
import lombok.*;


@Builder
@Entity
@Table(name = "address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String street;

    @Column
    private Long number;

    @Column
    private String complement;

    @Column
    private String city;

    @Column
    private String neighborhood;

    @Column
    private String state;

    @Column(name = "zip_code")
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "email")
    private User user;

}
