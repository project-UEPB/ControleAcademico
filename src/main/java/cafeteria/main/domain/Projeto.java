package cafeteria.main.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "projetos")
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "descricao")
    private String descricao;

    // @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "professor_id", nullable = true)
    private Professor professor;

    // @JsonManagedReference
    // @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    @JsonIgnore
    @OneToMany(mappedBy = "projeto")
    private List<Aluno> alunos;

    public Projeto(String name, String descricao) {
        this.name = name;
        this.descricao = descricao;
    }

    public void setAluno(Aluno aluno) {
    }
}
