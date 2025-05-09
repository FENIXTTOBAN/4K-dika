package upeu.sistemas.practicaspp_ms.Entities;

import jakarta.persistence.*;

@Entity
public class Evidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
