package org.sqli.pfe.enset.models.entities;

import lombok.*;
import org.sqli.pfe.enset.utils.enums.LogPathEnum;
import org.sqli.pfe.enset.utils.json.JsonUtils;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "log")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private Date date;

    @Column
    private String thread;

    @Column
    private String login;

    @Column(columnDefinition="text")
    private String body;

    @Transient
    public String getValueAtPath(LogPathEnum pathEnum) {
        return JsonUtils.getNodeValueAtPath(getBody(), pathEnum.getValue());
    }

}