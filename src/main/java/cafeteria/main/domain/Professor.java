package cafeteria.main.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "professores")
public class Professor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "matricula", unique = true)
    private String matricula;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "formacao")
    private String formacao;

    // @OneToOne
    // @JoinColumn(name = "projeto_id")
    // // @RestResource(path = "professorProjeto", rel="projeto")
    // private Projeto projeto;

    @JsonManagedReference
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Turma> turma = new ArrayList<>();

    public Professor(String name, String matricula, String email, String formacao) {
        this.name = name;
        this.matricula = matricula;
        this.email = email;
        this.formacao = formacao;
    }
}