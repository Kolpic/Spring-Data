package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "magic_wand")
public class MagicWand {

    @Id
    @Column
    private long id;
    @Column(length = 100)
    private String creator;
    @Column
    private long size;

}
