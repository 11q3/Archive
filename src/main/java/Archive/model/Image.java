package Archive.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "imageData")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    @Lob
    @Column(name = "image_data", length = 1000)
    private byte[] imageData;

    public static byte[] compressImage(byte[] bytes) {
        return null;
    }

    public static byte[] decompressImage(byte[] imageData) {
        return null;
    }
}
