package meta.com.br.challenge.api.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataConsulta;

    @Column
    private String repositorio;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
    private List<File> files;

    @PrePersist
    public void prePersist() {
        setDataConsulta(LocalDate.now());
    }

}
