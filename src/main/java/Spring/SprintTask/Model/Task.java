package Spring.SprintTask.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private int status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Folder folder;

    public String getStatusStatus(){
        if (this.status == 0){
            return "TO DO";
        }else if (this.status == 1){
            return "IN TEST";
        }else if (this.status == 2){
            return "DONE";
        }else{
            return "FAILED";
        }
    }

}
