package ecma.ai.codingbatapp.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StarBadge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private Integer value;//5 10 25

    @ManyToOne
    private ProgrammingLanguage language; //til ko'payishi mumkin
}
