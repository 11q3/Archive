package Archive.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;
import java.util.Date;

@Entity
@Table(name = "image_table")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @Lob
    private Blob image;

    private Date date = new Date();

    public long getId() {
        return id;
    }

    public Blob getImage() {
        return image;
    }

    public Date getDate() {
        return date;
    }
}
