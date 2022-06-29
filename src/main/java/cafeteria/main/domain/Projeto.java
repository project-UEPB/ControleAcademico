package cafeteria.main.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "projeto")
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "descricao")
    private String descricao;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @JsonManagedReference
    @OneToMany(mappedBy = "projeto", fetch = FetchType.EAGER)
    private List<Aluno> alunos;
}
