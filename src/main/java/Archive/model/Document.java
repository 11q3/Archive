package Archive.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name="document_id")
    private Long id;

    private String name;

    private String filePath;

    public void setPath(String string) {
    }
}
