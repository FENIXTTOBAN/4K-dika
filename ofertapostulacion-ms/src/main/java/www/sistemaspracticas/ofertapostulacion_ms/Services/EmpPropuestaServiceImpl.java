package www.sistemaspracticas.ofertapostulacion_ms.Services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import www.sistemaspracticas.ofertapostulacion_ms.Entities.Empresa;
import www.sistemaspracticas.ofertapostulacion_ms.Entities.EmpresaPropuesta;
import www.sistemaspracticas.ofertapostulacion_ms.Feign.*;
import www.sistemaspracticas.ofertapostulacion_ms.Repository.EmpPropuestaRepository;
import www.sistemaspracticas.ofertapostulacion_ms.Repository.EmpresaRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class EmpPropuestaServiceImpl implements EmpPropuestaService {

    private final EmpPropuestaRepository empPropuestaRepository;
    private final EmpresaRepository empresaRepository;
    private final PracticaFeign practicaFeign;
    private final PersonaFeign personaFeign;

    public EmpPropuestaServiceImpl(EmpPropuestaRepository empPropuestaRepository, EmpresaRepository empresaRepository, PracticaFeign practicaFeign, PersonaFeign personaFeign) {
        this.empPropuestaRepository = empPropuestaRepository;
        this.empresaRepository = empresaRepository;
        this.practicaFeign = practicaFeign;
        this.personaFeign = personaFeign;
    }

    @Override
    public EmpresaPropuesta aprobarPropuesta(Long idPropuesta, Empresa datosEmpresa, Long idPracticador) {
        Optional<EmpresaPropuesta> optional = empPropuestaRepository.findById(idPropuesta);
        if (optional.isEmpty()) {
            throw new RuntimeException("Propuesta no encontrada");
        }

        EmpresaPropuesta propuesta = optional.get();

        if (Boolean.TRUE.equals(propuesta.getAprobada())){
            throw new RuntimeException("La propuesta fue aprobada");
        }

        // Validar quien aprueba es PRACTICADOR
        Persona persona = personaFeign.obtenerPorId(idPracticador);
        if (persona.getTipoPersona() != TipoPersona.PRACTICADOR) {
            throw new RuntimeException("No autorizado: solo PRACTICADOR puede aprobar propuestas");
        }

        // Registrar empresa
        datosEmpresa.setPersonaId(idPracticador);
        datosEmpresa.setEstado(true);
        Empresa nuevaEmpresa = empresaRepository.save(datosEmpresa);

        // Crear práctica
        Practica practica = new Practica();
        practica.setIdPersona(propuesta.getEstudianteId());
        practica.setIdEmpresa(nuevaEmpresa.getId());
        practica.setFechaInicio(propuesta.getFechaInicio());
        practica.setEstado(EstadoPractica.PROCESO);
        practicaFeign.registrar(practica);

        // Marcar propuesta como aprobada
        propuesta.setAprobada(true);
        propuesta.setAprobadaPor(idPracticador);
        return empPropuestaRepository.save(propuesta);
    }

    @Override
    public EmpresaPropuesta registrarPropuesta(EmpresaPropuesta propuesta) {
        propuesta.setAprobada(false);
        propuesta.setFechaPropuesta(LocalDate.now());
        return empPropuestaRepository.save(propuesta);
    }
}
