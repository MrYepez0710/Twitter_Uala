package com.uala.twitter.db;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Follow  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private Integer userId;

    @NonNull
    private Integer followingId;

    @NonNull
    private String followingNickname;

    @NonNull
    private boolean state;
}
