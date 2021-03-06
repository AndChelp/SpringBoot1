package first.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table(name = "users")
@Data
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "firstname")
    @Size(min = 2, max = 50)
    @NotNull
    private String firstName;

    @Column(name = "lastname")
    @Size(min = 2, max = 50)
    @NotNull
    private String lastName;

    @Column(name = "age")
    @Min(14)
    @Max(150)
    @NotNull
    private short age;

    @Column(name = "haspremium")
    private boolean hasPremium;

    @Column(name = "lastauthorization")
    @NotNull
    private Date lastAuthorization;

}
