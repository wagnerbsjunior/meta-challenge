package meta.com.br.challenge.api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dataId;
    private String title;
    private String name;
    private String extension;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int count;
    private int lines;

    @ManyToOne
    private Request request;
}
